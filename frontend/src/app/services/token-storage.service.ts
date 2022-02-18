import {Injectable} from '@angular/core';
import { User } from '../model/user';
import {BrowserStorageService} from '../util/browser-storage.service';

const TOKEN_KEY = 'auth-token';
const REFRESHTOKEN_KEY = 'auth-refreshtoken';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor(private browserStorageService: BrowserStorageService) { }

  signOut(): void {
    this.browserStorageService.remove(TOKEN_KEY);
    this.browserStorageService.remove(REFRESHTOKEN_KEY);
    this.browserStorageService.remove(USER_KEY);
  }

  public saveToken(token: string): void {
    this.browserStorageService.set(TOKEN_KEY, token);

    const user = this.getUser();
    if (user.id) {
      this.saveUser({ ...user, accessToken: token });
    }
  }

  public getToken(): string | null {
    return this.browserStorageService.get(TOKEN_KEY);
  }

  public saveRefreshToken(token: string): void {
    this.browserStorageService.set(REFRESHTOKEN_KEY, token);
  }

  public getRefreshToken(): string | null {
    return this.browserStorageService.get(REFRESHTOKEN_KEY);
  }

  public saveUser(user: User): void {
    this.browserStorageService.set(USER_KEY, JSON.stringify(user));
  }

  public getUser(): any {
    const user = this.browserStorageService.get(USER_KEY);
    if (user) {
      return JSON.parse(user);
    }

    return {};
  }
}
