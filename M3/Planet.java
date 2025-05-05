package proyectoFinal;

import java.util.ArrayList;

public class Planet implements MilitaryUnit {
	private int technologyDefense;
	private int technologyAtack;
	private int metal;
	private int deuterium;
	private int upgradeDefenseTechnologyDeuteriumCost;
	private int upgradeAttackTechnologyDeuteriumCost;
	ArrayList<MilitaryUnit>[] army = new ArrayList[7];
	
	public Planet(int technologyDefense, int technologyAtack, int metal, int deuterium,
			int upgradeDefenseTechnologyDeuteriumCost, int upgradeAttackTechnologyDeuteriumCost,
			ArrayList<MilitaryUnit>[] army) {
		super();
		this.technologyDefense = technologyDefense;
		this.technologyAtack = technologyAtack;
		this.metal = metal;
		this.deuterium = deuterium;
		this.upgradeDefenseTechnologyDeuteriumCost = upgradeDefenseTechnologyDeuteriumCost;
		this.upgradeAttackTechnologyDeuteriumCost = upgradeAttackTechnologyDeuteriumCost;
		this.army = army;
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
	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void takeDamage(int receivedDamage) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getCurrentArmor() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getMetalCost() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getDeuteriumCost() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getChanceGeneratingWaste() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int getChanceAttackAgain() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void resetArmor() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
