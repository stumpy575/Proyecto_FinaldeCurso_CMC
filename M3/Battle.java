package proyectoFinal;

import java.util.ArrayList;
import java.util.Random;

public class Battle implements Variables{
	private ArrayList<MilitaryUnit>[] planetArmy;
	private ArrayList<MilitaryUnit>[] enemyArmy;
	private ArrayList[][] armies;
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
	
	public void startBattle() {
		initInitialArmies();
		
		int firstAttacker = (int) (2*Math.random());
		int firstDefender = firstAttacker+1%2;
		
		int attackGroupIndex = selectAttackGroup(firstAttacker);
		int attackUnitIndex = (int) (armies[firstAttacker][attackGroupIndex].size()*Math.random());
		
		int defenseGroupIndex = selectDefenseGroup(firstDefender);
		int defenseUnitIndex = (int) (armies[firstDefender][defenseGroupIndex].size()*Math.random());
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
	
	private int selectAttackGroup(int attackingArmyIndex) {
		int[] chanceArray;
		if (attackingArmyIndex == 0) {
			chanceArray = CHANCE_ATTACK_PLANET_UNITS;
		} else {
			chanceArray = CHANCE_ATTACK_ENEMY_UNITS;
		}
		return selectGroup(chanceArray);
	}
	
	private int selectDefenseGroup(int defendingArmyIndex) {
		ArrayList[] army = armies[defendingArmyIndex];
		
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