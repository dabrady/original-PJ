// package pokemanz;
import java.util.*;
import java.lang.reflect.*;

/*
 * This class models a trainer, a person who catches, trains, cares for and battles with Pokemon.
 * The majority of the people within the known Pokemon world are trainers.
 */

public class Trainer{	
	String name;
	int dollars = 1;
	String tClass = "Trainer";
	//Trainer card?
	
	Pokemon[] team = new Pokemon[6];
	ArrayList<Pokemon> allOwnedPokemon = new ArrayList<Pokemon>();  //includes all Pokemon on your team as well as other Pokemon stored elsewhere
	ArrayList<Item> bag = new ArrayList<Item>();  //inventory
	ArrayList<Pokemon> storage = new ArrayList<Pokemon>();
	Pokedex pokedex = new Pokedex();
	
	Trainer(String name){
		this.name = name;
		// make team		
	}	
	
	Trainer(String name, String tClass){
		this.name = name;
		this.tClass = tClass;
		// make team
	}
	
	public String toString(Pokemon[] team){
		String s = "[";
		for (Pokemon p : team){
			if (p != null){	
				s += p.species + "  ";
			}
		}
		s = s.trim() + "]";
		return s;
	}
	
	public void add(Pokemon p){
		boolean added = false;
		for (int i = 0; i < this.team.length; i++){
			if (this.team[i] == null){
				this.team[i] = p;
				added = true;
				break;
			}
		}
		if (added == false){
			this.storage.add(p);
			System.out.println("Uh oh! Your team is full. " + p.species + " was put into storage.");
		}
	}
	
	public void replace(Pokemon a, Pokemon b){  //replace a with b (based on name of Pokemon, not species, accounting for multiple Pokemon of same species)
		for (int i = 0; i < this.team.length; i++){
			if (this.team[i].name == a.name){  // base comparison on name of Pokemon, not species
				this.team[i] = b;
				break;
			}
		}
	}
	
	public void delete(Pokemon p){  //removes Pokemon p from team
		for (int i = 0; i < this.team.length; i++){
			if (this.team[i].name == p.name){  //base comparison on name of Pokemon, not species
				this.team[i] = null;
			}
		}
		//create an arraylist containing the team, minus the nulls
		ArrayList<Pokemon> temp = new ArrayList<Pokemon>(6);
		for (int i = 0; i < this.team.length; i++){
			if (this.team[i] != null){
				temp.add(this.team[i]);
			}
		}
		//set team equal to arraylist so that there are no nulls between Pokemon
		Object[] arr = temp.toArray();
		for (int i = 0; i < this.team.length; i++){
			Pokemon x = (Pokemon) arr[i];
			this.team[i] = x;
		}
	}
	
	public void release(Pokemon p){  //releases Pokemon p from storage
		int index = 0;
		for (Pokemon x : this.storage){
			if(x.name == p.name){
				index = this.storage.indexOf(x);  //does this work? The documentation says it returns the index of the FIRST OCCURRENCE of object x.
			}
		}
		this.storage.remove(index);
	}
	
	public void createRandTeam(){  //won't work until all 151 pokemon classes have been defined!!
		Dex pokedex = new Dex(1);
		Random rgen = new Random();
		for (int i = 0; i < this.team.length; i++){
			int rand = rgen.nextInt(151) + 1; //this way, zero is excluded; 151 is automatically excluded, so the new range is 1 - 151;
			try{
			//block of code used to create a new object that is an instance of the specified class
				Class cls = Class.forName((String) pokedex.b.get(rand));  //get class of specified name
				Class[] partypes = new Class[0];  //create a list of the parameters needed by the constructor for this class
				Constructor ctor = cls.getConstructor(partypes); //get the constructor within the class that takes the specified parameters
				Object[] arglist = new Object[0];  //create a list of arguments to be passed to the constructor we just got
				Pokemon newPokemon = (Pokemon) ctor.newInstance(arglist);  //create a new object that is an instance of the specified class
				this.team[i] = 	newPokemon;  //assign new object to team
				this.allOwnedPokemon.add(newPokemon);
			}catch(Throwable t){
				System.err.println(t);
			}
		}
		
	}
	public void shuffleTeam(){
		Random rgen = new Random();
		for (int i = 0; i < this.team.length; i++){
			int rand = rgen.nextInt(this.team.length);
			Pokemon temp = this.team[i];
			this.team[i] = this.team[rand];
			this.team[rand] = temp;
		}
	}
	
	public static void main(String[] args){
		Trainer t = new Trainer("Brock", "Gym Leader");
		System.out.println(Arrays.toString(t.team));
		t.shuffleTeam();
		System.out.println(Arrays.toString(t.team));
	}

}