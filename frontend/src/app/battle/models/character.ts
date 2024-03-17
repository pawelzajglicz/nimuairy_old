import { HealthState } from './health-state.enum';
import { PlayerSide } from '../battle/enums/player-side';

export interface Character {
  attackPower: number;
  attackRange: number;
  currentHealth: number;
  healthState: HealthState;
  maxHealth: number;
  side: PlayerSide;
  state: CharacterActionState;
  movedInCurrentTurn?: boolean;
}

export enum CharacterActionState {
  NONE, MOVE, ATTACK
}
