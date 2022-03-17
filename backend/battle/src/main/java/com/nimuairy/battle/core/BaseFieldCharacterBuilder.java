package com.nimuairy.battle.core;

public class BaseFieldCharacterBuilder {

	private int attackPower;
	private int healthPoints;
	private int moveRange;
	private int xPosition;
	private int yPosition;

	public BaseFieldCharacterBuilder setAttackPower(int attackPower) {
		this.attackPower = attackPower;
		return this;
	}

	public BaseFieldCharacterBuilder setHealthPoints(int healthPoints) {
		this.healthPoints = healthPoints;
		return this;
	}

	public BaseFieldCharacterBuilder setMoveRange(int moveRange) {
		this.moveRange = moveRange;
		return this;
	}

	public BaseFieldCharacterBuilder setXPosition(int xPosition) {
		this.xPosition = xPosition;
		return this;
	}

	public BaseFieldCharacterBuilder setYPosition(int yPosition) {
		this.yPosition = yPosition;
		return this;
	}

	public BaseFieldCharacter createBaseFieldCharacter() {
		return new BaseFieldCharacter(healthPoints, moveRange, xPosition, yPosition, attackPower);
	}
}