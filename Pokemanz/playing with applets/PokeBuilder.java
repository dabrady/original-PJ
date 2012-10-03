import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.*;
//NEEDS TO BE UPDATED to use methods shown in class!!!
//DOESN'T WORK THE WAY I WANT IT TO D:

//this class runs the PokeBuilder applet
// public class PokeApplet{
	// private final static int SIZE = 300; //size of window
	
	// public static void main(String[] args){
		// PokeBuilder applet = new PokeBuilder();  //instantiate the applet
		// applet.init();  //initialize the applet
		// applet.start(); //start the applet
		
		// Frame window = new Frame("PokeBuilder");  //create a window to hold the applet
		// window.add("Center", applet);  //add applet to window's center
		// window.resize(SIZE, SIZE);
		// window.setVisible(true);  //show the window (CTRL + C closes window)
	// }
// }

//the PokeBuilder class consists of a number of Panels arranged within a CardLayout along with associated "back" and "next" buttons
public class PokeBuilder extends Applet implements ActionListener{
	//selects the next and previous cards in cardPanel
	private Button back;
	private Button next;
	
	private Panel attributePanel;
	private SummaryPanel summaryPanel;
	
	//final String arrays representing various attribute selections
	private final String[] GENDERS = new String[] {"Male", "Female"};
	private final String[] STATS = new String[] {"HP", "Attack", "Defense", "Speed"};
	private final String[] SPECIES = new String[] {"Bulbasaur", "Ivysaur", "Venusaur"};
	
	//this method overrides the init method from the applet class
	public void init(){
		//create a GridLayout to hold our Cards/Menus and Buttons
		setLayout(new GridLayout(2, 1));
		
		//get the number of skill points to be allocated to the Pokemon
		int statPoints;
		try{ //only use if running from appletviewer and not PokeApplet.java
				statPoints = Integer.parseInt(getParameter("StatPoints"));
		}catch(NumberFormatException e){
			statPoints = 10;
		}
		
		//create an array of panels for our attributes: one for the name, gender, stats, and species of the Pokemon
		AttributePanel[] panels = new AttributePanel[] {
			new CheckboxPanel("Species", this.SPECIES, this.SPECIES[0]),
			new TextFieldPanel("Name", "Enter your Pokemon's name: ", 20),
			new CheckboxPanel("Gender", this.GENDERS, this.GENDERS[0]),
			new StatPanel("Stats", this.STATS, statPoints)};
			
		//create a Panel to place our CardLayout
		this.attributePanel = new Panel();
		this.attributePanel.setLayout(new CardLayout());
		
		//add the AttributePanels to the main Panel
		for(int i = 0; i < panels.length; i++){
			this.attributePanel.add(panels[i], panels[i].getAttribute());
		}
		
		//create the SummaryPanel and add it to our CardLayout
		this.summaryPanel = new SummaryPanel(panels);
		this.attributePanel.add(this.summaryPanel, "Summary");
		
		//add the attributePanel
		add(this.attributePanel);
		
		//create and add our 'back' and 'next' buttons
		Panel p = new Panel();
		
		this.back = new Button("back");
		this.back.addActionListener(this);
		p.add(this.back);
		
		this.next = new Button("next");
		this.next.addActionListener(this);
		p.add(this.next);
		
		p.setBackground(Color.BLACK);
		
		add(p);
	}
	
	//called when the 'back' or 'next' button is clicked
	public void actionPerformed(ActionEvent e){
		CardLayout cardLayout = (CardLayout) this.attributePanel.getLayout();
		
		if(e.getSource() == this.back){
			cardLayout.previous(this.attributePanel);
		}else if(e.getSource() == this.next){
			cardLayout.next(this.attributePanel);
		}
		
		//update the Summary after each change
		this.summaryPanel.update();
	}
}

//Panel for holding character attributes
abstract class AttributePanel extends Panel{
	//text description of the attribute
	protected String attribute;
	
	public AttributePanel(String attr){
		this.attribute = attr;
	}
	
	public final String getAttribute(){
		return this.attribute;
	}
	
	//force subclasses to override the toString method
	public abstract String toString();
}

