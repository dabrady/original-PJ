// package pokemanz;
import java.util.*;

/*
 * This class models a Type, the elemental type that is a fundamental property of each Pokemon and their moves.
 * There are 17 types, and each type has three properties: types it is not very effective (weak) against,
 *														   types it is resistant to (strong against), and
 *														   types it is completely unaffted by (immune to).
 * While moves may only be of one type, some Pokemon may be of dual-type.
 */

public abstract class Type {
  //declare variables to be inherited
  public String name;
  public String[] weakAgainst; // YOU are weak against THEM
  public String[] resistantTo;  //YOU are resistant to THEM
  public String[] unaffectedBy;  //YOU are unaffected by THEM
}

class Normal extends Type{
	Normal(){
		super.name = "NORMAL";
		super.weakAgainst = new String[1];
		super.resistantTo = new String[0];
		super.unaffectedBy = new String[1];
		
		super.weakAgainst[0] = "FIGHT";
		super.unaffectedBy[0] = "GHOST";
	}
}

class Fighting extends Type{
	Fighting(){
		super.name = "FIGHTING";
		super.weakAgainst = new String[2];
		super.resistantTo = new String[3];
		super.unaffectedBy = new String[0];
		
		super.weakAgainst[0] = "FLYING";
		super.weakAgainst[1] = "PSYCHIC";
		
		super.resistantTo[0] = "ROCK";
		super.resistantTo[1] = "BUG";
		super.resistantTo[2] = "DARK";
	}
}

class Flying extends Type{
	Flying(){
		super.name = "FLYING";
		super.weakAgainst = new String[3];
		super.resistantTo = new String[3];
		super.unaffectedBy = new String[1];

		super.weakAgainst[0] = "ROCK";
		super.weakAgainst[1] = "ELECTRIC";
		super.weakAgainst[2] = "ICE";
		
		super.resistantTo[0] = "FIGHT";
		super.resistantTo[1] = "BUG";
		super.resistantTo[2] = "GRASS";
		
		super.unaffectedBy[0] = "GROUND";
	}
}

class Poison extends Type{
	Poison(){
		super.name = "POISON";
		super.weakAgainst = new String[2];
		super.resistantTo = new String[4];
		super.unaffectedBy = new String[0];	
		
		super.weakAgainst[0] = "GROUND";
		super.weakAgainst[1] = "PSYCHIC";
		
		super.resistantTo[0] = "FIGHT";
		super.resistantTo[1] = "POISON";
		super.resistantTo[2] = "BUG";
		super.resistantTo[3] = "GRASS";
	}
}

class Ground extends Type{
	Ground(){
		super.name = "GROUND";
		super.weakAgainst = new String[3];
		super.resistantTo = new String[2];
		super.unaffectedBy = new String[1];

		super.weakAgainst[0] = "WATER";
		super.weakAgainst[1] = "GRASS";
		super.weakAgainst[2] = "ICE";
		
		super.resistantTo[0] = "POISON";
		super.resistantTo[1] = "ROCK";
		
		super.unaffectedBy[0] = "ELECTRIC";
	}
}

class Rock extends Type{
	Rock(){
		super.name = "ROCK";
		super.weakAgainst = new String[5];
		super.resistantTo = new String[4];
		super.unaffectedBy = new String[0];

		super.weakAgainst[0] = "FIGHT";
		super.weakAgainst[1] = "GROUND";
		super.weakAgainst[2] = "STEEL";
		super.weakAgainst[3] = "WATER";
		super.weakAgainst[4] = "GRASS";
		
		super.resistantTo[0] = "NORMAL";
		super.resistantTo[1] = "FLYING";
		super.resistantTo[2] = "POISON";
		super.resistantTo[3] = "FIRE";
	}
}

class Bug extends Type{
	Bug(){
		super.name = "BUG";
		super.weakAgainst = new String[3];
		super.resistantTo = new String[3];
		super.unaffectedBy = new String[0];	
	
		super.weakAgainst[0] = "FLYING";
		super.weakAgainst[1] = "ROCK";
		super.weakAgainst[2] = "FIRE";
		
		super.resistantTo[0] = "FIGHT";
		super.resistantTo[1] = "GROUND";
		super.resistantTo[2] = "GRASS";
	}
}

class Ghost extends Type{
	Ghost(){
		super.name = "GHOST";
		super.weakAgainst = new String[2];
		super.resistantTo = new String[2];
		super.unaffectedBy = new String[2];

		super.weakAgainst[0] = "GHOST";
		super.weakAgainst[1] = "DARK";
		
		super.resistantTo[0] = "POISON";
		super.resistantTo[1] = "BUG";
		
		super.unaffectedBy[0] = "NORMAL";
		super.unaffectedBy[1] = "FIGHT";
	}
}

