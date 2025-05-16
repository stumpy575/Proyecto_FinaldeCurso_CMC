package proyectoFinal;

import java.util.ArrayList;
import java.util.Arrays;

public class Battle implements Variables{
	private ArrayList<MilitaryUnit>[] planetArmy;
	private ArrayList<MilitaryUnit>[] enemyArmy;
	private ArrayList<MilitaryUnit>[][] armies;
	
	private int[] initialNumberUnitsPlanet, initialNumberUnitsEnemy;
	private int initialSumUnitsPlanet, initialSumUnitsEnemy;
	private int [][] initialCostFleet;
	
	private int[] currentNumberUnitsPlanet, currentNumberUnitsEnemy;
	private int currentSumUnitsPlanet, currentSumUnitsEnemy;
	
	private int[] wasteMetalDeuterium;
	private int[] enemyDrops, planetDrops;
	private int[][] resourcesLosses;
	
	private int winningSide;
	
	private String battleStatistics;
	private String battleDevelopmentLog;
	
	public Battle(ArrayList<MilitaryUnit>[] planetArmy, ArrayList<MilitaryUnit>[] enemyArmy) {
		super();
		this.planetArmy = planetArmy;
		this.enemyArmy = enemyArmy;
		this.armies = new ArrayList[2][7];
		
		this.initialNumberUnitsPlanet = new int[7];
		this.initialNumberUnitsEnemy = new int[4];
		this.initialSumUnitsPlanet = 0;
		this.initialSumUnitsEnemy = 0;
		this.initialCostFleet = new int[2][2];
		
		this.wasteMetalDeuterium = new int[2];
		this.resourcesLosses = new int[2][3];
		this.planetDrops = new int[7];
		this.enemyDrops = new int[7];
		
		this.battleStatistics = "";
		this.battleDevelopmentLog = "";
	}
	
	public void performBattle() {
		initInitialArmies();
		
//		// TODO debug
//		for (int i = 0; i < armies[0].length; i++) {
//			System.out.println("Planet army+"+i+": "+armies[0][i].size());
//		}
//		for (int i = 0; i < armies[1].length; i++) {
//			if (armies[1][i] != null) {
//				System.out.println("Enemy army+"+i+": "+armies[1][i].size());
//			}
//		}
//		// TODO debug
		
		
		log("BATTLE START");
		int atkArmy = (int) (2*Math.random());
		int defArmy = (atkArmy+1)%2;
//		//TODO debug
//		System.out.println("atkArmy:"+atkArmy);
//		System.out.println("defArmy:"+defArmy);
//		// TODO debug
		while (!doesBattleEnd()) {
			performTurn(atkArmy, defArmy);
//			//TODO debug
//			System.out.println("atkArmy:"+atkArmy);
//			System.out.println("defArmy:"+defArmy);
//			// TODO debug
			defArmy = atkArmy;
			atkArmy = (defArmy+1)%2;
		}
		log("BATTLE END");
		
		winningSide = calculateWinner();
		generateBattleStatistics();
	}
	
	private void initInitialArmies() {
		for (int i = 0; i < planetArmy.length; i++) {
			armies[0][i] = planetArmy[i];
			initialNumberUnitsPlanet[i] = planetArmy[i].size();
			initialSumUnitsPlanet += planetArmy[i].size();
			initialCostFleet[0][0] = METAL_COST_UNITS[i]*planetArmy[i].size();
			initialCostFleet[0][1] = DEUTERIUM_COST_UNITS[i]*planetArmy[i].size();
		}
		for (int i = 0; i < enemyArmy.length; i++) {
			armies[1][i] = enemyArmy[i];
			initialNumberUnitsEnemy[i] = enemyArmy[i].size();
			initialSumUnitsEnemy += enemyArmy[i].size();
			initialCostFleet[1][0] = METAL_COST_UNITS[i]*planetArmy[i].size();
			initialCostFleet[1][1] = DEUTERIUM_COST_UNITS[i]*planetArmy[i].size();
		}
		for (int i = 4; i < armies[1].length; i++) {
			armies[1][i] = new ArrayList<MilitaryUnit>();
		}
		this.currentNumberUnitsPlanet = initialNumberUnitsPlanet.clone();
		this.currentNumberUnitsEnemy = initialNumberUnitsEnemy.clone();
		this.currentSumUnitsPlanet = initialSumUnitsPlanet;
		this.currentSumUnitsEnemy = initialSumUnitsEnemy;
	}
	