//holds numerical values for various character stats
class StatPanel extends AttributePanel implements ActionListener{
	//points allocated to each skill
	Label[] pointsAllocated;
	//overall points remaining to allocate
	Label pointsRemaining;
	//the attributes for this applet
	private String[] stats;
	
	public StatPanel(String attr, String[] stat, int alloc){
		super(attr);
		this.stats = stat;
		//create the pointsRemaining label
		this.pointsRemaining = new Label("Points remaining: " + alloc, Label.CENTER);
		//set the applet's layout to a FlowLayout
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
		
		//add the components to the layout
		this.pointsAllocated = new Label[this.stats.length];
		int[] baseStats = ...
		for(int i = 0; i < this.stats.length; i++){
			this.pointsAllocated[i] = new Label("0", Label.CENTER);	////<<<<-----------------
			this.addStat(this.stats[i], this.pointsAllocated[i]);
		}
		
		add(this.pointsRemaining);
	}
	
	private void addStat(String stat, Label label){
		Panel p = new Panel();
		
		//set the Panel layout within a 3x1 grid
		p.setLayout(new GridLayout(3, 1));
		
		p.setBackground(Color.GREEN.darker());
		
		//add Labels to describe attribute
		p.add(new Label(stat, Label.CENTER));
		p.add(label);
		
		//attach the +/- buttons to the parent ActionListener
		Button incr = new StatButton("+", label);
		incr.addActionListener(this);
		Button decr = new StatButton("-", label);
		decr.addActionListener(this);
		
		//add another Panel with the +/- buttons
		Panel buttonPanel = new Panel();
		buttonPanel.add(incr);
		buttonPanel.add(decr);
		p.add(buttonPanel);
		
		add(p);
	}
	
	public String toString(){
		//return a String containing the allocation for each stat
		String s = "";
		int points = 0;
		for(int i = 0; i < this.stats.length; i++){
			points = Integer.parseInt(this.pointsAllocated[i].getText());
			s = s + this.stats[i] + " (" + points + ")    ";
		}
		
		return s;
	}
	
	public void actionPerformed(ActionEvent e){
		//get the points left to allocate
		int n = Integer.parseInt(this.pointsRemaining.getText().substring(18));
		
		//update the Button's Panel and the main Label
		n += ((StatButton) e.getSource()).update(n);
		this.pointsRemaining.setText("Points remaining: " + n);
	}
}

//represents a button capable of adjusting the value of a stat
class StatButton extends Button{
	//label referencing the points allocated to this stat
	private Label pointsAllocated;
	
	public StatButton(String desc, Label label){
		super(desc);
		this.pointsAllocated = label;
	}
	
	//parses the value from the label
	public int getPointsAllocated(){
		return Integer.parseInt(this.pointsAllocated.getText());
	}
	
	//updates the pointsAllocated label
	private void allocatePoints(int n){
		int value = this.getPointsAllocated() + n;
		this.pointsAllocated.setText("" + value);
	}
	
	//updates the paretn attribute's value
	public int update(int pointsRemaining){
		//allocate a point for the 'plus' buttons
		if(this.getLabel().equals("+")){
			//only allocate if there's points remaining
			if(pointsRemaining > 0){
				this.allocatePoints(1);
				return -1;
			}
		}else{  //otherwise, deallocate a point
			//dont' allow negative allocation
			if(this.getPointsAllocated() > 0){
				this.allocatePoints(-1);
				return 1;
			}
		}
		//de/allocation failed
		return 0;
	}
}

//holds a String attribute within a Panel
class TextFieldPanel extends AttributePanel{
	//the TextField for the attribute
	private TextField textField;
	
	public TextFieldPanel(String attr, String prompt, int textLength){
		super(attr);		
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 0));
		
		//add a Label if the prompt is a valid String
		if(prompt != null){
			add(new Label(prompt, Label.LEFT));
		}
		
		//create and add the TextField to the Panel
		this.textField = new TextField(textLength);
		add(textField);
	}
	
	public String toString(){
		//return the attribute, a 'not specified' message
		if(textField.getText().trim().equals("")){
			return attribute + ": not specified";
		}
		//else
		return attribute + ": " + textField.getText().trim();
	}
}