class Steel extends Type{
	Steel(){
		super.name = "STEEL";
		super.weakAgainst = new String[3];
		super.resistantTo = new String[11];
		super.unaffectedBy = new String[1];

		super.weakAgainst[0] = "FIGHT";
		super.weakAgainst[1] = "GROUND";
		super.weakAgainst[2] = "FIRE";
		
		super.resistantTo[0] = "NORMAL";
		super.resistantTo[1] = "FLYING";
		super.resistantTo[2] = "ROCK";
		super.resistantTo[3] = "BUG";
		super.resistantTo[4] = "GHOST";
		super.resistantTo[5] = "STEEL";
		super.resistantTo[6] = "GRASS";
		super.resistantTo[7] = "PSYCHIC";
		super.resistantTo[8] = "ICE";
		super.resistantTo[9] = "DRAGON";
		super.resistantTo[10] = "DARK";
		
		super.unaffectedBy[0] = "POISON";
	}
}

class Fire extends Type{
	Fire(){
		super.name = "FIRE";
		super.weakAgainst = new String[3];
		super.resistantTo = new String[5];
		super.unaffectedBy = new String[0];
		
		super.weakAgainst[0] = "GROUND";
		super.weakAgainst[1] = "ROCK";
		super.weakAgainst[2] = "WATER";
		
		super.resistantTo[0] = "BUG";
		super.resistantTo[1] = "STEEL";
		super.resistantTo[2] = "FIRE";
		super.resistantTo[3] = "GRASS";
		super.resistantTo[4] = "ICE";
	}
}

class Water extends Type{
	Water(){
		super.name = "WATER";
		super.weakAgainst = new String[2];
		super.resistantTo = new String[4];
		super.unaffectedBy = new String[0];

		super.weakAgainst[0] = "GRASS";
		super.weakAgainst[1] = "ELECTRIC";
		
		super.resistantTo[0] = "STEEL";
		super.resistantTo[1] = "FIRE";
		super.resistantTo[2] = "WATER";
		super.resistantTo[3] = "ICE";
	}
}

class Grass extends Type{
	Grass(){
		super.name = "GRASS";
		super.weakAgainst = new String[5];
		super.resistantTo = new String[4];
		super.unaffectedBy = new String[0];	
		
		super.weakAgainst[0] = "FLYING";
		super.weakAgainst[1] = "POISON";
		super.weakAgainst[2] = "BUG";
		super.weakAgainst[3] = "FIRE";
		super.weakAgainst[4] = "ICE";
		
		super.resistantTo[0] = "GROUND";
		super.resistantTo[1] = "WATER";
		super.resistantTo[2] = "GRASS";
		super.resistantTo[3] = "ELECTRIC";		
	}
}

class Electric extends Type{
	Electric(){
		super.name = "ELECTRIC";
		super.weakAgainst = new String[1];
		super.resistantTo = new String[3];
		super.unaffectedBy = new String[0];	
		
		super.weakAgainst[0] = "GROUND";
		
		super.resistantTo[0] = "FLYING";
		super.resistantTo[1] = "STEEL";
		super.resistantTo[2] = "ELECTRIC";
	}
}

class Psychic extends Type{
	Psychic(){
		super.name = "PSYCHIC";
		super.weakAgainst = new String[3];
		super.resistantTo = new String[2];
		super.unaffectedBy = new String[0];	
	
		super.weakAgainst[0] = "BUG";
		super.weakAgainst[1] = "GHOST";
		super.weakAgainst[2] = "DARK";
		
		super.resistantTo[0] = "FIGHT";
		super.resistantTo[1] = "PSYCHIC";	
	}
}

class Ice extends Type{
	Ice(){
		super.name = "ICE";
		super.weakAgainst = new String[4];
		super.resistantTo = new String[1];
		super.unaffectedBy = new String[0];	
		
		super.weakAgainst[0] = "FIGHT";
		super.weakAgainst[1] = "ROCK";
		super.weakAgainst[2] = "STEEL";
		super.weakAgainst[3] = "FIRE";
		
		super.resistantTo[0] = "ICE";
	}
}

class Dragon extends Type{
	Dragon(){
		super.name = "DRAGON";
		super.weakAgainst = new String[2];
		super.resistantTo = new String[4];
		super.unaffectedBy = new String[0];	
		
		super.weakAgainst[0] = "ICE";
		super.weakAgainst[1] = "DRAGON";
		
		super.resistantTo[0] = "FIRE";
		super.resistantTo[1] = "WATER";
		super.resistantTo[2] = "GRASS";
		super.resistantTo[3] = "ELECTRIC";
	}
}

class Dark extends Type{
	Dark(){
		super.name = "DARK";
		super.weakAgainst = new String[2];
		super.resistantTo = new String[2];
		super.unaffectedBy = new String[1];	
		
		super.weakAgainst[0] = "FIGHT";
		super.weakAgainst[1] = "BUG";
		
		super.resistantTo[0] = "GHOST";
		super.resistantTo[1] = "DARK";
		
		super.unaffectedBy[0] = "PSYCHIC";
	}
}