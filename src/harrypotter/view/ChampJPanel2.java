package harrypotter.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ChampJPanel2 extends JPanel{
	private ActionListener aL;
	private ChampJPanel champ;
	private JLabel house;
	private JLabel model;
	private JLabel text;
	
	public ChampJPanel2(ActionListener aL, ChampJPanel cJP) {
		super();
		this.aL = aL;
		setLayout(null);
		setSize(250,600);
		champ = cJP;
		house = new JLabel();
		model = new JLabel();
		text = new JLabel("" + champ.getname().getName());
		text.setFont(new Font("Harry P" , Font.BOLD, 50));
		model.setIcon(new ImageIcon("./Images/Models/" + champ.getModelN() + "2.png"));
		house.setIcon(new ImageIcon("./Images/Crests/" + champ.getHouseN() + "_crest4.png"));
		add(model);
		add(house);
		text.setForeground(Color.YELLOW);
		add(text);
		model.setBounds(0,0,250,500);
		model.setVisible(true);
		model.setOpaque(false);
		house.setBounds(0,500,91,100);
		house.setVisible(true);
		house.setOpaque(false);
		text.setBounds(91,500,159,100);
		text.setVisible(true);
		text.setOpaque(false);
		setForeground(Color.WHITE);
	}
	
}
