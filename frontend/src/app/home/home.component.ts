import {AfterViewInit, Component} from '@angular/core';

import { webSocket } from "rxjs/webSocket";
@Component({
  selector: 'nim-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements AfterViewInit {

  constructor() { }
  ngAfterViewInit(): void {


    const subject = webSocket('ws://localhost:8080/api/messaging');

    subject.subscribe(
       msg => console.log('message received: ', msg), // Called whenever there is a message from the server.
       err => console.log(err), // Called if at any point WebSocket API signals some kind of error.
       () => console.log('complete') // Called when connection is closed (for whatever reason).
     );

     setTimeout(() => {
      subject.next({message: 'some message'});
      console.log('sended')
     }, 5000)
  }

}
