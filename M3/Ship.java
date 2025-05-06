package proyectoFinal;

public abstract class Ship implements Variables, MilitaryUnit{
	int armor;
	int initialArmor;
	int baseDamage;
}

public class LightHunter extends Ship{
	
	public LightHunter(int armor, int baseDamage) {
		super();
		armor=Ship.ARMOR_LIGTHHUNTER+(tecnologiadefensa*Ship.PLUS_ARMOR_LIGTHHUNTER_BY_TECHNOLOGY/Ship.ARMOR_LIGTHHUNTER);
		baseDamage=Ship.BASE_DAMAGE_LIGTHHUNTER+(tecnologiaataque*Ship.PLUS_ATTACK_LIGTHHUNTER_BY_TECHNOLOGY/Ship.BASE_DAMAGE_LIGTHHUNTER);
	}
	public LightHunter() {
		armor=Ship.ARMOR_LIGTHHUNTER;
		baseDamage=Ship.BASE_DAMAGE_LIGTHHUNTER;
		
	}

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return baseDamage;
	}

	@Override
	public void takeDamage(int receivedDamage) {
		armor-=receivedDamage;
		
	}

	@Override
	public int getActualArmor() {
		// TODO Auto-generated method stub
		return armor;
	}

	@Override
	public int getMetalCost() {
		// TODO Auto-generated method stub
		return Ship.METAL_COST_LIGTHHUNTER;
	}

	@Override
	public int getDeuteriumCost() {
		// TODO Auto-generated method stub
		return Ship.DEUTERIUM_COST_LIGTHHUNTER;
	}

	@Override
	public int getChanceGeneratingWaste() {
		// TODO Auto-generated method stub
		return Ship.CHANCE_GENERATNG_WASTE_LIGTHHUNTER;
	}

	@Override
	public int getChangeAttackAgain() {
		// TODO Auto-generated method stub
		return Ship.CHANCE_ATTACK_AGAIN_LIGTHHUNTER;
	}

	@Override
	public void resetArmor() {
		armor=initialArmor;
		
	}
	
	
}

public class HeavyHunter extends Ship{
	
	public HeavyHunter(int armor, int baseDamage) {
		super();
		armor=Ship.ARMOR_HEAVYHUNTER+(tecnologiadefensa*Ship.PLUS_ARMOR_HEAVYHUNTER_BY_TECHNOLOGY/Ship.ARMOR_HEAVYHUNTER);
		baseDamage=Ship.BASE_DAMAGE_HEAVYHUNTER+(tecnologiaataque*Ship.PLUS_ATTACK_HEAVYHUNTER_BY_TECHNOLOGY/Ship.BASE_DAMAGE_HEAVYHUNTER);
	}
	public HeavyHunter() {
		armor=Ship.ARMOR_HEAVYHUNTER;
		baseDamage=Ship.BASE_DAMAGE_HEAVYHUNTER;
		
	}

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return baseDamage;
	}

	@Override
	public void takeDamage(int receivedDamage) {
		armor-=receivedDamage;
		
	}

	@Override
	public int getActualArmor() {
		// TODO Auto-generated method stub
		return armor;
	}

	@Override
	public int getMetalCost() {
		// TODO Auto-generated method stub
		return Ship.METAL_COST_HEAVYHUNTER;
	}

	@Override
	public int getDeuteriumCost() {
		// TODO Auto-generated method stub
		return Ship.DEUTERIUM_COST_HEAVYHUNTER;
	}

	@Override
	public int getChanceGeneratingWaste() {
		// TODO Auto-generated method stub
		return Ship.CHANCE_GENERATNG_WASTE_HEAVYHUNTER;
	}

	@Override
	public int getChangeAttackAgain() {
		// TODO Auto-generated method stub
		return Ship.CHANCE_ATTACK_AGAIN_HEAVYHUNTER;
	}

	@Override
	public void resetArmor() {
		armor=initialArmor;
		
	}
	
}

public class BattleShip extends Ship {
	
	public BattleShip(int armor, int baseDamage) {
		super();
		armor=Ship.ARMOR_BATTLESHIP+(tecnologiadefensa*Ship.PLUS_ARMOR_BATTLESHIP_BY_TECHNOLOGY/Ship.ARMOR_BATTLESHIP);
		baseDamage=Ship.BASE_DAMAGE_BATTLESHIP+(tecnologiaataque*Ship.PLUS_ATTACK_BATTLESHIP_BY_TECHNOLOGY/Ship.BASE_DAMAGE_BATTLESHIP);
	}
	public BattleShip() {
		armor=Ship.ARMOR_BATTLESHIP;
		baseDamage=Ship.BASE_DAMAGE_BATTLESHIP;
		
	}

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return baseDamage;
	}

	@Override
	public void takeDamage(int receivedDamage) {
		armor-=receivedDamage;
		
	}

	@Override
	public int getActualArmor() {
		// TODO Auto-generated method stub
		return armor;
	}

	@Override
	public int getMetalCost() {
		// TODO Auto-generated method stub
		return Ship.METAL_COST_BATTLESHIP;
	}

	@Override
	public int getDeuteriumCost() {
		// TODO Auto-generated method stub
		return Ship.DEUTERIUM_COST_BATTLESHIP;
	}

	@Override
	public int getChanceGeneratingWaste() {
		// TODO Auto-generated method stub
		return Ship.CHANCE_GENERATNG_WASTE_BATTLESHIP;
	}

	@Override
	public int getChangeAttackAgain() {
		// TODO Auto-generated method stub
		return Ship.CHANCE_ATTACK_AGAIN_BATTLESHIP;
	}

	@Override
	public void resetArmor() {
		armor=initialArmor;
		
	}
	
}

public class ArmoredShip extends Ship {
	
	public ArmoredShip(int armor, int baseDamage) {
		super();
		armor=Ship.ARMOR_ARMOREDSHIP+(tecnologiadefensa*Ship.PLUS_ARMOR_ARMOREDSHIP_BY_TECHNOLOGY/Ship.ARMOR_ARMOREDSHIP);
		baseDamage=Ship.BASE_DAMAGE_ARMOREDSHIP+(tecnologiaataque*Ship.PLUS_ATTACK_ARMOREDSHIP_BY_TECHNOLOGY/Ship.BASE_DAMAGE_ARMOREDSHIP);
	}
	public ArmoredShip() {
		armor=Ship.ARMOR_ARMOREDSHIP;
		baseDamage=Ship.BASE_DAMAGE_ARMOREDSHIP;
		
	}

	@Override
	public int attack() {
		// TODO Auto-generated method stub
		return baseDamage;
	}

	@Override
	public void takeDamage(int receivedDamage) {
		armor-=receivedDamage;
		
	}

	@Override
	public int getActualArmor() {
		// TODO Auto-generated method stub
		return armor;
	}

	@Override
	public int getMetalCost() {
		// TODO Auto-generated method stub
		return Ship.METAL_COST_ARMOREDSHIP;
	}

	@Override
	public int getDeuteriumCost() {
		// TODO Auto-generated method stub
		return Ship.DEUTERIUM_COST_ARMOREDSHIP;
	}

	@Override
	public int getChanceGeneratingWaste() {
		// TODO Auto-generated method stub
		return Ship.CHANCE_GENERATNG_WASTE_ARMOREDSHIP;
	}

	@Override
	public int getChangeAttackAgain() {
		// TODO Auto-generated method stub
		return Ship.CHANCE_ATTACK_AGAIN_ARMOREDSHIP;
	}

	@Override
	public void resetArmor() {
		armor=initialArmor;
		
	}
	
}