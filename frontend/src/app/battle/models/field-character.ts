import { Character } from './character';

export interface FieldCharacter extends Character {
  attackPower: number;
  moveRange: number;
  xPosition: number;
  yPosition: number;
}
