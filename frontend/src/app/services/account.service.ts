import {HttpClient, HttpHeaders} from '@angular/common/http'
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {BehaviorSubject} from 'rxjs';
import {map, tap} from 'rxjs/operators'

import {environment} from 'src/environments/environment';
import { ChatService } from '../chat/chat.service';
import {User} from '../model/user';
import {NotificationService} from '../notification-module/notification.service';
import {TokenStorageService} from '../services/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private currentUserSource = new BehaviorSubject<User>(null);
  currentUser$ = this.currentUserSource.asObservable();
  isUserLoggedIn$ = this.currentUserSource.asObservable().pipe(map(user => !!user && Object.keys(user).length > 0));
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private chatService: ChatService,
              private http: HttpClient,
              private notificationService: NotificationService,
              private router: Router,
              private tokenStorageService: TokenStorageService) {

                const savedUser = this.tokenStorageService.getUser();
                if (!!savedUser) {
                  this.currentUserSource.next(savedUser);
                }
              }

  getCurrentUser() {
    return this.currentUserSource.value;
  }

  login(model: any) {
    return this.http.post<User>(environment.apiUrl + 'auth/signin', model)
    .subscribe({
        next: (user: User) => {
          this.tokenStorageService.saveToken(user.token);
          this.tokenStorageService.saveRefreshToken(user.refreshToken);
          this.tokenStorageService.saveUser(user);
          this.currentUserSource.next(user);
          this.router.navigateByUrl('/home');
        },
        error: error => {
          console.log(error);
        }
    });
  }

  logout() {
    this.tokenStorageService.signOut();
    this.currentUserSource.next(null);
    this.chatService.endChat();
  }

  refreshToken(token: string) {
    return this.http.post(environment.apiUrl + 'auth/refreshtoken', {
      refreshToken: token
    }, this.httpOptions);
  }

  register(model: any) {
    return this.http.post<User>(environment.apiUrl + 'auth/signup', model)
      .subscribe((user: User) => this.notificationService.success(`User ${user.username} successfully registered!`));
  }

  setCurrentUser(user: User) {
    this.currentUserSource.next(user);
  }
}
