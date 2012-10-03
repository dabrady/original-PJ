// package pokemanz;
import java.util.*;

/* This class contains all Pokedex entries for their corresponding Pokemon.
 */

public class Entry{	
	static HashMap<String, String> hm = new HashMap<String, String>(152);
	String entry;

	Entry(String species, boolean bool){
		Entry.compileEntries();
		this.entry = Entry.get(species, bool);
	}
	
	public static void main(String[] args){
		Entry pikaEntry = new Entry("Pikachu", true);
		Entry squirEntry = new Entry("Squirtle", true);
		Entry hbEntry = new Entry("Honey Badger", true);
		System.out.println("Honey Badger: " + hbEntry.entry);
		System.out.println("Pikachu: " + pikaEntry.entry);
		System.out.println("Squirtle: " + squirEntry.entry);
	}
	
	public static String get(String species, boolean bool){
		if (bool == false){  //If they don't own this Pokemon, return a message:
			return "Nothing is known about this Pokemon.";
		}else{
			return hm.get(species).toString();  //If they do own this Pokemon, return the corresponding entry for the given Pokemon.
		}
	}
	
	public static void compileEntries(){
	//Replace standard entries with new, original entries:
		hm.put("Honey Badger", "It takes what it wants. Honey Badger don't give a shit. Nothing can stop the Honey Badger.");
		hm.put("Bulbasaur", "Bulbasaur, it bears the seed of a plant on its back from birth. The seed slowly develops. Researchers are unsure whether to classify Bulbasaur as a plant or animal. Bulbasaur are extremely calm and are difficult to capture in the wild. ");
		hm.put("Ivysaur", "The Seed Pokémon, Ivysaur, Bulbasaur's evolved form. The bulb on its back absorbs nourishment and blooms into a large flower.");
		hm.put("Venusaur", "Venusaur, the final form of the Bulbasaur evolution. This Seed Pokémon soaks up the sun's rays, and then stores up energy.");
		hm.put("Charmander", "Charmander. A flame burns on the tip of its tail from birth. It is said that a Charmander dies if its flame ever goes out.");
		hm.put("Charmeleon", "Charmeleon, the Flame Pokémon. It has razor sharp claws and its tail is exceptionally strong.");
		hm.put("Charizard", "Charizard, the Flame Pokémon. Charizard's powerful flame can melt absolutely anything.");
		hm.put("Squirtle", "Squirtle. This Tiny Turtle Pokémon draws its long neck into its shell to launch incredible water attacks with amazing range and accuracy. The blasts can be quite powerful.");
		hm.put("Wartortle", "Wartortle, the Turtle Pokémon. The evolved form of Squirtle. Its long furry tail is a symbol of its age and wisdom.");
		hm.put("Blastoise", "Blastoise, the Shellfish Pokémon. The evolved form of Wartortle. Blastoise's strength lies in its power, rather than its speed. Its shell is like armor and the attacks from the hydro cannons on its back are virtually unstoppable.");
		hm.put("Caterpie", "Caterpie, the Worm Pokémon. Caterpie uses the suction cups on its feet to climb trees and feed on its favorite leaves.");
		hm.put("Metapod", "Metapod, Caterpie's next stage. It has encased its body in a hard shell. This specimen reached this stage faster than any previously discovered Pokémon of this variety.");
		hm.put("Butterfree", "Butterfree. One week after Caterpie evolves into Metapod, it again evolves into Butterfree.");
		hm.put("Weedle", "Weedle. The stinger on this Pokémon's head guarantees that any attacker will get the point right where it hurts.");
		hm.put("Kakuna", "Kakuna, a transitional stage between Weedle and Beedrill. Kakunas remain inactive until they evolve into deadly Beedrills and hatch.");
		hm.put("Beedrill", "Beedrill. This Pokémon is an evolved form of Weedle, following its Kakuna stage. Its sting is highly poisonous.");
		hm.put("Pidgey", "Pidgey is a Flying Pokémon. Among all the Flying Pokémon, it is the gentlest and easiest to capture. A perfect target for the beginning Pokémon Trainer to test his Pokémon's skills. Pidgey's Gust power creates tornadoes. It also has a Sand-Attack.");
		hm.put("Pidgeotto", "Pidgeotto, an evolved form of Pidgey. It is armed with sharp claws and dives from the sky to capture its prey. Unlike the more gentle Pidgey, Pidgeotto can be more dangerous. Approach with extreme caution.");
		hm.put("Pidgeot", "Pidgeot, the Bird Pokémon. The evolved form of Pidgeotto, it can fly at twice the speed of sound at an altitude of nearly one mile.");
		hm.put("Rattata", "A Forest Pokémon, Rattata. It likes cheese, nuts, fruits, and berries. It also comes out into open fields to steal food from stupid travelers.");
		hm.put("Raticate", "Its rear feet have three toes each. They are webbed, enabling it to swim across rivers.");
		hm.put("Spearow", "Unlike Pidgey, Spearow has a terrible attitude. It is very wild and will sometimes attack other Pokémon and humans.");
		hm.put("Fearow", "Fearow, the Beak Pokémon. The evolved form of Spearow. Its large, powerful wings allow it to fly for an entire day. The crushing power of its beak is tremendous.");
		hm.put("Ekans", "Moves silently and stealthily. Eats the eggs of birds, such as Pidgey and Spearow, whole.");
		hm.put("Arbok", "It is rumored that the ferocious warning markings on its belly differ from area to area.");
		hm.put("Pikachu", "When several of these Pokemon gather, their electricity could build and cause lightning storms. ");  //Test this one
		hm.put("Raichu", "");
		hm.put("Sandshrew", "");
		hm.put("Sandslash", "");
		hm.put("Nidoran (Female)", "");
		hm.put("Nidorina", "");
		hm.put("Nidoqueen", "");
		hm.put("Nidoran (Male)", "");
		hm.put("Nidorino", "");
		hm.put("Nidoking", "");
		hm.put("Clefairy", "");
		hm.put("Clefable", "");
		hm.put("Vulpix", "");
		hm.put("Ninetales", "");
		hm.put("Jigglypuff", "");
		hm.put("Wigglytuff", "");
		hm.put("Zubat", "");
		hm.put("Golbat", "");
		hm.put("Oddish", "");
		hm.put("Gloom", "");
		hm.put("Vileplume", "");
		hm.put("Paras", "");
		hm.put("Parasect", "");
		hm.put("Venonat", "");
		hm.put("Venomoth", "");
		hm.put("Diglett", "");
		hm.put("Dugtrio", "");
		hm.put("Meowth", "");
		hm.put("Persian", "");
		hm.put("Psyduck", "");
		hm.put("Golduck", "");
		hm.put("Mankey", "");
		hm.put("Primeape", "");
		hm.put("Growlithe", "");
		hm.put("Arcanine", "");
		hm.put("Poliwag", "");
		hm.put("Poliwhirl", "");
		hm.put("Poliwrath", "");
		hm.put("Abra", "");
		hm.put("Kadabra", "");
		hm.put("Alakazam", "");
		hm.put("Machop", "");
		hm.put("Machoke", "");
		hm.put("Machamp", "");
		hm.put("Bellsprout", "");
		hm.put("Weepinbell", "");
		hm.put("Victreebell", "");
		hm.put("Tentacool", "");
		hm.put("Tentacruel", "");
		hm.put("Geodude", "");
		hm.put("Graveler", "");
		hm.put("Golem", "");
		hm.put("Ponyta", "");
		hm.put("Rapidash", "");
		hm.put("Slowpoke", "");
		hm.put("Slowbro", "");
		hm.put("Magnemite", "");
		hm.put("Magneton", "");
		hm.put("Farfetch'd", "");
		hm.put("Doduo", "");
		hm.put("Dodrio", "");
		hm.put("Seel", "");
		hm.put("Dewgong", "");
		hm.put("Grimer", "");
		hm.put("Muk", "");
		hm.put("Shellder", "");
		hm.put("Cloyster", "");
		hm.put("Ghastly", "");
		hm.put("Haunter", "");
		hm.put("Gengar", "");
		hm.put("Onix", "");
		hm.put("Drowzee", "");
		hm.put("Hypno", "");
		hm.put("Krabby", "");
		hm.put("Kingler", "");
		hm.put("Voltorb", "");
		hm.put("Electrode", "");
		hm.put("Exeggcute", "");
		hm.put("Exeggcutor", "");
		hm.put("Cubone", "");
		hm.put("Marowak", "");
		hm.put("Hitmonlee", "");
		hm.put("Hitmonchan", "");
		hm.put("Lickitung", "");
		hm.put("Koffing", "");
		hm.put("Weezing", "");
		hm.put("Rhyhorn", "");
		hm.put("Rhydon", "");
		hm.put("Chansey", "");
		hm.put("Tangela", "");
		hm.put("Kangaskhan", "");
		hm.put("Horsea", "");
		hm.put("Seadra", "");
		hm.put("Goldeen", "");
		hm.put("Seaking", "");
		hm.put("Staryu", "");
		hm.put("Starmie", "");
		hm.put("Mr. Mime", "");
		hm.put("Scyther", "");
		hm.put("Jynx", "");
		hm.put("Electrabuzz", "");
		hm.put("Magmar", "");
		hm.put("Pinsir", "");
		hm.put("Tauros", "");
		hm.put("Magikarp", "");
		hm.put("Gyarados", "");
		hm.put("Lapras", "");
		hm.put("Ditto", "");
		hm.put("Eevee", "");
		hm.put("Vaporeon", "");
		hm.put("Jolteon", "");
		hm.put("Flareon", "");
		hm.put("Porygon", "");
		hm.put("Omanyte", "");
		hm.put("Omastar", "");
		hm.put("Kabuto", "");
		hm.put("Kabutops", "");
		hm.put("Aerodactyl", "");
		hm.put("Snorlax", "");
		hm.put("Articuno", "");
		hm.put("Zapdos", "");
		hm.put("Moltres", "");
		hm.put("Dratini", "");
		hm.put("Dragonair", "");
		hm.put("Dragonite", "");
		hm.put("Mewtwo", "");
		hm.put("Mew", "");
	}
}