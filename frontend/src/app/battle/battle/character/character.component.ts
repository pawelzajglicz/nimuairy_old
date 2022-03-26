import { ChangeDetectionStrategy, Component, Input } from '@angular/core';
import { PlayerSide } from '../enums/player-side';
import { Character } from '../../models/character';

@Component({
  selector: 'nim-character',
  templateUrl: './character.component.html',
  styleUrls: ['./character.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CharacterComponent {

  @Input() character: Character;
  @Input() imageUrl: string;

  playerSide = PlayerSide;

  constructor() {}

}