class CheckboxPanel extends AttributePanel implements ItemListener{
	//a checkboxgroup to hold our checkboxes
	protected CheckboxGroup cbg;
	protected int[] baseStats;
	
	//this method overrides the init method from the Applet class
	public CheckboxPanel(String attr, String[] items, String selectedItem){
		super(attr);		
		this.setLayout(new GridLayout(items.length + 1, 1, 5, 5));
		add(new Label(super.attribute, Label.CENTER));
		
		//create the checkboxgroup
		this.cbg = new CheckboxGroup();
		for(int i = 0; i < items.length; i++){
			add(new Checkbox(items[i], this.cbg, items[i].equals(selectedItem)));
		}
	}
	
	public String toString(){
		return attribute + ": " + this.cbg.getSelectedCheckbox().getLabel();
	}
	
	public int[] getBaseStats(){
		return this.baseStats;
	}
	
	public void itemStateChanged(ItemEvent e){
		String species = this.cbg.getSelectedCheckbox.getLabel();
		if(species.equals("Bulbasaur")){
			this.baseStats[0] = 45;
			this.baseStats[1] = 49;
			this.baseStats[2] = 49;
			this.baseStats[3] = 45;
		}else if(species.equals("Ivysaur")){
			this.baseStats[0] = 60;
			this.baseStats[1] = 62;
			this.baseStats[2] = 63;
			this.baseStats[3] = 60;		
		}else if(species.equals("Venusaur")){
			this.baseStats[0] = 80;
			this.baseStats[1] = 82;
			this.baseStats[2] = 83;
			this.baseStats[3] = 80;
		}
	}
	
	// public void paint(Graphics g){
		// g.setColor(Color.BLACK);
		// g.setFont(new Font("Helvetica", Font.BOLD, 24));
	// }
	
	// public void itemStateChanged(ItemEvent e){
		// String species = this.cbg.getSelectedCheckbox().getLabel();
		// g.drawString("You've selected: " + species, 50, 35);
		// // System.out.println("You've selected: " + species);
		// Object p = Pokemon.createNew(species);
		// switch(species){
			// case("Bulbasaur"):
				// p = new Bulbasaur();
				// System.out.println("You've created a new " + species + "!");
				// break;
			// case("Ivysaur"):
				// p = new Ivysaur();
				// System.out.println("You've created a new " + species + "!");
				// break;
			// case("Venusaur"):
				// p = new Venusaur();
				// System.out.println("You've created a new " + species + "!");
				// break;
			// default:
				// p = new Bulbasaur();
				// break;
		// }
	// }
}

//Panel containing a summary of the attributes
class SummaryPanel extends Panel{
	//a Label to describe each attribute
	private Label[] summaries;
	//reference to array of AttributePanels for the attributes
	private AttributePanel[] panels;
	
	public SummaryPanel(AttributePanel[] ap){
		super();
		this.panels = ap;
		this.setLayout(new GridLayout(this.panels.length + 1, 1, 5, 5));
		add(new Label("Summary: ", Label.CENTER));
		
		//add the Labels to the Panel
		this.summaries = new Label[this.panels.length];
		for(int i = 0; i < this.panels.length; i++){
			this.summaries[i] = new Label("", Label.LEFT);
			add(summaries[i]);
		}
	}
	
	//since we don't know exactly which panel has been updated, let each AttributePanel update its label
	public void update(){
		for(int i = 0; i < this.panels.length; i++){
			this.summaries[i].setText(this.panels[i].toString());
		}
	}
}

// /* 
 // * This abstract class models a Pokemon, with each extended class representing a different species of Pokemon.
 // */

