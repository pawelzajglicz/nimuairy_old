import { ChangeDetectionStrategy, ChangeDetectorRef, Component, HostListener, Input, OnInit } from '@angular/core';
import { Wall } from 'src/app/battle/models/wall';
import { BattleService } from '../../battle.service';
import { WallCharacterSpotComponent } from './wall-character-spot/wall-character-spot.component';

@Component({
  selector: 'nim-wall-spot',
  templateUrl: './wall-spot.component.html',
  styleUrls: ['./wall-spot.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class WallSpotComponent implements OnInit{

  @Input() wall: Wall;

  characterSpots: WallCharacterSpotComponent[];

  @HostListener('click', ['$event.target'])
  public onClick(): void {
    if (this.wall == null) {
      return;
    }
    this.battleService.handleWallClicked(this.wall);
  }

  constructor(private battleService: BattleService,
              private changeDetector: ChangeDetectorRef) { }

  ngOnInit(): void {
    this.battleService.characterDead$.subscribe(character => {
      if (character === this.wall) {
        this.wall = null;
        this.changeDetector.markForCheck();
      }
    });
    this.changeDetector.markForCheck();
  }

}
