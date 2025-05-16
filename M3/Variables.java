package proyectoFinal;

public interface Variables {
	
	// resources available when starting the game
	public final int PLANET_STARTING_DEUTERIUM = 15000;
	public final int PLANET_STARTING_METAL = 50000;
	
	// resources available to create the first enemy fleet
	public final int DEUTERIUM_BASE_ENEMY_ARMY = 2600;
	public final int METAL_BASE_ENEMY_ARMY = 18000;
	
	// percentage increase of resources available to create enemy fleet
	public final int ENEMY_FLEET_INCREASE = 6;
	
	// resources increment every minute
	public final int PLANET_DEUTERIUM_GENERATED = 1500;
	public final int PLANET_METAL_GENERATED = 5000;
	
	// TECHNOLOGY COST
	public final int UPGRADE_BASE_DEFENSE_TECHNOLOGY_DEUTERIUM_COST = 2000;
	public final int UPGRADE_BASE_ATTACK_TECHNOLOGY_DEUTERIUM_COST = 2000;
	public final int UPGRADE_PLUS_DEFENSE_TECHNOLOGY_DEUTERIUM_COST = 60;
	public final int UPGRADE_PLUS_ATTACK_TECHNOLOGY_DEUTERIUM_COST = 60;
	
	// COST SHIPS
	public final int METAL_COST_LIGTHHUNTER = 3000;
	public final int METAL_COST_HEAVYHUNTER = 6500;
	public final int METAL_COST_BATTLESHIP = 45000;
	public final int METAL_COST_ARMOREDSHIP = 30000;
	public final int DEUTERIUM_COST_LIGTHHUNTER = 50;
	public final int DEUTERIUM_COST_HEAVYHUNTER = 50;
	public final int DEUTERIUM_COST_BATTLESHIP = 7000;
	public final int DEUTERIUM_COST_ARMOREDSHIP = 15000;
	
	// COST DEFENSES
	public final int DEUTERIUM_COST_MISSILELAUNCHER = 0;
	public final int DEUTERIUM_COST_IONCANNON = 500;
	public final int DEUTERIUM_COST_PLASMACANNON = 5000;
	public final int METAL_COST_MISSILELAUNCHER = 2000;
	public final int METAL_COST_IONCANNON = 4000;
	public final int METAL_COST_PLASMACANNON = 50000;
	
	// Array of units' costs
	public final int[] METAL_COST_UNITS = {METAL_COST_LIGTHHUNTER,METAL_COST_HEAVYHUNTER,METAL_COST_BATTLESHIP,METAL_COST_ARMOREDSHIP,
	METAL_COST_MISSILELAUNCHER,METAL_COST_IONCANNON,METAL_COST_PLASMACANNON};
	public final int[] DEUTERIUM_COST_UNITS = {DEUTERIUM_COST_LIGTHHUNTER,DEUTERIUM_COST_HEAVYHUNTER,DEUTERIUM_COST_BATTLESHIP,DEUTERIUM_COST_ARMOREDSHIP,
	DEUTERIUM_COST_MISSILELAUNCHER,DEUTERIUM_COST_IONCANNON,DEUTERIUM_COST_PLASMACANNON};
	
	// BASE DAMAGE SHIPS
	public final int BASE_DAMAGE_LIGTHHUNTER = 80;
	public final int BASE_DAMAGE_HEAVYHUNTER = 150;
	public final int BASE_DAMAGE_BATTLESHIP = 1000;
	public final int BASE_DAMAGE_ARMOREDSHIP = 700;
	
	// BASE DAMAGE DEFENSES
	public final int BASE_DAMAGE_MISSILELAUNCHER = 80;
	public final int BASE_DAMAGE_IONCANNON = 250;
	public final int BASE_DAMAGE_PLASMACANNON = 2000;
	
	// Array units' base damage
	public final int[] BASE_DAMAGE_UNITS = {BASE_DAMAGE_LIGTHHUNTER,BASE_DAMAGE_HEAVYHUNTER,BASE_DAMAGE_BATTLESHIP,BASE_DAMAGE_ARMOREDSHIP,
	BASE_DAMAGE_MISSILELAUNCHER,BASE_DAMAGE_IONCANNON,BASE_DAMAGE_PLASMACANNON};
	
	// REDUCTION_DEFENSE
	public final int REDUCTION_DEFENSE_IONCANNON = 100;
	
	// ARMOR SHIPS
	public final int ARMOR_LIGTHHUNTER = 400;
	public final int ARMOR_HEAVYHUNTER = 1000;
	public final int ARMOR_BATTLESHIP = 6000;
	public final int ARMOR_ARMOREDSHIP = 8000;
	
