import {Component} from '@angular/core';
import {AccountService} from '../services/account.service';

@Component({
  selector: 'nim-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.scss']
})
export class NavigationComponent  {

  constructor(public accountService: AccountService) { }

}
