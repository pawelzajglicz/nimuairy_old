import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { of, Subject, tap } from 'rxjs';
import { webSocket, WebSocketSubject } from 'rxjs/webSocket';

import { environment } from 'src/environments/environment';
import { Conversation } from './models/conversation';
import { Message } from './models/message';
import { NewMessage } from './models/new-message';
import { Ticket } from './models/ticket';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private baseUrl = environment.apiUrl + 'chat/';
  private conversationsCache: Map<number, Conversation> = new Map<number, Conversation>();
  private conversations: Set<Conversation> = new Set<Conversation>();
  private numberofMessagesToLoad = 10;

  private newMessageOnConversation = new Subject<number>();
  newMessageOnConversation$ = this.newMessageOnConversation.asObservable();
  private webSocket: WebSocketSubject<Message | NewMessage>;

  constructor(private http: HttpClient) { }

  startChat() {
    this.getChatTicket().subscribe((ticket: Ticket) => {

      this.webSocket = webSocket(`${environment.webSocketUrl}messaging?ticket=` + ticket.ticket)
      this.webSocket.subscribe((messageResponse: Message | NewMessage) => {
        const message = messageResponse as Message;
        if (this.conversationsCache.has(message.conversationId)) {
          this.conversationsCache.get(message.conversationId).messages.push(message);
          this.newMessageOnConversation.next(message.conversationId);
        }
      });
    });
  }

  getChatTicket() {
    return this.http.get<Ticket>(this.baseUrl + 'tickets');
  }

  loadConversationWithUser(interlocutorId: number) {
    if (this.conversationsCache.has(interlocutorId)) {
      return of(this.conversationsCache.get(interlocutorId));
    } else {
      return this.http.get<Conversation>(this.baseUrl + `conversations?interlocutorId=${interlocutorId}`)
        .pipe(tap(conversation => {
          this.conversationsCache.set(conversation.conversationId, conversation)
          this.conversations.add(conversation)
        }));
    }
  }

  loadConversationMoreMessages(conversationId: number) {
    const firstLoadedMessageId = this.conversationsCache.get(conversationId).messages[0].orderNumber;

    return this.http.get<Message[]>(this.baseUrl + `conversations/${conversationId}` +
      `/messages?from=${Math.max(firstLoadedMessageId - this.numberofMessagesToLoad, 1)}` +
      `&to=${firstLoadedMessageId - 1}`
    )
      .pipe(
        tap(messages => {
          const conversation = this.conversationsCache.get(conversationId);
          conversation.messages.unshift(...messages.sort((message1, message2) => message1.orderNumber - message2.orderNumber));
          this.newMessageOnConversation.next(conversationId);
        }));
  }

  sendMessage(conversation: Conversation, message: string, senderId: number) {
    this.webSocket.next({content: message, conversationId: conversation.conversationId, senderId});
  }

  endChat() {
    this.webSocket.complete();
    this.webSocket = null;
  }
}