	// ARMOR DEFENSES
	public final int ARMOR_MISSILELAUNCHER = 200;
	public final int ARMOR_IONCANNON = 1200;
	public final int ARMOR_PLASMACANNON = 7000;
	
	// Array units' armor
	public final int[] ARMOR_UNITS = {ARMOR_LIGTHHUNTER,ARMOR_HEAVYHUNTER,ARMOR_BATTLESHIP,ARMOR_ARMOREDSHIP,ARMOR_MISSILELAUNCHER,ARMOR_IONCANNON,ARMOR_PLASMACANNON};
	
	//fleet armor increase percentage per tech level
	public final int PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY = 5;
	public final int PLUS_ARMOR_HEAVYHUNTER_BY_TECHNOLOGY = 5;
	public final int PLUS_ARMOR_BATTLESHIP_BY_TECHNOLOGY = 5;
	public final int PLUS_ARMOR_ARMOREDSHIP_BY_TECHNOLOGY = 5;
	
	// defense armor increase percentage per tech level
	public final int PLUS_ARMOR_MISSILELAUNCHER_BY_TECHNOLOGY = 5;
	public final int PLUS_ARMOR_IONCANNON_BY_TECHNOLOGY = 5;
	public final int PLUS_ARMOR_PLASMACANNON_BY_TECHNOLOGY = 5;
	
	// Array of units' armor increase percentage per tech level
	public final int[] PLUS_ARMOR_UNITS_BY_TECHNOLOGY = {PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY,PLUS_ARMOR_HEAVYHUNTER_BY_TECHNOLOGY,PLUS_ARMOR_BATTLESHIP_BY_TECHNOLOGY,PLUS_ARMOR_ARMOREDSHIP_BY_TECHNOLOGY,
	PLUS_ARMOR_MISSILELAUNCHER_BY_TECHNOLOGY,PLUS_ARMOR_IONCANNON_BY_TECHNOLOGY,PLUS_ARMOR_PLASMACANNON_BY_TECHNOLOGY};
	
	// fleet attack power increase percentage per tech level
	public final int PLUS_ATTACK_LIGTHHUNTER_BY_TECHNOLOGY = 5;
	public final int PLUS_ATTACK_HEAVYHUNTER_BY_TECHNOLOGY = 5;
	public final int PLUS_ATTACK_BATTLESHIP_BY_TECHNOLOGY = 5;
	public final int PLUS_ATTACK_ARMOREDSHIP_BY_TECHNOLOGY = 5;
	
	// Defense attack power increase percentage per tech level
	public final int PLUS_ATTACK_MISSILELAUNCHER_BY_TECHNOLOGY = 5;
	public final int PLUS_ATTACK_IONCANNON_BY_TECHNOLOGY = 5;
	public final int PLUS_ATTACK_PLASMACANNON_BY_TECHNOLOGY = 5;
	
	// Array of units' attack power increase percentage per tech level
	public final int[] PLUS_ATTACK_UNITS_BY_TECHNOLOGY = {PLUS_ATTACK_LIGTHHUNTER_BY_TECHNOLOGY, PLUS_ATTACK_HEAVYHUNTER_BY_TECHNOLOGY, PLUS_ATTACK_BATTLESHIP_BY_TECHNOLOGY, PLUS_ATTACK_ARMOREDSHIP_BY_TECHNOLOGY,
	PLUS_ATTACK_MISSILELAUNCHER_BY_TECHNOLOGY,PLUS_ATTACK_IONCANNON_BY_TECHNOLOGY,PLUS_ATTACK_PLASMACANNON_BY_TECHNOLOGY};
	
	// fleet probability of generating waste
	public final int CHANCE_GENERATNG_WASTE_LIGTHHUNTER = 55;
	public final int CHANCE_GENERATNG_WASTE_HEAVYHUNTER = 65;
	public final int CHANCE_GENERATNG_WASTE_BATTLESHIP = 80;
	public final int CHANCE_GENERATNG_WASTE_ARMOREDSHIP = 90;
	
	// Defense probability of generating waste
	public final int CHANCE_GENERATNG_WASTE_MISSILELAUNCHER = 55;
	public final int CHANCE_GENERATNG_WASTE_IONCANNON = 65;
	public final int CHANCE_GENERATNG_WASTE_PLASMACANNON = 75;
	
	// Array of units' chance of generating waste
	public final int[] CHANCE_GENERATING_WASTE_UNITS = 
	{CHANCE_GENERATNG_WASTE_LIGTHHUNTER,CHANCE_GENERATNG_WASTE_HEAVYHUNTER,CHANCE_GENERATNG_WASTE_BATTLESHIP,CHANCE_GENERATNG_WASTE_ARMOREDSHIP, 
	CHANCE_GENERATNG_WASTE_MISSILELAUNCHER,CHANCE_GENERATNG_WASTE_IONCANNON,CHANCE_GENERATNG_WASTE_PLASMACANNON};
	
