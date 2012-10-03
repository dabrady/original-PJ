//package pokemanz.misc;
import java.lang.reflect.*;
import java.util.*;

/* 
 * This abstract class models a Pokemon, with each extended class representing a different species of Pokemon.
 */

public abstract class Pokemon{
	//declare variables to be inherited
	public static final Random rand = new Random();
	public String gender;
	public String species;
	public String evolvedForm;
	public Type type1; //primary type object (define class type)
	public Type type2; //secondary type object
	public HashMap<Double, Move> learnSet = new HashMap<Double, Move>(14, 1f);
	public int evolveAt;
	public int[] baseStats = new int[6];
	public int[] indivStats = new int[6];
	public int[] battleStats = new int[6];
	public String name;
	public String status = "Normal";
	public Item pouch = null; //spot for held-item
	public Trainer owner;  //Null for wild Pokemon
	public ArrayList<Move> moves = new ArrayList<Move>(0);
	public double level; //experience level. Once level 100 has been reached, a Pokemon can no longer level up.
	public int xp; //experience points. Once level 100 has been reached, a Pokemon can no longer gain XP.	
	
	//Critical hit ratio, same for all Pokemon, modified by certain moves or items
	public double RATIO = .625;
	public final int CRIT_HIT = (rand.nextDouble() + .001 > RATIO)? 1: 2;	
	
	Pokemon(){
		this.gender = (this.rand.nextInt(2) == 0)? "male": "female";
		this.status = null;
		this.level = 1.;
		this.xp = 0;		
	}
	
	//declare abstract methods to be inherited and defined	
		
	//instance method(s) inherited by all Pokemon
	public void setName(String name){
		this.name = name;
	}
	
	public void reportType(){
		if (this.type2 == null) System.out.println(this.getClass().getName() + "s are " + this.type1.name + " type Pokemon.");
		else System.out.println(this.getClass().getName() + "s are " + this.type1.name + "-" + this.type2.name + " type Pokemon.");
	}
	
	//returns a double to be multiplied by the attack power of the move being used
	public double getMultiplier(Pokemon p, String movetype) {
		Type type1 = p.type1;
		Type type2 = p.type2;
		Type TYPE1 = this.type1;
		Type TYPE2 = this.type2;
		double mult = 1.;
		
		
		//get multipliers for type-effectiveness
		for (int i = 0; i < 2; i++){
			Type type = (i == 0)? type1: type2;
			Type TYPE = (i == 0)? TYPE1: TYPE2;
			boolean flag = false;	
			
			//check for held item
			if (this.pouch != null){
				//do something
			}
			
			//check for STAB
			if (TYPE.name == movetype) mult *= 1.5;		
			
			for (int j = 0; j < type.weakAgainst.length; j++) {
			  if (type.weakAgainst[j].equals(movetype)) {
				//System.out.println("Pssht! Just a flesh wound!");
				mult *= 2;
				flag = true;
				break;
			  }
			}
			
			if (type2 == null) break;
			if (flag == true) continue;
			
			for (int j = 0; j < type.resistantTo.length; j++) {
			  if (type.resistantTo[j].equals(movetype)) {
				// System.out.println("Bah! 'Tis only a scratch!");
				mult *= 0.5;
				flag = true;
				break;
			  }
			}

			if (type2 == null) break;
			if (flag == true) continue;
			
			for (int j = 0; j < type.unaffectedBy.length; j++) {
			  if (type.unaffectedBy[j].equals(movetype)) {
				//System.out.println("Hehe...that tickles!");
				mult *= 0;
				break;
			  }
			}
			
			if (type2 == null) break;
		}

		return mult;
	}
	//FOR SPARTA!! attack method
	public void attack(Pokemon emeny, Move move){  //battleStats: [HP, Attack, Defense, SpecAttack, SpecDefense, Speed]
		Random rgen = new Random();
		double damage = 0., modifier = 0.;
		int level = (int) this.level;
		int attack = this.battleStats[1];
		int defense = emeny.battleStats[2];
		int base = move.power;
		double other = 1.;
		double rand = 1 - (rgen.nextInt(16) / 100);
		
		//Manage crit. hit shit
		//modify RATIO for use in calculations
		if (move.incrCritHit){
			this.RATIO = .25;
			if (this.pouch != null && this.pouch.incrCritHit)
				this.RATIO = .333;
		}else if(this.pouch != null && this.pouch.incrCritHit){
			this.RATIO = .125;
		}
		//***SOMEHOW INCORPORATE CONSUMING/USING ITEMS THAT INCREASE CRIT.HIT ***
		
		int critical = this.CRIT_HIT;
		//reset RATIO
		this.RATIO = .625;
		
		//Calculate damage
		double multiplier = this.getMultiplier(emeny, move.type.name);
		modifier = multiplier * critical * other * rand;
		damage = ( ((2 * level + 10) / 250) * (attack / defense) * base + 2) * modifier;
		damage = (damage > 1)? (int) damage: 1; //rounds down if greater than 1, else rounds up to 1
		
		System.out.println("Calculated damage: " + damage);
		
		emeny.battleStats[0] -= damage;
		move.pp_current --;
		
		//Print results
		if (critical == 2)
			System.out.println("A critical hit!");
		
		if (multiplier == 0)
			System.out.println("It didn't work! D:");
		else if (multiplier > 0 && multiplier < 2)
			System.out.println("A decent hit!");
		else if (multiplier >= 2)
			System.out.println("It's super effective! :D");
		
		System.out.println(emeny.name + "'s HP has been reduced from " + (emeny.battleStats[0] + damage) + " to " + emeny.battleStats[0] + ".");
		System.out.println(emeny.name + "'s status is: " + emeny.status);
	}
	
