import { ChangeDetectionStrategy, Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { User } from 'src/app/model/user';

@Component({
  selector: 'nim-contacts-list',
  templateUrl: './contacts-list.component.html',
  styleUrls: ['./contacts-list.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ContactsListComponent implements OnInit {


  @Input() contacts: User[];
  @Output() chosenToStartConversation: EventEmitter<number> = new EventEmitter();

  constructor() { }

  ngOnInit(): void {
   // setInterval(() => console.log(this.contacts), 3000);
  }

  emitStartConversation(userId: number) {
    console.log('start emitStartConversation with: ', userId);
    this.chosenToStartConversation.emit(userId);
  }

}
