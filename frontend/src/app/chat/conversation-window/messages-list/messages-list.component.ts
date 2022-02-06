import { AfterViewInit, ChangeDetectionStrategy, Component, ElementRef, Input, HostListener, Output, EventEmitter } from '@angular/core';
import { Message } from '../../models/message';

@Component({
  selector: 'nim-messages-list',
  templateUrl: './messages-list.component.html',
  styleUrls: ['./messages-list.component.scss'],
    changeDetection: ChangeDetectionStrategy.OnPush
})
export class MessagesListComponent implements AfterViewInit {

  @Input() messages: Message[];
  @Input() totalNumberOfMessages: number;
  @Output() loadMoreMessages: EventEmitter<void> = new EventEmitter();

  loadingTopDistanceMargin = 40; // px
  private scrollToBottomAtMoreMessages = false;

  constructor(public elementRef: ElementRef) { }

  ngAfterViewInit() {
    this.elementRef.nativeElement.scrollTop = this.elementRef.nativeElement.scrollHeight;
    if (this.shouldLoadMoreMessagesAtStart()) {
      this.loadMoreMessages.emit();
      this.scrollToBottomAtMoreMessages = true;
    }
  }

  @HostListener('scroll')
  onScroll() {
    if (this.shouldLoadMoreMessagesAtScroll()) {
      this.loadMoreMessages.emit();
    }
  }

  private shouldLoadMoreMessagesAtScroll() {
    return (this.elementRef.nativeElement.scrollTop <= this.loadingTopDistanceMargin) && (this.messages.length < this.totalNumberOfMessages);
  }

  private shouldLoadMoreMessagesAtStart() {
    return (this.elementRef.nativeElement.scrollHeight === this.elementRef.nativeElement.offsetHeight) && (this.messages.length !== this.totalNumberOfMessages);
  }
}
