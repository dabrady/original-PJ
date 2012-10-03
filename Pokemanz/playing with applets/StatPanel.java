import java.awt.*;
import java.awt.event.*;

//holds numerical values for various character stats
public class StatPanel extends AttributePanel implements ActionListener{
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
		for(int i = 0; i < this.stats.length; i++){
			this.pointsAllocated[i] = new Label("0", Label.CENTER);
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














