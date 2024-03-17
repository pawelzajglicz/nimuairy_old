package com.nimuairy.battle.core;


import com.nimuairy.battle.core.exceptions.InvalidWallPosition;
import com.nimuairy.battle.core.exceptions.PlayerSide;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class Wall extends Character {

	private final int numberOfPositions;
	private final List<Hero> hero;

	public Wall(int healthPoints, int numberOfPositions, PlayerSide side) {
		super(healthPoints, side);
		this.numberOfPositions = numberOfPositions;
		hero = Arrays.asList(new Hero[4]);
	}

	public int getNumberOfPositions() {
		return numberOfPositions;
	}

	public void clearPosition(Integer position) {
		validatePosition(position);
		hero.set(position, null);
	}

	public void setCharacterAtPosition(WallCharacter character, Integer position) {
		validatePosition(position);
		removeCharacter(character);
//		hero.set(position, character);
	}

	public void removeCharacter(WallCharacter character) {
//		for (int i = 0; i < numberOfPositions; i++) {
//			if (hero.get(i) == character) {
//				clearPosition(i);
//				break;
//			}
//		}
	}

	private void validatePosition(Integer position) {
		if (position < 0 || position >= numberOfPositions) {
			throw new InvalidWallPosition();
		}
	}
}
