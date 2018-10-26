package harrypotter.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import harrypotter.controller.GuiController;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.DamagingSpell;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.FirstTask;
import harrypotter.model.tournament.Tournament;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.ObstacleCell;

@SuppressWarnings("serial")
public class GameView extends JFrame {
	private ActionListener aL;
	private KeyListener kL;

	private JLabel background;
	private JPanel startView;
	private JPanel champSelectView;
	private JPanel gryffindorSelectView;
	private JPanel ravenclawSelectView;
	private JPanel slytherinSelectView;
	private JPanel hufflepuffSelectView;
	private JPanel spellSelectView;
	private JPanel dmgSpellSelectView;
	private JPanel helSpellSelectView;
	private JPanel relSpellSelectView;
	private FirstTaskInstruction firstTaskInstruction;
	private FirstTaskMap task1;
	private JPanel secondTaskInstruction;
	private SecondTaskMap task2;
	private JPanel thirdTaskInstruction;
	private ThirdTaskMap task3;
	private JPanel allDeadView;
	private int taskCount;

	private JLabel welcome;
	public JTextField	 name = new JTextField();
	private JButton back = new JButton("Back");
	private JPanel spellInfo;
	public ChampJPanel champ1;
	public ChampJPanel2 champ1M;
	public ChampJPanel champ2;
	public ChampJPanel2 champ2M;
	public ChampJPanel champ3;
	public ChampJPanel2 champ3M;
	public ChampJPanel champ4;
	public ChampJPanel2 champ4M;

	public View currentView;
	public View previousView;
	private int champCount;

	private ArrayList<Spell> spells = Tournament.loadSpells2("Spells.csv");
	private File file = new File("./Audio/Theme.wav");
	public Clip clip = AudioSystem.getClip();
	private WindowListener wL;
	private File lion = new File("./Audio/Lion.wav");
	private Clip clipL = AudioSystem.getClip();
	private File raven = new File("./Audio/Raven.wav");
	private Clip clipR = AudioSystem.getClip();
	private File snake = new File("./Audio/Snake.wav");
	private Clip clipS = AudioSystem.getClip();

	public int getChampCount() {
		return champCount;
	}

	public void setChampCount(int champCount) {
		this.champCount = champCount;
	}

	private int spellCount;

	public int getSpellCount() {
		return spellCount;
	}

	public void setSpellCount(int spellCount) {
		this.spellCount = spellCount;
	}

