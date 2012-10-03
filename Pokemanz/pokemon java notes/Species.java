package pokemanz;
import java.util.*;

class Species extends Pokemon{
	Map<String,int> baseStats = new HashMap<String,int>(4);
	Map<String,int> actualStats = new HashMap<String,int>(4);
	
	void setBaseStats(int hp, int atk, int def, int spd){
		this.baseStats.put("HP", hp);
		this.baseStats.put("Attack", atk);
		this.baseStats.put("Defense", def);
		this.baseStats.put("Speed", spd);
	}
	
	void setActualStats(int hp, int atk, int def, int spd){
		this.actualStats.put("HP", hp);
		this.actualStats.put("Attack", atk);
		this.actualStats.put("Defense", def);
		this.actualStats.put("Speed", spd);
	}
}