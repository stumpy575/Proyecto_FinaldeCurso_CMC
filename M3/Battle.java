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
	private int[] wasteMetalDeuterium;
	private int[] enemyDrops, planetDrops;
	private int[][] resourcesLosses;
	private int[] currentNumberUnitsPlanet, currentNumberUnitsEnemy;
	public Battle(ArrayList<MilitaryUnit>[] planetArmy, ArrayList<MilitaryUnit>[] enemyArmy) {
		super();
		this.planetArmy = planetArmy;
		this.enemyArmy = enemyArmy;
		this.battleDevelopment = "";
	}
	
	public void performBattle() {
		initInitialArmies();
		
		int atkArmy = (int) (2*Math.random());
		int defArmy = atkArmy+1%2;
		while (true) {
			performRound(atkArmy, defArmy);
			defArmy = atkArmy;
			atkArmy = defArmy+1%2;
		}
	}
	
	private void performRound(int atkArmy, int defArmy) {
		int atkGroup = selectAttackGroup(atkArmy);
		int atkUnit = (int) (armies[atkArmy][atkGroup].size()*Math.random());
		
		int defGroup = selectDefenseGroup(defArmy);
		int defUnit = (int) (armies[defArmy][defGroup].size()*Math.random());
		
		int damage = armies[atkArmy][atkGroup].get(atkUnit).attack();
		armies[defArmy][defGroup].get(defUnit).takeDamage(damage);
	}
	
	private void initInitialArmies() {
		this.armies = new ArrayList[2][7];
		for (int i = 0; i < planetArmy.length; i++) {
			armies[0][i] = planetArmy[i];
		}
		for (int i = 0; i < enemyArmy.length; i++) {
			armies[1][i] = enemyArmy[i];
		}
	}
	
	private int selectAttackGroup(int atkArmy) {
		int[] chanceArray;
		if (atkArmy == 0) {
			chanceArray = CHANCE_ATTACK_PLANET_UNITS;
		} else {
			chanceArray = CHANCE_ATTACK_ENEMY_UNITS;
		}
		return selectGroup(chanceArray);
	}
	
	private int selectDefenseGroup(int defArmy) {
		ArrayList[] army = armies[defArmy];
		
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
}