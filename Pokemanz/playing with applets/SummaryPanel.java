import java.awt.*;

//Panel containing a summary of the attributes
public class SummaryPanel extends Panel{
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