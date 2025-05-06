package proyectoFinal;

import java.util.ArrayList;
import java.util.Random;

public class Battle implements Variables{
	private ArrayList<MilitaryUnit>[] planetArmy;
	private ArrayList<MilitaryUnit>[] enemyArmy;
	private ArrayList<MilitaryUnit>[][] armies;
	private String battleDevelopment;
	private int [][] initialCostFleet;
	private int initialNumberUnitsPlanet, initialNumberUnitsEnemy;
	private int currentNumberUnitsPlanet, currentNumberUnitsEnemy;
	private int[] wasteMetalDeuterium;
	private int[] enemyDrops, planetDrops;
	private int[][] resourcesLosses;
	public Battle(ArrayList<MilitaryUnit>[] planetArmy, ArrayList<MilitaryUnit>[] enemyArmy) {
		super();
		this.planetArmy = planetArmy;
		this.enemyArmy = enemyArmy;
		this.battleDevelopment = "";
		this.wasteMetalDeuterium = new int[2];
	}
	
	
	
	public void performBattle() {
		initInitialArmies();
		
		int atkArmy = (int) (2*Math.random());
		int defArmy = atkArmy+1%2;
		while ((currentNumberUnitsPlanet/initialNumberUnitsPlanet >= PERCENTAGE_UNITS_BATTLE_END_THRESHOLD &&
			currentNumberUnitsEnemy/initialNumberUnitsEnemy >= PERCENTAGE_UNITS_BATTLE_END_THRESHOLD)
			|| currentNumberUnitsPlanet == 0 
			|| currentNumberUnitsEnemy == 0) {
			performTurn(atkArmy, defArmy);
			defArmy = atkArmy;
			atkArmy = defArmy+1%2;
		}
	}
	
	private void performTurn(int atkArmy, int defArmy) {
		int atkGroup = selectRandomAttackGroup(atkArmy);
		int atkUnit = (int) (armies[atkArmy][atkGroup].size()*Math.random());
		do {
			int defGroup = selectRandomDefenseGroup(defArmy);
			int defUnit = (int) (armies[defArmy][defGroup].size()*Math.random());
			
			int damage = armies[atkArmy][atkGroup].get(atkUnit).attack();
			armies[defArmy][defGroup].get(defUnit).takeDamage(damage);
			
			if (armies[defArmy][defGroup].get(defUnit).getCurrentArmor() <= 0) {
				generateWaste(defGroup);
				armies[defArmy][defGroup].remove(defUnit);
				if (defArmy == 0) {
					currentNumberUnitsPlanet -= 1;
				} else {
					currentNumberUnitsEnemy -= 1;
				}
			}
		} while(doesUnitAttackAgain(atkGroup));
	}

	private void initInitialArmies() {
		this.armies = new ArrayList[2][7];
		this.initialNumberUnitsPlanet = 0;
		this.initialNumberUnitsEnemy = 0;
		for (int i = 0; i < planetArmy.length; i++) {
			armies[0][i] = planetArmy[i];
			initialNumberUnitsPlanet += planetArmy[i].size();
		}
		for (int i = 0; i < enemyArmy.length; i++) {
			armies[1][i] = enemyArmy[i];
			initialNumberUnitsEnemy += enemyArmy[i].size();
		}
		this.currentNumberUnitsPlanet = initialNumberUnitsPlanet;
		this.currentNumberUnitsEnemy = initialNumberUnitsEnemy;
	}
	
	private int selectRandomAttackGroup(int atkArmy) {
		int[] chanceArray;
		if (atkArmy == 0) {
			chanceArray = CHANCE_ATTACK_PLANET_UNITS;
		} else {
			chanceArray = CHANCE_ATTACK_ENEMY_UNITS;
		}
		return selectGroup(chanceArray);
	}
	
	private int selectRandomDefenseGroup(int defArmy) {
		ArrayList<MilitaryUnit>[] army = armies[defArmy];
		
		int[] chanceArray = new int[army.length];
		for (int i = 0; i < army.length; i++) {
			chanceArray[i] = army[i].size();
		}
		
		return selectGroup(chanceArray);
	}
	
