import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { of, tap } from 'rxjs';

import { environment } from 'src/environments/environment';
import { Conversation } from './models/conversation';
import { Message } from './models/message';
import { Ticket } from './models/ticket';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private baseUrl = environment.apiUrl + 'chat/';
  private conversationsCache: Map<number, Conversation> = new Map<number, Conversation>();
  private conversations: Set<Conversation> = new Set<Conversation>();
  private numberofMessagesToLoad = 10;

  constructor(private http: HttpClient) { }

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
    .pipe(tap(messages => {
      const conversation = this.conversationsCache.get(conversationId);
      conversation.messages.unshift(...messages.sort((message1, message2) => message1.orderNumber - message2.orderNumber));
    }));
  }
}