// abstract class Pokemon{
	// //declare variables to be inherited
	// String species;
	// String name;
	// Type type1; //primary type object (define class type)
	// Type type2; //secondary type object
	// String status;
	// String pouch; //spot for held-item
	// ArrayList<String> moves = new ArrayList<String>(0);
	
	// double level; //experience level. Once level 100 has been reached, a Pokemon can no longer level up.
	// int xp; //experience points. Once level 100 has been reached, a Pokemon can no longer gain XP.
	// int evolveAt;

	// int[] baseStats = new int[4];
	// int[] indivStats = new int[4];
	// int[] currentStats = new int[4];
		
	// static HashMap<Double, String> learnSet = new HashMap<Double, String>(14, 1f);
	
	// //declare abstract methods to be inherited and defined	
	// protected abstract void setup();
	
    // Pokemon(){}
	
	// //instance method(s) inherited by all Pokemon
	// public void reportType(){
		// if (this.type2 == null) System.out.println(this.getClass().getName() + "s are " + this.type1.name + " type Pokemon.");
		// else System.out.println(this.getClass().getName() + "s are " + this.type1.name + "-" + this.type2.name + " type Pokemon.");
	// }
	
	// public void setName(String name){
		// this.name = name;
	// }
	
	// // RESETS MOVESET. Any modifications that have been made are removed.
	// //				   Pokemon now knows (up to) the first four moves in his learnset, or (if its level is high enough) the last four it should have learned.
	// public void resetMoves(){
		// this.moves.clear();
		// //get the levels at which the Pokemon learns a new move
		// Set keyset = this.learnSet.keySet();
		// double maxkey = 0;
		// //find the highest level at which the Pokemon learns a new move
		// for (Object x : keyset){
			// if ((double)x > maxkey) maxkey = (double)x;
		// }
		// //Starting from the highest level at which the Pokemon learns a new move, count down. If the count is <= the Pokemon's current level AND can learn a
		// //new move at that level AND its moveset is not full, the new move is added to the end of its moveset.
		// for (double i = maxkey; i > 0; i -= 0.25){
			// try{
				// if (i <= this.level + 0.75 && this.learnSet.containsKey(i)){
					// if (this.moves.size() == 4){
						// break;
					// }else{
						// this.moves.add(0, (String) this.learnSet.get(i));
					// }
				// }
			// }catch(Throwable t){}
		// }
		// //Remove certain moves for dual-type Pokemon
	// }	
	
	// public void modMoves(String newMove, int moveslot){
	// //  Moveset has max of 4 moves; therefore, it is indexed 0 - 3.
	// //  ***Allows teaching of moves that are not in the default learnset of the species.***
	// //	***Also allows expansion of current moveset beyond max size. However, calling resetMoves() after using this will resize moves to 0 and reset to original moves determined by level.***
		// if (moveslot > this.moves.size()){
			// this.moves.add(newMove);
			// // System.out.println("Moves modified. " + this.name + " has learned " + newMove + ".");
		// }else{
			// String oldMove = this.moves.get(moveslot - 1);
			// this.moves.set((moveslot - 1), newMove);
			// // System.out.println("Moves modified. " + this.name + " has learned " + newMove + ".");
		// }
	// }
	
	// public void setStats(int hp, int atk, int def, int spd){	//Replenishes current stats to full power; COULD BE USED AS A TOTAL HEALING METHOD!!
    	// //Assign individual stats
		// this.indivStats[0] = hp;
		// this.indivStats[1] = atk;
		// this.indivStats[2] = def;
		// this.indivStats[3] = spd;
		// for (int i = 0; i < indivStats.length; i++) this.currentStats[i] = this.indivStats[i];
	// }	
	
	// public Pokemon evolve(){
		// if ((int)this.level == this.evolveAt){		//varies between species
			// System.out.println("\nYour Bulbasaur is evolving!");
			// Ivysaur evol = new Ivysaur();  //create new Ivysaur
			// evol.level = this.level;
			
			// //create a copy of Bulbasaur's moves to be transfered to the Ivysaur
			// String[] moveset = new String[this.moves.size()];
			// for (int i = 0; i < this.moves.size(); i++){
				// moveset[i] = this.moves.get(i);
			// }
			
			// //set newly evolved Ivysaur's moves
			// for (int i = 0; i < moveset.length; i++){
				// evol.modMoves(moveset[i], i + 1);
			// }
			
			// while(true){
				// Scanner m = new Scanner(System.in);
				// System.out.println(this.name + " is now a(n) " + evol.species + "!");
				// System.out.println("Would you like to rename your new " + evol.species + "? Y/N");
				// String choice = m.nextLine().toUpperCase();
				// if (choice.equals("Y")){
					// System.out.print("Enter a new name for your Pokemon: ");
					// String name = m.nextLine().trim();
					// evol.setName(name);  //give it a new name
					// break;
				// }else{
					// System.out.println("Are you sure? Y/N");
					// choice = m.nextLine().toUpperCase();
					// if (choice.equals("Y")){
						// System.out.println("Okay. Your " + evol.species + " will remain " + this.name + ".");
						// evol.setName(this.name);
						// break;
					// }else{
						// continue;
					// }
				// }
			// }
			// System.out.println("Your " + evol.species + " is now named " + evol.name + ".");
			// return evol;
		// }
		// return this;
	// }
	
	// public Pokemon levelUp(){
		// if (this.level < 100){
			// this.level ++;
			// System.out.println(this.name + " has gained enough XP to level up!\nThey are now a level " + (int)this.level + " " + this.getClass().getName() + ".");
			// Pokemon p = this.evolve();
			// for (double i = 0; i <= 0.75; i += 0.25){  //cycles through possible moves to be learned at this level (accounts for multiple moves learned)
				// double level = p.level + i;
				// String newMove = p.learnSet.get(level);
				// while (newMove != null){
					// Scanner m = new Scanner(System.in);
					// System.out.println(p.name + " is trying to learn " + newMove + ". Will you let them? Y/N");  //List stats of newMove
					// String choice = m.nextLine();
					
					// if (choice.toUpperCase().equals("Y")){
						// if (p.moves.size() < 4){
							// p.modMoves(newMove, p.moves.size() + 1);
							// System.out.println(p.name + " now knows:    " + p.arrListToString(p.moves) + "\n");
							// break;
						// }else{
							// System.out.println("Uh oh! " + p.name + " already knows four moves. Would you like to replace one with " + newMove + "? Y/N");
							// choice = m.nextLine();
							// if (choice.toUpperCase().equals("Y")){
								// while(true){
									// System.out.println("Which move would you like to replace? Enter a number between 1 and 4. ");
									// int move;
									// try{
										// move = Integer.parseInt(m.nextLine());
									// }catch(Throwable t){
										// System.out.println("Sorry, that's not an option.\n");
										// continue;
									// }
									// if (move <= 4 && move > 0){
										// p.modMoves(newMove, move);
										// System.out.println(p.name + " now knows:    " + p.arrListToString(p.moves) + "\n");
										// break;
									// }else{
										// System.out.println("Sorry, that's not an option.\n");
										// continue;
									// }
								// }
							// }else{
								// System.out.println("Are you sure? Y/N");
								// choice = m.nextLine();
								// if (choice.toUpperCase().equals("Y")){
									// System.out.println("Well, if you're sure...let's hope you don't regret it!");
									// break;
								// }else{
									// continue;
								// }
							// }
							// break;
						// }
					// }else{
						// System.out.println("Are you sure? Y/N");
						// choice = m.nextLine();
						// if (choice.toUpperCase().equals("Y")){
							// System.out.println("Well, if you're sure...let's hope you don't regret it!");
							// break;
						// }else{
							// continue;
						// }
					// }
				// }
			// }
			// return p;
		// }else{
			// System.out.println("Your Pokemon has already reached the highest level attainable!");
			// return this;
		// }
	// }
	
	// //static method(s) inherited by all Pokemon
	// public static Object createNew(String species){
		// //create pokemon of given species
		// Object newPokemon = new Object();
		// try{
		// //block of code used to create a new object that is an instance of the specified class
			// Class cls = Class.forName(species);  //get class of specified name
			// Class[] partypes = new Class[0];  //create a list of the parameters needed by the constructor for this class
			// Constructor ctor = cls.getConstructor(partypes); //get the constructor within the class that takes the specified parameters
			// Object[] arglist = new Object[0];  //create a list of arguments to be passed to the constructor we just got
			// newPokemon = ctor.newInstance(arglist);  //create a new object that is an instance of the specified class			
		// }catch(Throwable t){	//catch any errors and print error message
			// System.err.println("No.");		
		// }
		// return newPokemon;
	// }
	
	// public static Object createNew(int dexnum){
		// //create pokemon of given species
		// Dex pokedex = new Dex(1);
		// Object newPokemon = new Object();
		// String species = (String)pokedex.b.get(dexnum);
		// try{
		// //block of code used to create a new object that is an instance of the specified class
			// Class cls = Class.forName(species);  //get class of specified name
			// Class[] partypes = new Class[0];  //create a list of the parameters needed by the constructor for this class
			// Constructor ctor = cls.getConstructor(partypes); //get the constructor within the class that takes the specified parameters
			// Object[] arglist = new Object[0];  //create a list of arguments to be passed to the constructor we just got
			// newPokemon = ctor.newInstance(arglist);  //create a new object that is an instance of the specified class			
		// }catch(Throwable t){	//catch any errors and print error message
			// System.out.println("No.");
			// System.err.println(t);
		// }
		// return newPokemon;
	// }
	
	// //ArrayList toString method()
	// public String arrListToString(ArrayList<String> arr){
		// String listString = "";
		// for (String s : arr){
			// listString += s + "    ";
		// }
		// return listString;
	// }
	
	// //test it
	// // public static void test(){
		// // // test pokemon creation and modification of moveset
		// // {	// create pokekmon of given species
			// // Trainer t = new Trainer("Ash");
			// // System.out.println("\nYou are a trainer now, " + t.name + ".");
			// // Bulbasaur a = (Bulbasaur) Pokemon.createNew("Bulbasaur");
			// // a.setName("Clarence");
			// // System.out.println("You've caught a new level " + (int)a.level + " " + a.getClass().getName() + "!");
			// // System.out.println("His name is " + a.name + ".");
			// // t.team[0] = a;
			// // System.out.println("Your team consists of: " + t.toString(t.team));
			// // a.reportType();
			// // System.out.println("He knows:    " + a.arrListToString(a.moves) + "\n");
			// // a.setStats(68, 7, 237, 95);
			// // System.out.println("His base stats (HP, Atk, Def, Spd):    " + Arrays.toString(a.baseStats));
			// // System.out.println("His individual stats (HP, Atk, Def, Spd):    " + Arrays.toString(a.indivStats));
			// // System.out.println("His current stats (HP, Atk, Def, Spd):    " + Arrays.toString(a.currentStats) + "\n");
						
			// // while(a.level < 15){
				// // a.levelUp();
			// // }
			// // Pokemon b = (Pokemon) a.levelUp();
			// // t.replace(a,b);
			// // System.out.println("\nYour team now consists of: " + t.toString(t.team));
			// // System.out.println(b.name + " knows: " + b.arrListToString(b.moves));
			// // // //modify moveset
			// // // a.modMoves("Thousand Years of Death", a.moves.size() + 1);
			// // // a.modMoves("Dynamic Entry", a.moves.size()+ 1);
			// // // System.out.println(a.name + " now knows:    " + a.arrListToString(a.moves));
			// // // a.resetMoves();
			// // // System.out.println("Uh oh. The moves have been reset!");
			// // // System.out.println("He now knows:    " + a.arrListToString(a.moves));
						
			// // // //create pokemon based on given Pokedex number
			// // // int dexnum = 1;
			// // // Bulbasaur b = (Bulbasaur) Pokemon.createNew(dexnum);
			// // // b.setNickname("Samwise");
			// // // b.level = 11;
			// // // b.resetMoves();
			// // // System.out.println("You've created a new level " + b.level + " " + b.getClass().getName() + "!");
			// // // System.out.println("His name is " + b.name + ".");
			// // // System.out.println("He knows:    " + b.arrListToString(b.moves) + "\n");			
		// // }
	// // }