	public String movesToString(){
		String moves = "[";
		for (Move move : this.moves)
			moves += move.name + "   ";
		return moves.trim() + "]";
	}
		
	// RESETS MOVESET. Any modifications that have been made are removed.
	//				   Pokemon now knows (up to) the first four moves in his learnset, or (if its level is high enough) the last four it should have learned.
	public void resetMoves(){
		this.moves.clear();
		//get the levels at which the Pokemon learns a new move
		Set keyset = this.learnSet.keySet();
		double maxkey = 0;
		//find the highest level at which the Pokemon learns a new move
		for (Object x : keyset){
			if ((double)x > maxkey) maxkey = (double)x;
		}
		//Starting from the highest level at which the Pokemon learns a new move, count down. If the count is <= the Pokemon's current level AND can learn a
		//new move at that level AND its moveset is not full, the new move is added to the end of its moveset.
		for (double i = maxkey; i > 0; i -= 0.25){
			try{
				if (i <= this.level + 0.75 && this.learnSet.containsKey(i)){
					if (this.moves.size() == 4){
						break;
					}else{
						this.moves.add(0, this.learnSet.get(i));
					}
				}
			}catch(Throwable t){}
		}
		//Remove certain moves for dual-type Pokemon
	}	
	
	//Remember, move names cannot contain spaces!
	public void modMoves(Move newMove, int moveslot){
	//  Moveset has max of 4 moves; therefore, it is indexed 0 - 3.
	//  ***Allows teaching of moves that are not in the default learnset of the species.***
	//	***Also allows expansion of current moveset beyond max size. However, calling resetMoves() after using this will resize moves to 0 and reset to original moves determined by level.***
		if (moveslot > this.moves.size()){
			this.moves.add(newMove);
			// System.out.println("Moves modified. " + this.name + " has learned " + newMove.name + ".");
		}else{
			this.moves.set((moveslot - 1), newMove);
			// System.out.println("Moves modified. " + this.name + " has learned " + newMove.name + ".");
		}
	}
	
	public void setStats(int hp, int atk, int def, int satk, int sdef, int spd){	//Replenishes current stats to full power; COULD BE USED AS A TOTAL HEALING METHOD!!
    	//Assign individual stats
		this.indivStats[0] = hp;
		this.indivStats[1] = atk;
		this.indivStats[2] = def;
		this.indivStats[3] = satk;
		this.indivStats[4] = sdef;
		this.indivStats[5] = spd;
		for (int i = 0; i < indivStats.length; i++) this.battleStats[i] = this.indivStats[i];
	}	
	
