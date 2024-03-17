package com.nimuairy.battle.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Battle {

	private Player leftPlayer;
	private Player rightPlayer;

	@JsonProperty("xSize")
	private int xSize;
	@JsonProperty("ySize")
	private int ySize;
}
