import { Player } from './player';

export interface Battle {
  leftPlayer: Player;
  rightPlayer: Player;
  xSize: number;
  ySize: number;
}
