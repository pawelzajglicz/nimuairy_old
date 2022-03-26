package com.nimuairy.battle.core;

import com.nimuairy.battle.core.exceptions.PlayerSide;

import java.util.HashSet;

public class BattleCreator {

	private static int basicXSize = 15;
	private static int basicYSize = 11;

	public static Battle createBattle(Player leftPlayer, Player rightPlayer) {
		Battle battle = new Battle();

		battle.setLeftPlayer(leftPlayer);
		battle.setRightPlayer(rightPlayer);

		giveLeftPlayerStarterPack(leftPlayer);
		giveRightPlayerStarterPack(rightPlayer);
		battle.setXSize(basicXSize);
		battle.setYSize(basicYSize);

		return battle;
	}

	private static void giveLeftPlayerStarterPack(Player player) {

		giveCommonCharacters(player, PlayerSide.LEFT);

		player.setFieldCharacters(new HashSet<>());
		addBaseFieldCharacter(player, 1, 2, PlayerSide.LEFT);
		addBaseFieldCharacter(player, 1, 4, PlayerSide.LEFT);
		addBaseFieldCharacter(player, 1, 6, PlayerSide.LEFT);
		addBaseFieldCharacter(player, 1, 8, PlayerSide.LEFT);
	}

	private static void giveCommonCharacters(Player player, PlayerSide side) {
		player.setOrb(new Orb(75, side));
		Wall wall = new Wall(1500, 4, side);
		player.setWall(wall);

		int wallCharacterAttack = 50;
		int wallCharacterHealth = 200;
		wall.setCharacterAtPosition(new BaseWallCharacter(wallCharacterHealth, wallCharacterAttack, side), 0);
		wall.setCharacterAtPosition(new BaseWallCharacter(wallCharacterHealth, wallCharacterAttack, side), 1);
		wall.setCharacterAtPosition(new BaseWallCharacter(wallCharacterHealth, wallCharacterAttack, side), 2);
		wall.setCharacterAtPosition(new BaseWallCharacter(wallCharacterHealth, wallCharacterAttack, side), 3);
	}

	private static boolean addBaseFieldCharacter(Player player, int xPosition, int yPosition, PlayerSide side) {
		int fieldCharacterAttack = 200;
		int fieldCharacterHealth = 500;
		int fieldMoveRange = 3;
		return player.getFieldCharacters().add(new BaseFieldCharacterBuilder()
						.setAttackPower(fieldCharacterAttack)
						.setMoveRange(fieldMoveRange)
						.setXPosition(xPosition)
						.setYPosition(yPosition)
						.setHealthPoints(fieldCharacterHealth)
						.setYPlayerSide(side)
				.createBaseFieldCharacter());
	}

	private static void giveRightPlayerStarterPack(Player player) {

		giveCommonCharacters(player, PlayerSide.RIGHT);

		player.setFieldCharacters(new HashSet<>());
		addBaseFieldCharacter(player, basicXSize - 2, 2, PlayerSide.RIGHT);
		addBaseFieldCharacter(player, basicXSize - 2, 4, PlayerSide.RIGHT);
		addBaseFieldCharacter(player, basicXSize - 2, 6, PlayerSide.RIGHT);
		addBaseFieldCharacter(player, basicXSize - 2, 8, PlayerSide.RIGHT);
	}
}
