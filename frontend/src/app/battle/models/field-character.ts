import { Character } from './character';

export interface FieldCharacter extends Character {
  moveRange: number;
  xPosition: number;
  yPosition: number;
}

export function isFieldCharacter(character: any): character is FieldCharacter {
  return character.moveRange !== undefined;
}
