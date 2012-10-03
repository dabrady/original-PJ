import java.util.*;

public class BattleSimulator3000{
	public static void printMoves(Pokemon p){
		int num = 1;
		for(Move m1 : p.moves){
			System.out.print(num + ") " + m1.name.toUpperCase() + "\t");
			num++;
		}
		System.out.println();
		for (Move m2 : p.moves){
			System.out.print("TYPE: " + m2.type.name + "\t");
		}
		System.out.println();
		for (Move m3 : p.moves){
			System.out.print("PP: " + m3.pp_current + "/" + m3.pp_base + "\t");
		}
		System.out.println();
	}
	public static void main(String[] args){
	//test pokemon creation and modification of moveset
		Trainer t = new Trainer("Ash");
		Trainer x = new Trainer("Douche");

		t.add(new Bulbasaur());
		x.add(new Ivysaur());
		
		System.out.println(t.name + ", your team consists of a level " + (int)t.team[0].level + " " + t.team[0].name + ".");
		System.out.println("It knows: " + t.team[0].movesToString() + "\n");
		System.out.println(x.name + "'s team consists of a level " + (int)x.team[0].level + " " + x.team[0].name + ".");
		System.out.println("It knows: " + x.team[0].movesToString() + "\n");
		System.out.println("Your rival, " + x.name + ", has challenged you to a round in the BattleSimulator3000!");
		System.out.println("He is going to use his level " + (int)x.team[0].level + " " + x.team[0].name + " to attack your level " + (int)t.team[0].level + " " + t.team[0].name + ".");
		System.out.println("Are you gonna let him do that to you? No! You sure as hell aren't.\n");
		
		Scanner m = new Scanner(System.in);
		for(int i = 0; i < 3; i++){
			System.out.println("Quick! Have your Pokemon use a move:");
			printMoves(t.team[0]);
			int choice = Integer.parseInt(m.nextLine());
			Bulbasaur hero = (Bulbasaur)t.team[0];
			Ivysaur emeny = (Ivysaur)x.team[0];
			boolean bool = true;
			while (bool){
				switch(choice){
					case 1:
						hero.attack(emeny, hero.moves.get(0));
						bool = false;
						break;
					default:
						System.out.println("Not an option! Try again.");
						choice = Integer.parseInt(m.nextLine());
						break;
				}
			}
			
			System.out.println("\nNicely done, " + t.name + "! But is " + x.name + " just gonna sit there and take that from you? Oh HELL nah.");
			System.out.println("(" + x.name + " retaliates)\n");
			printMoves(x.team[0]);
			choice = Integer.parseInt(m.nextLine());
			bool = true;
			while (bool){
				switch(choice){
					case 1:
						emeny.attack(hero, emeny.moves.get(0));
						bool = false;
						break;
					case 2:
						emeny.attack(hero, emeny.moves.get(1));
						bool = false;
						break;
					case 3:
						emeny.attack(hero, emeny.moves.get(2));
						bool = false;
						break;
					default:
						System.out.println("Not an option! Try again.");
						choice = Integer.parseInt(m.nextLine());
						break;
				}
			}
		}	
			// Trainer t = new Trainer("Ash");
			// System.out.println("\nYou are a trainer now, " + t.name + ".");
			// Bulbasaur a = new Bulbasaur();
			// a.setName("Clarence");
			// t.add(a);
			// System.out.println("You've caught a new level " + (int)a.level + " " + a.species + "!");
			// System.out.println("Their name is " + a.name + ", their owner is " + a.owner.name + " and they are " + a.gender + ".");
			// System.out.println("Your team consists of: " + Trainer.teamToString(t.team));
			// a.reportType();
			// System.out.println("They know:    " + a.movesToString() + "\n");
			// a.setStats(68, 7, 237, 80, 80, 95);
			// System.out.println("Their base stats (HP, Atk, Def, SpecAtk, SpecDef, Spd):    " + Arrays.toString(a.baseStats));
			// System.out.println("Their individual stats (HP, Atk, Def, SpecAtk, SpecDef, Spd):    " + Arrays.toString(a.indivStats));
			// System.out.println("Their current stats (HP, Atk, Def, SpecAtk, SpecDef, Spd):    " + Arrays.toString(a.battleStats) + "\n");
						
			// // Trainer x = new Trainer("Brady");
			// // System.out.println("\n" + t.name + ", you've encountered another trainer, " + x.name + ".");
			// // Ivysaur b = new Ivysaur();
			// // x.team[0] = b;
			// // System.out.println("He wants his Ivysaur to use Tackle against your Bulbasaur.");
			// // System.out.println("Will it be effective? Let's find out. What is the multiplier? " + a.getMultiplier(b, "NORMAL"));
			
			// while(t.team[0].level < 17){
				// t.replace(t.team[0], t.team[0].levelUp());
			// }
			// Ivysaur i = (Ivysaur)t.team[0];
			// System.out.println("Your team consists of: " + Trainer.teamToString(t.team));
			// System.out.println("Their name is " + i.name + ", their owner is " + i.owner.name + " and they are " + i.gender + ".");
					
			// // Pokemon b = (Ivysaur) a.levelUp();
			// // t.replace(a, b);
			// // System.out.println("\nYour team now consists of: " + Trainer.teamToString(t.team));
			// // System.out.println(b.name + " knows: " + b.movesToString());
			
			// // while(b.level < 31){
				// // b.levelUp();
			// // }
			
			// // Pokemon c = (Venusaur) b.levelUp();
			// // t.replace(b, c);
			// // System.out.println("\nYour team now consists of: " + Trainer.teamToString(t.team));
			// // System.out.println(c.name + " knows: " + c.movesToString());
	}
}

//EXAMPLE CODES

// // create pokemon of given species
			
			// //modify moveset
			// a.modMoves("Thousand Years of Death", a.moves.size() + 1);
			// a.modMoves("Dynamic Entry", a.moves.size()+ 1);
			// System.out.println(a.name + " now knows:    " + a.movesToString());
			// a.resetMoves();
			// System.out.println("Uh oh. The moves have been reset!");
			// System.out.println("He now knows:    " + a.movesToString());
						
			// //create pokemon based on given Pokedex number
			// int dexnum = 1;
			// Bulbasaur b = (Bulbasaur) Pokemon.createNew(dexnum);
			// b.setNickname("Samwise");
			// b.level = 11;
			// b.resetMoves();
			// System.out.println("You've created a new level " + b.level + " " + b.getClass().getName() + "!");
			// System.out.println("His name is " + b.name + ".");
			// System.out.println("He knows:    " + b.movesToString() + "\n");	