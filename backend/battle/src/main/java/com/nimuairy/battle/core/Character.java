package com.nimuairy.battle.core;

import com.nimuairy.battle.core.exceptions.PlayerSide;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Character {

	private int currentHealth;
	private int maxHealth;
	private HealthState healthState;
	private PlayerSide side;

	public Character(int healthPoints, PlayerSide side) {
		currentHealth = healthPoints;
		maxHealth = healthPoints;
		manageHealthState();
		this.side = side;
	}

	public void receiveDamage(int damage) {
		currentHealth = Math.max(currentHealth - damage, 0);
		manageHealthState();
	}

	public void restoreHealth(int healpoints) {
		currentHealth = Math.max(currentHealth + healpoints, maxHealth);
		manageHealthState();
	}

	private void manageHealthState() {
		if (currentHealth == 0) {
			healthState = HealthState.DEAD;
		} else if (currentHealth == maxHealth) {
			healthState = HealthState.HEALTHY;
		} else {
			healthState = HealthState.INJURED;
		}
	}

}
