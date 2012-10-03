import java.awt.*;

//holds a String attribute within a Panel
public class TextFieldPanel extends AttributePanel{
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