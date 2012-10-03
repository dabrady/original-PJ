//package pokemanz.misc;
import java.lang.reflect.*;
import java.util.*;

/* 
 * This abstract class models a Pokemon, with each extended class representing a different species of Pokemon.
 */

//Add a gender variable? If so, must add if statements to all print statements using 'he' or 'she'. Otherwise, just use 'it'.
public abstract class Pokemon{
	//declare variables to be inherited
	String species;
	String nickname;
	Type type1; //primary type object (define class type)
	Type type2; //secondary type object
	String status;
	String pouch; //spot for held-item
	ArrayList<String> moves = new ArrayList<String>(0);
	 
	double level; //experience level. Once level 100 has been reached, a Pokemon can no longer level up.
	int xp; //experience points. Once level 100 has been reached, a Pokemon can no longer gain XP.

	int[] baseStats = new int[4];
	int[] indivStats = new int[4];
	int[] currentStats = new int[4];
		
	static HashMap<Double, String> learnSet = new HashMap<Double, String>(14, 1f);
	
	//declare abstract methods to be inherited and defined	
	protected abstract void setup();
	protected abstract void setNickname(String nickname);
	protected abstract void setMoves(double level);
	protected abstract void setStats(int hp, int atk, int def, int spd);
	protected abstract void modMoves(String newMove, int index);

    Pokemon(){}
	
	//instance method(s) inherited by all Pokemon
	public void reportType(){
		if (this.type2 == null) System.out.println(this.getClass().getName() + "s are " + this.type1.name + " type Pokemon.");
		else System.out.println(this.getClass().getName() + "s are " + this.type1.name + "-" + this.type2.name + " type Pokemon.");
	}
	
