package com.nimuairy.battle.core;

import lombok.Data;

@Data
public class HumanPlayer extends Player {

	private Long userId;

	public HumanPlayer(String name) {
		super(name);
	}
}
