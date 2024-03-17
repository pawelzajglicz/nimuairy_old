package com.nimuairy.battle.core;

import com.nimuairy.battle.core.exceptions.PlayerSide;

public abstract class WallCharacter extends Character {

	public WallCharacter(int healthPoints, PlayerSide side) {
		super(healthPoints, side);
	}
}
