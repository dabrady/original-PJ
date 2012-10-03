
//Attack method

public void attack(Pokemon emeny, Move move){  //battleStats: [HP, Attack, Defense, SpecAttack, SpecDefense, Speed]
	Random rgen = new Random();
	double damage = 0., modifier = 0.;
	int level = (int) this.level;
	int attack = this.battleStats[1];
	int defense = enemy.battleStats[2];
	int base = move.power;
	int critical = (rgen.nextInt(100) + 1 > 6)? 1: 2;
	double other = 1.;
	double rand = 1 - (rgen.nextInt(16) / 100);
	
	double modifier = this.getMultiplier(emeny, move.type.name) * critical * other * rand;
	damage = ( ((2 * level + 10) / 250) * (attack / defense) * base + 2) * modifier;
	damage = (damage > 1)? (int) damage: 1; //rounds down if greater than 1, else rounds up to 1
	
	emeny.battleStats[0] -= damage;
}