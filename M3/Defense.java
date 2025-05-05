package proyectoFinal;

abstract public class Defense {
	private int armor;
	private int initialArmor;
	private int baseDamage;
	public Defense(int armor, int baseDamage) {
		super();
		this.armor = armor;
		this.initialArmor = armor;
		this.baseDamage = baseDamage;
	}
	
}

class MissileLauncher extends Defense {

	public MissileLauncher(int armor, int baseDamage) {
		super(armor, baseDamage);
	}
	
}

class IonCannon extends Defense {

	public IonCannon(int armor, int baseDamage) {
		super(armor, baseDamage);
	}
	
}

class PlasmaCannon extends Defense {

	public PlasmaCannon(int armor, int baseDamage) {
		super(armor, baseDamage);
	}
	
}