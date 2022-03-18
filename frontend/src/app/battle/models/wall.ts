import { Character } from './character';
import { WallCharacter } from './wall-character';

export interface Wall extends Character {
  characters: WallCharacter[];
}
