package harrypotter.view;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HelSpellPanel extends JPanel{

	JButton heal;
	
	public HelSpellPanel(){
		super();
		heal = new JButton("Heal");
		add(heal);
		heal.setVisible(true);
		
	}
}
