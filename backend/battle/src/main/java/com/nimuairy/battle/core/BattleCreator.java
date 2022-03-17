package com.nimuairy.battle.core;

import java.util.HashSet;

public class BattleCreator {

	public static Battle createBattle(Player leftPlayer, Player rightPlayer) {
		Battle battle = new Battle();

		battle.setLeftPlayer(leftPlayer);
		battle.setRightPlayer(rightPlayer);

		giveLeftPlayerStarterPack(leftPlayer);
		giveRightPlayerStarterPack(rightPlayer);
		battle.setXSize(23);
		battle.setYSize(12);

		return battle;
	}

	private static void giveLeftPlayerStarterPack(Player player) {

		giveCommonCharacters(player);

		player.setFieldCharacters(new HashSet<>());
		addBaseFieldCharacter(player, 2, 3);
		addBaseFieldCharacter(player, 2, 5);
		addBaseFieldCharacter(player, 2, 7);
		addBaseFieldCharacter(player, 2, 9);
	}

	private static void giveCommonCharacters(Player player) {
		player.setOrb(new Orb(75));
		Wall wall = new Wall(1500, 4);
		player.setWall(wall);

		int wallCharacterAttack = 50;
		int wallCharacterHealth = 200;
		wall.setCharacterAtPosition(new BaseWallCharacter(wallCharacterHealth, wallCharacterAttack), 0);
		wall.setCharacterAtPosition(new BaseWallCharacter(wallCharacterHealth, wallCharacterAttack), 1);
		wall.setCharacterAtPosition(new BaseWallCharacter(wallCharacterHealth, wallCharacterAttack), 2);
		wall.setCharacterAtPosition(new BaseWallCharacter(wallCharacterHealth, wallCharacterAttack), 3);
	}

	private static boolean addBaseFieldCharacter(Player player, int xPosition, int yPosition) {
		int fieldCharacterAttack = 200;
		int fieldCharacterHealth = 500;
		int fieldMoveRange = 3;
		return player.getFieldCharacters().add(new BaseFieldCharacterBuilder()
						.setAttackPower(fieldCharacterAttack)
						.setMoveRange(fieldMoveRange)
						.setXPosition(xPosition)
						.setYPosition(yPosition)
						.setHealthPoints(fieldCharacterHealth)
				.createBaseFieldCharacter());
	}

	private static void giveRightPlayerStarterPack(Player player) {

		giveCommonCharacters(player);

		player.setFieldCharacters(new HashSet<>());
		addBaseFieldCharacter(player, 22, 3);
		addBaseFieldCharacter(player, 22, 5);
		addBaseFieldCharacter(player, 22, 7);
		addBaseFieldCharacter(player, 22, 9);
	}
}
