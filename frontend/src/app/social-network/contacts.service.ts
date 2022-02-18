import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class ContactsService {

  private baseUrl = environment.apiUrl + 'contacts/'

  constructor(private http: HttpClient) { }

  getUserContacts(userId: number) {
    return this.http.get<User[]>(`${this.baseUrl}${userId}`);
  }

}
