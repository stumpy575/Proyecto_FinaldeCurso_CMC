package proyectoFinal;

import java.util.ArrayList;

public class Planet implements Variables {
	private int technologyDefense;
	private int technologyAttack;
	private int metal;
	private int deuterium;
	private int upgradeDefenseTechnologyDeuteriumCost;
	private int upgradeAttackTechnologyDeuteriumCost;
	private ArrayList<MilitaryUnit>[] army;
	
	public Planet(int technologyDefense, int technologyAtack, int metal, int deuterium) {
		super();
		this.technologyDefense = technologyDefense;
		this.technologyAttack = technologyAtack;
		this.metal = metal;
		this.deuterium = deuterium;
		this.upgradeDefenseTechnologyDeuteriumCost = UPGRADE_BASE_DEFENSE_TECHNOLOGY_DEUTERIUM_COST;
		this.upgradeAttackTechnologyDeuteriumCost = UPGRADE_BASE_ATTACK_TECHNOLOGY_DEUTERIUM_COST;
		this.army = new ArrayList[7];
		for (int i = 0; i < army.length; i++) {
			this.army[i] = new ArrayList();
		}
	}
	
	public int getTechnologyDefense() {
		return technologyDefense;
	}
	public void setTechnologyDefense(int technologyDefense) {
		this.technologyDefense = technologyDefense;
	}
	public int getTechnologyAttack() {
		return technologyAttack;
	}
	public void setTechnologyAttack(int technologyAtack) {
		this.technologyAttack = technologyAtack;
	}
	public int getMetal() {
		return metal;
	}
	public void setMetal(int metal) {
		this.metal = metal;
	}
	public int getDeuterium() {
		return deuterium;
	}
	public void setDeuterium(int deuterium) {
		this.deuterium = deuterium;
	}
	public int getUpgradeDefenseTechnologyDeuteriumCost() {
		return upgradeDefenseTechnologyDeuteriumCost;
	}
	public void setUpgradeDefenseTechnologyDeuteriumCost(int upgradeDefenseTechnologyDeuteriumCost) {
		this.upgradeDefenseTechnologyDeuteriumCost = upgradeDefenseTechnologyDeuteriumCost;
	}
	public int getUpgradeAttackTechnologyDeuteriumCost() {
		return upgradeAttackTechnologyDeuteriumCost;
	}
	public void setUpgradeAttackTechnologyDeuteriumCost(int upgradeAttackTechnologyDeuteriumCost) {
		this.upgradeAttackTechnologyDeuteriumCost = upgradeAttackTechnologyDeuteriumCost;
	}
	public ArrayList<MilitaryUnit>[] getArmy() {
		return army;
	}
	public void setArmy(ArrayList<MilitaryUnit>[] army) {
		this.army = army;
	}
	
	public void upgradeTechnologyDefense() throws ResourceException {
		if (deuterium < upgradeDefenseTechnologyDeuteriumCost) {
			throw new ResourceException("[!] Not enough deuterium to upgrade!");
		} else {
			deuterium -= upgradeDefenseTechnologyDeuteriumCost;
			technologyDefense += 1;
			upgradeDefenseTechnologyDeuteriumCost = (int) (upgradeDefenseTechnologyDeuteriumCost*(100+UPGRADE_PLUS_DEFENSE_TECHNOLOGY_DEUTERIUM_COST)/100);
		}
	}
	
	public void upgradeTechnologyAttack() throws ResourceException {
		if (deuterium < upgradeAttackTechnologyDeuteriumCost) {
			throw new ResourceException("[!] Not enough deuterium to upgrade!");
		} else {
			deuterium -= upgradeAttackTechnologyDeuteriumCost;
			technologyAttack += 1;
			upgradeAttackTechnologyDeuteriumCost = (int) (upgradeAttackTechnologyDeuteriumCost*(100+UPGRADE_PLUS_ATTACK_TECHNOLOGY_DEUTERIUM_COST)/100);
		}
	}
	
	private int checkEnoughResourcesToBuild(int n, int unitType) throws ResourceException {
		int metal_cost = METAL_COST_UNITS[unitType];
		int deuterium_cost = DEUTERIUM_COST_UNITS[unitType];
		int n_possible = n;
		if (metal<metal_cost*n || deuterium<deuterium_cost*n) {
			int n_possible_metal = (int) (metal/metal_cost);
			if (deuterium_cost > 0) {
				int n_possible_deuterium = (int) (deuterium/deuterium_cost);
				if (n_possible_metal < n_possible_deuterium) {
					n_possible = n_possible_metal;
				} else {
					n_possible = n_possible_deuterium;
				}
			} else {
				n_possible = n_possible_metal;
			}
		}
		return n_possible;
	}
	
