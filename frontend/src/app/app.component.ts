import {Component} from '@angular/core';
import { AccountService } from './services/account.service';

@Component({
  selector: 'nim-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  constructor(public accountService: AccountService) {
  }
}
