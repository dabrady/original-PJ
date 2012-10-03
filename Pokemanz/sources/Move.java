import java.util.*;

public abstract class Move{ 
	public String name; //name of move
	public Type type; //type of the move
	public int damage_category; //0 for physical, 1 for special, 2 for status
	public int effect; // integer represents potential secondary effect; used in applyEffect()
	public int pp_base; //base times move can be used
	public int pp_max; //max value PP can be raised to
	public int pp_current; //number of moves left
	public int power; //power
	public int target; // 0 for opponent, 1 for self
	public double acc; //accuracy
	public boolean incrCritHit = false; //changed to true if a move has an increased critical hit ratio
  
    /* Moves that modify stats (HP, atk, def, spatk, spdef, spd) raise or lower a stat by one level/stage per use, according to the following charts:
     *		   ________________REDUCER______________BASE_______________RAISER_______________
     * 	STAGE |  -6    -5    -4    -3    -2    -1  | 0 |  1     2     3     4     5     6   | 
     * 	FRACT.|  2/8   2/7   2/6   2/5   2/4   2/3 | 1 | 1.5    2    2.5    3    3.5    4   |
     * 	  %   |  25%   29%   33%   40%   50%   67% |100| 150%  200%  250%  300%  350%  400% |  
     *         -----------------------------------------------------------------------------
     *
     * For moves that affect Accuracy and Evasion:
     *		   ________________REDUCER______________BASE_______________RAISER_______________
     * 	STAGE |  -6    -5    -4    -3    -2    -1  | 0 |  1     2     3     4     5     6   | 
     * 	FRACT.|  3/9   3/8   3/7   3/6   3/5   3/4 | 1 | 1.33  1.67   2    2.33  2.67   3   |
     * 	  %   |  33%   38%   43%   50%   60%   75% |100| 133%  167%  200%  233%  267%  300% |  
     *         -----------------------------------------------------------------------------
     */

	public void applyEffect(Pokemon emeny) {  //battleStats: [HP, Attack, Defense, SpecAttack, SpecDefense, Speed]
		switch(this.effect) {
			case 0:  //physical damage,  no secondary effect
				break; 
			case 1:  //no physical damage, attack reducer
				break; 
			case 2:  //no physical damage, attack raiser
				break;
			case 3:  //no physical damage, drains HP (poison, LeechSeed, etc.)
			/* A poisoned Pokémon will lose 1/16 of its maximum hit points every turn in battle. Outside of battle, a poisoned Pokémon will
			* lose one HP for every four steps taken. If a poisoned Pokémon causes an opponent to faint, it will not take damage that turn.
			* A badly poisoned Pokémon will lose 1/16 of its maximum HP on the first turn, after which damage will increase by 1/16 every time damage
			* is dealt to the badly poisoned Pokémon. Switching a badly poisoned Pokémon out or a battle ending will turn the badly poisoned status
			* into normal poison. A badly poisoned Pokémon that is also under the effect of Leech Seed will have its poison damage counter, as well as
			* its Leech Seed damage, increase by 1/8 and 1/16 of the Pokémon's maximum HP each turn respectively. Haze will bring the damage taken by badly
			* poisoned Pokémon back to 1/16. Rest will remove the bad poison, but will not reset the damage counter. If a Pokémon gets badly poisoned again,
			* its damage taken will carry on from where it was when using Rest.
			*/
				break;
			case 4:  //no physical damage, puts target to Sleep
			/* Sleep lasts for a randomly chosen duration of 1 to 7 turns (1 to 3 in Stadium).
			* A Pokémon cannot move on the turn it wakes up. A Pokémon can inflict self-induced sleep using the move Rest, which will restore all
			* of the Pokémon's health and remove any other non-volatile status ailment. A disobedient Pokémon may also nap during battle.        
			* Sleeping Pokémon are vulnerable to Dream Eater. 
			*/
				break;
			default:
				break;
	    }
    }
  
	public void listStats(){
		System.out.println("TYPE: " + this.type.name);
		System.out.println("PP: " + this.pp_current + "/" + this.pp_base);
	}
}

class Growl extends Move{
	Growl() {
		this.name = "Growl";
		this.type = new Normal();
		this.power = 0;
		this.acc = 1;
		this.effect = 1;
		this.target = 0;
		this.pp_base = 40;
		this.pp_current = 40;
		this.pp_max = 64;
		this.damage_category = 2;
	}
}

class Tackle extends Move{
	Tackle() {
		this.name = "Tackle";
		this.type = new Normal();
		this.power = 50;
		this.acc = .95;
		this.effect = 0;
		this.target = 0;
		this.pp_base = 35;
		this.pp_current = 35;
		this.pp_max = 56;
		this.damage_category = 0;
	}
}

class Scratch extends Move {
	Scratch() {
		this.name = "Scratch";
		this.type = new Normal();
		this.power = 40;
		this.acc = 1;
		this.effect = 0;
		this.pp_base = 35;
		this.pp_current = 35;
		this.pp_max = 56;
		this.damage_category = 0;
	}
}

class LeechSeed extends Move {
	LeechSeed() {
		this.name = "LeechSeed";
		this.type = new Grass();
		this.power = 0;
		this.acc = .9;
		this.effect = 3;
		this.pp_base = 10;
		this.pp_current = 10;
		this.pp_max = 16;
		this.damage_category = 2;
	}
}

class VineWhip extends Move{
	VineWhip() {
		this.name = "VineWhip";
		this.type = new Grass();
		this.power = 35;
		this.acc = 1;
		this.effect = 0;
		this.pp_base = 15;
		this.pp_current = 15;
		this.pp_max = 24;
		this.damage_category = 0;
	}
}

class PoisonPowder extends Move{
	PoisonPowder() {
		this.name = "PoisonPowder";
		this.type = new Poison();
		this.power = 0;
		this.acc = .75;
		this.effect = 3;
		this.pp_base = 35;
		this.pp_current = 35;
		this.pp_max = 56;
		this.damage_category = 2;
	}
}

class SleepPowder extends Move{
	SleepPowder() {
		this.name = "SleepPowder";
		this.type = new Grass();
		this.power = 0;
		this.acc = .75;
		this.effect = 4;
		this.pp_base = 15;
		this.pp_current = 15;
		this.pp_max = 24;
		this.damage_category = 2;
	}
}

class RazorLeaf extends Move{	//has increased Crit.Hit ratio; raises crit.hit to stage 3 (+2 stages)
	RazorLeaf() {
		this.name = "RazorLeaf";
		this.type = new Grass();
		this.power = 55;
		this.acc = .95;
		this.effect = 0;
		this.pp_base = 25;
		this.pp_current = 25;
		this.pp_max = 40;
		this.damage_category = 0;
		this.incrCritHit = true;
	}
}

class SweetScent extends Move{

}

class Growth extends Move{

}

class Synthesis extends Move{

}

class SolarBeam extends Move{

}

