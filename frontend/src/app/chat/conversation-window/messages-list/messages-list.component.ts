import { AfterViewInit, ChangeDetectionStrategy, Component, ElementRef, Input, HostListener, Output, EventEmitter, ChangeDetectorRef, OnInit } from '@angular/core';
import { filter } from 'rxjs';

import { ChatService } from '../../chat.service';
import { Conversation } from '../../models/conversation';
import { Message } from '../../models/message';

@Component({
  selector: 'nim-messages-list',
  templateUrl: './messages-list.component.html',
  styleUrls: ['./messages-list.component.scss'],
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class MessagesListComponent implements OnInit, AfterViewInit {

  @Input() conversation: Conversation;
  @Output() loadMoreMessages: EventEmitter<void> = new EventEmitter();

  loadingTopDistanceMargin = 40; // px

  constructor(public elementRef: ElementRef, private changeDetectorRef: ChangeDetectorRef, private chatService: ChatService) { }

  ngOnInit() {

    this.chatService.newMessageOnConversation$
    .pipe(
      filter(conversationId => conversationId === this.conversation.conversationId)
    )
    .subscribe(() => {
      const scrollHeight = this.elementRef.nativeElement.scrollHeight;
      this.changeDetectorRef.markForCheck();
      if (this.isScrollAtBottom()) {
        setTimeout(() =>
          this.elementRef.nativeElement.scrollTop = this.elementRef.nativeElement.scrollTop + this.elementRef.nativeElement.scrollHeight - scrollHeight
        );
      }
    });
  }

  ngAfterViewInit() {
    this.elementRef.nativeElement.scrollTop = this.elementRef.nativeElement.scrollHeight;
    if (this.shouldLoadMoreMessages()) {
      this.loadMoreMessages.emit();
    }
  }

  @HostListener('scroll')
  onScroll() {
    if (this.shouldLoadMoreMessages()) {
      this.loadMoreMessages.emit();
    }
  }

  trackByMessageId(index: number, message: Message){
     return message.id;
  }

  private shouldLoadMoreMessages() {
    return (this.elementRef.nativeElement.scrollTop <= this.loadingTopDistanceMargin) && (this.conversation.messages.length < this.conversation.totalMessages);
  }

  private isScrollAtBottom() {
    return this.elementRef.nativeElement.scrollHeight === this.elementRef.nativeElement.offsetHeight + this.elementRef.nativeElement.scrollTop;
  }
}
