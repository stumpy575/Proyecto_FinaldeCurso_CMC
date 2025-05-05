package proyectoFinal;

abstract public class Defense implements MilitaryUnit, Variables {
	private int armor;
	private int initialArmor;
	private int baseDamage;
	public Defense(int armor, int baseDamage) {
		super();
		this.armor = armor;
		this.initialArmor = armor;
		this.baseDamage = baseDamage;
	}
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

class MissileLauncher extends Defense {

	public MissileLauncher(int armor, int baseDamage) {
		super(armor, baseDamage);
	}

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return super.getBaseDamage();
	}

	@Override
	public void takeDamage(int receivedDamage) {
		super.setArmor(super.getArmor() - receivedDamage);
	}

	@Override
	public int getCurrentArmor() {
		return super.getArmor();
	}

	@Override
	public int getMetalCost() {
		return METAL_COST_MISSILELAUNCHER;
	}

	@Override
	public int getDeuteriumCost() {
		// TODO Auto-generated method stub
		return DEUTERIUM_COST_MISSILELAUNCHER;
	}

	@Override
	public int getChanceGeneratingWaste() {
		// TODO Auto-generated method stub
		return CHANCE_GENERATNG_WASTE_MISSILELAUNCHER;
	}

	@Override
	public int getChanceAttackAgain() {
		// TODO Auto-generated method stub
		return CHANCE_ATTACK_AGAIN_MISSILELAUNCHER;
	}

	@Override
	public void resetArmor() {
		super.setArmor(super.getInitialArmor());
	}
	
}

class IonCannon extends Defense {

	public IonCannon(int armor, int baseDamage) {
		super(armor, baseDamage);
	}

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return super.getBaseDamage();
	}

	@Override
	public void takeDamage(int receivedDamage) {
		super.setArmor(super.getArmor() - receivedDamage);
	}

	@Override
	public int getCurrentArmor() {
		return super.getArmor();
	}

	@Override
	public int getMetalCost() {
		return METAL_COST_IONCANNON;
	}

	@Override
	public int getDeuteriumCost() {
		return DEUTERIUM_COST_IONCANNON;
	}

	@Override
	public int getChanceGeneratingWaste() {
		return CHANCE_GENERATNG_WASTE_IONCANNON;
	}

	@Override
	public int getChanceAttackAgain() {
		// TODO Auto-generated method stub
		return CHANCE_ATTACK_AGAIN_IONCANNON;
	}

	@Override
	public void resetArmor() {
		super.setArmor(super.getInitialArmor());
	}
	
}

class PlasmaCannon extends Defense {

	public PlasmaCannon(int armor, int baseDamage) {
		super(armor, baseDamage);
	}

	@Override
	public int attack() {
		return super.getBaseDamage();
	}

	@Override
	public void takeDamage(int receivedDamage) {
		super.setArmor(super.getArmor() - receivedDamage);
	}

	@Override
	public int getCurrentArmor() {
		return super.getArmor();
	}

	@Override
	public int getMetalCost() {
		return METAL_COST_PLASMACANNON;
	}

	@Override
	public int getDeuteriumCost() {
		return DEUTERIUM_COST_PLASMACANNON;
	}

	@Override
	public int getChanceGeneratingWaste() {
		return CHANCE_GENERATNG_WASTE_PLASMACANNON;
	}

	@Override
	public int getChanceAttackAgain() {
		return CHANCE_ATTACK_AGAIN_PLASMACANNON;
	}

	@Override
	public void resetArmor() {
		super.setArmor(super.getInitialArmor());
	}
	
}