	public Pokemon levelUp(){
		if (this.level < 100){
			this.level ++;
			System.out.println(this.name + " has gained enough XP to level up!\nThey are now a level " + (int)this.level + " " + this.getClass().getName() + ".");
			//Try to evolve
			Pokemon p = this;
			if (this instanceof Evolution)
				p = this.evolve();
				
			for (double i = 0; i <= 0.75; i += 0.25){  //cycles through possible moves to be learned at this level (accounts for multiple moves learned)
				double level = p.level + i;
				Move newMove = p.learnSet.get(level);
				while (newMove != null){
					Scanner m = new Scanner(System.in);
					System.out.println(p.name + " is trying to learn " + newMove.name + ". Will you let them? Y/N");  
					
					//List stats of newMove
					newMove.listStats();
					
					String choice = m.nextLine();
					
					if (choice.toUpperCase().equals("Y")){
						if (p.moves.size() < 4){
							p.modMoves(newMove, p.moves.size() + 1);
							System.out.println(p.name + " now knows:\t" + p.movesToString() + "\n");
							break;
						}else{
							System.out.println("Uh oh! " + p.name + " already knows four moves. Would you like to replace one with " + newMove.name + "? Y/N");
							choice = m.nextLine();
							if (choice.toUpperCase().equals("Y")){
								while(true){
									System.out.println("They know:    " + p.movesToString() + "\n");
									System.out.println("Which move would you like to replace? Enter a number between 1 and 4. ");
									int oldMove;
									try{
										oldMove = Integer.parseInt(m.nextLine());
									}catch(Throwable t){
										System.out.println("Sorry, that's not an option.\n");
										continue;
									}
									if (oldMove <= 4 && oldMove > 0){
										p.modMoves(newMove, oldMove);
										System.out.println(p.name + " now knows:    " + p.movesToString() + "\n");
										break;
									}else{
										System.out.println("Sorry, that's not an option.\n");
										continue;
									}
								}
							}else{
								System.out.println("Are you sure? Y/N");
								choice = m.nextLine();
								if (choice.toUpperCase().equals("Y")){
									System.out.println("Well, if you're sure...let's hope you don't regret it!");
									break;
								}else{
									continue;
								}
							}
							break;
						}
					}else{
						System.out.println("Are you sure? Y/N");
						choice = m.nextLine();
						if (choice.toUpperCase().equals("Y")){
							System.out.println("Well, if you're sure...let's hope you don't regret it!");
							break;
						}else{
							continue;
						}
					}
				}
			}
			p.xp = 0;
			return p;
		}else{
			System.out.println("Your Pokemon has already reached the highest level attainable!");
			this.xp = 0;
			return this;
		}
	}
	
	//pointless method needed to make levelUp() work properly
	public Pokemon evolve(){
		return this;
	}
	
	//static method(s) inherited by all Pokemon
	
	/* The problem with the createNew methods is that even though it creates a new instance of a class, it returns it as a generic Object.
	 * The program would then have to cast this return-type to the type of object that was created (i.e. Bulbasaur b = (Bulbasaur) createNew("Bulbasaur")),
	 * which effectively defeats the purpose of having a method that can create an object whose type is not known at compile time.
	 */
	 
	//given the name of a pokemon, create a new object of that type; not sure about the practicality of it, but could be useful somewhere
	public static Object createNew(String species){
		//create pokemon of given species
		Object newPokemon = new Object();
		try{
		//block of code used to create a new object that is an instance of the specified class
			Class<?> cls = Class.forName(species);  //get class of specified name
			Class<?>[] partypes = new Class<?>[0];  //create a list of the parameters needed by the constructor for this class
			Constructor ctor = cls.getConstructor(partypes); //get the constructor within the class that takes the specified parameters
			Object[] arglist = new Object[0];  //create a list of arguments to be passed to the constructor we just got
			newPokemon = ctor.newInstance(arglist);  //create a new object that is an instance of the specified class			
		}catch(Throwable t){	//catch any errors and print error message
			System.err.println("No.");		
		}
		return newPokemon;
	}
	
