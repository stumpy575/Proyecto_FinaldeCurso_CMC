package proyectoFinal;

import java.util.ArrayList;

public class Planet {
	private int technologyDefense;
	private int technologyAtack;
	private int metal;
	private int deuterium;
	private int upgradeDefenseTechnologyDeuteriumCost;
	private int upgradeAttackTechnologyDeuteriumCost;
	ArrayList<MilitaryUnit>[] army ;
	
	
	
	public Planet(int technologyDefense, int technologyAtack, int metal, int deuterium,
			int upgradeDefenseTechnologyDeuteriumCost, int upgradeAttackTechnologyDeuteriumCost) {
		super();
		this.technologyDefense = technologyDefense;
		this.technologyAtack = technologyAtack;
		this.metal = metal;
		this.deuterium = deuterium;
		this.upgradeDefenseTechnologyDeuteriumCost = upgradeDefenseTechnologyDeuteriumCost;
		this.upgradeAttackTechnologyDeuteriumCost = upgradeAttackTechnologyDeuteriumCost;
		this.army = new ArrayList[7];
	}
	
	public int getTechnologyDefense() {
		return technologyDefense;
	}
	public void setTechnologyDefense(int technologyDefense) {
		this.technologyDefense = technologyDefense;
	}
	public int getTechnologyAtack() {
		return technologyAtack;
	}
	public void setTechnologyAtack(int technologyAtack) {
		this.technologyAtack = technologyAtack;
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
	
	 void newLightHunter(int n) {
		 for (int i = 0; i<n; i++) {
			 army[0].add((MilitaryUnit) new LightHunter()); 
		 }
	 }
	 void newHeavyHunter(int n) {
		 for (int i = 0; i<n; i++) {
			 army[1].add((MilitaryUnit) new HeavyHunter()); 
		 }
	 }
	 void newBattleShip(int n) {
		 for (int i = 0; i<n; i++) {
			 army[2].add((MilitaryUnit) new BattleShip()); 
		 }
	 }
	 void newArmoredShip(int n) {
		 for (int i = 0; i<n; i++) {
			 army[3].add((MilitaryUnit) new ArmoredShip()); 
		 }
	 }
	 void newMissileLauncher(int n) {
		 for (int i = 0; i<n; i++) {
			 army[4].add((MilitaryUnit) new MissileLauncher()); 
		 }
	 }
	 void newIonCannon(int n) {
		 for (int i = 0; i<n; i++) {
			 army[5].add((MilitaryUnit) new IonCannon()); 
		 }
	 }
	 void newPlasmaCannon(int n) {
		 for (int i = 0; i<n; i++) {
			 army[1].add((MilitaryUnit) new PlasmaCannon()); 
		 }
	 }
	 Void printStats() {
		 //hay que hacer el metodo
	 }
}
