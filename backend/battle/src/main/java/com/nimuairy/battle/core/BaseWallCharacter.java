package com.nimuairy.battle.core;

import com.nimuairy.battle.core.exceptions.Attacking;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseWallCharacter extends WallCharacter implements Attacking {

	private int attackPower;

	public BaseWallCharacter(int healthPoints, int attackPower) {
		super(healthPoints);
		this.attackPower = attackPower;
	}

	@Override
	public int getAttackPower() {
		return attackPower;
	}
}