// }

// /***********************************************************************************************************************************************************************************************/
// /***********************************************************************************************************************************************************************************************/
// /***********************************************************************************************************************************************************************************************/

// /*
 // * The following 150+ classes model individual species of Pokemon.
 // */

// // DON'T DUPLICATE FOR ALL POKEMON UNTIL WE'VE IRONED OUT ALL THE LITTLE DETAILS.
// class Bulbasaur extends Pokemon{
	// //Possible example of call to move(): Move move1 = new Move("tackle", 60, 95)
    	
	// public Bulbasaur(){this.setup();}  //takes no args, creates Bulbasaur with indivStats and currentStats equal to baseStats
		
	// public Bulbasaur(int hp, int atk, int def, int spd){  //takes 4 ints, creates Bulbasaur and assigns them to indivStats, then equates currentStats to indivStats
		// this.setup();
		// this.setStats(hp, atk, def, spd);
	// }
	
	// public void setup(){
		// //Define inherited variables
		// this.species = "Bulbasaur";
		// this.name = this.species;
		// this.type1 = new Grass();
		// this.type2 = new Poison();
		// this.status = null;
		// this.level = 1.;
		// this.xp = 0;
		// this.evolveAt = 16;
		// //Assign potential moves to learnset; new moves acquired as Pokemon levels up
		// this.learnSet.put(1., "Tackle"); 											//start
		// this.learnSet.put(3., "Growl");  											//level 3
		// this.learnSet.put(7., "Leach Seed");										//level 7
		// this.learnSet.put(9., "Vine Whip");  //gets same-type attack bonus        //level 9
		// this.learnSet.put(13., "Poison Powder");									//level 13
		// this.learnSet.put(13.25, "Sleep Powder");									//level 13
		// this.learnSet.put(15., "Takedown");										//level 15
		// this.learnSet.put(19., "Razor Leaf");  //gets same-type attack bonus		//level 19
		// this.learnSet.put(21., "Sweet Scent");									//level 21
		// this.learnSet.put(25., "Growth");											//level 25
		// this.learnSet.put(27., "Double-Edge");									//level 27
		// this.learnSet.put(31., "Worry Seed");										//level 31
		// this.learnSet.put(33., "Synthesis");										//level 33
		// this.learnSet.put(37., "Seed Bomb");  //gets same-type attack bonus		//level 37
		
		// this.resetMoves();
		
		// //Assign baseStats in prearranged order: HP, Attack, Defense, Speed
		// this.baseStats[0] = 45;
		// this.baseStats[1] = 49;
		// this.baseStats[2] = 49;
		// this.baseStats[3] = 45;
		
		// //Assign indivStats and currentStats to baseStats
		// for (int i = 0; i < 4; i++){
			// this.indivStats[i] = this.baseStats[i];  //RANDOMIZE INDIVIDUAL STATS
			// this.currentStats[i] = this.baseStats[i];
		// }
	// }
