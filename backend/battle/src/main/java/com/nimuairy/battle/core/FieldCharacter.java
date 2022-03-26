package com.nimuairy.battle.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nimuairy.battle.core.exceptions.PlayerSide;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class FieldCharacter extends Character {

	private int moveRange;

	@JsonProperty("xPosition")
	private int xPosition;

	@JsonProperty("yPosition")
	private int yPosition;

	public FieldCharacter(int healthPoints, int moveRange, int xPosition, int yPosition, PlayerSide side) {
		super(healthPoints, side);
		this.moveRange = moveRange;
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
}
