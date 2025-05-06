package proyectoFinal;

public abstract class Ship implements Variables, MilitaryUnit{
	private int armor;
	private int initialArmor;
	private int baseDamage;
	public Ship(int armor, int baseDamage) {
		super();
		this.armor = armor;
		this.initialArmor = armor;
		this.baseDamage = baseDamage;
	}
	public Ship() {}
	
	public int getArmor() {
		return armor;
	}
	public void setArmor(int armor) {
		this.armor = armor;
	}
	public int getInitialArmor() {
		return initialArmor;
	}
	public void setInitialArmor(int initialArmor) {
		this.initialArmor = initialArmor;
	}
	public int getBaseDamage() {
		return baseDamage;
	}
	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}
	
}

class LightHunter extends Ship{
	
	public LightHunter(int armor, int baseDamage) {
		super(armor, baseDamage);
		
	}
	public LightHunter() {
		super();
		super.setArmor(ARMOR_LIGTHHUNTER);
		super.setBaseDamage(BASE_DAMAGE_LIGTHHUNTER);
	}

	public int attack() {
		return super.getBaseDamage();
	}

	public void takeDamage(int receivedDamage) {
		super.setArmor(super.getArmor() - receivedDamage);
	}

	public int getCurrentArmor() {
		return super.getArmor();
	}

	public int getMetalCost() {
		return METAL_COST_LIGTHHUNTER;
	}

	public int getDeuteriumCost() {
		return DEUTERIUM_COST_LIGTHHUNTER;
	}

	public int getChanceGeneratingWaste() {
		return CHANCE_GENERATNG_WASTE_LIGTHHUNTER;
	}
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_LIGTHHUNTER;
	}
	
	public void resetArmor() {
		super.setArmor(super.getInitialArmor());
	}
	
}

class HeavyHunter extends Ship{
	
	public HeavyHunter(int armor, int baseDamage) {
		super(armor, baseDamage);
		
	}
	public HeavyHunter() {
		super();
		super.setArmor(ARMOR_HEAVYHUNTER);
		super.setBaseDamage(BASE_DAMAGE_HEAVYHUNTER);
	}

	public int attack() {
		return super.getBaseDamage();
	}

	public void takeDamage(int receivedDamage) {
		super.setArmor(super.getArmor() - receivedDamage);
	}

	public int getCurrentArmor() {
		return super.getArmor();
	}

	public int getMetalCost() {
		return METAL_COST_HEAVYHUNTER;
	}

	public int getDeuteriumCost() {
		return DEUTERIUM_COST_HEAVYHUNTER;
	}

	public int getChanceGeneratingWaste() {
		return CHANCE_GENERATNG_WASTE_HEAVYHUNTER;
	}
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_HEAVYHUNTER;
	}
	
	public void resetArmor() {
		super.setArmor(super.getInitialArmor());
	}
	
}

class Battleship extends Ship{
	
	public Battleship(int armor, int baseDamage) {
		super(armor, baseDamage);
		
	}
	public Battleship() {
		super();
		super.setArmor(ARMOR_BATTLESHIP);
		super.setBaseDamage(BASE_DAMAGE_BATTLESHIP);
	}

	public int attack() {
		return super.getBaseDamage();
	}

	public void takeDamage(int receivedDamage) {
		super.setArmor(super.getArmor() - receivedDamage);
	}

	public int getCurrentArmor() {
		return super.getArmor();
	}

	public int getMetalCost() {
		return METAL_COST_BATTLESHIP;
	}

	public int getDeuteriumCost() {
		return DEUTERIUM_COST_BATTLESHIP;
	}

	public int getChanceGeneratingWaste() {
		return CHANCE_GENERATNG_WASTE_BATTLESHIP;
	}
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_BATTLESHIP;
	}
	
	public void resetArmor() {
		super.setArmor(super.getInitialArmor());
	}
	
}

class ArmoredShip extends Ship{
	
	public ArmoredShip(int armor, int baseDamage) {
		super(armor, baseDamage);
		
	}
	public ArmoredShip() {
		super();
		super.setArmor(ARMOR_ARMOREDSHIP);
		super.setBaseDamage(BASE_DAMAGE_ARMOREDSHIP);
	}

	public int attack() {
		return super.getBaseDamage();
	}

	public void takeDamage(int receivedDamage) {
		super.setArmor(super.getArmor() - receivedDamage);
	}

	public int getCurrentArmor() {
		return super.getArmor();
	}

	public int getMetalCost() {
		return METAL_COST_ARMOREDSHIP;
	}

	public int getDeuteriumCost() {
		return DEUTERIUM_COST_ARMOREDSHIP;
	}

	public int getChanceGeneratingWaste() {
		return CHANCE_GENERATNG_WASTE_ARMOREDSHIP;
	}
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_ARMOREDSHIP;
	}
	
	public void resetArmor() {
		super.setArmor(super.getInitialArmor());
	}
	
}