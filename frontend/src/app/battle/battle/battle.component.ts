import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { BattleService } from './battle.service';
import { Battle } from '../models/battle';

@Component({
  selector: 'nim-battle',
  templateUrl: './battle.component.html',
  styleUrls: ['./battle.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class BattleComponent implements OnInit {

  constructor(private battleService: BattleService,
              private changeDetector: ChangeDetectorRef) { }

  battle: Battle;

  ngOnInit() {
    this.battleService.getBasicBattle()
      .subscribe(battle => {
        this.battle = battle;
        this.changeDetector.markForCheck();
      });
  }
}