	//given the Pokedex number of a pokemon, create a new object of that type
	public static Object createNew(int dexnum){
		//create pokemon of given species
		Dex pokedex = new Dex(1);
		Object newPokemon = new Object();
		String species = (String)pokedex.b.get(dexnum);
		try{
		//block of code used to create a new object that is an instance of the specified class
			Class<?> cls = Class.forName(species);  //get class of specified name
			Class<?>[] partypes = new Class<?>[0];  //create a list of the parameters needed by the constructor for this class
			Constructor ctor = cls.getConstructor(partypes); //get the constructor within the class that takes the specified parameters
			Object[] arglist = new Object[0];  //create a list of arguments to be passed to the constructor we just got
			newPokemon = ctor.newInstance(arglist);  //create a new object that is an instance of the specified class			
		}catch(Throwable t){	//catch any errors and print error message
			System.out.println("No.");
			System.err.println(t);
		}
		return newPokemon;
	}
}

/***********************************************************************************************************************************************************************************************/
/***********************************************************************************************************************************************************************************************/
/***********************************************************************************************************************************************************************************************/

/*
 * The following 150+ classes model individual species of Pokemon.
 * Those that implement the Evolution interface have the ability to evolve. Those that don't are the final evolutionary form of a species.
 */

class Bulbasaur extends Pokemon implements Evolution{
 	
	public Bulbasaur(){   //takes no args, creates Bulbasaur with indivStats and battleStats equal to baseStats
		//Define inherited variables
		this.species = "Bulbasaur";
		this.name = this.species;
		this.owner = null;
		this.type1 = new Grass();
		this.type2 = new Poison();
		this.evolveAt = 16;
		this.evolvedForm = "Ivysaur";

		//Assign potential moves to learnset; new moves acquired as Pokemon levels up
		this.learnSet.put(new Double(1.), new Tackle()); 											//start
		this.learnSet.put(new Double(4.), new Growl());  											//level 4
		this.learnSet.put(new Double(7.), new LeechSeed());										    //level 7
		this.learnSet.put(new Double(10.), new VineWhip());  //gets same-type attack bonus          //level 10
		this.learnSet.put(new Double(15.), new PoisonPowder());									    //level 15
		this.learnSet.put(new Double(15.25), new SleepPowder());									//level 15
		this.learnSet.put(new Double(20.), new RazorLeaf());  //gets same-type attack bonus		    //level 20
		this.learnSet.put(new Double(25.), new SweetScent());									    //level 25
		this.learnSet.put(new Double(32.), new Growth());											//level 32
		this.learnSet.put(new Double(39.), new Synthesis());										//level 39
		this.learnSet.put(new Double(46.), new SolarBeam());  //gets same-type attack bonus		    //level 46
				
		//Assign level-based moves to moveset
		this.resetMoves();
		
		//Assign baseStats in prearranged order: HP, Attack, Defense, Speed
		this.baseStats[0] = 45;
		this.baseStats[1] = 49;
		this.baseStats[2] = 49;
		this.baseStats[3] = 65;
		this.baseStats[4] = 65;
		this.baseStats[5] = 45;
		
		//Assign indivStats and battleStats to baseStats
		for (int i = 0; i < 6; i++){
			this.indivStats[i] = this.baseStats[i];  //RANDOMIZE INDIVIDUAL STATS
			this.battleStats[i] = this.baseStats[i];
		}
	}
	
	public Bulbasaur(int hp, int atk, int def, int satk, int sdef, int spd){  //takes 4 ints, creates Bulbasaur and assigns them to indivStats, then equates battleStats to indivStats
		this();  //call no-arg constructor
		this.setStats(hp, atk, def, satk, sdef, spd);
	}
	
