import { ChangeDetectionStrategy, Component, Input, OnInit } from '@angular/core';
import { Conversation } from '../models/conversation';

@Component({
  selector: 'nim-conversation-window',
  templateUrl: './conversation-window.component.html',
  styleUrls: ['./conversation-window.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ConversationWindowComponent {

  @Input() conversation: Conversation;

  constructor() { }

}
