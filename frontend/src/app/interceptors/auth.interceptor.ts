import {HttpEvent, HttpErrorResponse, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {BehaviorSubject, catchError, filter, Observable, switchMap, take, throwError } from 'rxjs';
import {AccountService} from '../services/account.service';
import {TokenStorageService} from '../services/token-storage.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {


  private isRefreshing = false;
  private refreshTokenSubject = new BehaviorSubject(null);
  private TOKEN_HEADER_KEY = 'Authorization';


  constructor(private accountService: AccountService,
              private tokenStorageService: TokenStorageService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<Object>> {
    let authReq = req;
    const token = this.tokenStorageService.getToken();
    if (token != null) {
      authReq = this.addTokenHeader(req, token);
    }

    return next.handle(authReq).pipe(catchError(error => {
      if (error instanceof HttpErrorResponse && !authReq.url.includes('auth/signin') && error.status === 401) {
        return this.handle401Error(authReq, next);
      }

      return throwError(() => error);
    }));
  }

  private handle401Error(request: HttpRequest<any>, next: HttpHandler) {
    if (!this.isRefreshing) {
      this.isRefreshing = true;
      this.refreshTokenSubject.next(null);

      const token = this.tokenStorageService.getRefreshToken();

      if (token && false)
        return this.accountService.refreshToken(token).pipe(
          switchMap((token: any) => {
            this.isRefreshing = false;

            this.tokenStorageService.saveToken(token.accessToken);
            this.refreshTokenSubject.next(token.accessToken);

            return next.handle(this.addTokenHeader(request, token.accessToken));
          }),
          catchError((error) => {
            this.isRefreshing = false;

            this.tokenStorageService.signOut();
            return throwError(() => error);
          })
        );
    }

    return this.refreshTokenSubject.pipe(
      filter(token => token !== null),
      take(1),
      switchMap((token) => next.handle(this.addTokenHeader(request, token)))
    );
  }

  private addTokenHeader(request: HttpRequest<any>, token: string) {
    return request.clone({ headers: request.headers.set(this.TOKEN_HEADER_KEY, 'Bearer ' + token) });
  }
}