	//ISSUE: returns Ivysaur as generic Pokemon, but Pokemon is an abstract class. Also, the way evolve is called, there is no way to know what Pokemon will be returned,
	//		 so how can it be used?
	public Pokemon evolve(){
		if ((int)this.level == this.evolveAt){		//varies between species
			System.out.println("\nYour " + this.species + " is evolving!");
			Ivysaur evol = new Ivysaur();
			evol.level = this.level;
			evol.owner = this.owner;
			evol.gender = this.gender;
			
			//create a copy of Bulbasaur's moves to be transfered to the new Pokemon
			Move[] moveset = new Move[this.moves.size()];
			for (int i = 0; i < this.moves.size(); i++){
				moveset[i] = this.moves.get(i);
			}
			
			//set newly evolved Pokemon's moves the same as previous evolutionary form
			for (int i = 0; i < moveset.length; i++){
				evol.modMoves(moveset[i], i + 1);
			}
			
			while(true){
				Scanner m = new Scanner(System.in);
				System.out.println(this.name + " is now a(n) " + evol.species + "!");
				System.out.println("Would you like to rename your new " + evol.species + "? Y/N");
				String choice = m.nextLine().toUpperCase();
				if (choice.equals("Y")){
					System.out.print("Enter a new name for your Pokemon: ");
					String name = m.nextLine().trim();
					evol.setName(name);  //give it a new name
					System.out.println("Your " + evol.species + " is now named " + evol.name + ".");
					break;
				}else{
					System.out.println("Are you sure? Y/N");
					choice = m.nextLine().toUpperCase();
					if (choice.equals("Y")){
						System.out.println("Okay. Your " + evol.species + " will remain " + this.name + ".");
						evol.setName(this.name);
						break;
					}else{
						continue;
					}
				}
			}
			return evol;
		}
		return this;
	}
}

/***********************************************************************************************************************************************************************************************/

class Ivysaur extends Pokemon implements Evolution{
	
	public Ivysaur(){  //takes no args, creates Ivysaur with indivStats and battleStats equal to baseStats
		//Define inherited variables
		this.species = "Ivysaur";
		this.name = this.species;
		this.owner = null;
		this.type1 = new Grass();
		this.type2 = new Poison();
		this.evolveAt = 32;
		this.evolvedForm = "Venusaur";
		//Assign potential moves to learnset; new moves acquired as Pokemon levels up
		this.learnSet.clear();  //removes learnset of previously evolved form
		this.learnSet.put(new Double(1.), new Tackle()); 											//start
		this.learnSet.put(new Double(1.25), new Growl());  											//start
		this.learnSet.put(new Double(1.5), new LeechSeed());										//start
		this.learnSet.put(new Double(10.), new VineWhip());  //gets same-type attack bonus         //level 10
		this.learnSet.put(new Double(15.), new PoisonPowder());									//level 15
		this.learnSet.put(new Double(15.25), new SleepPowder());									//level 15
		this.learnSet.put(new Double(22.), new RazorLeaf());  //gets same-type attack bonus		//level 22
		this.learnSet.put(new Double(29.), new SweetScent());									    //level 29
		this.learnSet.put(new Double(38.), new Growth());											//level 38
		this.learnSet.put(new Double(47.), new Synthesis());										//level 47
		this.learnSet.put(new Double(56.), new SolarBeam());  //gets same-type attack bonus		//level 56
		
		//Assign level-based moves to moveset
		this.resetMoves();
		
		//Assign baseStats in prearranged order: HP, Attack, Defense, Speed
		this.baseStats[0] = 60;
		this.baseStats[1] = 62;
		this.baseStats[2] = 63;
		this.baseStats[3] = 80;
		this.baseStats[4] = 80;
		this.baseStats[5] = 60;
		
		//Assign indivStats and battleStats to baseStats
		for (int i = 0; i < 6; i++){
			this.indivStats[i] = this.baseStats[i];  //RANDOMIZE INDIVIDUAL STATS
			this.battleStats[i] = this.baseStats[i];
		}
	}
	
	public Ivysaur	(int hp, int atk, int def, int satk, int sdef, int spd){  //takes 4 ints, creates Ivysaur and assigns them to indivStats, then equates battleStats to indivStats
		this();
		this.setStats(hp, atk, def, satk, sdef, spd);
	}
		
