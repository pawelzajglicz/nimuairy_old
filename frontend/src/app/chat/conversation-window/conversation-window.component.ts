import { ChangeDetectionStrategy, ChangeDetectorRef, Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { exhaustMap, Subject } from 'rxjs';

import { AccountService } from 'src/app/services/account.service';
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
  @Output() closeEvent = new EventEmitter<void>();
  @ViewChild(MessagesListComponent) messagesList: MessagesListComponent;
  loadMoreMessages: Subject<void> = new Subject<void>();

  minimized = false;

  constructor(private accountService: AccountService,
              private changeDetectorRef: ChangeDetectorRef,
              private chatService: ChatService) { }

  ngOnInit(): void {
    this.loadMoreMessages
      .pipe(
        exhaustMap(() => this.chatService.loadConversationMoreMessages(this.conversation.conversationId))
      )
      .subscribe(() => this.changeDetectorRef.markForCheck());
  }

  onCloseEvent() {
      this.closeEvent.emit();
  }

  onMessage(message: string) {
    this.chatService.sendMessage(this.conversation, message, this.accountService.getCurrentUser().id);
  }
}
