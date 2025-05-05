package proyectoFinal;

interface MilitaryUnit {
	abstract int attack();
	abstract void takeDamage(int receivedDamage);
	abstract int getCurrentArmor();
	abstract int getMetalCost();
	abstract int getDeuteriumCost();
	abstract int getChanceGeneratingWaste();
	abstract int getChanceAttackAgain();
	abstract void resetArmor();
}