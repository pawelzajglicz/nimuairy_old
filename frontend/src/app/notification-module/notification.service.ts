import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  // TODO - angular notifier has been dropped because not reliable in compatibility with Angular releases

  constructor() { }

  default(message: string) {
    console.log('default', message)
    // this.notifier.notify('default', message);
  }

  error(message: string = 'An error occured.') {
    console.log('error', message)
    // this.notifier.notify('error', message);
  }

  info(message: string) {
    console.log('info', message)
    // this.notifier.notify('info', message);
  }

  success(message: string) {
    console.log('success', message)
    // this.notifier.notify('success', message);
  }

  warning(message: string) {
    console.log('warning', message)
    // this.notifier.notify('warning', message);
  }
}
