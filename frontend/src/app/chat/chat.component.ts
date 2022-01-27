import { ChangeDetectionStrategy, Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { filter, takeUntil } from 'rxjs/operators';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';
import { environment } from 'src/environments/environment';
import { User } from '../model/user';
import { AccountService } from '../services/account.service';
import { ContactsService } from '../social-network/contacts.service';
import { ChatService } from './chat.service';
import { Ticket } from './ticket';

@Component({
  selector: 'nim-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ChatComponent implements OnInit, OnDestroy {

  contacts: User[] = [];
  isContactsListVisible = false;
  private destroyNotifier = new Subject<void>();
  private webSocket: WebSocketSubject<void>;

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

      this.chatService.getChatTicket().subscribe((ticket: Ticket) =>
        this.webSocket = webSocket(`${environment.webSocketUrl}messaging?ticket=` + ticket.ticket));

  }

  ngOnDestroy() {
    this.destroyNotifier.next()
    this.destroyNotifier.complete()
  }

  showContactsList() {
    this.isContactsListVisible = true;
  }

  startConversation(interlocutorId: number) {
    console.log('start conversation with: ', interlocutorId);
    this.chatService.loadConversationWithUser(interlocutorId).subscribe(res => {
      console.log(res);
    })
   /* this.chatService.getChatTicket().subscribe((ticket: Ticket) => {
      console.log({ticket})

      const subject = webSocket('ws://localhost:8080/api/messaging?ticket=' + ticket.ticket);

      subject.subscribe(
         msg => console.log('message received: ', msg), // Called whenever there is a message from the server.
         err => console.log(err), // Called if at any point WebSocket API signals some kind of error.
         () => console.log('complete') // Called when connection is closed (for whatever reason).
       );

       setTimeout(() => {
        subject.next({message: 'some message'});
        console.log('sended')
       }, 5000)

    },
    err => console.log(err))*/
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
