import { HealthState } from './health-state.enum';
import { PlayerSide } from '../battle/enums/player-side';

export interface Character {
  currentHealth: number;
  healthState: HealthState;
  maxHealth: number;
  side: PlayerSide
}
