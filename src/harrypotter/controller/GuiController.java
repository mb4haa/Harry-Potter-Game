package harrypotter.controller;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;

import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.NotEnoughIPException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.exceptions.OutOfRangeException;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.Potion;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.tournament.TournamentListener;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.Direction;
import harrypotter.view.GameView;
import harrypotter.view.MapButton;
import harrypotter.view.View;

public class GuiController implements ActionListener, WindowListener, KeyListener, TournamentListener{
	private Tournament tournament;
	private GameView gui;
	private Wizard currWizard;
	private ArrayList<Spell> currSpells;
	private Spell currSpell;
	private Direction spellDir;

	public GameView getGui() {
		return gui;
	}

	public void setGui(GameView gui) {
		this.gui = gui;
	}

	public GuiController() throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		setTournament(new Tournament());
		tournament.setLis(this);
		gui = new GameView(this);
		gui.setwL(this);
		currSpells = new ArrayList<Spell>();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		switch (e.getActionCommand()) {
		case "NewGame":
			gui.champSelectView();
			break;
		case "Gryffindor":
			gui.gryffindorSelectView();
			gui.soundChampSlect("Gryffindor");
			break;
		case "Slytherin":
			gui.slytherinSelectView();
			gui.soundChampSlect("Slytherin");
			break;
		case "Ravenclaw":
			gui.ravenclawSelectView();
			gui.soundChampSlect("Ravenclaw");
			break;
		case "Hufflepuff":
			gui.hufflepuffSelectView();
			gui.soundChampSlect("Hufflepuff");
			break;
		case "Back":
			gui.backView();
			break;
		case "helBook":
			gui.helSpellSelectView();
			break;
		case "dmgBook":
			gui.dmgSpellSelectView();
			break;
		case "relBook":
			gui.relSpellSelectView();
			break;
		case "Gryffindor_Male":
			GryffindorMaleSelect();
			break;
		case "Gryffindor_Female":
			GryffindorFemaleSelect();
			break;
		case "Slytherin_Male":
			SlytherinMaleSelect();
			break;
		case "Slytherin_Female":
			SlytherinFemaleSelect();
			break;
		case "Ravenclaw_Male":
			RavenclawMaleSelect();
			break;
		case "Ravenclaw_Female":
			RavenclawFemaleSelect();
			break;
		case "Hufflepuff_Male":
			HufflepuffMaleSelect();
			break;
		case "Hufflepuff_Female":
			HufflepuffFemaleSelect();
			break;
		case "Sectumsempra":
			addSpell("Sectumsempra");
			break;
		case "remSectumsempra":
			removeSpell("Sectumsempra");
			break;
		case "Reducto":
			addSpell("Reducto");
			break;
		case "remReducto":
			removeSpell("Reducto");
			break;
		case "Piertotum Locomotor":
			addSpell("Piertotum Locomotor");
			break;
		case "remPiertotum Locomotor":
			removeSpell("Piertotum Locomotor");
			break;
		case "Expulso":
			addSpell("Expulso");
			break;
		case "remExpulso":
			removeSpell("Expulso");
			break;
		case "Oppugno":
			addSpell("Oppugno");
			break;
		case "remOppugno":
			removeSpell("Oppugno");
			break;
		case "Incendio":
			addSpell("Incendio");
			break;
		case "remIncendio":
			removeSpell("Incendio");
			break;
		case "Bombarda":
			addSpell("Bombarda");
			break;
		case "remBombarda":
			removeSpell("Bombarda");
			break;
		case "Avada Kedavra":
			addSpell("Avada Kedavra");
			break;
		case "remAvada Kedavra":
			removeSpell("Avada Kedavra");
			break;
		case "Crucio":
			addSpell("Crucio");
			break;
		case "remCrucio":
			removeSpell("Crucio");
			break;
		case "Igni":
			addSpell("Igni");
			break;
		case "remIgni":
			removeSpell("Igni");
			break;
		case "Kamehameha":
			addSpell("Kamehameha");
			break;
		case "remKamehameha":
			removeSpell("Kamehameha");
			break;
		case "Cheering Charm":
			addSpell("Cheering Charm");
			break;
		case "remCheering Charm":
			removeSpell("Cheering Charm");
			break;
		case "Expecto Patronum":
			addSpell("Expecto Patronum");
			break;
		case "remExpecto Patronum":
			removeSpell("Expecto Patronum");
			break;
		case "Ferula":
			addSpell("Ferula");
			break;
		case "remFerula":
			removeSpell("Ferula");
			break;
		case "Protego Horribilis":
			addSpell("Protego Horribilis");
			break;
		case "remProtego Horribilis":
			removeSpell("Protego Horribilis");
			break;
		case "Rennervate":
			addSpell("Rennervate");
			break;
		case "remRennervate":
			removeSpell("Rennervate");
			break;
		case "Quen":
			addSpell("Quen");
			break;
		case "remQuen":
			removeSpell("Quen");
			break;
		case "Accio":
			addSpell("Accio");
			break;
		case "remAccio":
			removeSpell("Accio");
			break;
		case "Imperio":
			addSpell("Imperio");
			break;
		case "remImperio":
			removeSpell("Imperio");
			break;
		case "Wingardium Leviosa":
			addSpell("Wingardium Leviosa");
			break;
		case "remWingardium Leviosa":
			removeSpell("Wingardium Leviosa");
			break;
		case "Axii":
			addSpell("Axii");
			break;
		case "remAxii":
			removeSpell("Axii");
			break;
		case "Finish Champ":
			FinishChamp();
			break;
		case "Start Tournament":
			try {
				startTournament();
			} catch (IOException e1) {
			}
			break;
		case "RandGen":
			try {
				randGen();
			} catch (IOException e1) {
			}
			break;
		case "Start First Task":
			gui.firstTaskView();
			break;
		case "Start Second Task":
			gui.secondTaskView();
			break;
		case "Start Third Task":
			gui.thirdTaskView();
			break;
		case "castLeft":
			castSpell(Direction.LEFT);
			spellDir = Direction.LEFT;
			break;
		case "castRight":
			castSpell(Direction.RIGHT);
			spellDir = Direction.RIGHT;
			break;
		case "castForward":
			castSpell(Direction.FORWARD);
			spellDir = Direction.FORWARD;
			break;
		case "castBackward":
			castSpell(Direction.BACKWARD);
			spellDir = Direction.BACKWARD;
			break;
		case "Cast":
			MapButton mB = (MapButton)e.getSource();
			castRel(mB);
			break;
		case "Move":
			MapButton mB1 = (MapButton) e.getSource();
			slythMove(mB1);
			break;
		}
	}

	private void slythMove(MapButton mB) {
		if(gui.getTaskCount() == 1){
			try {
				((SlytherinWizard)gui.getTask1().getTask().getCurrentChamp()).setTraitDirection(gui.getTask1().getSlythDirection(mB));
				((SlytherinWizard)gui.getTask1().getTask().getCurrentChamp()).useTrait();
				gui.getTask1().generateMap();
			} catch (InCooldownException | OutOfBordersException | InvalidTargetCellException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		
	public void randGen() throws IOException {
		SlytherinWizard w1 = new SlytherinWizard("Islam");
		w1.setModel(new ImageIcon("./Images/Models/slythboywand.png"));
		gui.makeChampJPanel("Slytherin", "Slytherin_Male");
		w1.setGender("Male");
		w1.getSpells().add(new DamagingSpell("Sectumsempra",150,5,300));
		w1.getSpells().add(new HealingSpell("Rennervate",100,3,200));
		w1.getSpells().add(new RelocatingSpell("Wingardium Leviosa", 300, 5, 5));
		tournament.addChampion(w1);
		gui.champ1.getname().setName("Islam");
		gui.setChampCount(1);

		RavenclawWizard w2 = new RavenclawWizard("Yasmeen");
		w2.setModel(new ImageIcon("./Images/Models/ravengirlwand.png"));
		w2.setGender("Female");
		gui.makeChampJPanel("Ravenclaw", "Ravenclaw_Female");
		w2.getSpells().add(new DamagingSpell("Sectumsempra",150,5,300));
		w2.getSpells().add(new HealingSpell("Rennervate",100,3,200));
		w2.getSpells().add(new RelocatingSpell("Wingardium Leviosa", 300, 5, 5));
		tournament.addChampion(w2);
		gui.champ2.getname().setName("Yasmeen");
		gui.setChampCount(2);

		GryffindorWizard w3 = new GryffindorWizard("Bahaa");
		w3.setModel(new ImageIcon("./Images/Models/griffboywand.png"));
		w3.setGender("Male");
		gui.makeChampJPanel("Gryffindor", "Gryffindor_Male");
		w3.getSpells().add(new DamagingSpell("Sectumsempra",150,5,300));
		w3.getSpells().add(new HealingSpell("Rennervate",100,3,200));
		w3.getSpells().add(new RelocatingSpell("Wingardium Leviosa", 300, 5, 5));
		tournament.addChampion(w3);
		gui.champ3.getname().setName("Bahaa");
		gui.setChampCount(3);

		RavenclawWizard w4 = new RavenclawWizard("Sherif");
		w4.setModel(new ImageIcon("./Images/Models/ravenboywand.png"));
		w4.setGender("Male");
		gui.makeChampJPanel("Ravenclaw", "Ravenclaw_Male");
		w4.getSpells().add(new DamagingSpell("Sectumsempra",150,5,300));
		w4.getSpells().add(new HealingSpell("Rennervate",100,3,200));
		w4.getSpells().add(new RelocatingSpell("Wingardium Leviosa", 300, 5, 5));
		tournament.addChampion(w4);
		gui.champ4.getname().setName("Sherif");
		gui.setChampCount(4);

		gui.champSelectView();
	}

	public void GryffindorMaleSelect() {
		gui.getBackgroundHoba().remove(gui.getGryffindorView());
		gui.setPreviousView(View.GRYFFINDORSELECTVIEW);
		gui.spellSelectView();
		gui.makeChampJPanel("Gryffindor", "Gryffindor_Male");
		currWizard = new GryffindorWizard(gui.name.getText());
		currWizard.setModel(new ImageIcon("./Images/Models/griffboywand.png"));
		currWizard.setGender("Male");
		gui.name.setText("");
	}

	public void GryffindorFemaleSelect() {
		gui.getBackgroundHoba().remove(gui.getGryffindorView());
		gui.setPreviousView(View.GRYFFINDORSELECTVIEW);
		gui.spellSelectView();
		gui.makeChampJPanel("Gryffindor", "Gryffindor_Female");
		currWizard = new GryffindorWizard(gui.name.getText());
		currWizard.setModel(new ImageIcon("./Images/Models/griffgirlwand.png"));
		currWizard.setGender("Female");
		gui.name.setText("");
	}

	public void SlytherinMaleSelect() {
		gui.getBackgroundHoba().remove(gui.getSlytherinView());
		gui.setPreviousView(View.SLYTHERINSELECTVIEW);
		gui.spellSelectView();
		gui.makeChampJPanel("Slytherin", "Slytherin_Male");
		currWizard = new SlytherinWizard(gui.name.getText());
		currWizard.setModel(new ImageIcon("./Images/Models/slythboywand.png"));
		currWizard.setGender("Male");
		gui.name.setText("");
	}

	public void SlytherinFemaleSelect() {
		gui.getBackgroundHoba().remove(gui.getSlytherinView());
		gui.setPreviousView(View.SLYTHERINSELECTVIEW);
		gui.spellSelectView();
		gui.makeChampJPanel("Slytherin", "Slytherin_Female");
		currWizard = new SlytherinWizard(gui.name.getText());
		currWizard.setModel(new ImageIcon("./Images/Models/slythgirlwand.png"));
		currWizard.setGender("Female");
		gui.name.setText("");
	}

	public void RavenclawMaleSelect() {
		gui.getBackgroundHoba().remove(gui.getRavenclawView());
		gui.setPreviousView(View.RAVENCLAWSELECTVIEW);
		gui.spellSelectView();
		gui.makeChampJPanel("Ravenclaw", "Ravenclaw_Male");
		currWizard = new RavenclawWizard(gui.name.getText());
		currWizard.setModel(new ImageIcon("./Images/Models/ravenboywand.png"));
		currWizard.setGender("Male");
		gui.name.setText("");
	}

	public void RavenclawFemaleSelect() {
		gui.getBackgroundHoba().remove(gui.getRavenclawView());
		gui.setPreviousView(View.RAVENCLAWSELECTVIEW);
		gui.spellSelectView();
		gui.makeChampJPanel("Ravenclaw", "Ravenclaw_Female");
		currWizard = new RavenclawWizard(gui.name.getText());
		currWizard.setModel(new ImageIcon("./Images/Models/ravengirlwand.png"));
		currWizard.setGender("Female");
		gui.name.setText("");
	}

	public void HufflepuffMaleSelect() {
		gui.getBackgroundHoba().remove(gui.getHufflepuffView());
		gui.setPreviousView(View.HUFFLEPUFFSELECTVIEW);
		gui.spellSelectView();
		gui.makeChampJPanel("Hufflepuff", "Hufflepuff_Male");
		currWizard = new HufflepuffWizard(gui.name.getText());
		currWizard.setModel(new ImageIcon("./Images/Models/huffleboywand.png"));
		currWizard.setGender("Male");
		gui.name.setText("");
	}

	public void HufflepuffFemaleSelect() {
		gui.getBackgroundHoba().remove(gui.getHufflepuffView());
		gui.setPreviousView(View.HUFFLEPUFFSELECTVIEW);
		gui.spellSelectView();
		gui.makeChampJPanel("Hufflepuff", "Hufflepuff_Female");
		currWizard = new HufflepuffWizard(gui.name.getText());
		currWizard.setModel(new ImageIcon("./Images/Models/hufflegirlwand.png"));
		currWizard.setGender("Female");
		gui.name.setText("");
	}

	public static void main(String[] args) throws IOException, LineUnavailableException, UnsupportedAudioFileException {
		new GuiController();
	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowClosed(WindowEvent e) {
		gui.clip.stop();
		gui.clip.close();
	}

	@Override
	public void windowClosing(WindowEvent e) {
		gui.clip.stop();
		gui.clip.close();

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowOpened(WindowEvent e) {

	}

	public void addSpell(String spellName) {
		for (Spell spell : tournament.getSpells()) {
			if (spell.getName().equals(spellName)) {
				if (spell instanceof DamagingSpell) {
					Spell s = new DamagingSpell(spell.getName(), spell.getCost(), spell.getDefaultCooldown(),
							((DamagingSpell) spell).getDamageAmount());
					currSpells.add(s);
				} else if (spell instanceof HealingSpell) {
					Spell s = new HealingSpell(spell.getName(), spell.getCost(), spell.getDefaultCooldown(),
							((HealingSpell) spell).getHealingAmount());
					currSpells.add(s);
				} else {
					Spell s = new RelocatingSpell(spell.getName(), spell.getCost(), spell.getDefaultCooldown(),
							((RelocatingSpell) spell).getRange());
					currSpells.add(s);
				}
			}
		}
	}

	public void removeSpell(String spellName) {
		for (Spell spell : currSpells) {
			if (spell.getName().equals(spellName)) {
				currSpells.remove(spell);
			}
		}
	}

	public Wizard getCurrWizard() {
		return currWizard;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public void FinishChamp() {
		for (Spell spell : currSpells) {
			currWizard.getSpells().add(spell);
		}
		tournament.addChampion(currWizard);
		gui.setChampCount(gui.getChampCount() + 1);
		gui.champSelectView();
		gui.closeAudio();
		gui.setSpellCount(0);
		currSpells = new ArrayList<Spell>();
	}

	public void startTournament() throws IOException {
		gui.firstTaskInstruction();
		tournament.beginTournament();

	}

	@Override
	public void keyPressed(KeyEvent arg0) {

	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (gui.getTaskCount() == 1) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				try {
					if(tournament.getFirstTask().getAllowedMoves() == 1){
						Point p = ((Wizard)tournament.getFirstTask().getCurrentChamp()).getLocation();
						if(p.x - 1 >= 0 && tournament.getFirstTask().getMap()[p.x - 1][p.y] instanceof CollectibleCell){
							gui.getTask1().notifyPotion((Potion)((CollectibleCell)tournament.getFirstTask().getMap()[p.x-1][p.y]).getCollectible());
						}
						gui.getTask1().getTask().moveForward();
						gui.getTask1().generateMapDragon();
					}
					else{
						Point p = ((Wizard)tournament.getFirstTask().getCurrentChamp()).getLocation();
						if(tournament.getFirstTask().getMap()[p.x - 1][p.y] instanceof CollectibleCell){
							gui.getTask1().notifyPotion((Potion)((CollectibleCell)tournament.getFirstTask().getMap()[p.x-1][p.y]).getCollectible());
						}
						gui.getTask1().getTask().moveForward();
						gui.getTask1().generateMap();
					}
				} 
				catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				}
			} 
			else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				try {
					if(tournament.getFirstTask().getAllowedMoves() == 1){
						Point p = ((Wizard)tournament.getFirstTask().getCurrentChamp()).getLocation();
						if(p.x + 1 >= 0 && tournament.getFirstTask().getMap()[p.x + 1][p.y] instanceof CollectibleCell){
							gui.getTask1().notifyPotion((Potion)((CollectibleCell)tournament.getFirstTask().getMap()[p.x+1][p.y]).getCollectible());
						}
						gui.getTask1().getTask().moveBackward();
						gui.getTask1().generateMapDragon();
					}
					else{
						Point p = ((Wizard)tournament.getFirstTask().getCurrentChamp()).getLocation();
						if(tournament.getFirstTask().getMap()[p.x + 1][p.y] instanceof CollectibleCell){
							gui.getTask1().notifyPotion((Potion)((CollectibleCell)tournament.getFirstTask().getMap()[p.x+1][p.y]).getCollectible());
						}
						gui.getTask1().getTask().moveBackward();
						gui.getTask1().generateMap();
					}
				}
				catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				}
			} 
			else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				try {
					if(tournament.getFirstTask().getAllowedMoves() == 1){
						Point p = ((Wizard)tournament.getFirstTask().getCurrentChamp()).getLocation();
						if(p.y - 1 >= 0 && tournament.getFirstTask().getMap()[p.x][p.y - 1] instanceof CollectibleCell){
							gui.getTask1().notifyPotion((Potion)((CollectibleCell)tournament.getFirstTask().getMap()[p.x][p.y-1]).getCollectible());
						}
						gui.getTask1().getTask().moveLeft();
						gui.getTask1().generateMapDragon();
					}
					else{
						Point p = ((Wizard)tournament.getFirstTask().getCurrentChamp()).getLocation();
						if(tournament.getFirstTask().getMap()[p.x][p.y-1] instanceof CollectibleCell){
							gui.getTask1().notifyPotion((Potion)((CollectibleCell)tournament.getFirstTask().getMap()[p.x][p.y-1]).getCollectible());
						}
						gui.getTask1().getTask().moveLeft();
						gui.getTask1().generateMap();
					}
				}

				catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				}
			} 
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				try {
					if(tournament.getFirstTask().getAllowedMoves() == 1){
						Point p = ((Wizard)tournament.getFirstTask().getCurrentChamp()).getLocation();
						if(p.y + 1 >= 0 && tournament.getFirstTask().getMap()[p.x][p.y+1] instanceof CollectibleCell){
							gui.getTask1().notifyPotion((Potion)((CollectibleCell)tournament.getFirstTask().getMap()[p.x][p.y+1]).getCollectible());
						}
						gui.getTask1().getTask().moveRight();
						gui.getTask1().generateMapDragon();
					}
					else{
						Point p = ((Wizard)tournament.getFirstTask().getCurrentChamp()).getLocation();
						if(tournament.getFirstTask().getMap()[p.x][p.y+1] instanceof CollectibleCell){
							gui.getTask1().notifyPotion((Potion)((CollectibleCell)tournament.getFirstTask().getMap()[p.x][p.y+1]).getCollectible());
						}
						gui.getTask1().getTask().moveRight();
						gui.getTask1().generateMap();
					}
				} 
				catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				}
			} 
			else if (e.getKeyCode() == KeyEvent.VK_1) {
				gui.getTask1().castSpell1();
				currSpell = ((Wizard)tournament.getFirstTask().getCurrentChamp()).getSpells().get(0);
			} 
			else if (e.getKeyCode() == KeyEvent.VK_2) {
				gui.getTask1().castSpell2();
				currSpell = ((Wizard)tournament.getFirstTask().getCurrentChamp()).getSpells().get(1);

			} 
			else if (e.getKeyCode() == KeyEvent.VK_3) {
				gui.getTask1().castSpell3();
				currSpell = ((Wizard)tournament.getFirstTask().getCurrentChamp()).getSpells().get(2);
			}
			else if(e.getKeyCode() == KeyEvent.VK_T){
				gui.getTask1().useTrait();
			}
		} 
		else if (gui.getTaskCount() == 2) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				try {
					gui.getTask2().getTask().moveForward();
					gui.getTask2().generateMap();
				} 
				catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				}
			} 
			else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				try {
					gui.getTask2().getTask().moveBackward();
					gui.getTask2().generateMap();
				}
				catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				}
			} 
			else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				try {
					gui.getTask2().getTask().moveLeft();
					gui.getTask2().generateMap();
				} 
				catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				}
			} 
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				try {
					gui.getTask2().getTask().moveRight();
					gui.getTask2().generateMap();
				} 
				catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				}
			} 
			else if (e.getKeyCode() == KeyEvent.VK_1) {
				gui.getTask2().castSpell1();
				currSpell = ((Wizard)tournament.getSecondTask().getCurrentChamp()).getSpells().get(0);
			} 
			else if (e.getKeyCode() == KeyEvent.VK_2) {
				gui.getTask2().castSpell2();
				currSpell = ((Wizard)tournament.getSecondTask().getCurrentChamp()).getSpells().get(1);
			} 
			else if (e.getKeyCode() == KeyEvent.VK_3) {
				gui.getTask2().castSpell3();
				currSpell = ((Wizard)tournament.getSecondTask().getCurrentChamp()).getSpells().get(2);
			}
			else if(e.getKeyCode() == KeyEvent.VK_T){
				gui.getTask2().useTrait();
			}
		}
		else{
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				try {
					gui.getTask3().getTask().moveForward();
					gui.getTask3().generateMap();
				} 
				catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				}
			} 
			else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				try {
					gui.getTask3().getTask().moveBackward();
					gui.getTask3().generateMap();
				}
				catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				}
			} 
			else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				try {
					gui.getTask3().getTask().moveLeft();
					gui.getTask3().generateMap();
				} 
				catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				}
			} 
			else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				try {
					gui.getTask3().getTask().moveRight();
					gui.getTask3().generateMap();
				} 
				catch (OutOfBordersException | InvalidTargetCellException | IOException e1) {
				}
			} 
			else if (e.getKeyCode() == KeyEvent.VK_1) {
				gui.getTask3().castSpell1();
				currSpell = ((Wizard)tournament.getThirdTask().getCurrentChamp()).getSpells().get(0);
			} 
			else if (e.getKeyCode() == KeyEvent.VK_2) {
				gui.getTask3().castSpell2();
				currSpell = ((Wizard)tournament.getThirdTask().getCurrentChamp()).getSpells().get(1);
			} 
			else if (e.getKeyCode() == KeyEvent.VK_3) {
				gui.getTask3().castSpell3();
				currSpell = ((Wizard)tournament.getThirdTask().getCurrentChamp()).getSpells().get(2);
			}
			else if(e.getKeyCode() == KeyEvent.VK_T){
				gui.getTask3().useTrait();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	public void castSpell(Direction d){
		switch(d){
		case BACKWARD:
			if(gui.getTaskCount() == 1){
				if(currSpell instanceof DamagingSpell){
					try {
						gui.getTask1().getTask().castDamagingSpell((DamagingSpell) currSpell, Direction.BACKWARD);
					} catch (InCooldownException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NotEnoughIPException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (OutOfBordersException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidTargetCellException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					gui.getTask1().generateMap();
				}
				else{
					gui.getTask1().castRelocating(currSpell);
				}
			}
			else if(gui.getTaskCount() == 2){
				if(currSpell instanceof DamagingSpell){
					try {
						gui.getTask2().getTask().castDamagingSpell((DamagingSpell) currSpell, Direction.BACKWARD);
					} catch (InCooldownException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NotEnoughIPException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (OutOfBordersException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidTargetCellException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				gui.getTask2().generateMap();
			}
			else{
				if(currSpell instanceof DamagingSpell){
					try {
						gui.getTask3().getTask().castDamagingSpell((DamagingSpell) currSpell, Direction.BACKWARD);
					} catch (InCooldownException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NotEnoughIPException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (OutOfBordersException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidTargetCellException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				gui.getTask3().generateMap();
			}
			break;
		case FORWARD:
			if(gui.getTaskCount() == 1){
				if(currSpell instanceof DamagingSpell){
					try {
						gui.getTask1().getTask().castDamagingSpell((DamagingSpell) currSpell, Direction.FORWARD);
					} catch (InCooldownException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NotEnoughIPException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (OutOfBordersException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidTargetCellException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					gui.getTask1().generateMapDragon();
				}
				else{
					gui.getTask1().castRelocating(currSpell);
				}
			}
			else if(gui.getTaskCount() == 2){
				if(currSpell instanceof DamagingSpell){
					try {
						gui.getTask2().getTask().castDamagingSpell((DamagingSpell) currSpell, Direction.FORWARD);
					} catch (InCooldownException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NotEnoughIPException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (OutOfBordersException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidTargetCellException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				gui.getTask2().generateMap();
			}
			else{
				if(currSpell instanceof DamagingSpell){
					try {
						gui.getTask3().getTask().castDamagingSpell((DamagingSpell) currSpell, Direction.FORWARD);
					} catch (InCooldownException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NotEnoughIPException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (OutOfBordersException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidTargetCellException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				gui.getTask3().generateMap();
			}
			break;
		case LEFT:
			if(gui.getTaskCount() == 1){
				if(currSpell instanceof DamagingSpell){
					try {
						gui.getTask1().getTask().castDamagingSpell((DamagingSpell) currSpell, Direction.LEFT);
					} catch (InCooldownException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NotEnoughIPException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (OutOfBordersException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidTargetCellException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					gui.getTask1().generateMapDragon();
				}
				else{
					gui.getTask1().castRelocating(currSpell);
				}
			}
			else if(gui.getTaskCount() == 2){
				if(currSpell instanceof DamagingSpell){
					try {
						gui.getTask2().getTask().castDamagingSpell((DamagingSpell) currSpell, Direction.LEFT);
					} catch (InCooldownException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NotEnoughIPException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (OutOfBordersException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidTargetCellException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				gui.getTask2().generateMap();
			}
			else{
				if(currSpell instanceof DamagingSpell){
					try {
						gui.getTask3().getTask().castDamagingSpell((DamagingSpell) currSpell, Direction.LEFT);
					} catch (InCooldownException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NotEnoughIPException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (OutOfBordersException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidTargetCellException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				gui.getTask3().generateMap();
			}
			break;
		case RIGHT:
			if(gui.getTaskCount() == 1){
				if(currSpell instanceof DamagingSpell){
					try {
						gui.getTask1().getTask().castDamagingSpell((DamagingSpell) currSpell, Direction.RIGHT);
					} catch (InCooldownException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NotEnoughIPException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (OutOfBordersException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidTargetCellException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					gui.getTask1().generateMapDragon();
				}
				else{
					gui.getTask1().castRelocating(currSpell);
				}
			}
			else if(gui.getTaskCount() == 2){
				if(currSpell instanceof DamagingSpell){
					try {
						gui.getTask2().getTask().castDamagingSpell((DamagingSpell) currSpell, Direction.RIGHT);
					} catch (InCooldownException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NotEnoughIPException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (OutOfBordersException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidTargetCellException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				gui.getTask2().generateMap();
			}
			else{
				if(currSpell instanceof DamagingSpell){
					try {
						gui.getTask3().getTask().castDamagingSpell((DamagingSpell) currSpell, Direction.RIGHT);
					} catch (InCooldownException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NotEnoughIPException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (OutOfBordersException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (InvalidTargetCellException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				gui.getTask3().generateMap();
			}
			break;
		}

	}

	public void castRel(MapButton mB){
		if(gui.getTaskCount() == 1){
			gui.getTask1().setRelDir(mB.getXcor(), mB.getYcor());
			gui.getTask1().setRelRange(mB.getXcor(), mB.getYcor());
			System.out.println(gui.getTask1().getRelRange());
			try {
				gui.getTask1().getTask().castRelocatingSpell((RelocatingSpell) currSpell, spellDir, gui.getTask1().getRelDir(), gui.getTask1().getRelRange());
			} catch (InCooldownException | NotEnoughIPException | OutOfBordersException | InvalidTargetCellException
					| OutOfRangeException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gui.getTask1().generateMap();
		}
		else if(gui.getTaskCount() == 2){
			gui.getTask2().setRelDir(mB.getX(), mB.getY());
			gui.getTask2().setRelRange(mB.getX(), mB.getY());
			System.out.println("Here");
			try {
				gui.getTask2().getTask().castRelocatingSpell((RelocatingSpell) currSpell, spellDir, gui.getTask1().getRelDir(), gui.getTask1().getRelRange());
			} catch (InCooldownException | NotEnoughIPException | OutOfBordersException | InvalidTargetCellException
					| OutOfRangeException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			gui.getTask3().setRelDir(mB.getX(), mB.getY());
			gui.getTask3().setRelRange(mB.getX(), mB.getY());
			try {
				gui.getTask3().getTask().castRelocatingSpell((RelocatingSpell) currSpell, spellDir, gui.getTask1().getRelDir(), gui.getTask1().getRelRange());
			} catch (InCooldownException | NotEnoughIPException | OutOfBordersException | InvalidTargetCellException
					| OutOfRangeException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onFinishingFirst(String string) {
		if(string.equals("kamal")){
			try {
				gui.secondTaskInstruction();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			gui.allDeadView();
		}

	}

	@Override
	public void onFinishingSecond(String string) {
		if(string.equals("kamal")){
			try {
				gui.thirdTaskInstruction();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			gui.allDeadView();
		}

	}

	@Override
	public void onFinishingThird() {
		// TODO Auto-generated method stub

	}


}