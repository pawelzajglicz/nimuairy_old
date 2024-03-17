import { PlayerSide } from '../battle/enums/player-side';
import { FieldCharacter } from './field-character';
import { Orb } from './orb';
import { Wall } from './wall';


export interface Player {
  name: string;
  orb: Orb;
  wall: Wall;
  fieldCharacters: FieldCharacter[];
  side?: PlayerSide;
}