	public void levelUp(){
		if (this.level < 100){
			this.level ++;
			System.out.println(this.nickname + " has gained enough XP to level up!\nThey are now a level " + (int)this.level + " " + this.getClass().getName() + ".");
			// this.evolve((int) this.level, this.nickname);
			double level = this.level;
			String newMove = this.learnSet.get(level);
			while (newMove != null){
				Scanner m = new Scanner(System.in);
				System.out.println(this.nickname + " is trying to learn " + newMove + ". Will you let them? Y/N");  //List stats of newMove
				String choice = m.nextLine();
				
				if (choice.toUpperCase().equals("Y")){
					if (this.moves.size() < 4){
						this.modMoves(newMove, this.moves.size() + 1);
						System.out.println(this.nickname + " now knows:    " + this.arrListToString(this.moves) + "\n");
						break;
					}else{
						System.out.println("Uh oh! " + this.nickname + " already knows four moves. Would you like to replace one with " + newMove + "? Y/N");
						choice = m.nextLine();
						if (choice.toUpperCase().equals("Y")){
							while(true){
								System.out.println("Which move would you like to replace? Enter a number between 1 and 4. ");
								int move;
								try{
									move = Integer.parseInt(m.nextLine());
								}catch(Throwable t){
									System.out.println("Sorry, that's not an option.\n");
									continue;
								}
								if (move <= 4 && move > 0){
									this.modMoves(newMove, move);
									System.out.println(this.nickname + " now knows:    " + this.arrListToString(this.moves) + "\n");
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
		}else{
			System.out.println("Your Pokemon has already reached the highest level attainable!");
		}
	}
	
	//static method(s) inherited by all Pokemon
	public static Object createNew(String species){
		//create pokemon of given species
		Object newPokemon = new Object();
		try{
		//block of code used to create a new object that is an instance of the specified class
			Class cls = Class.forName(species);  //get class of specified name
			Class[] partypes = new Class[0];  //create a list of the parameters needed by the constructor for this class
			Constructor ctor = cls.getConstructor(partypes); //get the constructor within the class that takes the specified parameters
			Object[] arglist = new Object[0];  //create a list of arguments to be passed to the constructor we just got
			newPokemon = ctor.newInstance(arglist);  //create a new object that is an instance of the specified class			
		}catch(Throwable t){	//catch any errors and print error message
			System.err.println("No.");		
		}
		return newPokemon;
	}
	
	public static Object createNew(int dexnum){
		//create pokemon of given species
		Dex pokedex = new Dex(1);
		Object newPokemon = new Object();
		String species = (String)pokedex.b.get(dexnum);
		try{
		//block of code used to create a new object that is an instance of the specified class
			Class cls = Class.forName(species);  //get class of specified name
			Class[] partypes = new Class[0];  //create a list of the parameters needed by the constructor for this class
			Constructor ctor = cls.getConstructor(partypes); //get the constructor within the class that takes the specified parameters
			Object[] arglist = new Object[0];  //create a list of arguments to be passed to the constructor we just got
			newPokemon = ctor.newInstance(arglist);  //create a new object that is an instance of the specified class			
		}catch(Throwable t){	//catch any errors and print error message
			System.out.println("No.");
			System.err.println(t);
		}
		return newPokemon;
	}
	
	//ArrayList toString method()
	public String arrListToString(ArrayList<String> arr){
		String listString = "";
		for (String s : arr){
			listString += s + "    ";
		}
		return listString;
	}
	
	//test it
	public static void main(String[] args){test();}			//    <------------MAIN METHOD MAIN METHOD MAIN METHOD MAIN METHOD MAIN METHOD
	public static void test(){
		// test pokemon creation and modification of moveset
		{	// create pokekmon of given species
			Bulbasaur a = (Bulbasaur) Pokemon.createNew("Bulbasaur");
			a.setNickname("Clarence");
			System.out.println("You've created a new level " + (int)a.level + " " + a.getClass().getName() + "!");
			System.out.println("His name is " + a.nickname + ".");
			a.reportType();
			System.out.println("He knows:    " + a.arrListToString(a.moves) + "\n");
			a.setStats(68, 7, 237, 95);
			System.out.println("His base stats (HP, Atk, Def, Spd):    " + Arrays.toString(a.baseStats));
			System.out.println("His individual stats (HP, Atk, Def, Spd):    " + Arrays.toString(a.indivStats));
			System.out.println("His current stats (HP, Atk, Def, Spd):    " + Arrays.toString(a.currentStats) + "\n");
			
			a.levelUp();
			
			Trainer t = new Trainer("Ash");
			System.out.println("\nYou are a trainer now, " + t.name + ".");
			t.team[0] = a;
			System.out.println("Your team consists of: " + t.toString(t.team));
			a.level = 15;
			a.levelUp();
			
			// //modify moveset
			// a.modMoves("Thousand Years of Death", a.moves.size() + 1);
			// a.modMoves("Dynamic Entry", a.moves.size()+ 1);
			// System.out.println(a.nickname + " now knows:    " + a.arrListToString(a.moves));
			// a.setMoves(a.level);
			// System.out.println("Uh oh. The moves have been reset!");
			// System.out.println("He now knows:    " + a.arrListToString(a.moves));
						
			// //create pokemon based on given Pokedex number
			// int dexnum = 1;
			// Bulbasaur b = (Bulbasaur) Pokemon.createNew(dexnum);
			// b.setNickname("Samwise");
			// b.level = 11;
			// b.setMoves(b.level);
			// System.out.println("You've created a new level " + b.level + " " + b.getClass().getName() + "!");
			// System.out.println("His name is " + b.nickname + ".");
			// System.out.println("He knows:    " + b.arrListToString(b.moves) + "\n");			
		}
	}
}

/***********************************************************************************************************************************************************************************************/
/***********************************************************************************************************************************************************************************************/
/***********************************************************************************************************************************************************************************************/

/*
 * The following 150+ classes model individual species of Pokemon.
 */

// DON'T DUPLICATE FOR ALL POKEMON UNTIL WE'VE IRONED OUT ALL THE LITTLE DETAILS.
class Bulbasaur extends Pokemon{
	//Possible example of call to move(): Move move1 = new Move("tackle", 60, 95)
    	
	public Bulbasaur(){this.setup();}  //takes no args, creates Bulbasaur with indivStats and currentStats equal to baseStats
		
	public Bulbasaur(int hp, int atk, int def, int spd){  //takes 4 ints, creates Bulbasaur and assigns them to indivStats, then equates currentStats to indivStats
		this.setup();
		this.setStats(hp, atk, def, spd);
	}
	
	public void setup(){
		//Define inherited variables
		this.species = "Bulbasaur";
		this.nickname = this.species;
		this.type1 = new Grass();
		this.type2 = new Poison();
		this.status = null;
		this.level = 1.;
		this.xp = 0;
		//Assign potential moves to learnset; new moves acquired as Pokemon levels up
		this.learnSet.put(1., "Tackle"); 											//start
		this.learnSet.put(1.25, "Growl");  											//level 3
		this.learnSet.put(7., "Leach Seed");										//level 7
		this.learnSet.put(9., "Vine Whip");  //gets same-type attack bonus        //level 9
		this.learnSet.put(13., "Poison Powder");									//level 13
		this.learnSet.put(13.25, "Sleep Powder");									//level 13
		this.learnSet.put(15., "Takedown");										//level 15
		this.learnSet.put(19., "Razor Leaf");  //gets same-type attack bonus		//level 19
		this.learnSet.put(21., "Sweet Scent");									//level 21
		this.learnSet.put(25., "Growth");											//level 25
		this.learnSet.put(27., "Double-Edge");									//level 27
		this.learnSet.put(31., "Worry Seed");										//level 31
		this.learnSet.put(33., "Synthesis");										//level 33
		this.learnSet.put(37., "Seed Bomb");  //gets same-type attack bonus		//level 37
		
		// this.setMoves(this.level);
		
		//Assign baseStats in prearranged order: HP, Attack, Defense, Speed
		this.baseStats[0] = 45;
		this.baseStats[1] = 49;
		this.baseStats[2] = 49;
		this.baseStats[3] = 45;
		
		//Assign indivStats and currentStats to baseStats
		for (int i = 0; i < 4; i++){
			this.indivStats[i] = this.baseStats[i];  //RANDOMIZE INDIVIDUAL STATS
			this.currentStats[i] = this.baseStats[i];
		}
	}
	
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	
	// RESETS MOVESET. Any modifications that have been made are removed.
	//				   Pokemon now knows (up to) the first four moves in his learnset, or (if its level is high enough) the last four it should have learned.
	public void setMoves(double level){
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
						this.moves.add(0, (String) this.learnSet.get(i));
					}
				}
			}catch(Throwable t){}
		}
		//Remove certain moves for dual-type Pokemon
	}
	
	public void modMoves(String newMove, int moveslot){
	//  Moveset has max of 4 moves; therefore, it is indexed 0 - 3.
	//  ***Allows teaching of moves that are not in the default learnset of the species.***
	//	***Also allows expansion of current moveset beyond max size. However, calling setMoves() after using this will resize moves to 0 and reset to original moves determined by level.***
		if (moveslot > this.moves.size()){
			this.moves.add(newMove);
			System.out.println("Moves modified. " + this.nickname + " has learned " + newMove + ".");
		}else{
			String oldMove = this.moves.get(moveslot - 1);
			this.moves.set((moveslot - 1), newMove);
			System.out.println("Moves modified. " + this.nickname + " has learned " + newMove + ".");
		}
	}
	public void setStats(int hp, int atk, int def, int spd){	//Replenishes current stats to full; COULD BE USED AS A TOTAL HEALING METHOD!!
    	//Assign individual stats
		this.indivStats[0] = hp;
		this.indivStats[1] = atk;
		this.indivStats[2] = def;
		this.indivStats[3] = spd;
		for (int i = 0; i < indivStats.length; i++) this.currentStats[i] = this.indivStats[i];
	}
	
	public Pokemon evolve(int level, String nickname){
		if (level == 16){		//varies between species
			System.out.println("Your Bulbasaur is evolving!");
			Ivysaur evol = new Ivysaur();  //create new Ivysaur			
			evol.setNickname(nickname);  //give it a new nickname	
			return evol;
		}
		return this;
	}
}

/***********************************************************************************************************************************************************************************************/

class Ivysaur extends Pokemon{
	//Possible example of call to move(): Move move1 = new Move("tackle", 60, 95)
    	
	public Ivysaur(){this.setup();}  //takes no args, creates Ivysaur with indivStats and currentStats equal to baseStats
		
	public Ivysaur	(int hp, int atk, int def, int spd){  //takes 4 ints, creates Ivysaur and assigns them to indivStats, then equates currentStats to indivStats
		this.setup();
		this.setStats(hp, atk, def, spd);
	}
	
	public void setup(){
		//Define inherited variables
		this.species = "Ivysaur";
		this.nickname = this.species;
		this.type1 = new Grass();
		this.type2 = new Poison();
		this.status = null;
		this.level = 1.;
		this.xp = 0;
		//Assign potential moves to learnset; new moves acquired as Pokemon levels up
		this.learnSet.put(1., "Tackle"); 											//start
		this.learnSet.put(1.25, "Growl");  											//start
		this.learnSet.put(1.5, "Leach Seed");										//start
		this.learnSet.put(9., "Vine Whip");  //gets same-type attack bonus        //level 9
		this.learnSet.put(13., "Poison Powder");									//level 13
		this.learnSet.put(13.25, "Sleep Powder");									//level 13
		this.learnSet.put(15., "Takedown");										//level 15
		this.learnSet.put(20., "Razor Leaf");  //gets same-type attack bonus		//level 20
		this.learnSet.put(23., "Sweet Scent");									//level 23
		this.learnSet.put(28., "Growth");											//level 28
		this.learnSet.put(31., "Double-Edge");									//level 31
		this.learnSet.put(36., "Worry Seed");										//level 36
		this.learnSet.put(39., "Synthesis");										//level 38
		this.learnSet.put(44., "Solar Beam");  //gets same-type attack bonus		//level 44
		
		// this.moves[0] = (String) learnSet.get(1);
		this.setMoves(this.level);
		
		//Assign baseStats in prearranged order: HP, Attack, Defense, Speed
		this.baseStats[0] = 60;
		this.baseStats[1] = 62;
		this.baseStats[2] = 63;
		this.baseStats[3] = 60;
		
		//Assign indivStats and currentStats to baseStats
		for (int i = 0; i < 4; i++){
			this.indivStats[i] = this.baseStats[i];  //RANDOMIZE INDIVIDUAL STATS
			this.currentStats[i] = this.baseStats[i];
		}
	}
	
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	
	// RESETS MOVESET. Any modifications that have been made are removed.
	//				   Pokemon now knows (up to) the first four moves in his learnset, or (if its level is high enough) the last four it should have learned.
	public void setMoves(double level){
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
						this.moves.add(0, (String) this.learnSet.get(i));
					}
				}
			}catch(Throwable t){}
		}
		//Remove certain moves for dual-type Pokemon
	}
	
	public void modMoves(String newMove, int moveslot){
	//  Moveset has max of 4 moves; therefore, it is indexed 0 - 3.
	//  ***Allows teaching of moves that are not in the default learnset of the species.***
	//	***Also allows expansion of current moveset beyond max size. However, calling setMoves() after using this will resize moves to 0 and reset to original moves determined by level.***
		if (moveslot > this.moves.size()){
			this.moves.add(newMove);
			System.out.println("Moves modified. " + this.nickname + " has learned " + newMove + ".");
		}else{
			String oldMove = this.moves.get(moveslot - 1);
			this.moves.set((moveslot - 1), newMove);
			System.out.println("Moves modified. " + this.nickname + " has learned " + newMove + ".");
		}
	}
	public void setStats(int hp, int atk, int def, int spd){	//Replenishes current stats to full; COULD BE USED AS A TOTAL HEALING METHOD!!
    	//Assign individual stats
		this.indivStats[0] = hp;
		this.indivStats[1] = atk;
		this.indivStats[2] = def;
		this.indivStats[3] = spd;
		for (int i = 0; i < indivStats.length; i++) this.currentStats[i] = this.indivStats[i];
	}
	
	public Pokemon evolve(int level, String nickname){
		if (level == 32){		//varies between species
			Venusaur evol = new Venusaur();  //create new Venusaur
			evol.setNickname(nickname);  //transfer nickname
			return evol;
		}
		return this;
	}
}

