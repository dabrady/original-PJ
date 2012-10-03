import java.applet.*;
import java.awt.*;
import java.awt.event.*;

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
		// try{ //only use if running from appletviewer and not PokeApplet.java
			// 	statPoints = Integer.parseInt(getParameter("StatPoints"));
		// }catch(NumberFormatException e){
			statPoints = 10;
		// }
		
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










