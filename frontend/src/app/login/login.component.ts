import {Component} from '@angular/core';
import {AccountService} from '../services/account.service';

@Component({
  selector: 'nim-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss',
              '../styles/form-block.scss']
})
export class LoginComponent {

  model: {username: string, password: string} = {username: '', password: ''}

  constructor(public accountService: AccountService) { }

  login() {
    this.accountService.login(this.model);
  }
}
