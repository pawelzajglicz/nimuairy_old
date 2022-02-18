import { ChangeDetectionStrategy, Component, EventEmitter, HostListener, Input, Output } from '@angular/core';
import { User } from 'src/app/model/user';

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

  @HostListener('click', ['$event'])
  clickInside(event: any) {
    event.stopPropagation();
  }

  emitStartConversation(userId: number) {
    this.chosenToStartConversation.emit(userId);
  }
}
