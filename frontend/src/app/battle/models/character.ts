import { HealthState } from './health-state.enum';

export interface Character {
  currentHealth: number;
  healthState: HealthState;
  maxHealth: number;
}
