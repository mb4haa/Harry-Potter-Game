package harrypotter.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DmgSpellPanel extends JPanel{
	
	JButton forward;
	JButton backward;
	JButton left;
	JButton right;
	
	public DmgSpellPanel(){
		super();
		setLayout(new GridLayout(4, 0));
		forward = new JButton("Cast Up");
		add(forward);
		forward.setVisible(true);
		backward = new JButton("Cast Down");
		add(backward);
		backward.setVisible(true);
		left = new JButton("Cast Left");
		add(left);
		left.setVisible(true);
		right = new JButton("Cast Right");
		add(right);
		right.setVisible(true);
	}
	
}
