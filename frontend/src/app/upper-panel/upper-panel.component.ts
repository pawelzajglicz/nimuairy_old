import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService } from '../services/account.service';

@Component({
  selector: 'nim-upper-panel',
  templateUrl: './upper-panel.component.html',
  styleUrls: ['./upper-panel.component.scss']
})
export class UpperPanelComponent {

  constructor(public accountService: AccountService,
              private router: Router) { }


  logout() {
    this.accountService.logout();
  }

  goToHome() {
    this.router.navigateByUrl('/home');
  }
}
