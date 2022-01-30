import { ChangeDetectionStrategy, Component, Input, OnInit } from '@angular/core';
import { Message } from '../../models/message';

@Component({
  selector: 'nim-messages-list',
  templateUrl: './messages-list.component.html',
  styleUrls: ['./messages-list.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class MessagesListComponent {


  @Input() messages: Message[];
  @Input() totalNumber: number;


  constructor() { }
}
