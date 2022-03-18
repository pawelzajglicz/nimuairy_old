import { FieldCharacter } from './field-character';
import { Orb } from './orb';
import { Wall } from './wall';


export interface Player {
  orb: Orb;
  wall: Wall;
  fieldCharacters: Set<FieldCharacter>;
}
