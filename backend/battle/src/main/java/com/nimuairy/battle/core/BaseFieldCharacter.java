package com.nimuairy.battle.core;

import com.nimuairy.battle.core.exceptions.Attacking;
import com.nimuairy.battle.core.exceptions.PlayerSide;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseFieldCharacter extends FieldCharacter implements Attacking {

	private int attackPower;
	private int attackRange;

	public BaseFieldCharacter(int healthPoints, int moveRange, int xPosition, int yPosition, int attackPower, PlayerSide side) {
		super(healthPoints, moveRange, xPosition, yPosition, side);
		this.attackPower = attackPower;
		this.attackRange = 1;
	}

	@Override
	public int getAttackPower() {
		return attackPower;
	}
}
