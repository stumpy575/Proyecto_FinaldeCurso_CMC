package proyectoFinal;

import java.util.ArrayList;

public class Planet implements MilitaryUnit{
	int technologyDefense;
	int technologyAttack;
	int metal;
	int deuterium;
	int upgradeDefenseTEchnologyDeuteriumCost;
	int upgradeAttackTechnologyDeuteriumCost;
	ArrayList<MilitaryUnit>[] army = new ArrayList[7];
}
