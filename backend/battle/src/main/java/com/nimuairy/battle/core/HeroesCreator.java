package com.nimuairy.battle.core;

public class HeroesCreator {

	static int archerCharacterAttack = 50;
	static int archerCharacterHealth = 200;
	static int archerCharacterInitiative = 4;
	static int swordsmanCharacterAttack = 200;
	static int swordsmanCharacterHealth = 500;
	static int swordsmanCharacterInitiative = 3;

	public static Hero getArcher() {
		Hero archer = new Hero(archerCharacterHealth);
		archer.setAttack(archerCharacterAttack);
		archer.setFieldable(false);
		archer.setInitiative(archerCharacterInitiative);
		archer.setWallable(true);

		return archer;
	}

	public static Hero getSwordsman() {
		Hero swordsman = new Hero(swordsmanCharacterAttack);
		swordsman.setAttack(swordsmanCharacterHealth);
		swordsman.setFieldable(true);
		swordsman.setInitiative(swordsmanCharacterInitiative);
		swordsman.setWallable(false);

		return swordsman;
	}
}