	public Pokemon evolve(){
		if ((int)this.level == this.evolveAt){		//varies between species
			System.out.println("\nYour " + this.species + " is evolving!");
			Venusaur evol = new Venusaur();
			evol.level = this.level;
			evol.owner = this.owner;
			evol.gender = this.gender;
			
			//create a copy of Bulbasaur's moves to be transfered to the new Pokemon
			Move[] moveset = new Move[this.moves.size()];
			for (int i = 0; i < this.moves.size(); i++){
				moveset[i] = this.moves.get(i);
			}
			
			//set newly evolved Pokemon's moves the same as previous evolutionary form
			for (int i = 0; i < moveset.length; i++){
				evol.modMoves(moveset[i], i + 1);
			}
			
			while(true){
				Scanner m = new Scanner(System.in);
				System.out.println(this.name + " is now a(n) " + evol.species + "!");
				System.out.println("Would you like to rename your new " + evol.species + "? Y/N");
				String choice = m.nextLine().toUpperCase();
				if (choice.equals("Y")){
					System.out.print("Enter a new name for your Pokemon: ");
					String name = m.nextLine().trim();
					evol.setName(name);  //give it a new name
					break;
				}else{
					System.out.println("Are you sure? Y/N");
					choice = m.nextLine().toUpperCase();
					if (choice.equals("Y")){
						System.out.println("Okay. Your " + evol.species + " will remain " + this.name + ".");
						evol.setName(this.name);
						break;
					}else{
						continue;
					}
				}
			}
			System.out.println("Your " + evol.species + " is now named " + evol.name + ".");
			return evol;
		}
		return this;
	}
}

/***********************************************************************************************************************************************************************************************/

class Venusaur extends Pokemon{
	//Possible example of call to move(): Move move1 = new Move("tackle", 60, 95)
	
	public Venusaur(){  //takes no args, creates Venusaur with indivStats and battleStats equal to baseStats
		//Define inherited variables
		this.species = "Venusaur";
		this.name = this.species;
		this.type1 = new Grass();
		this.type2 = new Poison();
		//Assign potential moves to learnset; new moves acquired as Pokemon levels up
		this.learnSet.clear();  //removes learnset of previously evolved form
		this.learnSet.put(new Double(1.), new Tackle()); 											//start
		this.learnSet.put(new Double(1.25), new Growl());  											//start
		this.learnSet.put(new Double(1.5), new LeechSeed());										//start
		this.learnSet.put(new Double(1.75), new VineWhip());  //gets same-type attack bonus        //start
		this.learnSet.put(new Double(15.), new PoisonPowder());									//level 15
		this.learnSet.put(new Double(15.25), new SleepPowder());									//level 15
		this.learnSet.put(new Double(22.), new RazorLeaf());  //gets same-type attack bonus		//level 22
		this.learnSet.put(new Double(29.), new SweetScent());									    //level 29
		this.learnSet.put(new Double(41.), new Growth());											//level 41
		this.learnSet.put(new Double(53.), new Synthesis());										//level 53
		this.learnSet.put(new Double(65.), new SolarBeam());  //gets same-type attack bonus	    //level 65
				
		//Assign level-based moves to moveset
		this.resetMoves();
				
		//Assign baseStats in prearranged order: HP, Attack, Defense, Speed
		this.baseStats[0] = 80;
		this.baseStats[1] = 82;
		this.baseStats[2] = 83;
		this.baseStats[3] = 80;
		this.baseStats[4] = 80;
		this.baseStats[5] = 80;
		
		//Assign indivStats and battleStats to baseStats
		for (int i = 0; i < 6; i++){
			this.indivStats[i] = this.baseStats[i];  //RANDOMIZE INDIVIDUAL STATS
			this.battleStats[i] = this.baseStats[i];
		}
	}
	    			
	public Venusaur(int hp, int atk, int def, int satk, int sdef, int spd){  //takes 4 ints, creates Venusaur and assigns them to indivStats, then equates battleStats to indivStats
		this();
		this.setStats(hp, atk, def, satk, sdef, spd);
	}
}

/***********************************************************************************************************************************************************************************************/
/***********************************************************************************************************************************************************************************************/
/***********************************************************************************************************************************************************************************************/
//INTERFACES

interface Evolution{
	//methods
	Pokemon evolve();
}