	private boolean doesBattleEnd() {
		if (initialSumUnitsPlanet == 0) {
			log("The planet is undefended");
			return true;
		} else if (100*currentSumUnitsPlanet/initialSumUnitsPlanet < PERCENTAGE_UNITS_BATTLE_END_THRESHOLD) {
			if (currentSumUnitsEnemy == 0) {
				log("The planet army has lost all of its units");
			} else {
				log("The planet army has lost the vast majority of its units");
			}
			return true;
		} else if (100*currentSumUnitsEnemy/initialSumUnitsEnemy < PERCENTAGE_UNITS_BATTLE_END_THRESHOLD) {
			if (currentSumUnitsEnemy == 0) {
				log("The enemy army has lost all of its units");
			} else {
				log("The enemy army has lost the vast majority of its units");
			}
			return true;
		}
		return false;
	}
	
	private int calculateWinner() {
		calculateResourcesLost();
		if (resourcesLosses[0][2] < resourcesLosses[1][2]) {
			return 0;
		} else {
			return 1;
		}
	}
	
	private void calculateResourcesLost() {
		for (int i = 0; i < planetDrops.length; i++) {
			resourcesLosses[0][0] += planetDrops[i]*METAL_COST_UNITS[i];
			resourcesLosses[0][1] += planetDrops[i]*DEUTERIUM_COST_UNITS[i];
		}
		for (int i = 0; i < enemyDrops.length; i++) {
			resourcesLosses[1][0] += enemyDrops[i]*METAL_COST_UNITS[i];
			resourcesLosses[1][1] += enemyDrops[i]*DEUTERIUM_COST_UNITS[i];
		}
		resourcesLosses[0][2] = resourcesLosses[0][0] + resourcesLosses[0][1]*5;
		resourcesLosses[1][2] = resourcesLosses[1][0] + resourcesLosses[1][1]*5;
	}
	
	private void performTurn(int atkArmy, int defArmy) {
		if (atkArmy == 0) {
			log("*".repeat(20)+"PLANET ARMY ATTACKS"+"*".repeat(20));
		} else {
			log("*".repeat(20)+"ENEMY ARMY ATTACKS"+"*".repeat(20));
		}
		int atkGroup = selectRandomAttackGroup(atkArmy);
		int atkUnit = (int) (armies[atkArmy][atkGroup].size()*Math.random());
		do {
			if (getDefArmyCurrentSumUnits(defArmy) == 0) {
				break;
			}
			int defGroup = selectRandomDefenseGroup(defArmy);
			int defUnit = (int) (armies[defArmy][defGroup].size()*Math.random());
//			// TODO debug
//			System.out.println("atkGroup: "+atkGroup);
//			System.out.println("defGroup: "+defGroup);
//			// TODO debug
			
			
			log(UNIT_NAMES[atkGroup]+" #"+(atkUnit+1)+" attacks "+UNIT_NAMES[defGroup]+" #"+(defUnit+1));
			
			int damage = armies[atkArmy][atkGroup].get(atkUnit).attack();
			armies[defArmy][defGroup].get(defUnit).takeDamage(damage);
			log(UNIT_NAMES[atkGroup]+" #"+(atkUnit+1)+" deals "+damage+" damage");
			log(UNIT_NAMES[defGroup]+" #"+(defUnit+1)+" is left with "+armies[defArmy][defGroup].get(defUnit).getCurrentArmor()+" armor");
			
			if (armies[defArmy][defGroup].get(defUnit).getCurrentArmor() <= 0) {
				generateWaste(defGroup);
				armies[defArmy][defGroup].remove(defUnit);
				if (defArmy == 0) {
					currentNumberUnitsPlanet[defGroup] -= 1;
					currentSumUnitsPlanet -= 1;
					planetDrops[defGroup] += 1;
				} else {
					currentNumberUnitsEnemy[defGroup] -= 1;
					currentSumUnitsEnemy -= 1;
					enemyDrops[defGroup] += 1;
				}
				log(UNIT_NAMES[defGroup]+" #"+(defUnit+1)+" has been defeated");
			}
		} while(doesUnitAttackAgain(atkGroup));
		log("");
	}
	
	private int getDefArmyCurrentSumUnits(int defArmy) {
		if (defArmy == 0) {
			return currentSumUnitsPlanet;
		} else {
			return currentSumUnitsEnemy;
		}
	}
	
	private int selectRandomAttackGroup(int atkArmy) {
		int[] chanceArray;
		if (atkArmy == 0) {
			chanceArray = CHANCE_ATTACK_PLANET_UNITS;
		} else {
			chanceArray = CHANCE_ATTACK_ENEMY_UNITS;
		}
		for (int i = 0; i < chanceArray.length; i++) {
			if (armies[atkArmy][i].size() == 0) {
				chanceArray[i] = 0;
			}
		}
		// TODO debug
		System.out.println(Arrays.toString(chanceArray));
		// TODO debug
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
		int accumulatingSum = 0;
		for (int i = 0; i < chanceArray.length; i++) {
			accumulatingSum += chanceArray[i];
			if (randomNum < accumulatingSum) {
				return i;
			}
		}
		return -1;
	}
	
