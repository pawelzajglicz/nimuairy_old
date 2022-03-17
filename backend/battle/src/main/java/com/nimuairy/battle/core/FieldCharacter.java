package com.nimuairy.battle.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class FieldCharacter extends Character {

	private int moveRange;
	private int xPosition;
	private int yPosition;

	public FieldCharacter(int healthPoints, int moveRange, int xPosition, int yPosition) {
		super(healthPoints);
		this.moveRange = moveRange;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
}
