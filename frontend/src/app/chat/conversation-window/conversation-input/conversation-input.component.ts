import { ChangeDetectionStrategy, Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'nim-conversation-input',
  templateUrl: './conversation-input.component.html',
  styleUrls: ['./conversation-input.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ConversationInputComponent {

  message = '';
  @Output() newMessage: EventEmitter<string> = new EventEmitter();

  constructor() { }

  commitMessage() {
    if (!!this.message) {
      this.newMessage.emit(this.message);
      this.message = '';
    }
  }
}
