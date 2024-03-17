package com.nimuairy.battle.core;

import com.nimuairy.battle.core.exceptions.Attacking;
import com.nimuairy.battle.core.exceptions.PlayerSide;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseWallCharacter extends WallCharacter implements Attacking {

	private int attackPower;

	public BaseWallCharacter(int healthPoints, int attackPower, PlayerSide side) {
		super(healthPoints, side);
		this.attackPower = attackPower;
	}

	@Override
	public int getAttackPower() {
		return attackPower;
	}
}