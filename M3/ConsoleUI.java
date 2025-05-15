package proyectoFinal;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ConsoleUI implements Variables {
	private Planet planet;
	private String[] battle_stats_logs;
	private String[] battle_development_logs;
	
	private int enemy_metal = METAL_BASE_ENEMY_ARMY;
	private int enemy_deuterium = DEUTERIUM_BASE_ENEMY_ARMY;
	private ArrayList<MilitaryUnit>[] enemy_army;
	
	private boolean threat_coming;
	private enum menu_types {
		MAIN, PLANET_STATS, BUILD_UNIT, BUILD_AMOUNT, UPGRADE_TECH, BATTLE_REPORTS
	}
	
	public void start() {
		planet = new Planet(0, 0, PLANET_STARTING_METAL, PLANET_STARTING_DEUTERIUM);
		enemy_army = new ArrayList[4];
		for (int i = 0; i < enemy_army.length; i++) {
			this.enemy_army[i] = new ArrayList();
		}
		battle_stats_logs = new String[5];
		battle_development_logs = new String[5];
		threat_coming = false;
		
		Scanner sc = new Scanner(System.in);
		boolean quit = false;
		startEnemyTimer();
		while (!quit) {
			System.out.println(getMenu(menu_types.MAIN));
			switch(chooseValidOption(sc, menu_types.MAIN)) {
			case 1:
				viewPlanetStats(sc);
				pressToContinue();
				break;
			case 2:
				build(sc);
				pressToContinue();
				break;
			case 3:
				upgradeTechnology(sc);
				pressToContinue();
				break;
			case 4:
				viewBattleReports(sc);
				pressToContinue();
				break;
			case 5:
				viewThreat(sc);
				pressToContinue();
				break;
			case 0:
				quit = true;
				break;
			}
		}
	}
	
	private void startEnemyTimer() {
		Timer enemy_timer = new Timer();
		TimerTask enemy_timer_task = new TimerTask() {

			public void run() {
				if (threat_coming) {
					initBattle();
					threat_coming = false;
				} else {
					threat_coming = true;
					createEnemyArmy();
				}
			}
			
		};
		enemy_timer.schedule(enemy_timer_task, 5000, 20000); // Testing
//		enemy_timer.schedule(enemy_timer_task, 90000, 90000);
	}
	
	private String getMenu(menu_types mt) {
		switch(mt) {
		case MAIN:
			return threat_coming ? CONSOLE_MENU_MAIN_THREAT : CONSOLE_MENU_MAIN;
		case BUILD_UNIT:
			return CONSOLE_MENU_BUILD_UNIT;
		case BUILD_AMOUNT:
			return CONSOLE_MENU_BUILD_AMOUNT;
		case UPGRADE_TECH:
			return String.format(CONSOLE_MENU_UPGRADE_TECH, planet.getUpgradeAttackTechnologyDeuteriumCost(), planet.getUpgradeDefenseTechnologyDeuteriumCost());
		case BATTLE_REPORTS:
			return String.format(CONSOLE_MENU_BATTLE_REPORTS, getBattleReportAmount());
		default:
			return "";
		}
	}
	
	private int getMenuMaxOption(menu_types mt) {
		switch(mt) {
		case MAIN:
			return threat_coming ? 5 : 4;
		case BUILD_UNIT:
			return 7;
		case BUILD_AMOUNT:
			return 999;
		case UPGRADE_TECH:
			return 2;
		case BATTLE_REPORTS:
			return getBattleReportAmount();
		default:
			return 0;
		}
	}
	
	private int chooseValidOption(Scanner sc, menu_types mt) {
		int max_option = getMenuMaxOption(mt);
 		while (true) {
		    if (!sc.hasNextInt()) {
		        sc.nextLine();
		        System.out.printf("Please select a valid option (0-%1d)\n", max_option);
		        System.out.println(getMenu(mt));
		        continue;
		    }
		    int input = sc.nextInt();
		    if (input >= 0 && input <= max_option) {
		        return input;
		    } else {
		        System.out.printf("Please select a valid option (0-%1d)\n", max_option);
		        System.out.println(getMenu(mt));
		    }
		}
	}
	
	private void pressToContinue() {
		System.out.println("Press Enter to Continue");
		Scanner sc = new Scanner(System.in);
		sc.nextLine();
	}
	
	private void viewPlanetStats(Scanner sc) {
		planet.printStats();
	}

	private void build(Scanner sc) {
		System.out.println(getMenu(menu_types.BUILD_UNIT));
		int unit = chooseValidOption(sc, menu_types.BUILD_UNIT);
		System.out.println(getMenu(menu_types.BUILD_AMOUNT));
		int unit_amount = chooseValidOption(sc, menu_types.BUILD_AMOUNT);
		if (unit_amount > 0) {
			switch(unit) {
			case 1: 
				planet.newLightHunter(unit_amount);
				break;
			case 2: 
				planet.newHeavyHunter(unit_amount);
				break;
			case 3: 
				planet.newBattleship(unit_amount);
				break;
			case 4: 
				planet.newArmoredShip(unit_amount);
				break;
			case 5: 
				planet.newMissileLauncher(unit_amount);
				break;
			case 6: 
				planet.newIonCannon(unit_amount);
				break;
			case 7: 
				planet.newPlasmaCannon(unit_amount);
				break;
			case 0: 
				break;
			}
		}
	}

	private void upgradeTechnology(Scanner sc) {
		System.out.println(getMenu(menu_types.UPGRADE_TECH));
		switch(chooseValidOption(sc, menu_types.UPGRADE_TECH)) {
		case 1:
			try {
				planet.upgradeTechnologyAttack();
			} catch (ResourceException e) {
				System.out.println(e.toString().substring(e.toString().indexOf("[")));
			}
			break;
		case 2:
			try {
				planet.upgradeTechnologyDefense();
			} catch (ResourceException e) {
				System.out.println(e.toString().substring(e.toString().indexOf("[")));
			}
			break;
		case 0:
			break;
		}
	}

	private void viewBattleReports(Scanner sc) {
		int battle_report_amount = getBattleReportAmount();
		if (battle_report_amount == 0) {
			System.out.println("[!] No battle reports to view!");
		} else if (battle_report_amount == 1) {
			System.out.println("Displaying the only battle report logged...");
			System.out.println(battle_stats_logs[0]);
			System.out.println("Do you wish to view the step by step development? (y/n)");
			if (sc.next().toLowerCase().equals("y")) {
				System.out.println(battle_development_logs[0]);
			}
		} else {
			System.out.println(getMenu(menu_types.BATTLE_REPORTS));
			int option = chooseValidOption(sc, menu_types.BATTLE_REPORTS);
			if (option != 0) {
				System.out.println(battle_stats_logs[option-1]);
				System.out.println("Do you wish to view the step by step development? (y/n)");
				if (sc.next().toLowerCase().equals("y")) {
					System.out.println(battle_development_logs[option-1]);
				}
			}
		}
	}
	
	private int getBattleReportAmount() {
		int battle_report_amount = 0;
		for (String log : battle_development_logs) {
			if (log != null) {
				battle_report_amount++;
			}
		}
		return battle_report_amount;
	}

	private void viewThreat(Scanner sc) {
		String threat = "NEW THREAT COMING\n\n";
		for (int i = 0; i < enemy_army.length; i++) {
			threat += String.format(THREAT_VIEW_FORMAT, UNIT_NAMES[i], enemy_army[i].size())+"\n\n";
		}
		System.out.println(threat);
	}
	
	
	private void createEnemyArmy() {
		int metal_spent = 0;
		int deuterium_spent = 0;
		while(true) {
			MilitaryUnit unit_to_add;
			int group_to_add = selectEnemyGroup();
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
			metal_spent += METAL_COST_UNITS[group_to_add];
			deuterium_spent += DEUTERIUM_COST_UNITS[group_to_add];
			if (metal_spent > enemy_metal || deuterium_spent > enemy_deuterium) {
				break;
			} else {
				enemy_army[group_to_add].add(unit_to_add);
			}
		}
	}
	
	private int selectEnemyGroup() {
		int totalSum = 0;
		int[] chanceArray = CHANCE_CREATE_UNIT_ENEMY_ARMY;
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
	
	private void initBattle() {
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
}