// }

// /***********************************************************************************************************************************************************************************************/

// class Ivysaur extends Pokemon{
	// //Possible example of call to move(): Move move1 = new Move("tackle", 60, 95)
	
	// public Ivysaur(){this.setup();}  //takes no args, creates Ivysaur with indivStats and currentStats equal to baseStats
		
	// public Ivysaur	(int hp, int atk, int def, int spd){  //takes 4 ints, creates Ivysaur and assigns them to indivStats, then equates currentStats to indivStats
		// this.setup();
		// this.setStats(hp, atk, def, spd);
	// }
	
	// public void setup(){
		// //Define inherited variables
		// this.species = "Ivysaur";
		// this.name = this.species;
		// this.type1 = new Grass();
		// this.type2 = new Poison();
		// this.status = null;
		// this.level = 1.;
		// this.xp = 0;
		// this.evolveAt = 32;    
		// //Assign potential moves to learnset; new moves acquired as Pokemon levels up
		// this.learnSet.put(1., "Tackle"); 											//start
		// this.learnSet.put(1.25, "Growl");  											//start
		// this.learnSet.put(1.5, "Leach Seed");										//start
		// this.learnSet.put(9., "Vine Whip");  //gets same-type attack bonus        //level 9
		// this.learnSet.put(13., "Poison Powder");									//level 13
		// this.learnSet.put(13.25, "Sleep Powder");									//level 13
		// this.learnSet.put(15., "Takedown");										//level 15
		// this.learnSet.put(20., "Razor Leaf");  //gets same-type attack bonus		//level 20
		// this.learnSet.put(23., "Sweet Scent");									//level 23
		// this.learnSet.put(28., "Growth");											//level 28
		// this.learnSet.put(31., "Double-Edge");									//level 31
		// this.learnSet.put(36., "Worry Seed");										//level 36
		// this.learnSet.put(39., "Synthesis");										//level 38
		// this.learnSet.put(44., "Solar Beam");  //gets same-type attack bonus		//level 44
		
		// this.resetMoves();
		
		// //Assign baseStats in prearranged order: HP, Attack, Defense, Speed
		// this.baseStats[0] = 60;
		// this.baseStats[1] = 62;
		// this.baseStats[2] = 63;
		// this.baseStats[3] = 60;
		
		// //Assign indivStats and currentStats to baseStats
		// for (int i = 0; i < 4; i++){
			// this.indivStats[i] = this.baseStats[i];  //RANDOMIZE INDIVIDUAL STATS
			// this.currentStats[i] = this.baseStats[i];
		// }
	// }
		
	// public Pokemon evolve(double level, String name){
		// if ((int) level == 32){		//varies between species
			// Venusaur evol = new Venusaur();  //create new Venusaur
			// evol.setName(name);  //transfer name
			// return evol;
		// }
		// return this;
	// }
