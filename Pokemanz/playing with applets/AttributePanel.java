import java.awt.*;

//Panel for holding character attributes
public abstract class AttributePanel extends Panel{
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