	private void addMilitaryUnit(int unitType, int armor, int attack) {
		switch (unitType) {
		case 0:
			army[0].add((MilitaryUnit) new LightHunter(armor, attack));
			break;
		case 1:
			army[1].add((MilitaryUnit) new HeavyHunter(armor, attack));
			break;
		case 2:
			army[2].add((MilitaryUnit) new Battleship(armor, attack));
			break;
		case 3:
			army[3].add((MilitaryUnit) new ArmoredShip(armor, attack));
			break;
		case 4:
			army[4].add((MilitaryUnit) new MissileLauncher(armor, attack));
			break;
		case 5:
			army[5].add((MilitaryUnit) new IonCannon(armor, attack));
			break;
		case 6:
			army[6].add((MilitaryUnit) new PlasmaCannon(armor, attack));
			break;
		}
		
	}
	
	private void newMilitaryUnit(int n, int unitType) throws ResourceException {
		int n_possible = checkEnoughResourcesToBuild(n, unitType);
		for (int i = 0; i<n_possible; i++) {
			final int armor = (int) (ARMOR_UNITS[unitType]*(100+technologyDefense*PLUS_ARMOR_UNITS_BY_TECHNOLOGY[unitType])/100);
			final int attack = (int) (BASE_DAMAGE_UNITS[unitType]*(100+technologyAttack*PLUS_ATTACK_UNITS_BY_TECHNOLOGY[unitType])/100);
			addMilitaryUnit(unitType, armor, attack);
			metal -= METAL_COST_UNITS[unitType];
			deuterium -= DEUTERIUM_COST_UNITS[unitType];
		}
		if (n_possible == 0) {
			throw new ResourceException("[!] Not enough resources for any "+UNIT_NAMES[unitType]+"s.");
		} else if (n_possible < n){
			throw new ResourceException("[!] Not enough resources. Only "+n_possible+" "+UNIT_NAMES[unitType]+"s have been built.");
		}
		if (Main.isConsoleMode()) {
			System.out.println(n_possible+" "+UNIT_NAMES[unitType]+" built");
		}
	}
	
	public void newLightHunter(int n) throws ResourceException {
		newMilitaryUnit(n, 0);
	}
	
	public void newHeavyHunter(int n) throws ResourceException {
		newMilitaryUnit(n, 1);
	}
	
	public void newBattleship(int n) throws ResourceException {
		newMilitaryUnit(n, 2);
	}
	
	public void newArmoredShip(int n) throws ResourceException {
		newMilitaryUnit(n, 3);
	}
	
	public void newMissileLauncher(int n) throws ResourceException {
		newMilitaryUnit(n, 4);
	}
	
	public void newIonCannon(int n) throws ResourceException {
		newMilitaryUnit(n, 5);
	}
	
	public void newPlasmaCannon(int n) throws ResourceException {
		newMilitaryUnit(n, 6);
	}
	
	public String getStats() {
		String stats = "Planet Stats:\n\n";
		stats += "TECHNOLOGY\n\n";
		stats += String.format(PLANET_STATS_FORMAT, "Attack Technology", technologyAttack)+"\n";
		stats += String.format(PLANET_STATS_FORMAT, "Defense Technology", technologyDefense)+"\n\n";
		stats += "DEFENSES\n\n";
		stats += String.format(PLANET_STATS_FORMAT, UNIT_NAMES[4], army[4].size())+"\n";
		stats += String.format(PLANET_STATS_FORMAT, UNIT_NAMES[5], army[5].size())+"\n";
		stats += String.format(PLANET_STATS_FORMAT, UNIT_NAMES[6], army[6].size())+"\n\n";
		stats += "FLEET\n\n";
		stats += String.format(PLANET_STATS_FORMAT, UNIT_NAMES[0], army[0].size())+"\n";
		stats += String.format(PLANET_STATS_FORMAT, UNIT_NAMES[1], army[1].size())+"\n";
		stats += String.format(PLANET_STATS_FORMAT, UNIT_NAMES[2], army[2].size())+"\n";
		stats += String.format(PLANET_STATS_FORMAT, UNIT_NAMES[3], army[3].size())+"\n\n";
		stats += "RESOURCES\n\n";
		stats += String.format(PLANET_STATS_FORMAT_RESOURCES, "Metal", metal)+"\n";
		stats += String.format(PLANET_STATS_FORMAT_RESOURCES, "Deuterium", deuterium)+"\n";
		return stats;
	}
}
