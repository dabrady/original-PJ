// package pokemanz;
import java.lang.reflect.*;
import java.util.*;

//Make abstract?
public abstract class Pokemon{
	String species;
	String type1; //primary type object (define class type)
	String type2; //secondary type object
	String status;
	 
	int level; //experience level
	int xp; //experience points
		
	//declare abstract methods to be inherited and defined	
	protected abstract void baseSetup();
	protected abstract void setNickname(String nickname);
	protected abstract void setMoves(int level);
	protected abstract void setStats(int hp, int atk, int def, int spd);
	
	//test it
	public static void main(String[] args){
		// create pokekmon of given species
		Bulbasaur a = (Bulbasaur) Pokemon.createNew("Bulbasaur");
		a.baseSetup();
		a.setNickname("Clarence");
		System.out.println("You've created a new " + a.getClass().getName() + "!");		
		System.out.println("His name is " + a.nickname + ".");
		System.out.println("He knows: " + Arrays.toString(a.moves));
		
		//create pokekmon of given species
		Bulbasaur b = (Bulbasaur) Pokemon.createNew("Bulbasaur");
		b.baseSetup();
		b.setNickname("Simon");
		System.out.println("You've created a new " + b.getClass().getName() + "!");		
		System.out.println("His name is " + b.nickname + ".");
		System.out.println("He knows: " + Arrays.toString(b.moves));
		
		//create pokemon based on given Pokedex number
		int dexnum = 1;
		Bulbasaur c = (Bulbasaur) Pokemon.createNew(dexnum);
		c.baseSetup();
		c.setNickname("Samwise");
		System.out.println("You've created another " + c.getClass().getName() + "!");
		System.out.println("His name is " + c.nickname + ".");
		System.out.println("He knows: " + Arrays.toString(c.moves));
	}
	
	Pokemon(){  //not needed, is created by default (as long as no other constructors are defined; must define if creating multiple constructors)
	
	}
	
	public static Object createNew(String species){
		//create pokemon of given species
		Object newPokemon = new Object();
		try{
			Class cls = Class.forName(species);
			Class[] partypes = new Class[0];  //'partypes' stands for 'parameter types'
			Constructor ctor = cls.getConstructor(partypes);
			Object[] arglist = new Object[0];
			newPokemon = ctor.newInstance(arglist);			
		}catch(Throwable t){
			System.err.println(t);		
		}
		return newPokemon;
	}
	
	public static Object createNew(int dexnum){
		Dex pokedex = new Dex();
		Object newPokemon = new Object();
		String species = (String)pokedex.b.get(dexnum);
		try{
		//block of code used to create a new object that is an instance of the specified class
			Class cls = Class.forName(species);  //get class of specified name
			Class[] partypes = new Class[0];  //create a list of the parameters needed by the constructor for this class
			Constructor ctor = cls.getConstructor(partypes); //get the constructor within the class that takes the specified parameters
			Object[] arglist = new Object[0];  //create a list of arguments to be passed to the constructor we just got
			newPokemon = ctor.newInstance(arglist);  //create a new object that is an instance of the specified class			
		}catch(Throwable t){
			System.err.println(t);
		}
		return newPokemon;
	}
}

class Bulbasaur extends Pokemon{
	String nickname;
	static HashMap<Integer, String> learnSet = new HashMap<Integer, String>(14);
	static int[] baseStats = new int[4];
	String[] moves = new String[4];
	int[] actualStats = new int[4];
	int[] currentStats = new int[4];
	//Possible example of call to move(): Move move1 = new Move("tackle", 60, 95)
    	
	public Bulbasaur(){  //takes no args, creates Bulbasaur with actualStats and currentStats equal to baseStats
		super.species = "Bulbasaur";
		super.type1 = null;
		super.type2 = null;
		super.status = null;
		super.level = 1;
		super.xp = 0;
		this.moves[0] = (String) learnSet.get(1);
		for (int i = 0; i < 4; i++){
			this.actualStats[i] = baseStats[i];
			this.currentStats[i] = baseStats[i];
		}
	}
	
	public Bulbasaur(int hp, int atk, int def, int spd){  //takes 4 ints, creates Bulbasaur and assigns them to actualStats, then equates currentStats to actualStats
		super.species = "Bulbasaur";
		super.type1 = null;
		super.type2 = null;
		super.status = null;
		super.level = 1;
		super.xp = 0;
		this.moves[0] = (String) learnSet.get(1);
		this.setStats(hp, atk, def, spd);
		for (int i = 0; i < 4; i++){
			this.currentStats[i] = this.actualStats[i]; 
		}
	}
	
	public void baseSetup(){
		//Assign potential moves to learnset; new moves acquired as Pokemon levels up
		learnSet.put(1, "Tackle"); 											//start
		learnSet.put(3, "Growl");  											//level 3
		learnSet.put(7, "Leech Seed");										//level 7
		learnSet.put(9, "Vine Whip");  //gets same-type attack bonus        //level 9
		learnSet.put(13, "Poison Powder");									//level 13
		learnSet.put(12, "Sleep Powder");									//level 13
		learnSet.put(15, "Takedown");										//level 15
		learnSet.put(19, "Razor Leaf");  //gets same-type attack bonus		//level 19
		learnSet.put(21, "Sweet Scent");									//level 21
		learnSet.put(25, "Growth");											//level 25
		learnSet.put(27, "Double-Edge");									//level 27
		learnSet.put(31, "Worry Seed");										//level 31
		learnSet.put(33, "Synthesis");										//level 33
		learnSet.put(37, "Seed Bomb");  //gets same-type attack bonus		//level 37
		
		//Assign base stats in prearranged order: HP, Attack, Defense, Speed
		baseStats[0] = 45;
		baseStats[1] = 49;
		baseStats[2] = 49;
		baseStats[3] = 45;    
	}
	
	public void setNickname(String nickname){
		this.nickname = nickname;
	}
	
	public void setMoves(int level){	//WILL THIS WORK?
		for (int i = 0; i < level; i++){
			try{
				String move = (String) learnSet.get(i + 1);
				this.moves[i] = move;
			}catch(Throwable t){}
		}
	}
	
	public void setStats(int hp, int atk, int def, int spd){
    	//Assign actual stats
		this.actualStats[0] = hp;
		this.actualStats[1] = atk;
		this.actualStats[2] = def;
		this.actualStats[3] = spd;
	}
	
	// void evolve(int level, String nickname){
		// if (level == 16){		//varies between species
			// Ivysaur evol = new Ivysaur();  //create new Ivysaur
			// evol.setNickname(nickname);  //transfer nickname
			// //add Ivysaur to team
			// this = null;  //drop hint to Garbage Collector that this instance of Bulbasaur will no longer be used
		// }
	// }
}