/***********************************************************************************************************************************************************************************************/

class Venusaur extends Pokemon{
	//Possible example of call to move(): Move move1 = new Move("tackle", 60, 95)
    	
	public Venusaur(){this.setup();}  //takes no args, creates Venusaur with indivStats and currentStats equal to baseStats
		
	public Venusaur(int hp, int atk, int def, int spd){  //takes 4 ints, creates Venusaur and assigns them to indivStats, then equates currentStats to indivStats
		this.setup();
		this.setStats(hp, atk, def, spd);
	}
	
	public void setup(){
		//Define inherited variables
		this.species = "Venusaur";
		this.nickname = this.species;
		this.type1 = new Grass();
		this.type2 = new Poison();
		this.status = null;
		this.level = 1.;
		this.xp = 0;
		//Assign potential moves to learnset; new moves acquired as Pokemon levels up
		this.learnSet.put(1., "Tackle"); 											//start
		this.learnSet.put(1.25, "Growl");  											//start
		this.learnSet.put(1.5, "Leach Seed");										//start
		this.learnSet.put(1.75, "Vine Whip");  //gets same-type attack bonus        //start
		this.learnSet.put(13., "Poison Powder");									//level 13
		this.learnSet.put(13.25, "Sleep Powder");									//level 13
		this.learnSet.put(15., "Takedown");										    //level 15
		this.learnSet.put(20., "Razor Leaf");  //gets same-type attack bonus		//level 20
		this.learnSet.put(23., "Sweet Scent");									    //level 23
		this.learnSet.put(28., "Growth");											//level 28
		this.learnSet.put(31., "Double-Edge");									    //level 31
		this.learnSet.put(32., "Petal Dance"); //gets same-type attack bonus	    //level 32
		this.learnSet.put(39., "Worry Seed");										//level 39
		this.learnSet.put(45., "Synthesis");										//level 45
		this.learnSet.put(53., "Solar Beam");  //gets same-type attack bonus	    //level 53
		
		// this.setMoves(this.level);
		
		//Assign baseStats in prearranged order: HP, Attack, Defense, Speed
		this.baseStats[0] = 80;
		this.baseStats[1] = 82;
		this.baseStats[2] = 83;
		this.baseStats[3] = 80;
		
		//Assign indivStats and currentStats to baseStats
		for (int i = 0; i < 4; i++){
			this.indivStats[i] = this.baseStats[i];  //RANDOMIZE INDIVIDUAL STATS
			this.currentStats[i] = this.baseStats[i];
		}
	}
	
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	
	// RESETS MOVESET. Any modifications that have been made are removed.
	//				   Pokemon now knows (up to) the first four moves in his learnset, or (if its level is high enough) the last four it should have learned.
	public void setMoves(double level){
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
						this.moves.add(0, (String) this.learnSet.get(i));
					}
				}
			}catch(Throwable t){}
		}
		//Remove certain moves for dual-type Pokemon
	}
	
	public void modMoves(String newMove, int moveslot){
	//  Moveset has max of 4 moves; therefore, it is indexed 0 - 3.
	//  ***Allows teaching of moves that are not in the default learnset of the species.***
	//	***Also allows expansion of current moveset beyond max size. However, calling setMoves() after using this will resize moves to 0 and reset to original moves determined by level.***
		if (moveslot > this.moves.size()){
			this.moves.add(newMove);
			System.out.println("Moves modified. " + this.nickname + " has learned " + newMove + ".");
		}else{
			String oldMove = this.moves.get(moveslot - 1);
			this.moves.set((moveslot - 1), newMove);
			System.out.println("Moves modified. " + this.nickname + " has learned " + newMove + ".");
		}
	}
	public void setStats(int hp, int atk, int def, int spd){	//Replenishes current stats to full; COULD BE USED AS A TOTAL HEALING METHOD!!
    	//Assign individual stats
		this.indivStats[0] = hp;
		this.indivStats[1] = atk;
		this.indivStats[2] = def;
		this.indivStats[3] = spd;
		for (int i = 0; i < indivStats.length; i++) this.currentStats[i] = this.indivStats[i];
	}
}

/***********************************************************************************************************************************************************************************************/
/***********************************************************************************************************************************************************************************************/
/***********************************************************************************************************************************************************************************************/