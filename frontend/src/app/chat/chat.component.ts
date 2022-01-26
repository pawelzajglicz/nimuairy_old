import { ChangeDetectionStrategy, Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { filter, takeUntil } from 'rxjs/operators';
import { User } from '../model/user';
import { AccountService } from '../services/account.service';
import { ContactsService } from '../social-network/contacts.service';
import { ChatService } from './chat.service';

@Component({
  selector: 'nim-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ChatComponent implements OnInit, OnDestroy {


  contacts: User[] = [];
  isContactsListVisible = false;
  private destroyNotifier = new Subject<void>()

  constructor(public accountService: AccountService,
    private chatService: ChatService,
    public contactsService: ContactsService) { }

  ngOnInit(): void {
    this.accountService.currentUser$.pipe(
        takeUntil(this.destroyNotifier),
        filter(Boolean))
      .subscribe((currentUser: User) => {
        console.log(currentUser, 'getUserContacts');
        this.contactsService.getUserContacts(currentUser.id).subscribe(contacts => this.contacts = contacts);
      })

  }

  ngOnDestroy() {
    this.destroyNotifier.next()
    this.destroyNotifier.complete()
  }

  showContactsList() {
    this.isContactsListVisible = true;
  }

  startConversation(event: any) {
    console.log('start conversation with: ', event);
    this.chatService.getChatTicket().subscribe((ticket: string) => {
      console.log({ticket})
    },
    err => console.log(err))
  }

  @HostListener('click', ['$event'])
  clickInside(event: any) {
    console.log("clicked inside", event);
    event.stopPropagation();
  }

  @HostListener('document:click')
  clickOutside() {
    console.log("clicked outside");
    this.isContactsListVisible = false;
  }

}
