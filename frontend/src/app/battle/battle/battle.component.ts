import { Component, OnInit } from '@angular/core';
import { BattleService } from './battle.service';

@Component({
  selector: 'nim-battle',
  templateUrl: './battle.component.html',
  styleUrls: ['./battle.component.scss']
})
export class BattleComponent implements OnInit {

  constructor(private battleService: BattleService) { }

  ngOnInit() {
    this.battleService.getBasicBattle()
      .subscribe(battle => console.log(battle));
  }
}
