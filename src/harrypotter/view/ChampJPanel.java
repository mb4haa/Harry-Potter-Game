package harrypotter.view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.Spell;

@SuppressWarnings("serial")
public class ChampJPanel extends JPanel {
	private JLabel house;
	private String houseN;
	private String modelN;
	private JLabel model;
	private JLabel name;

	public JLabel getname() {
		return name;
	}

	public void setname(JLabel name) {
		this.name = name;
	}

	private JLabel hp;
	private JLabel ip;
	private JTextArea spell1;

	public JTextArea getSpell1() {
		return spell1;
	}

	public void setSpell1(JTextArea spell1) {
		this.spell1 = spell1;
	}

	private JTextArea spell2;

	public JTextArea getSpell2() {
		return spell2;
	}

	public void setSpell2(JTextArea spell2) {
		this.spell2 = spell2;
	}

	private JTextArea spell3;

	public JTextArea getSpell3() {
		return spell3;
	}

	public void setSpell3(JTextArea spell3) {
		this.spell3 = spell3;
	}

	private Wizard wizard;
	private int count;
	private JButton next;
	private ActionListener aL;

	public ChampJPanel(ActionListener aL) {
		super();
		this.aL = aL;
		setLayout(null);
		setSize(960, 1080);
		house = new JLabel();
		next = new JButton("Finish");
		next.addActionListener(this.aL);
		next.setActionCommand("Finish Champ");
		next.setFont(new Font("Harry P", Font.BOLD, 50));
		next.setSize(150, 100);
		add(next);
		next.setVisible(false);
		next.setBounds(740, 900, 180, 100);
		model = new JLabel();
		count = 0;
	}

	public void setHouse(String houseName) {
		switch (houseName) {
		case "Gryffindor":
			house.setIcon(new ImageIcon("./Images/Crests/Gryffindor_crest3.png"));
			wizard = new GryffindorWizard(name.getText());
			setHouseN("Gryffindor");
			break;
		case "Hufflepuff":
			house.setIcon(new ImageIcon("./Images/Crests/Hufflepuff_crest3.png"));
			wizard = new HufflepuffWizard(name.getText());
			setHouseN("Hufflepuff");
			break;
		case "Ravenclaw":
			house.setIcon(new ImageIcon("./Images/Crests/Ravenclaw_crest3.png"));
			wizard = new RavenclawWizard(name.getText());
			setHouseN("Ravenclaw");
			break;
		case "Slytherin":
			house.setIcon(new ImageIcon("./Images/Crests/Slytherin_crest3.png"));
			wizard = new SlytherinWizard(name.getText());
			setHouseN("Slytherin");
			break;
		}
		add(house);
		house.setSize(100, 110);
		house.setBounds(630, 100, 100, 110);
		house.setVisible(true);
		house.setOpaque(false);
		setStats();
	}

	public void setModel(String houseModelName) {
		switch (houseModelName) {
		case "Gryffindor_Male":
			model.setIcon(new ImageIcon("./Images/Models/griffboywand.png"));
			setModelN("griffboywand");
			break;
		case "Gryffindor_Female":
			model.setIcon(new ImageIcon("./Images/Models/griffgirlwand.png"));
			setModelN("griffgirlwand");
			break;
		case "Hufflepuff_Male":
			model.setIcon(new ImageIcon("./Images/Models/huffboywand.png"));
			setModelN("huffboywand");
			break;
		case "Hufflepuff_Female":
			model.setIcon(new ImageIcon("./Images/Models/huffgirlwand.png"));
			setModelN("huffgirlwand");
			break;
		case "Ravenclaw_Male":
			model.setIcon(new ImageIcon("./Images/Models/ravenboywand.png"));
			setModelN("ravenboywand");
			break;
		case "Ravenclaw_Female":
			model.setIcon(new ImageIcon("./Images/Models/ravengirlwand.png"));
			setModelN("ravengirlwand");
			break;
		case "Slytherin_Male":
			model.setIcon(new ImageIcon("./Images/Models/slythboywand.png"));
			setModelN("slythboywand");
			break;
		case "Slytherin_Female":
			model.setIcon(new ImageIcon("./Images/Models/slythgirlwand.png"));
			setModelN("slythgirlwand");
			break;
		}
		add(model);
		model.setBounds(330, 100, 300, 600);
		model.setVisible(true);
		model.setOpaque(false);
	}

