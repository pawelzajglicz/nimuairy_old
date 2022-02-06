import { ChangeDetectionStrategy, ChangeDetectorRef, Component, Input, OnInit, ViewChild } from '@angular/core';
import { exhaustMap, Subject } from 'rxjs';
import { MessagesListComponent } from './messages-list/messages-list.component';
import { ChatService } from '../chat.service';
import { Conversation } from '../models/conversation';

@Component({
  selector: 'nim-conversation-window',
  templateUrl: './conversation-window.component.html',
  styleUrls: ['./conversation-window.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ConversationWindowComponent implements OnInit {

  @Input() conversation: Conversation;
  @ViewChild(MessagesListComponent) messagesList: MessagesListComponent;
  loadMoreMessages: Subject<void> = new Subject<void>();

  constructor(private changeDetectorRef: ChangeDetectorRef,
    private chatService: ChatService) { }

  ngOnInit(): void {
    this.loadMoreMessages
      .pipe(
        exhaustMap(() => this.chatService.loadConversationMoreMessages(this.conversation.conversationId))
      )
      .subscribe(() => this.changeDetectorRef.markForCheck())
  }
}
