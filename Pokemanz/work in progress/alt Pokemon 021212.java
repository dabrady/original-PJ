package game.pokemanz;
import game.pokemanz.Dex;
import java.util.*;

//Might not even use the Dex

class Pokemon{
	String name;
	Dex dex = new Dex();
	
	Pokemon(){
	
	}
	
	Pokemon createNewSpecies(Pokemon species){
	//get correct dex
		this.dex.setup();
		Map a = this.dex.getDex(1);
	//create pokemon of type SPECIES
		
	}
	
	// Pokemon(Map<String,int> dex1, String name){  //create new Pokemon based on its name
		// int dexnum = dex1.get(name);
	
	// }
	
	// Pokemon(Map<int,String> dex2, int dexnum){  //create new Pokemon based on its Pokedex number
	
	
	// }

}