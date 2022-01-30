import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Ticket } from './models/ticket';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private baseUrl = environment.apiUrl + 'chat/';

  constructor(private http: HttpClient) { }

  getChatTicket() {
    return this.http.get<Ticket>(this.baseUrl + 'ticket');
  }

  loadConversationWithUser(interlocutorId: number) {
    return this.http.get<any>(this.baseUrl + `conversation?interlocutorId=${interlocutorId}`);
  }
}