	private int selectGroup(int[] chanceArray) {
		int totalSum = 0;
		for (int i : chanceArray) {
			totalSum += i;
		}
		int randomNum = (int) (totalSum*Math.random());
		for (int i = 0; i < chanceArray.length; i++) {
			if (randomNum < chanceArray[i]) {
				return i;
			}
		}
		return -1;
	}
	
	private void generateWaste(int defGroup) {
		int chance = 0;
		int metalWaste = 0;
		int deuteriumWaste = 0;
		switch (defGroup) {
		case 0:
			chance = CHANCE_GENERATNG_WASTE_LIGTHHUNTER;
			metalWaste = METAL_COST_LIGTHHUNTER * PERCENTATGE_WASTE;
			deuteriumWaste = DEUTERIUM_COST_LIGTHHUNTER * PERCENTATGE_WASTE;
			break;
		case 1:
			chance = CHANCE_GENERATNG_WASTE_HEAVYHUNTER;
			metalWaste = METAL_COST_HEAVYHUNTER * PERCENTATGE_WASTE;
			deuteriumWaste = DEUTERIUM_COST_HEAVYHUNTER * PERCENTATGE_WASTE;
			break;
		case 2:
			chance = CHANCE_GENERATNG_WASTE_BATTLESHIP;
			metalWaste = METAL_COST_BATTLESHIP * PERCENTATGE_WASTE;
			deuteriumWaste = DEUTERIUM_COST_BATTLESHIP * PERCENTATGE_WASTE;
			break;
		case 3:
			chance = CHANCE_GENERATNG_WASTE_ARMOREDSHIP;
			metalWaste = METAL_COST_ARMOREDSHIP * PERCENTATGE_WASTE;
			deuteriumWaste = DEUTERIUM_COST_ARMOREDSHIP * PERCENTATGE_WASTE;
			break;
		case 4:
			chance = CHANCE_GENERATNG_WASTE_MISSILELAUNCHER;
			metalWaste = METAL_COST_MISSILELAUNCHER * PERCENTATGE_WASTE;
			deuteriumWaste = DEUTERIUM_COST_MISSILELAUNCHER * PERCENTATGE_WASTE;
			break;
		case 5:
			chance = CHANCE_GENERATNG_WASTE_IONCANNON;
			metalWaste = METAL_COST_IONCANNON * PERCENTATGE_WASTE;
			deuteriumWaste = DEUTERIUM_COST_IONCANNON * PERCENTATGE_WASTE;
			break;
		case 6:
			chance = CHANCE_GENERATNG_WASTE_PLASMACANNON;
			metalWaste = METAL_COST_PLASMACANNON * PERCENTATGE_WASTE;
			deuteriumWaste = DEUTERIUM_COST_PLASMACANNON * PERCENTATGE_WASTE;
			break;
		}
		int randomNum = (int) (100*Math.random());
		if (randomNum < chance) {
			wasteMetalDeuterium[0] += metalWaste;
			wasteMetalDeuterium[1] += deuteriumWaste;
		}
	}
	
	private boolean doesUnitAttackAgain(int atkGroup) {
		int chance = 0;
		switch (atkGroup) {
		case 0:
			chance = CHANCE_ATTACK_AGAIN_LIGTHHUNTER;
			break;
		case 1:
			chance = CHANCE_ATTACK_AGAIN_HEAVYHUNTER;
			break;
		case 2:
			chance = CHANCE_ATTACK_AGAIN_BATTLESHIP;
			break;
		case 3:
			chance = CHANCE_ATTACK_AGAIN_ARMOREDSHIP;
			break;
		case 4:
			chance = CHANCE_ATTACK_AGAIN_MISSILELAUNCHER;
			break;
		case 5:
			chance = CHANCE_ATTACK_AGAIN_IONCANNON;
			break;
		case 6:
			chance = CHANCE_ATTACK_AGAIN_PLASMACANNON;
			break;
		}
		int randomNum = (int) (100*Math.random());
		if (randomNum < chance) {
			return true;
		}
		return false;
	}
}