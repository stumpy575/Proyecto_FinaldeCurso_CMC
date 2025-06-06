package proyectoFinal;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ConsoleUI implements Variables {
	private Game game;
	private Timer enemy_timer;
	private boolean quit;
	private enum menu_types {
		MAIN, PLANET_STATS, BUILD_UNIT, BUILD_AMOUNT, UPGRADE_TECH, BATTLE_REPORTS
	}
	
	public void start() {
		game = new Game();
		game.start();
		startEnemyTimer();
		
		Scanner sc = new Scanner(System.in);
		quit = false;
		try {
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
		} catch (IllegalStateException | NoSuchElementException e) {
			game.gameOver();
		} finally {
			sc.close();
		}
	}
	
	private String getMenu(menu_types mt) {
		switch(mt) {
		case MAIN:
			return CONSOLE_MENU_MAIN;
		case BUILD_UNIT:
			return CONSOLE_MENU_BUILD_UNIT;
		case BUILD_AMOUNT:
			return CONSOLE_MENU_BUILD_AMOUNT;
		case UPGRADE_TECH:
			return String.format(CONSOLE_MENU_UPGRADE_TECH, game.getPlanet().getUpgradeAttackTechnologyDeuteriumCost(), game.getPlanet().getUpgradeDefenseTechnologyDeuteriumCost());
		case BATTLE_REPORTS:
			return String.format(CONSOLE_MENU_BATTLE_REPORTS, game.getBattleReportAmount());
		default:
			return "";
		}
	}
	
	private int getMenuMaxOption(menu_types mt) {
		switch(mt) {
		case MAIN:
			return 5;
		case BUILD_UNIT:
			return 7;
		case BUILD_AMOUNT:
			return 999;
		case UPGRADE_TECH:
			return 2;
		case BATTLE_REPORTS:
			return game.getBattleReportAmount();
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
		System.out.println(game.getPlanet().getStats());
	}

	private void build(Scanner sc) {
		System.out.println(getMenu(menu_types.BUILD_UNIT));
		int unit = chooseValidOption(sc, menu_types.BUILD_UNIT);
		if (unit == 0) {
			return;
		}
		System.out.println(getMenu(menu_types.BUILD_AMOUNT));
		int unit_amount = chooseValidOption(sc, menu_types.BUILD_AMOUNT);
		if (unit_amount == 0) {
			return;
		}
		try {
			game.build(unit-1, unit_amount);
		} catch (ResourceException e) {
			System.out.println(e.toString().substring(e.toString().indexOf("[")));
		}
	}

	private void upgradeTechnology(Scanner sc) {
		System.out.println(getMenu(menu_types.UPGRADE_TECH));
		switch(chooseValidOption(sc, menu_types.UPGRADE_TECH)) {
		case 1:
			try {
				game.getPlanet().upgradeTechnologyAttack();
			} catch (ResourceException e) {
				System.out.println(e.toString().substring(e.toString().indexOf("[")));
			}
			break;
		case 2:
			try {
				game.getPlanet().upgradeTechnologyDefense();
			} catch (ResourceException e) {
				System.out.println(e.toString().substring(e.toString().indexOf("[")));
			}
			break;
		case 0:
			break;
		}
	}

	private void viewBattleReports(Scanner sc) {
		int battle_report_amount = game.getBattleReportAmount();
		if (battle_report_amount == 0) {
			System.out.println("[!] No battle reports to view!");
		} else if (battle_report_amount == 1) {
			System.out.println("Only 1 battle report logged.");
			System.out.println("Press Enter to display it");
			Scanner sc2 = new Scanner(System.in);
			sc2.nextLine();
			System.out.println(game.getBattle_stats_logs()[0]);
			System.out.println("Do you wish to view the step by step development? (y/n)");
			if (sc.next().toLowerCase().equals("y")) {
				System.out.println(game.getBattle_development_logs()[0]);
			}
		} else {
			System.out.println(getMenu(menu_types.BATTLE_REPORTS));
			int option = chooseValidOption(sc, menu_types.BATTLE_REPORTS);
			if (option != 0) {
				System.out.println(game.getBattle_stats_logs()[option-1]);
				System.out.println("Do you wish to view the step by step development? (y/n)");
				if (sc.next().toLowerCase().equals("y")) {
					System.out.println(game.getBattle_development_logs()[option-1]);
				}
			}
		}
	}

	private void viewThreat(Scanner sc) {
		if (!game.isThreat_coming()) {
			System.out.println("[!] No threats detected.");
		} else {
			String threat = "NEW THREAT COMING\n\n";
			for (int i = 0; i < game.getEnemy_army().length; i++) {
				threat += String.format(THREAT_VIEW_FORMAT, UNIT_NAMES[i], game.getEnemy_army()[i].size())+"\n\n";
			}
			System.out.println(threat);
		}
	}
	
	private void startEnemyTimer() {
		enemy_timer = new Timer();
		TimerTask enemy_timer_task = new TimerTask() {

			public void run() {
				if (game.isThreat_coming()) {
					game.initBattle();
					if (game.getBattle().getCurrentSumUnitsPlanet() == 0) {
						System.out.println("\n".repeat(10));
						if(game.getBattle().getInitialSumUnitsPlanet() > 0) {
							System.out.println("!!! All your units have been destroyed !!!");
						}
						gameOver();
					} else {
						game.incrementWaveNumber();
						game.setThreat_coming(false);
						System.out.println("!!! A battle has ensued !!!");
					}
				} else {
					game.setThreat_coming(true);
					game.createEnemyArmy();
					System.out.println("!!! A threat has been detected !!!");
				}
			}
			
		};
		enemy_timer.schedule(enemy_timer_task, 5000, 20000); // Testing
//		enemy_timer.schedule(enemy_timer_task, 90000, 90000);
	}
	
	private void gameOver() {
		quit = true;
		enemy_timer.cancel();
		System.out.println("!!! Your planet has been invaded !!!");
		System.out.println("Press Enter to proceed to the Game Over screen");
		try {
			System.in.close();
		} catch (IOException e) {
			
		}
	}
}
