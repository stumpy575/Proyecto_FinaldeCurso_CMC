package proyectoFinal;

import java.util.Scanner;

public class ConsoleUI implements Variables {
	private Planet planet;
	private String[] battle_stats_logs;
	private String[] battle_development_logs;
	private boolean threat_coming;
	private enum menu_types {
		MAIN, PLANET_STATS, BUILD_UNIT, BUILD_AMOUNT, UPGRADE_TECH, BATTLE_REPORTS
	}
	
	public void start() {
		planet = new Planet(0, 0, PLANET_STARTING_METAL, PLANET_STARTING_DEUTERIUM);
		battle_stats_logs = new String[5];
		battle_development_logs = new String[5];
		threat_coming = true;
		
		Scanner sc = new Scanner(System.in);
		boolean quit = false;
		while (!quit) {
			System.out.println(getMenu(menu_types.MAIN));
			switch(chooseValidOption(sc, menu_types.MAIN)) {
			case 1:
				viewPlanetStats(sc);
				break;
			case 2:
				build(sc);
				break;
			case 3:
				upgradeTechnology(sc);
				break;
			case 4:
				viewBattleReports(sc);
				break;
			case 5:
				viewThreat(sc);
				break;
			case 0:
				quit = true;
				break;
			}
		}
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
			return CONSOLE_MENU_UPGRADE_TECH;
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
	
	private void pressToContinue(Scanner sc) {
		System.out.println("Press Anything to Continue");
		sc.next();
	}
	
	private void viewPlanetStats(Scanner sc) {
		planet.printStats();
		pressToContinue(sc);
	}

	private void build(Scanner sc) {
		System.out.println(getMenu(menu_types.BUILD_UNIT));
		int unit = chooseValidOption(sc, menu_types.BUILD_UNIT);
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
		pressToContinue(sc);
	}

	private void upgradeTechnology(Scanner sc) {
		System.out.println(getMenu(menu_types.UPGRADE_TECH));
		switch(chooseValidOption(sc, menu_types.UPGRADE_TECH)) {
		case 1:
			try {
				planet.upgradeTechnologyAttack();
			} catch (ResourceException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 2:
			try {
				planet.upgradeTechnologyDefense();
			} catch (ResourceException e) {
				System.out.println(e.getMessage());
			}
			break;
		case 0:
			break;
		}
		pressToContinue(sc);
	}

	private void viewBattleReports(Scanner sc) {
		System.out.println(getMenu(menu_types.BATTLE_REPORTS));
		int option = chooseValidOption(sc, menu_types.BATTLE_REPORTS);
		if (option != 0) {
			System.out.println(battle_stats_logs[option]);
			System.out.println("Do you wish to view the step by step development? (y/n)");
			if (sc.next().toLowerCase().equals("y")) {
				System.out.println(battle_development_logs[option]);
			}
		}
		pressToContinue(sc);
	}
	
	private int getBattleReportAmount() {
		int battle_report_amount = 0;
		for (String log : battle_development_logs) {
			if (!log.isEmpty()) {
				battle_report_amount++;
			}
		}
		return battle_report_amount;
	}

	private void viewThreat(Scanner sc) {
		
	}
	
	
	private void createEnemyArmy() {
		
	}
	
}
