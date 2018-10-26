package harrypotter.view;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;

@SuppressWarnings("serial")
public class ChampJPanel3 extends JPanel {

	private JLabel house;
	private JLabel model;
	private JLabel name;
	private Wizard wizard;
	private JLabel hp;
	private JLabel ip;
	ActionListener aL;

	public ChampJPanel3(ActionListener listener, Wizard w) {
		super();
		wizard = w;
		aL = listener;
		if(wizard == null){
			return;
		}
		setLayout(null);
		setSize(640, 1080);
		house = new JLabel();
		model = new JLabel();
		if (wizard instanceof GryffindorWizard) {
			setHouse("Gryffindor");
		} else if (wizard instanceof HufflepuffWizard) {
			setHouse("Hufflepuff");
		} else if (wizard instanceof RavenclawWizard) {
			setHouse("Ravenclaw");
		} else {
			setHouse("Slytherin");
		}
		setCName();
		setStats();
		setModel();
		setOpaque(false);
	}

	public void setHouse(String houseName) {
		switch (houseName) {
		case "Gryffindor":
			house.setIcon(new ImageIcon("./Images/Crests/Gryffindor_crest3.png"));
			break;
		case "Hufflepuff":
			house.setIcon(new ImageIcon("./Images/Crests/Hufflepuff_crest3.png"));
			break;
		case "Ravenclaw":
			house.setIcon(new ImageIcon("./Images/Crests/Ravenclaw_crest3.png"));
			break;
		case "Slytherin":
			house.setIcon(new ImageIcon("./Images/Crests/Slytherin_crest3.png"));
			break;
		}
		add(house);
		house.setSize(100, 110);
		house.setBounds(430, 100, 100, 110);
		house.setVisible(true);
		house.setOpaque(false);
	}

	public void setModel() {
		model.setIcon(null);
		if(wizard instanceof GryffindorWizard){
			GryffindorWizard gw = (GryffindorWizard) wizard;
			if(gw.getGender().equals("Male")){
				model.setIcon(new ImageIcon("./Images/Models/griffboywand2.png"));
			}
			else{
				model.setIcon(new ImageIcon("./Images/Models/griffgirlwand2.png"));
			}
		}
		else if(wizard instanceof SlytherinWizard){
			SlytherinWizard sw= (SlytherinWizard) wizard;
			if(sw.getGender() == "Male"){
				model.setIcon(new ImageIcon("./Images/Models/slythboywand2.png"));
			}
			else{
				model.setIcon(new ImageIcon("./Images/Models/slythgirlwand2.png"));
			}
		}
		else if(wizard instanceof RavenclawWizard){
			RavenclawWizard rw = (RavenclawWizard)wizard;
			if(rw.getGender() == "Male"){
				model.setIcon(new ImageIcon("./Images/Models/ravenboywand2.png"));
			}
			else{
				model.setIcon(new ImageIcon("./Images/Models/ravengirlwand2.png"));
			}
		}
		else{
			HufflepuffWizard hw = (HufflepuffWizard)wizard;
			if(hw.getGender() == "Male"){
				model.setIcon(new ImageIcon("./Images/Models/huffboywand2.png"));
			}
			else{
				model.setIcon(new ImageIcon("./Images/Models/huffgirlwand2.png"));
			}
		}
		add(model);
		model.setSize(250,500);
		model.setBounds(195, 100, 250, 500);
		model.setVisible(true);
		model.setOpaque(false);
	}

	public void setStats() {
		hp = new JLabel("Hp: " + wizard.getHp());
		ip = new JLabel("Ip: " + wizard.getIp());
		add(hp);
		add(ip);
		hp.setBounds(170, 650, 300, 50);
		ip.setBounds(170, 700, 300, 50);
		hp.setFont(new Font("Harry P", Font.PLAIN, 30));
		ip.setFont(new Font("Harry P", Font.PLAIN, 30));
		hp.setHorizontalAlignment(SwingConstants.CENTER);
		ip.setHorizontalAlignment(SwingConstants.CENTER);
		hp.setVisible(true);
		hp.setOpaque(false);
		ip.setVisible(true);
		ip.setOpaque(false);
	}

	public void setCName() {
		name = new JLabel(wizard.getName());
		add(name);
		name.setBounds(170, 600, 300, 50);
		name.setFont(new Font("Harry P", Font.PLAIN, 30));
		name.setVisible(true);
		name.setOpaque(false);
		name.setHorizontalAlignment(SwingConstants.CENTER);

	}

	public Wizard getWizard() {
		return wizard;
	}

	public void setWizard(Wizard wizard) {
		this.wizard = wizard;
	}

	public void update(){
		if(wizard == null){
			return;
		}
		removeAll();
		if (wizard instanceof GryffindorWizard) {
			setHouse("Gryffindor");
		} else if (wizard instanceof HufflepuffWizard) {
			setHouse("Hufflepuff");
		} else if (wizard instanceof RavenclawWizard) {
			setHouse("Ravenclaw");
		} else {
			setHouse("Slytherin");
		}
		setCName();
		setStats();
		setModel();
	}
	
}
