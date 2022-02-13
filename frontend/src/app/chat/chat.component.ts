import { ChangeDetectionStrategy, ChangeDetectorRef, Component, HostListener, OnDestroy, OnInit } from '@angular/core';
import { Subject } from 'rxjs';
import { filter, takeUntil } from 'rxjs/operators';
import { User } from '../model/user';
import { AccountService } from '../services/account.service';
import { ContactsService } from '../social-network/contacts.service';
import { ChatService } from './chat.service';
import { Conversation } from './models/conversation';

@Component({
  selector: 'nim-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class ChatComponent implements OnInit, OnDestroy {

  contacts: User[] = [];
  conversations: Conversation[] = []
  isContactsListVisible = false;
  private destroyNotifier = new Subject<void>();

  constructor(public accountService: AccountService,
    private changeDetectorRef: ChangeDetectorRef,
    private chatService: ChatService,
    public contactsService: ContactsService) { }

  ngOnInit(): void {
    this.accountService.currentUser$.pipe(
      takeUntil(this.destroyNotifier),
      filter(Boolean))
      .subscribe((currentUser: User) =>
        this.contactsService.getUserContacts(currentUser.id).subscribe(contacts => this.contacts = contacts)
      );

    this.chatService.startChat();
  }

  ngOnDestroy() {
    this.destroyNotifier.next();
    this.destroyNotifier.complete();
  }

  @HostListener('click', ['$event'])
  clickInside(event: any) {
    event.stopPropagation();
  }

  @HostListener('document:click')
  clickOutside() {
    this.isContactsListVisible = false;
  }

  showContactsList() {
    this.isContactsListVisible = true;
  }

  startConversation(participiantId: number) {
    if (this.isConversationWithUserNotLoaded(participiantId)) {
      this.chatService.loadConversationWithUser(participiantId).subscribe((conversation: Conversation) => {
        this.conversations.push(conversation);
        this.changeDetectorRef.markForCheck();
      })
    }
  }

  private isConversationWithUserNotLoaded(participiantId: number): boolean {
    const conversationLoaded = this.conversations.find(c => c.participants.length === 2 &&
      c.participants.find(participiant => participiant.id === participiantId));
    return !conversationLoaded;
  }
}