	private void generateWaste(int defGroup) {
		int chance = CHANCE_GENERATING_WASTE_UNITS[defGroup];
		int metalWaste = METAL_COST_UNITS[defGroup]*PERCENTATGE_WASTE/100;
		int deuteriumWaste = DEUTERIUM_COST_UNITS[defGroup]*PERCENTATGE_WASTE/100;
		int randomNum = (int) (100*Math.random());
		if (randomNum < chance) {
			wasteMetalDeuterium[0] += metalWaste;
			wasteMetalDeuterium[1] += deuteriumWaste;
		}
	}
	
	private boolean doesUnitAttackAgain(int atkGroup) {
		int chance = CHANCE_ATTACK_AGAIN_UNITS[atkGroup];
		int randomNum = (int) (100*Math.random());
		if (randomNum < chance) {
			return true;
		}
		return false;
	}
	
	private void generateBattleStatistics() {
		battleStatistics += "BATTLE STATISTICS\n";
		
		battleStatistics += String.format(ARMY_TABLE_FORMAT, "Planet Army", "Units", "Drops", "Enemy Army", "Units", "Drops")+"\n\n";
		for (int i = 0; i<armies[0].length; i++) {
			if (i < 4) {
				battleStatistics += String.format(ARMY_TABLE_FORMAT, UNIT_NAMES[i], Integer.toString(initialNumberUnitsPlanet[i]), Integer.toString(planetDrops[i]), UNIT_NAMES[i], Integer.toString(initialNumberUnitsEnemy[i]), Integer.toString(enemyDrops[i]))+"\n\n";
			} else {
				battleStatistics += String.format(ARMY_TABLE_FORMAT, UNIT_NAMES[i], Integer.toString(initialNumberUnitsPlanet[i]), Integer.toString(planetDrops[i]), "", "", "")+"\n\n";
			}	
		}
		battleStatistics += "\n";
		
		battleStatistics += "*".repeat(80)+"\n";
		battleStatistics += String.format(RESOURCES_HEADER_FORMAT, "Planet Army Cost", "Enemy Army Cost")+"\n";
		battleStatistics += String.format(RESOURCES_ROW_FORMAT, "Metal:", initialCostFleet[0][0], "Metal:", initialCostFleet[1][0])+"\n";
		battleStatistics += String.format(RESOURCES_ROW_FORMAT, "Deuterium:", initialCostFleet[0][1], "Deuterium:", initialCostFleet[1][1])+"\n\n";
		
		battleStatistics += "*".repeat(80)+"\n";
		battleStatistics += String.format(RESOURCES_HEADER_FORMAT, "Planet Army Losses", "Enemy Army Losses")+"\n";
		battleStatistics += String.format(RESOURCES_ROW_FORMAT, "Metal:", resourcesLosses[0][0], "Metal:", resourcesLosses[1][0])+"\n";
		battleStatistics += String.format(RESOURCES_ROW_FORMAT, "Deuterium:", resourcesLosses[0][1], "Deuterium:", resourcesLosses[1][1])+"\n";
		battleStatistics += String.format(RESOURCES_ROW_FORMAT, "Weighted:", resourcesLosses[0][2], "Metal:", resourcesLosses[1][2])+"\n\n";
		
		battleStatistics += "*".repeat(80)+"\n";
		battleStatistics += "Waste Generated:\n";
		battleStatistics += String.format(RESOURCES_ROW_FORMAT_SHORT, "Metal:", wasteMetalDeuterium[0])+"\n";
		battleStatistics += String.format(RESOURCES_ROW_FORMAT_SHORT, "Deuterium:", wasteMetalDeuterium[1])+"\n\n";
		
		if (winningSide == 0) {
			battleStatistics += "Battle Won By Planet, We Collect Rubble\n\n";
		} else {
			battleStatistics += "Battle Won By Enemy, We Don't Collect Rubble\n\n";
		}
	}
	
	private void log(String line) {
		battleDevelopmentLog += line+"\n";
	}

	public int[] getWasteMetalDeuterium() {
		return wasteMetalDeuterium;
	}

	public String getBattleStatistics() {
		return battleStatistics;
	}

	public String getBattleDevelopmentLog() {
		return battleDevelopmentLog;
	}

	public ArrayList<MilitaryUnit>[] getPlanetArmy() {
		return planetArmy;
	}

	public int getWinningSide() {
		return winningSide;
	}
}