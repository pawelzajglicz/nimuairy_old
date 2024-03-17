import { ChangeDetectionStrategy, ChangeDetectorRef, Component, HostListener, Input, OnInit } from '@angular/core';
import { CharacterActionState } from 'src/app/battle/models/character';
import { Orb } from 'src/app/battle/models/orb';
import { BattleService } from '../../battle.service';
import { PlayerSide } from '../../enums/player-side';

@Component({
  selector: 'nim-orb-spot',
  templateUrl: './orb-spot.component.html',
  styleUrls: ['./orb-spot.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class OrbSpotComponent implements OnInit {

  @Input() orb: Orb;

  @HostListener('click', ['$event.target'])
  public onClick(): void {
    if (this.orb == null) {
      return;
    }
    this.battleService.handleOrbClicked(this.orb);
  }

  constructor(private battleService: BattleService,
    private changeDetector: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.orb.state = CharacterActionState.NONE;
    this.battleService.characterDead$.subscribe(character => {
      if (character === this.orb) {
        const orbSide = this.orb.side;
        this.orb = null;
        this.changeDetector.markForCheck();
        setTimeout(() => this.battleService.announcePlayerWin(orbSide === PlayerSide.LEFT ? PlayerSide.RIGHT : PlayerSide.LEFT));
      }
    });
    this.changeDetector.markForCheck();
  }
}
