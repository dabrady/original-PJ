 
import java.util.*;

class origPokemon {
  String species;
  String type1; //primary type object, not string (define class Primary_type with extender Secondary_type)
  String type2 = "null"; //secondary type object, not string
  String status; //have an associated list that maps a number to a status of string type (so it can be printed)
  
  int level; //experience level
  int xp; //experience points
  //int maxHP; don't need it.
  
  //int nature //another associated list mapping a number to a nature of string type (so it can be printed)
  //abilities?
  
  // Pokemon (String name, String type1, String move, int hp) {
    // this.name = name;
    // this.type1 = type1;
    // this.maxHP = hp;
    // this.currentHP = hp;
    // this.move = move;
  // }
  
  // Pokemon (String name, String type1, String type2, String move, int hp) {
    // this.name = name;
    // this.type1 = type1;
    // this.type2 = type2;
    // this.maxHP = hp;
    // this.currentHP = hp;
    // this.move = move;
  // }
  origPokemon(){  // work on this.
	//match pokemon name
  } 
  
  public String toString() {
    return this.name;
  }
  
  static String battle(ArrayList<Pokemon> team1, ArrayList<Pokemon> team2) {
    int t1 = team1.size();
    int t2 = team2.size();
    int p1 = 0;
    int p2 = 0;
    Scanner scan = new Scanner(System.in);
    String whatever;
    Random rand = new Random();
    int turn = rand.nextInt(2);
    System.out.println("It's a battle between You and Gary.");
    System.out.println("You sent out " + team1.get(p1).name + "!");
    System.out.println("Gary sent out " + team2.get(p2).name + "!");
    System.out.println(team1.get(p1).name + "'s HP: " + team1.get(p1).currentHP + "/" + team1.get(p1).maxHP);
    System.out.println(team2.get(p2).name + "'s HP: " + team2.get(p2).currentHP + "/" + team2.get(p2).maxHP);
    if (turn == 0) {
      System.out.println(team1.get(p1).name + " attacks first.");
    }
    else { 
      System.out.println(team2.get(p2).name + " attacks first.");
    }
    System.out.println();
  
    while (true) {
      if (turn == 0) {
        whatever = scan.nextLine(); // makes it wait for the user to press enter, prevents it from printing everything at onve
        System.out.println();
        System.out.println(team1.get(p1) + " used " + team1.get(p1).move + " against " + team2.get(p2) + "!");
        attack(team1.get(p1), team2.get(p2));
        System.out.println(team1.get(p1).name + "'s HP: " + team1.get(p1).currentHP + "/" + team1.get(p1).maxHP);
        System.out.println(team2.get(p2).name + "'s HP: " + team2.get(p2).currentHP + "/" + team2.get(p2).maxHP);
        System.out.println();
        if (team2.get(p2).currentHP <= 0) {
          if (p2 + 1 == t2) {
            return "You have destroyed all da enemy Pokemanz. You win!";
          }
          else {
            System.out.println(team2.get(p2).name + " has fainted.");
            p2 = p2 + 1;
            System.out.println("Gary sent out " + team2.get(p2).name);
          }
        }
        turn = 1;
      }
      else {
        whatever = scan.nextLine();
        System.out.println();
        System.out.println(team2.get(p2) + " used " + team2.get(p2).move + " against " + team1.get(p1) + "!");
        attack(team2.get(p2), team1.get(p1));
        System.out.println(team1.get(p1).name + "'s HP: " + team1.get(p1).currentHP + "/" + team1.get(p1).maxHP);
        System.out.println(team2.get(p2).name + "'s HP: " + team2.get(p2).currentHP + "/" + team2.get(p2).maxHP);
        System.out.println();
        if (team1.get(p1).currentHP <= 0) {
          if (p1 + 1 == t1) {
            return "All of your Pokemanz have fainted. You will never be as good as Gary.";
          }
          else {
            System.out.println(team1.get(p1).name + " has fainted.");
            p1 = p1 + 1;
            System.out.println("You sent out " + team1.get(p1).name);
          }
        }
        turn = 0;
      }
    }
  }
    
  static void attack(Pokemon a, Pokemon b) {
    Random r = new Random();
    int x = r.nextInt(4);
    if (x == 0) {
      b.currentHP = b.currentHP - 50;
      System.out.println("Critical Hit!");
    }
    else if (x == 1) {
      System.out.println("The attack missed!");
    }
    else {
      b.currentHP = b.currentHP - 25;
      System.out.println("The attack hit.");
    }
  }
  
  
}