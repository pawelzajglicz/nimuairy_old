import {ChangeDetectionStrategy, Component, EventEmitter, Input, Output} from '@angular/core';
import {User} from 'src/app/model/user';

@Component({
  selector: 'nim-contacts-list',
  templateUrl: './contacts-list.component.html',
  styleUrls: ['./contacts-list.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ContactsListComponent {


  @Input() contacts: User[];
  @Output() chosenToStartConversation: EventEmitter<number> = new EventEmitter();

  constructor() { }

  emitStartConversation(userId: number) {
    this.chosenToStartConversation.emit(userId);
  }
}
