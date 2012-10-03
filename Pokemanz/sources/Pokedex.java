// package pokemanz;
import java.util.*;

/*
 * This class models a Pokedex, the device that every trainer has that keeps track of all Pokemon they've encountered
 * and provides details on each.
 */

public class Pokedex{   //each trainer gets one, part of their start menu
	/* As a trainer encounters Pokemon, they are added to (or revealed in?) their Pokedex.
	 * Entries will not be added until the trainer owns the corresponding Pokemon.
	 * Entries will contain: name, sprite/picture, data (including base stats).
	 */
	 
	static final Dex dex = new Dex(0);  //look up by name
	//static Dex dex1 = new Dex(1);  //look up by dexnumber
	HashMap pokedex = new HashMap(152);  //allocate one spot for all possible Pokemon a trainer can encounter (change if more than 151 Pokemon are designed)
	int seen = 0;  //keep track of how many Pokemon you've seen
	int own = 0;  //keep track of how many Pokemon you currently own (in your team AND elsewhere [boxes, perhaps])
	// Create basic entries for all Pokemon.
	// Make searchable by: Name, Number, Type, ...?
	
	Pokedex(){}
	
	public void add(Pokemon p){
		String species = (String) p.getClass().getName();
		if (pokedex.containsKey(species)){
			return;
		}else{
			Object[] data = new Object[2];
			data[0] = (Integer) dex.a.get(species);  //Pokedex Number
			//if owned:
				//own++;
				data[1] = new Entry(species, true);  //Pokedex entry for this species
			//else{ data[1] = new Entry(species, false); }  //No Pokedex entry added
			pokedex.put(species, data);			
			seen++;
			System.out.println("'" + species + "' was added to your Pokedex!");
		}
	}
}
