import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private baseUrl = environment.apiUrl + 'chat/';

  constructor(private http: HttpClient) { }

  getChatTicket() {
    return this.http.get<string>(this.baseUrl + 'ticket');
  }
}