	public void setName(String name) {
		this.name = new JLabel(name);
		this.name.setName(name);
		if(this.name.getName() == null)
			this.name.setName("");
		add(this.name);
		this.name.setBounds(330, 700, 300, 50);
		this.name.setFont(new Font("Harry P", Font.PLAIN, 30));
		this.name.setVisible(true);
		this.name.setOpaque(false);
		this.name.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public void setStats() {
		hp = new JLabel("Hp: " + wizard.getHp());
		ip = new JLabel("Ip: " + wizard.getIp());
		add(hp);
		add(ip);
		hp.setBounds(330, 750, 300, 50);
		ip.setBounds(330, 800, 300, 50);
		hp.setFont(new Font("Harry P", Font.PLAIN, 30));
		ip.setFont(new Font("Harry P", Font.PLAIN, 30));
		hp.setHorizontalAlignment(SwingConstants.CENTER);
		ip.setHorizontalAlignment(SwingConstants.CENTER);
		hp.setVisible(true);
		hp.setOpaque(false);
		ip.setVisible(true);
		ip.setOpaque(false);
	}

	public void addSpell1(Spell s) {
		spell1 = new JTextArea(s.getName() + "\nIpCost: " + s.getCost() + "\nCooldown: " + s.getCoolDown());
		spell1.setFont(new Font("Harry P", Font.BOLD, 24));
		spell1.setName(s.getName());
		add(spell1);
		spell1.setEnabled(false);
		spell1.setBounds(180, 900, 200, 100);
		spell1.setVisible(true);
		spell1.setOpaque(false);
		count = 1;
	}

	public void addSpell2(Spell s) {
		spell2 = new JTextArea(s.getName() + "\nIpCost: " + s.getCost() + "\nCooldown: " + s.getDefaultCooldown());
		spell2.setFont(new Font("Harry P", Font.BOLD, 24));
		spell2.setEnabled(false);
		spell2.setName(s.getName());
		add(spell2);
		spell2.setBounds(380, 900, 200, 100);
		spell2.setVisible(true);
		spell2.setOpaque(false);
		count = 2;
	}

	public void addSpell3(Spell s) {
		spell3 = new JTextArea(s.getName() + "\nIpCost: " + s.getCost() + "\nCooldown: " + s.getCoolDown());
		spell3.setFont(new Font("Harry P", Font.BOLD, 24));
		add(spell3);
		spell3.setName(s.getName());
		spell3.setEnabled(false);
		spell3.setBounds(580, 900, 200, 100);
		spell3.setVisible(true);
		spell3.setOpaque(false);
		count = 3;
		next.setVisible(true);
	}

	public void removeSpell(Spell s) {
		if (spell1.getName().equals(s.getName())) {
			if (count == 3) {
				spell1.setText(spell2.getText());
				spell1.setName(spell2.getName());
				spell2.setText(spell3.getText());
				spell2.setName(spell3.getName());
				spell3.setText("");
				remove(spell3);
				spell3.setVisible(false);
			} else if (count == 2) {
				spell1.setText(spell2.getText());
				spell1.setName(spell2.getName());
				remove(spell2);
				spell2.setVisible(false);
			} else {
				remove(spell1);
				spell1.setVisible(false);
			}
		} else if (spell2.getName().equals(s.getName())) {
			if (count == 3) {
				spell2.setText(spell3.getText());
				spell2.setName(spell3.getName());
				spell3.setText("");
				remove(spell3);
				spell3.setVisible(false);
			} else {
				remove(spell2);
				spell2.setVisible(false);
			}
		} else {
			spell3.setText("");
			remove(spell3);
			spell3.setVisible(false);
		}
		count--;
		revalidate();
		repaint();
		next.setVisible(false);
	}

	public String getHouseN() {
		return houseN;
	}

	public void setHouseN(String houseN) {
		this.houseN = houseN;
	}

	public String getModelN() {
		return modelN;
	}

	public void setModelN(String modelN) {
		this.modelN = modelN;
	}

}