// }

// /***********************************************************************************************************************************************************************************************/

// class Venusaur extends Pokemon{
	// //Possible example of call to move(): Move move1 = new Move("tackle", 60, 95)
    	
	// public Venusaur(){this.setup();}  //takes no args, creates Venusaur with indivStats and currentStats equal to baseStats
		
	// public Venusaur(int hp, int atk, int def, int spd){  //takes 4 ints, creates Venusaur and assigns them to indivStats, then equates currentStats to indivStats
		// this.setup();
		// this.setStats(hp, atk, def, spd);
	// }
	
	// public void setup(){
		// //Define inherited variables
		// this.species = "Venusaur";
		// this.name = this.species;
		// this.type1 = new Grass();
		// this.type2 = new Poison();
		// this.status = null;
		// this.level = 1.;
		// this.xp = 0;
		// //Assign potential moves to learnset; new moves acquired as Pokemon levels up
		// this.learnSet.put(1., "Tackle"); 											//start
		// this.learnSet.put(1.25, "Growl");  											//start
		// this.learnSet.put(1.5, "Leach Seed");										//start
		// this.learnSet.put(1.75, "Vine Whip");  //gets same-type attack bonus        //start
		// this.learnSet.put(13., "Poison Powder");									//level 13
		// this.learnSet.put(13.25, "Sleep Powder");									//level 13
		// this.learnSet.put(15., "Takedown");										    //level 15
		// this.learnSet.put(20., "Razor Leaf");  //gets same-type attack bonus		//level 20
		// this.learnSet.put(23., "Sweet Scent");									    //level 23
		// this.learnSet.put(28., "Growth");											//level 28
		// this.learnSet.put(31., "Double-Edge");									    //level 31
		// this.learnSet.put(32., "Petal Dance"); //gets same-type attack bonus	    //level 32
		// this.learnSet.put(39., "Worry Seed");										//level 39
		// this.learnSet.put(45., "Synthesis");										//level 45
		// this.learnSet.put(53., "Solar Beam");  //gets same-type attack bonus	    //level 53
		
		// // this.resetMoves();
		
		// //Assign baseStats in prearranged order: HP, Attack, Defense, Speed
		// this.baseStats[0] = 80;
		// this.baseStats[1] = 82;
		// this.baseStats[2] = 83;
		// this.baseStats[3] = 80;
		
		// //Assign indivStats and currentStats to baseStats
		// for (int i = 0; i < 4; i++){
			// this.indivStats[i] = this.baseStats[i];  //RANDOMIZE INDIVIDUAL STATS
			// this.currentStats[i] = this.baseStats[i];
		// }
	// }
// }

// /***********************************************************************************************************************************************************************************************/
// /***********************************************************************************************************************************************************************************************/
// /***********************************************************************************************************************************************************************************************/