	// fleet chance to attack again
	public final int CHANCE_ATTACK_AGAIN_LIGTHHUNTER = 3;
	public final int CHANCE_ATTACK_AGAIN_HEAVYHUNTER = 7;
	public final int CHANCE_ATTACK_AGAIN_BATTLESHIP = 45;
	public final int CHANCE_ATTACK_AGAIN_ARMOREDSHIP = 70;
	
	//Defense chance to attack again
	public final int CHANCE_ATTACK_AGAIN_MISSILELAUNCHER = 5;
	public final int CHANCE_ATTACK_AGAIN_IONCANNON = 12;
	public final int CHANCE_ATTACK_AGAIN_PLASMACANNON = 30;
	
	// Array of units' chance to attack again
	public final int[] CHANCE_ATTACK_AGAIN_UNITS =
	{CHANCE_ATTACK_AGAIN_LIGTHHUNTER,CHANCE_ATTACK_AGAIN_HEAVYHUNTER,CHANCE_ATTACK_AGAIN_BATTLESHIP,CHANCE_ATTACK_AGAIN_ARMOREDSHIP,
	CHANCE_ATTACK_AGAIN_MISSILELAUNCHER,CHANCE_ATTACK_AGAIN_IONCANNON,CHANCE_ATTACK_AGAIN_PLASMACANNON};
	
	// CHANCE ATTACK EVERY UNIT
	// LIGTHHUNTER, HEAVYHUNTER, BATTLESHIP, ARMOREDSHIP, MISSILELAUNCHER, IONCANNON, PLASMACANNON
	public final int[] CHANCE_ATTACK_PLANET_UNITS = {5,10,15,40,5,10,15};
	
	// LIGTHHUNTER, HEAVYHUNTER, BATTLESHIP, ARMOREDSHIP
	public final int[] CHANCE_ATTACK_ENEMY_UNITS = {10,20,30,40};
	
	// percentage of waste that will be generated with respect to the cost of the units
	public final int PERCENTATGE_WASTE = 70;
	
	// percentage of units in at least one army for battle to continue
	public final int PERCENTAGE_UNITS_BATTLE_END_THRESHOLD = 20;
	
	// array of unit names (used for battle statistics and development log)
	public final String[] UNIT_NAMES = {"Light Hunter","Heavy Hunter","Battleship","Armored Ship","Missile Launcher","Ion Cannon","Plasma Cannon"};
	
	// battle statistics string formats
	public final String ARMY_TABLE_FORMAT = "%-20s%10s%10s     %-20s%10s%10s";
	public final String RESOURCES_HEADER_FORMAT = "%-40s%-40s";
	public final String RESOURCES_ROW_FORMAT = "%-10s%10d" + " ".repeat(20) + "%-10s%10d";
	public final String RESOURCES_ROW_FORMAT_SHORT = "%-10s%10d";
	
	// planet stats string formats
	public final String PLANET_STATS_FORMAT = "%-20s%5d";
	public final String PLANET_STATS_FORMAT_RESOURCES = "%-10s%15d";
	
	// Threat view string format
	public final String THREAT_VIEW_FORMAT = "%-20s%5d";
	
	// Console Menus
	public final String CONSOLE_MENU_MAIN = "Options:\n1. View Planet Stats\n2. Build\n3. Upgrade Technology\n4. View Battle Reports\n0. Exit";
	public final String CONSOLE_MENU_MAIN_THREAT = "Options:\n1. View Planet Stats\n2. Build\n3. Upgrade Technology\n4. View Battle Reports\n5. View Threat\n0. Exit";
	public final String CONSOLE_MENU_UPGRADE_TECH = "Which technology do you wish to upgrade?\n1. Attack (%d Deuterium)\n2. Defense (%d Deuterium)\n0. Cancel";
	public final String CONSOLE_MENU_BUILD_UNIT = "Which unit do you wish to build?\n1. Light Hunter\n2. Heavy Hunter\n3. Battleship\n4. Armored Ship\n5. Missile Launcher\n6. Ion Cannon\n7. Plasma Cannon\n0. Cancel";
	public final String CONSOLE_MENU_BUILD_AMOUNT = "How many? (1-999) (0. Cancel)";
	public final String CONSOLE_MENU_BATTLE_REPORTS = "What battle do you wish to view? (1-%1d) (0. Cancel)";
	
	// Chance create unit in enemy army
	// LightHunter, HeavyHunter, Battleship, ArmoredShip
	public final int[] CHANCE_CREATE_UNIT_ENEMY_ARMY = {35,25,20,20};
}
