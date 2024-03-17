package com.nimuairy.battle.core;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public abstract class Player {

	private String name;
	private Orb orb;
	private Wall wall;
	private Set<FieldCharacter> fieldCharacters;

	Player() {

	}

	Player(String name) {
		this.name = name;
	}

//	public List<WallCharacter> getWallCharacters() {
//		return wall.getHero();
//	}
}
