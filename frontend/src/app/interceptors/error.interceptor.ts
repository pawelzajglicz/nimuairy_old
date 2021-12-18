import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpStatusCode
} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {NavigationExtras, Router} from '@angular/router';
import {catchError, Observable, throwError} from 'rxjs';
import {NotificationService} from '../notification-module/notification.service';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {

  constructor(private notificationService: NotificationService,
              private router: Router) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(
      catchError(error => {
        console.log(error);
        if (error) {
          switch (error.status) {
            case HttpStatusCode.BadRequest:
              this.notificationService.error(error.error.message);
              break;
            case HttpStatusCode.NotFound:
              this.router.navigateByUrl('/not-found');
              break;
            case HttpStatusCode.InternalServerError:
              const navigationExtras: NavigationExtras = {state: {error: error.error}};
              this.router.navigateByUrl('/server-error', navigationExtras);
              break;
            default:
              this.notificationService.error('Something unexpected went wrong');
              console.log(error);
              break;
          }
        }

        return throwError(() => error);
      })
    );
  }
}
