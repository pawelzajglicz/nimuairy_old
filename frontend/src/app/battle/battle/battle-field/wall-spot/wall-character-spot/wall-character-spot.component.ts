import { ChangeDetectionStrategy, Component, Input } from '@angular/core';
import { WallCharacter } from 'src/app/battle/models/wall-character';

@Component({
  selector: 'nim-wall-character-spot',
  templateUrl: './wall-character-spot.component.html',
  styleUrls: ['./wall-character-spot.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class WallCharacterSpotComponent {

  @Input() character: WallCharacter;

  constructor() { }

}