	public GameView(GuiController adapter)
			throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aL = adapter;
		kL = adapter;
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		champ1 = new ChampJPanel(aL);
		champ2 = new ChampJPanel(aL);
		champ3 = new ChampJPanel(aL);
		champ4 = new ChampJPanel(aL);
		background = new JLabel(new ImageIcon("./Images/hogwarts.jpg"));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight()));
		add(background);
		background.setLayout(null);
		setTitle("Harry Potter");
		setFont(new Font("Harry P", Font.PLAIN, 14));
		startView();
		styleButton(back);
		spellInfo = new JPanel();
		welcome = new JLabel("Welcome to the common room young?");
		welcome.setFont(new Font("Harry P", Font.BOLD, 40));
		welcome.setForeground(Color.YELLOW);
		name.setFont(new Font("Harry P", Font.PLAIN, 32));
		back.setForeground(Color.YELLOW);
		back.setFont(new Font("Harry P", Font.BOLD, 80));
		back.setVisible(true);
		back.setOpaque(false);
		back.setContentAreaFilled(false);
		back.setPreferredSize(new Dimension(100, 100));
		back.setActionCommand("Back");
		back.addActionListener(aL);
		setUndecorated(true);
		audio();
		champCount = 0;
		setVisible(true);
	}

	public void backView() {
		background.removeAll();
		if (previousView == View.STARTVIEW) {
			background.setIcon(null);
			startView();
		} else if (previousView == View.CHAMPSELECTVIEW) {
			background.setIcon(null);
			champSelectView();
			closeAudio();
		} else if (previousView == View.GRYFFINDORSELECTVIEW) {
			gryffindorSelectView();
		} else if (previousView == View.HUFFLEPUFFSELECTVIEW) {
			hufflepuffSelectView();
		} else if (previousView == View.SLYTHERINSELECTVIEW) {
			slytherinSelectView();
		} else if (previousView == View.RAVENCLAWSELECTVIEW) {
			ravenclawSelectView();
		} else if (previousView == View.SPELSELECTVIEW) {
			spellSelectView();
		}
	}

	public void startView() {
		background.setIcon(new ImageIcon("./Images/Backgrounds/hogwarts.jpg"));
		startView = new JPanel();
		startView.setLayout(new GridLayout(0, 1));
		JButton b = new JButton("New Game");
		styleButton(b);
		b.setForeground(Color.yellow);
		b.setContentAreaFilled(false);
		b.setFont(new Font("Harry P", Font.BOLD, 60));
		b.setVisible(true);
		b.setPreferredSize(new Dimension(250, 100));
		b.setActionCommand("NewGame");
		b.addActionListener(aL);
		startView.add(b);
		startView.setOpaque(false);
		background.add(startView);
		startView.setBounds(1500, 500, 250, 100);
		JButton randgen = new JButton("RandGen");
		randgen.addActionListener(aL);
		randgen.setActionCommand("RandGen");
		startView.add(randgen);
		randgen.setBounds(0,600,100,100);
		randgen.setVisible(true);
		startView.setVisible(true);
		currentView = View.STARTVIEW;
		previousView = View.STARTVIEW;
	}

	public void champSelectView() {
		background.removeAll();
		background.setIcon(null);
		background.setIcon(new ImageIcon("./Images/Backgrounds/champSelect.jpg"));
		champSelectView = new JPanel();
		champSelectView.setLayout(null);
		JPanel houses = new JPanel();
		houses.setLayout(new GridLayout(1, 4));
		JButton Gryffindor = makeHouse("Gryffindor");
		JButton Slytherin = makeHouse("Slytherin");
		JButton Hufflepuff = makeHouse("Hufflepuff");
		JButton Ravenclaw = makeHouse("Ravenclaw");
		houses.add(Gryffindor);
		houses.add(Ravenclaw);
		houses.add(Slytherin);
		houses.add(Hufflepuff);
		houses.setOpaque(false);
		houses.setVisible(true);
		champSelectView.add(back);
		back.setBounds(100, 900, 200, 100);
		if (champCount != 4) {
			champSelectView.add(houses);
			houses.setBounds(410, 250, 1100, 220);
		}
		if (champCount >= 1) {
			champ1M = new ChampJPanel2(aL, champ1);
			champSelectView.add(champ1M);
			champ1M.setBounds(300, 470, 250, 600);
			champ1M.setVisible(true);
			champ1M.setOpaque(false);
		}
		if (champCount >= 2) {
			champ2M = new ChampJPanel2(aL, champ2);
			champSelectView.add(champ2M);
			champ2M.setBounds(750, 470, 250, 600);
			champ2M.setVisible(true);
			champ2M.setOpaque(false);
		}
		if (champCount >= 3) {
			champ3M = new ChampJPanel2(aL, champ3);
			champSelectView.add(champ3M);
			champ3M.setBounds(1200, 470, 250, 600);
			champ3M.setVisible(true);
			champ3M.setOpaque(false);
		}
		if (champCount == 4) {
			champ4M = new ChampJPanel2(aL, champ4);
			champSelectView.add(champ4M);
			champ4M.setBounds(1650, 470, 250, 600);
			champ4M.setVisible(true);
			champ4M.setOpaque(false);
			JButton startTournament = new JButton("Start Tournament");
			startTournament.setActionCommand("Start Tournament");
			startTournament.addActionListener(aL);
			champSelectView.add(startTournament);
			startTournament.setBounds(860,350,200,100);
			startTournament.setVisible(true);
			startTournament.setOpaque(false);
			
		}
		champSelectView.setOpaque(false);
		background.add(champSelectView);
		champSelectView.setBounds(0, 0, 1920, 1080);
		previousView = View.STARTVIEW;
		currentView = View.CHAMPSELECTVIEW;
	}

	public void gryffindorSelectView() {
		background.remove(champSelectView);
		background.setIcon(new ImageIcon("./Images/Backgrounds/Gryff.jpg"));
		gryffindorSelectView = new JPanel();
		gryffindorSelectView.setLayout(null);
		JButton male = new JButton(new ImageIcon("./Images/Models/griffboywand.png"));
		styleButton(male);
		male.setVisible(true);
		male.setActionCommand("Gryffindor_Male");
		male.addActionListener(aL);
		JButton female = new JButton(new ImageIcon("./Images/Models/griffgirlwand.png"));
		styleButton(female);
		female.setVisible(true);
		female.setActionCommand("Gryffindor_Female");
		female.addActionListener(aL);
		gryffindorSelectView.add(welcome);
		welcome.setBounds(400, 200, 500, 50);
		gryffindorSelectView.add(name);
		name.setBounds(550, 250, 200, 50);
		gryffindorSelectView.add(back);
		back.setBounds(100, 900, 200, 100);
		gryffindorSelectView.add(male);
		male.setBounds(400, 400, 300, 600);
		gryffindorSelectView.add(female);
		female.setBounds(700, 400, 300, 600);
		previousView = View.CHAMPSELECTVIEW;
		currentView = View.GRYFFINDORSELECTVIEW;
		background.add(gryffindorSelectView);
		gryffindorSelectView.setBounds(0, 0, 1920, 1080);
		gryffindorSelectView.setOpaque(false);
	}

	public void ravenclawSelectView() {
		background.setIcon(new ImageIcon("./Images/Backgrounds/Raven.jpg"));
		ravenclawSelectView = new JPanel();
		ravenclawSelectView.setLayout(null);
		background.remove(champSelectView);
		JButton male = new JButton(new ImageIcon("./Images/Models/ravenboywand.png"));
		styleButton(male);
		male.setVisible(true);
		male.setActionCommand("Ravenclaw_Male");
		male.addActionListener(aL);
		JButton female = new JButton(new ImageIcon("./Images/Models/ravengirlwand.png"));
		styleButton(female);
		female.setVisible(true);
		female.setActionCommand("Ravenclaw_Female");
		female.addActionListener(aL);
		ravenclawSelectView.add(welcome);
		welcome.setBounds(400, 200, 500, 50);
		ravenclawSelectView.add(name);
		name.setBounds(550, 250, 200, 50);
		ravenclawSelectView.add(back);
		back.setBounds(100, 900, 200, 100);
		ravenclawSelectView.add(male);
		male.setBounds(400, 400, 300, 600);
		ravenclawSelectView.add(female);
		female.setBounds(700, 400, 300, 600);
		currentView = View.RAVENCLAWSELECTVIEW;
		previousView = View.CHAMPSELECTVIEW;
		background.add(ravenclawSelectView);
		ravenclawSelectView.setBounds(0, 0, 1920, 1080);
		ravenclawSelectView.setOpaque(false);

	}

	public void slytherinSelectView() {
		background.setIcon(new ImageIcon("./Images/Backgrounds/Slyth3.png"));
		slytherinSelectView = new JPanel();
		slytherinSelectView.setLayout(null);
		background.remove(champSelectView);
		JButton male = new JButton(new ImageIcon("./Images/Models/slythboywand.png"));
		styleButton(male);
		male.setVisible(true);
		male.setActionCommand("Slytherin_Male");
		male.addActionListener(aL);
		JButton female = new JButton(new ImageIcon("./Images/Models/slythgirlwand.png"));
		styleButton(female);
		female.setVisible(true);
		female.setActionCommand("Slytherin_Female");
		female.addActionListener(aL);
		slytherinSelectView.add(welcome);
		welcome.setBounds(400, 200, 500, 50);
		slytherinSelectView.add(name);
		name.setBounds(550, 250, 200, 50);
		slytherinSelectView.add(back);
		back.setBounds(100, 900, 200, 100);
		slytherinSelectView.add(male);
		male.setBounds(400, 400, 300, 600);
		slytherinSelectView.add(female);
		female.setBounds(700, 400, 300, 600);
		currentView = View.SLYTHERINSELECTVIEW;
		previousView = View.CHAMPSELECTVIEW;
		background.add(slytherinSelectView);
		slytherinSelectView.setBounds(0, 0, 1920, 1080);
		slytherinSelectView.setOpaque(false);
	}

	public void hufflepuffSelectView() {
		background.setIcon(new ImageIcon("./Images/Backgrounds/Huffle.jpg"));
		hufflepuffSelectView = new JPanel();
		hufflepuffSelectView.setLayout(null);
		background.remove(champSelectView);
		JButton male = new JButton(new ImageIcon("./Images/Models/huffboywand.png"));
		styleButton(male);
		male.setVisible(true);
		male.setActionCommand("Hufflepuff_Male");
		male.addActionListener(aL);
		JButton female = new JButton(new ImageIcon("./Images/Models/huffgirlwand.png"));
		styleButton(female);
		female.setForeground(Color.WHITE);
		female.setFont(new Font("Harry P", Font.BOLD, 50));
		female.setVisible(true);
		female.setActionCommand("Hufflepuff_Female");
		female.addActionListener(aL);
		hufflepuffSelectView.add(welcome);
		welcome.setBounds(400, 200, 500, 50);
		hufflepuffSelectView.add(name);
		name.setBounds(550, 250, 200, 50);
		hufflepuffSelectView.add(back);
		back.setBounds(100, 900, 200, 100);
		hufflepuffSelectView.add(male);
		male.setBounds(400, 400, 300, 600);
		hufflepuffSelectView.add(female);
		female.setBounds(700, 400, 300, 600);
		currentView = View.HUFFLEPUFFSELECTVIEW;
		previousView = View.CHAMPSELECTVIEW;
		background.add(hufflepuffSelectView);
		hufflepuffSelectView.setBounds(0, 0, 1920, 1080);
		hufflepuffSelectView.setOpaque(false);
	}

	public void spellSelectView() {
		background.setIcon(null);
		background.setIcon(new ImageIcon("./Images/Backgrounds/Library.png"));
		spellSelectView = new JPanel();
		spellSelectView.setLayout(null);
		JButton dmgBook = new JButton(new ImageIcon("./Images/Books/Book_DMG2.png"));
		JButton helBook = new JButton(new ImageIcon("./Images/Books/Book_HEL.png"));
		JButton relBook = new JButton(new ImageIcon("./Images/Books/Book_REL2.png"));
		styleButton(dmgBook);
		styleButton(helBook);
		styleButton(relBook);
		dmgBook.setVisible(true);
		dmgBook.setActionCommand("dmgBook");
		relBook.addActionListener(aL);
		relBook.setVisible(true);
		relBook.setActionCommand("relBook");
		helBook.addActionListener(aL);
		helBook.setVisible(true);
		helBook.setActionCommand("helBook");
		dmgBook.addActionListener(aL);
		dmgBook.setRolloverIcon(new ImageIcon("./Images/Books/Book_DMG.png"));
		;
		helBook.setRolloverIcon(new ImageIcon("./Images/Books/Book_HEL2.png"));
		relBook.setRolloverIcon(new ImageIcon("./Images/Books/Book_Rel.png"));
		spellSelectView.add(dmgBook);
		spellSelectView.add(helBook);
		spellSelectView.add(relBook);
		dmgBook.setBounds(900, 820, 200, 124);
		helBook.setBounds(1200, 780, 150, 152);
		relBook.setBounds(720, 750, 150, 164);
		spellSelectView.add(back);
		back.setBounds(100, 900, 200, 100);
		currentView = View.SPELSELECTVIEW;
		background.add(spellSelectView);
		spellSelectView.setOpaque(false);
		spellSelectView.setBounds(0, 0, 1920, 1080);
	}

	public void dmgSpellSelectView() {
		background.remove(spellSelectView);
		background.setIcon(null);
		background.setIcon(new ImageIcon("./Images/Books/SpellSelect.jpg"));
		dmgSpellSelectView = new JPanel();
		dmgSpellSelectView.setLayout(null);
		dmgSpellSelectView.setOpaque(false);
		getChampJPanel(dmgSpellSelectView);
		JPanel p = null;
		p = DmgSpells();
		p.setVisible(true);
		p.setOpaque(false);
		dmgSpellSelectView.add(p);
		p.setBounds(50, 70, 250, 630);
		dmgSpellSelectView.add(spellInfo);
		spellInfo.setBounds(50, 700, 820, 200);
		spellInfo.setVisible(false);
		spellInfo.setOpaque(false);
		dmgSpellSelectView.add(back);
		back.setBounds(100, 900, 200, 100);
		background.add(dmgSpellSelectView);
		dmgSpellSelectView.setVisible(true);
		dmgSpellSelectView.setBounds(0, 0, 1920, 1080);
		previousView = View.SPELSELECTVIEW;
	}

	public void helSpellSelectView() {
		background.remove(spellSelectView);
		background.setIcon(null);
		background.setIcon(new ImageIcon("./Images/Books/SpellSelect.jpg"));
		helSpellSelectView = new JPanel();
		helSpellSelectView.setLayout(null);
		helSpellSelectView.setOpaque(false);
		getChampJPanel(helSpellSelectView);
		JPanel p = HelSpells();
		p.setVisible(true);
		p.setOpaque(false);
		helSpellSelectView.add(p);
		p.setBounds(50, 70, 250, 340);
		helSpellSelectView.add(spellInfo);
		spellInfo.setBounds(50, 700, 820, 200);
		helSpellSelectView.add(back);
		back.setBounds(100, 900, 200, 100);
		background.add(helSpellSelectView);
		helSpellSelectView.setVisible(true);
		helSpellSelectView.setBounds(0, 0, 1920, 1080);
		spellInfo.setOpaque(false);
		spellInfo.setVisible(false);
		previousView = View.SPELSELECTVIEW;
	}

	public void relSpellSelectView() {
		background.remove(spellSelectView);
		background.setIcon(null);
		background.setIcon(new ImageIcon("./Images/Books/SpellSelect.jpg"));
		relSpellSelectView = new JPanel();
		relSpellSelectView.setLayout(null);
		relSpellSelectView.setOpaque(false);
		getChampJPanel(relSpellSelectView);
		JPanel p = RelSpells();
		p.setVisible(true);
		p.setOpaque(false);
		relSpellSelectView.add(p);
		p.setBounds(50, 70, 250, 230);
		relSpellSelectView.add(spellInfo);
		spellInfo.setBounds(50, 700, 820, 200);
		relSpellSelectView.add(back);
		back.setBounds(100, 900, 200, 100);
		background.add(relSpellSelectView);
		relSpellSelectView.setVisible(true);
		relSpellSelectView.setBounds(0, 0, 1920, 1080);
		spellInfo.setOpaque(false);
		spellInfo.setVisible(false);
		previousView = View.SPELSELECTVIEW;
	}

	public void firstTaskInstruction() throws IOException{
		background.removeAll();
		firstTaskInstruction = new FirstTaskInstruction(aL);
		background.add(firstTaskInstruction);
		firstTaskInstruction.setVisible(true);
		firstTaskInstruction.setBounds(0,0,1920,1080);
	}
	
	public void firstTaskView(){
		taskCount = 1;
		addKeyListener(kL);
		background.removeAll();
		background.setIcon(null);
		background.setIcon(new ImageIcon("./Images/Backgrounds/Untitled.png"));
		task1 = new FirstTaskMap(aL);
		background.add(task1);
		task1.setBounds(0, 0, 1920, 1080);
		task1.setVisible(true);
		task1.setOpaque(false);
	}
	
	public void secondTaskInstruction() throws IOException{
		background.removeAll();
		secondTaskInstruction = new SecondTaskInstruction(aL);
		background.add(secondTaskInstruction);
		secondTaskInstruction.setVisible(true);
		secondTaskInstruction.setBounds(0,0,1920,1080);
	}
	
	public void secondTaskView(){
		taskCount = 2;
		background.removeAll();
		background.setIcon(null);
		background.setIcon(new ImageIcon("./Images/Backgrounds/Untitled.png"));
		task2 = new SecondTaskMap(aL);
		background.add(task2);
		task2.setBounds(0,0,1920,1080);
		task2.setVisible(true);
		task2.setOpaque(false);
	}
	
	public void thirdTaskInstruction() throws IOException{
		background.removeAll();
		thirdTaskInstruction = new ThirdTaskInstruction(aL);
		background.add(thirdTaskInstruction);
		thirdTaskInstruction.setVisible(true);
		thirdTaskInstruction.setBounds(0,0,1920,1080);
	}
	
	public void thirdTaskView(){
		taskCount = 3;
		background.removeAll();
		background.setIcon(null);
		background.setIcon(new ImageIcon("./Images/Backgrounds/Untitled.png"));
		task3 = new ThirdTaskMap(aL);
		background.add(task3);
		task3.setBounds(0,0,1920,1080);
		task3.setVisible(true);
		task3.setOpaque(false);
	}
	
	public void allDeadView(){
		background.removeAll();
		background.setIcon(null);
		background.setIcon(new ImageIcon("./Images/Backgrounds/deadwin.jpg"));
		
	}
	
	public JButton makeHouse(String housename) {
		JButton image = new JButton(new ImageIcon("./Images/Crests/" + housename + "_crest.png"));
		image.setActionCommand(housename);
		styleButton(image);
		image.setPreferredSize(new Dimension(200, 220));
		image.setRolloverIcon(new ImageIcon("./Images/Crests/" + housename + "_crest2.png"));
		image.addActionListener(aL);
		return image;
	}

	public void audio() throws LineUnavailableException, IOException, UnsupportedAudioFileException {
		clip.open(AudioSystem.getAudioInputStream(file));
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		clip.start();
	}

	public void soundChampSlect(String House) {
		switch (House) {
		case "Gryffindor":
			try {
				clipL.open(AudioSystem.getAudioInputStream(lion));
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			}
			clipL.start();
			break;
		case "Ravenclaw":
			try {
				clipR.open(AudioSystem.getAudioInputStream(raven));
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			}
			clipR.start();
			break;
		case "Slytherin":
			try {
				clipS.open(AudioSystem.getAudioInputStream(snake));
			} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			}
			clipS.start();
			break;
		}
	}

	public void styleButton(JButton btn) {
		btn.setFocusPainted(false);
		btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		btn.setBorderPainted(false);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
	}

	public WindowListener getwL() {
		return wL;
	}

	public void setwL(WindowListener wL) {
		this.wL = wL;
	}

	public JLabel getBackgroundHoba() {
		return this.background;
	}

	public JPanel getGryffindorView() {
		return this.gryffindorSelectView;
	}

	public JPanel getRavenclawView() {
		return this.ravenclawSelectView;
	}

	public JPanel getSlytherinView() {
		return this.slytherinSelectView;
	}

	public JPanel getHufflepuffView() {
		return this.hufflepuffSelectView;
	}

	public void styleSpellButton(JButton btn, Spell s) {
		btn.setContentAreaFilled(false);
		btn.setOpaque(false);
		btn.setForeground(Color.black);
		btn.setFont(new Font("Harry P", Font.BOLD, 30));
		btn.setHorizontalAlignment(SwingConstants.LEFT);
		btn.setBorderPainted(false);
		btn.setVisible(true);
		btn.addActionListener(aL);
		btn.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				spellInfo.setVisible(false);
				if (!(btn.getForeground() == Color.YELLOW))
					btn.setForeground(Color.black);
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				spellInfo.setVisible(true);
				setSpellInfo(s);
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (btn.getForeground() == Color.black && spellCount != 3) {
					btn.setActionCommand(btn.getName());
					btn.setForeground(Color.YELLOW);
					spellCount++;
					if (champCount == 0) {
						if (spellCount == 1) {
							champ1.addSpell1(s);
						} else if (spellCount == 2) {
							champ1.addSpell2(s);
						} else {
							champ1.addSpell3(s);
						}
					} else if (champCount == 1) {
						if (spellCount == 1) {
							champ2.addSpell1(s);
						} else if (spellCount == 2) {
							champ2.addSpell2(s);
						} else {
							champ2.addSpell3(s);
						}
					} else if (champCount == 2) {
						if (spellCount == 1) {
							champ3.addSpell1(s);
						} else if (spellCount == 2) {
							champ3.addSpell2(s);
						} else {
							champ3.addSpell3(s);
						}
					} else {
						if (spellCount == 1) {
							champ4.addSpell1(s);
						} else if (spellCount == 2) {
							champ4.addSpell2(s);
						} else {
							champ4.addSpell3(s);
						}
					}
				} else if (btn.getForeground() == Color.YELLOW) {
					btn.setActionCommand("rem" + btn.getName());
					btn.setForeground(Color.black);
					spellCount--;
					if (champCount == 0) {
						champ1.removeSpell(s);
					} else if (champCount == 1) {
						champ2.removeSpell(s);
					} else if (champCount == 2) {
						champ3.removeSpell(s);
					} else {
						champ4.removeSpell(s);
					}
				}

			}
		});
	}

	public void makeChampJPanel(String house, String model) {
		if (champCount == 0) {
			champ1.setName(name.getText());
			champ1.setHouse(house);
			champ1.setModel(model);
			champ1.setOpaque(false);
		} else if (champCount == 1) {
			champ2.setName(name.getText());
			champ2.setHouse(house);
			champ2.setModel(model);
			champ2.setOpaque(false);
		} else if (champCount == 2) {
			champ3.setName(name.getText());
			champ3.setHouse(house);
			champ3.setModel(model);
			champ3.setOpaque(false);
		} else {
			champ4.setName(name.getText());
			champ4.setHouse(house);
			champ4.setModel(model);
			champ4.setOpaque(false);
		}
	}

	public void addWizard() {

	}

	public void getChampJPanel(JPanel view) {
		switch (champCount) {
		case 0:
			view.add(champ1);
			champ1.setBounds(960, 0, 960, 1080);
			break;
		case 1:
			view.add(champ2);
			champ2.setBounds(960, 0, 960, 1080);
			break;
		case 2:
			view.add(champ3);
			champ3.setBounds(960, 0, 960, 1080);
			break;
		case 3:
			view.add(champ4);
			champ4.setBounds(960, 0, 960, 1080);
			break;
		}
	}

	public JPanel DmgSpells() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(11, 1));
		for (int i = 0; i < 11; i++) {
			Spell s = spells.get(i);
			JButton b = new JButton(s.getName());
			b.setName(s.getName());
			styleSpellButton(b, s);
			p.add(b);
		}
		return p;
	}

	public JPanel HelSpells() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(6, 1));
		for (int i = 11; i < 17; i++) {
			Spell s = spells.get(i);
			JButton b = new JButton(s.getName());
			b.setName(s.getName());
			styleSpellButton(b, s);
			p.add(b);
		}
		return p;
	}

	public JPanel RelSpells() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4, 1));
		for (int i = 17; i < spells.size(); i++) {
			Spell s = spells.get(i);
			JButton b = new JButton(s.getName());
			b.setName(s.getName());
			styleSpellButton(b, s);
			p.add(b);
		}
		return p;
	}

	public void setSpellInfo(Spell s) {
		spellInfo.removeAll();
		spellInfo.setSize(770, 200);
		spellInfo.setLayout(null);
		JTextArea text = new JTextArea();
		switch (s.getName()) {
		case "Sectumsempra":
			text.setText("Sectumsempra is a curse invented by Professor Severus Snape, " + "during his childhood, "
					+ "\nwhen he was known as The Half-Blood Prince. "
					+ "\nHe created it with the intention of using it against his enemies, "
					+ "\nlikely including the Marauders and it became one of his specialties.");
			break;
		case "Reducto":
			text.setText("The Reductor Curse is a curse that can be used to blast solid objects into pieces."
					+ "\n It is rather easy to reduce a target to a fine mist or a pile of ashes.");
			break;
		case "Piertotum Locomotor":
			text.setText(
					"Piertotum Locomotor is the incantation of a Charm used to bring life to those artefacts that had"
							+ "\n, previously, been inanimate and unmoving. "
							+ "\nThe target's movements can be controlled by the caster of the Charm. "
							+ "\nWhy this is being used to deal damage I have no idea.");
			break;
		case "Oppugno":
			text.setText("The Oppugno Jinx is a spell that directs an object or individual to attack the victim."
					+ "\n This jinx will cause conjured creatures or other moveable objects under the control of "
					+ "\nthe caster to attack the target.");
			break;
		case "Incendio":
			text.setText("The Fire-Making Spell, "
					+ "\nalso known as the Fire-Making Charm is a charm and a form of Conjuration that can be used"
					+ "\n to conjure a jet of orange and red flame, thereby setting things alight.");
			break;
		case "Expulso":
			text.setText("The Expulso Curse is a curse used to produce immense explosions,"
					+ "\n blasting the target apart with a burst of blue light;"
					+ "\n it has enough force to throw people into walls.");
			break;
		case "Avada Kedavra":
			text.setText("The Killing Curse (Avada Kedavra) is a tool of the Dark Arts and one of "
					+ "\nthe three Unforgivable Curses. It is one of the most powerful and sinister "
					+ "\nspells known to wizardkind. When cast successfully on a living person or creature,"
					+ "\nthe curse causes instantaneous, painless death, "
					+ "\nwithout any signs of violence on the body.");
			break;
		case "Crucio":
			text.setText("The Cruciatus Curse (Crucio) is a tool of the Dark Arts "
					+ "\nand one of the three Unforgivable Curses. "
					+ "\nIt is one of the most powerful and sinister spells known to wizardkind. "
					+ "\nWhen cast successfully, the curse inflicts intense, " + "\nexcruciating pain on the victim.");
			break;
		case "Bombarda":
			text.setText("Bombarda is the incantation of a charm used to provoke small explosions; "
					+ "\none use for this explosion is to blast open sealed doors "
					+ "\nor to blow bars off of windows. " + "\nThe incantation Bombarda Maxima is a stronger, "
					+ "\nmore powerful variation of this spell.");
			break;
		case "Kamehameha":
			text.setText("An energy attack from Dragon Ball, cause why not.");
			break;
		case "Igni":
			text.setText("Making up spells are we?");
			break;

		case "Cheering Charm":
			text.setText("The Cheering Charm is a spell which makes a person happy." + "\n When the spell is overdone,"
					+ "\n it can cause the victim to be overcome with fits of hysterical laughter.");
			break;
		case "Expecto Patronum":
			text.setText("The Patronus Charm is the most famous and one of the most powerful "
					+ "\ndefensive charms known to wizardkind. It is an immensely complicated and extremely difficult "
					+ "\nspell that evokes a partially-tangible positive energy force known as a Patronus or spirit guardian."
					+ "\n It is the primary protection against Dementors and Lethifolds, against which there are no other defence.");
			break;
		case "Ferula":
			text.setText("Ferula is a charm used to bandage and splint broken bones." + "\n It eases pain as well.");
			break;
		case "Protego Horribilis":
			text.setText("Protego horribilis is the incantation to a stronger version "
					+ "\nof the Shield Charm which protects a very large area against highly Dark Magic.");
			break;
		case "Rennervate":
			text.setText("The Reviving Spell is a charm that awakens whomever the caster's wand is pointed at. "
					+ "\nIt could be called a counter-spell for the stunning spell except that another wizard must do it.");
		case "Quen":
			text.setText("Here's to another made-up spell. Cheers");
			break;

		case "Accio":
			text.setText("The Summoning Charm was a charm that caused a target at a distance "
					+ "\nfrom the caster to levitate or fly over to them. This spell needs thought behind it, "
					+ "\nthe object must be clear in the casters mind, before trying to summon.");
			break;
		case "Imperio":
			text.setText("The Imperius Curse is a tool of the Dark Arts, and is one of the "
					+ "\nthree Unforgivable Curses. It is one of the most powerful and sinister "
					+ "\nspells known to wizardkind. When cast successfully, the curse places "
					+ "\nthe victim completely under the caster's control, though a person "
					+ "\nwith exceptional strength of will is capable of resisting it.");
			break;
		case "Wingardium Leviosa":
			text.setText("The Levitation Charm is a charm used to make objects fly, or levitate, "
					+ "\ntaught to first years at Hogwarts School of Witchcraft and Wizardry.");
			break;
		case "Axii":
			text.setText("Yay, One more made up spell.");
			break;
		}
		text.setFont(new Font("Harry P", Font.PLAIN, 28));
		text.setForeground(Color.BLACK);
		spellInfo.add(text);
		text.setBounds(0, 0, 770, 150);
		text.setVisible(true);
		text.setOpaque(false);
		text.setEnabled(false);
		JTextArea amount;
		if (s instanceof DamagingSpell) {
			amount = new JTextArea("Damage Amount: " + ((DamagingSpell) s).getDamageAmount());
		} else if (s instanceof HealingSpell) {
			amount = new JTextArea("Healing Amount: " + ((HealingSpell) s).getHealingAmount());
		} else {
			amount = new JTextArea("Range: " + ((RelocatingSpell) s).getRange());
		}
		amount.setOpaque(false);
		amount.setEnabled(false);
		amount.setFont(new Font("Harry P", Font.PLAIN, 28));
		JTextArea cd = new JTextArea("Cooldown: " + s.getDefaultCooldown());
		cd.setOpaque(false);
		cd.setEnabled(false);
		cd.setFont(new Font("Harry P", Font.PLAIN, 28));
		JTextArea cost = new JTextArea("IP Cost: " + s.getCost());
		cost.setOpaque(false);
		cost.setEnabled(false);
		cost.setFont(new Font("Harry P", Font.PLAIN, 28));
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0, 3));
		p.add(amount);
		p.add(cost);
		p.add(cd);
		spellInfo.add(p);
		p.setBounds(0, 150, 770, 50);
		p.setVisible(true);
		p.setOpaque(false);
	}

	public void setPreviousView(View prev) {
		previousView = prev;
	}

	public void closeAudio() {
		clipL.close();
		clipS.close();
		clipR.close();
	}

	public ChampJPanel getChamp1() {
		return champ1;
	}

	public void setChamp1(ChampJPanel champ1) {
		this.champ1 = champ1;
	}

	public ChampJPanel getChamp2() {
		return champ2;
	}

	public void setChamp2(ChampJPanel champ2) {
		this.champ2 = champ2;
	}

	public ChampJPanel getChamp3() {
		return champ3;
	}

	public void setChamp3(ChampJPanel champ3) {
		this.champ3 = champ3;
	}

	public ChampJPanel getChamp4() {
		return champ4;
	}

	public void setChamp4(ChampJPanel champ4) {
		this.champ4 = champ4;
	}

	public FirstTaskMap getTask1() {
		return task1;
	}

	public void setTask1(FirstTaskMap task1) {
		this.task1 = task1;
	}

	public int getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(int taskCount) {
		this.taskCount = taskCount;
	}

	public SecondTaskMap getTask2() {
		return task2;
	}

	public void setTask2(SecondTaskMap task2) {
		this.task2 = task2;
	}

	public ThirdTaskMap getTask3() {
		return task3;
	}

	public void setTask3(ThirdTaskMap task3) {
		this.task3 = task3;
	}

		
}