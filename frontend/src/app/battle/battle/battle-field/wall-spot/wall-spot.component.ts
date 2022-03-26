import { ChangeDetectionStrategy, Component, Input } from '@angular/core';
import { Wall } from 'src/app/battle/models/wall';
import { WallCharacterSpotComponent } from './wall-character-spot/wall-character-spot.component';

@Component({
  selector: 'nim-wall-spot',
  templateUrl: './wall-spot.component.html',
  styleUrls: ['./wall-spot.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class WallSpotComponent {

  @Input() wall: Wall;

  characterSpots: WallCharacterSpotComponent[];

  constructor() { }

}
