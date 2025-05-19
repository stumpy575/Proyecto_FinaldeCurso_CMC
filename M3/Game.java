package proyectoFinal;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Game implements Variables {
	private Planet planet;
	private static int battleCounter = 1;
	private String[] battle_stats_logs;
	private String[] battle_development_logs;
	
	private int enemy_metal = METAL_BASE_ENEMY_ARMY;
	private int enemy_deuterium = DEUTERIUM_BASE_ENEMY_ARMY;
	private ArrayList<MilitaryUnit>[] enemy_army;
	
	private boolean threat_coming;
	
	public void start() {
		planet = new Planet(0, 0, PLANET_STARTING_METAL, PLANET_STARTING_DEUTERIUM);
		battle_stats_logs = new String[5];
		battle_development_logs = new String[5];
		threat_coming = false;
		startResourcesTimer();
		if (Main.isConsoleMode()) {
			startEnemyTimer();
		}
	}

	public void startResourcesTimer() {
		Timer resources_timer = new Timer();
		TimerTask resources_timer_task = new TimerTask() {
			public void run() {
				
				System.out.println(PLANET_METAL_GENERATED+" Metal and " + PLANET_DEUTERIUM_GENERATED + " Deuterium generated");
				planet.setMetal(planet.getMetal() + PLANET_METAL_GENERATED);
				planet.setDeuterium(planet.getDeuterium() + PLANET_DEUTERIUM_GENERATED);
			}
		};
		resources_timer.schedule(resources_timer_task, 60000, 60000);
	}
	
	private void startEnemyTimer() {
		Timer enemy_timer = new Timer();
		TimerTask enemy_timer_task = new TimerTask() {

			public void run() {
				if (threat_coming) {
					initBattle();
					threat_coming = false;
					System.out.println("!!! A battle has occurred !!!");
				} else {
					threat_coming = true;
					createEnemyArmy();
					System.out.println("!!! A threat has been detected !!!");
				}
			}
			
		};
		enemy_timer.schedule(enemy_timer_task, 5000, 20000); // Testing
//		enemy_timer.schedule(enemy_timer_task, 90000, 90000);
	}
	
	public void createEnemyArmy() {
		initEnemyArmy();
		int metal_remaining = enemy_metal;
		int deuterium_remaining = enemy_deuterium;
		while(true) {
			MilitaryUnit unit_to_add;
			int group_to_add = selectEnemyGroup(metal_remaining,deuterium_remaining);
			if (group_to_add == -1) {
				break;
			}
			switch(group_to_add) {
			case 0:
				unit_to_add = (MilitaryUnit) new LightHunter();
				break;
			case 1:
				unit_to_add = (MilitaryUnit) new HeavyHunter();
				break;
			case 2:
				unit_to_add = (MilitaryUnit) new Battleship();
				break;
			case 3:
				unit_to_add = (MilitaryUnit) new ArmoredShip();
				break;
			default:
				unit_to_add = null;
				break;
			}
			metal_remaining -= METAL_COST_UNITS[group_to_add];
			deuterium_remaining -= DEUTERIUM_COST_UNITS[group_to_add];
			enemy_army[group_to_add].add(unit_to_add);
		}
	}
	
	private void initEnemyArmy() {
		enemy_army = new ArrayList[4];
		for (int i = 0; i < enemy_army.length; i++) {
			this.enemy_army[i] = new ArrayList();
		}
	}
	private int selectEnemyGroup(int metal_remaining, int deuterium_remaining) {
		int totalSum = 0;
		int[] chanceArray = CHANCE_CREATE_UNIT_ENEMY_ARMY.clone();
		for (int i = 0; i < chanceArray.length; i++) {
			if (metal_remaining < METAL_COST_UNITS[i] || deuterium_remaining < DEUTERIUM_COST_UNITS[i]) {
				chanceArray[i] = 0;
			}
		}
		
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
	
	public void initBattle() {
		Battle battle = new Battle(planet.getArmy(), enemy_army);
		battle.performBattle();
		
		String battle_stats = battle.getBattleStatistics();
		String battle_development = battle.getBattleDevelopmentLog();
		addLogToArray(battle_stats_logs, battle_stats);
		addLogToArray(battle_development_logs, battle_development);
		
		planet.setArmy(battle.getPlanetArmy());
		int wasteMetal = battle.getWasteMetalDeuterium()[0];
		int wasteDeuterium = battle.getWasteMetalDeuterium()[1];
		int winningSide = battle.getWinningSide();
		if (winningSide == 0) {
			planet.setMetal(planet.getMetal() + wasteMetal);
			planet.setDeuterium(planet.getDeuterium() + wasteDeuterium);
		}
		enemy_metal = enemy_metal*(100+ENEMY_FLEET_INCREASE)/100;
		enemy_deuterium = enemy_deuterium*(100+ENEMY_FLEET_INCREASE)/100;
		try {
		    BattleExporter.exportBattleToHTML(battle, battleCounter++);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
	
	private void addLogToArray(String[] log_array, String log) {
		if (!isArrayFull(log_array)) {
			for (int i = 0; i <log_array.length; i++) {
				if (log_array[i] == null) {
					log_array[i] = log;
					break;
				}
			}
		} else {
			for (int i = 0; i < log_array.length-1; i++) {
				log_array[i] = log_array[i+1];
			}
			log_array[log_array.length-1] = log;
		}
		
	}
	
	private boolean isArrayFull(String[] array) {
		for (int i = 0; i <array.length; i++) {
			if (array[i] == null) {
				return false;
			}
		}
		return true;
	}

	public int getBattleReportAmount() {
		int battle_report_amount = 0;
		for (String log : battle_development_logs) {
			if (log != null) {
				battle_report_amount++;
			}
		}
		return battle_report_amount;
	}
	
	public void build(int unitType, int unit_amount) throws ResourceException {
		switch(unitType) {
		case 0: 
			planet.newLightHunter(unit_amount);
			break;
		case 1: 
			planet.newHeavyHunter(unit_amount);
			break;
		case 2: 
			planet.newBattleship(unit_amount);
			break;
		case 3: 
			planet.newArmoredShip(unit_amount);
			break;
		case 4: 
			planet.newMissileLauncher(unit_amount);
			break;
		case 5: 
			planet.newIonCannon(unit_amount);
			break;
		case 6: 
			planet.newPlasmaCannon(unit_amount);
			break;
		}
	}
	
	public Planet getPlanet() {
		return planet;
	}

	public String[] getBattle_stats_logs() {
		return battle_stats_logs;
	}

	public String[] getBattle_development_logs() {
		return battle_development_logs;
	}

	public boolean isThreat_coming() {
		return threat_coming;
	}

	public ArrayList<MilitaryUnit>[] getEnemy_army() {
		return enemy_army;
	}
}
