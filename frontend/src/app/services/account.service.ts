import {HttpClient, HttpHeaders} from '@angular/common/http'
import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {ReplaySubject} from 'rxjs';
import {map} from 'rxjs/operators'
import {environment} from 'src/environments/environment';
import {User} from '../model/user';
import {NotificationService} from '../notification-module/notification.service';
import {TokenStorageService} from '../services/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  private currentUserSource = new ReplaySubject<User>(1);
  currentUser$ = this.currentUserSource.asObservable();
  isUserLoggedIn$ = this.currentUserSource.asObservable().pipe(map(user => !!user));
  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient,
              private notificationService: NotificationService,
              private router: Router,
              private tokenStorageService: TokenStorageService) { }

  login(model: any) {
    return this.http.post<User>(environment.apiUrl + 'auth/signin', model).subscribe({
        next: (user: User) => {
          this.tokenStorageService.saveToken(JSON.stringify(user.token));
          this.tokenStorageService.saveRefreshToken(JSON.stringify(user.refreshToken));
          this.tokenStorageService.saveUser(JSON.stringify(user));
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
