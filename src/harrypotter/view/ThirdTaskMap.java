package harrypotter.view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import harrypotter.controller.GuiController;
import harrypotter.exceptions.InCooldownException;
import harrypotter.exceptions.InvalidTargetCellException;
import harrypotter.exceptions.NotEnoughIPException;
import harrypotter.exceptions.OutOfBordersException;
import harrypotter.model.character.Champion;
import harrypotter.model.character.GryffindorWizard;
import harrypotter.model.character.HufflepuffWizard;
import harrypotter.model.character.RavenclawWizard;
import harrypotter.model.character.SlytherinWizard;
import harrypotter.model.character.Wizard;
import harrypotter.model.magic.HealingSpell;
import harrypotter.model.magic.RelocatingSpell;
import harrypotter.model.magic.Spell;
import harrypotter.model.tournament.SecondTask;
import harrypotter.model.tournament.ThirdTask;
import harrypotter.model.world.Cell;
import harrypotter.model.world.ChampionCell;
import harrypotter.model.world.CollectibleCell;
import harrypotter.model.world.CupCell;
import harrypotter.model.world.Direction;
import harrypotter.model.world.EmptyCell;
import harrypotter.model.world.Obstacle;
import harrypotter.model.world.ObstacleCell;
import harrypotter.model.world.TreasureCell;

@SuppressWarnings("serial")
public class ThirdTaskMap extends JPanel{

	private JPanel mapGrid;
	private ChampJPanel3 champ1;
	private ChampJPanel3 champ2;
	private ChampJPanel3 champ3;
	private ChampJPanel3 champ4;
	private ArrayList<Champion> orderChamps;
	private ActionListener aL;
	private ThirdTask task;
	private JPanel spells;
	private Timer timer;
	private JPanel notification = new JPanel();
	private ArrayList<Point> marked = new ArrayList<Point>();
	private Direction relDir;
	private int relRange;
	

	public ThirdTaskMap(ActionListener listener){
		super();
		setSize(1920,1080);

		setLayout(null);
		aL = listener;
		task = ((GuiController)aL).getTournament().getThirdTask();
		champ2 = new ChampJPanel3(aL, null);
		champ3 = new ChampJPanel3(aL, null);
		champ4 = new ChampJPanel3(aL, null);
		setOrderChamps(task.getChampions());
		setChampionOrder();

		ChampPanel();
		mapGrid = new JPanel();
		spells = new JPanel();
		mapGrid.setSize(1090,880);
		mapGrid.setLayout(new GridLayout(10, 10));
		generateMap();
		add(mapGrid);
		mapGrid.setBounds(95, 100, 1090, 880);
		mapGrid.setVisible(true);
		mapGrid.setOpaque(false);

		spells.setLayout(new GridLayout(3, 0));
		updateSpells();
		add(spells);
		spells.setBounds(1450, 750, 300, 150);
		spells.revalidate();
		spells.setOpaque(false);
		spells.setVisible(true);
	}

	public void generateMap(){
		mapGrid.removeAll();
		Cell [][] cells= task.getMap();
		for(int i=0; i<10; i++){
			for(int j = 0; j<10; j++){
				Cell c = cells[i][j];
				if(c instanceof EmptyCell){
					ImageIcon icon = new ImageIcon("./Images/Cells/Task3/ET3.jpg");
					MapButton mB = new MapButton(icon);
					mB.setVisible(true);
					mapGrid.add(mB);
					mB.setBorder(BorderFactory.createEmptyBorder());
					mB.setFocusable(false);
					mB.setXcor(i);
					mB.setYcor(j);mB.addActionListener(aL);
				}
				else if(c instanceof ChampionCell){
					ChampionCell cc = (ChampionCell)c;
					Champion champ= ((ChampionCell) cc).getChamp();
					if(champ instanceof GryffindorWizard){
						GryffindorWizard gw = (GryffindorWizard) champ;

						if(gw.getGender().equals("Male")){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/GBT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/GGT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}

					}
					else if(champ instanceof SlytherinWizard){
						SlytherinWizard sw= (SlytherinWizard) champ;
						if(sw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/SBT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/SGT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}

					}
					else if(champ instanceof RavenclawWizard){
						RavenclawWizard rw = (RavenclawWizard)champ;
						if(rw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/RBT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/RGT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
					}
					else{
						HufflepuffWizard hw = (HufflepuffWizard)champ;
						if(hw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/HBT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/HGT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
					}
				}
				else if(c instanceof ObstacleCell){
					ImageIcon icon = new ImageIcon("./Images/Cells/Task3/OT3.jpg");
					MapButton mB = new MapButton(icon);
					mB.setVisible(true);
					mapGrid.add(mB);
					mB.setBorder(BorderFactory.createEmptyBorder());
					mB.setToolTipText("" + ((Obstacle)((ObstacleCell)c).getObstacle()).getHp());
					mB.setFocusable(false);
					mB.setXcor(i);
					mB.setYcor(j);mB.addActionListener(aL);
				}
				else{
					ImageIcon icon = new ImageIcon("./Images/Cells/Task3/ET3.jpg");
					MapButton mB = new MapButton(icon);
					mB.setVisible(true);
					mapGrid.add(mB);
					mB.setBorder(BorderFactory.createEmptyBorder());
					mB.setFocusable(false);
					mB.setXcor(i);
					mB.setYcor(j);mB.addActionListener(aL);
				}
			}
		}
		mapGrid.revalidate();
		mapGrid.repaint();
		ChampPanel();
		updateSpells();
	}

	public void generateMapTarget(){
		mapGrid.removeAll();
		Point l = ((Wizard)task.getCurrentChamp()).getLocation();
		Cell [][] cells= task.getMap();
		for(int i=0; i<10; i++){
			for(int j = 0; j<10; j++){
				Cell c = cells[i][j];
				if((i == l.x && j == l.y - 1) && task.getMap()[i][j] instanceof ObstacleCell){
					MapButton mB = new MapButton(new ImageIcon("./Images/Cells/Task3/OT31.jpg"));
					mB.setActionCommand("castLeft");
					mB.addActionListener(aL);
					mB.setVisible(true);
					mapGrid.add(mB);
					mB.setBorder(BorderFactory.createEmptyBorder());
					mB.setFocusable(false);
					mB.setXcor(i);
					mB.setYcor(j);mB.addActionListener(aL);
				}
				else if((i == l.x && j == l.y + 1) && task.getMap()[i][j] instanceof ObstacleCell){
					MapButton mB = new MapButton(new ImageIcon("./Images/Cells/Task3/OT31.jpg"));
					mB.setActionCommand("castRight");
					mB.addActionListener(aL);
					mB.setVisible(true);
					mapGrid.add(mB);
					mB.setBorder(BorderFactory.createEmptyBorder());
					mB.setFocusable(false);
					mB.setXcor(i);
					mB.setYcor(j);mB.addActionListener(aL);
				}
				else if((i == l.x - 1 && j == l.y) && task.getMap()[i][j] instanceof ObstacleCell){
					MapButton mB = new MapButton(new ImageIcon("./Images/Cells/Task3/OT31.jpg"));
					mB.setActionCommand("castForward");
					mB.addActionListener(aL);
					mB.setVisible(true);
					mapGrid.add(mB);
					mB.setBorder(BorderFactory.createEmptyBorder());
					mB.setFocusable(false);
					mB.setXcor(i);
					mB.setYcor(j);mB.addActionListener(aL);
				}
				else if((i == l.x + 1 && j == l.y) && task.getMap()[i][j] instanceof ObstacleCell){
					MapButton mB = new MapButton(new ImageIcon("./Images/Cells/Task3/OT31.jpg"));
					mB.setActionCommand("castBackward");
					mB.addActionListener(aL);
					mB.setVisible(true);
					mapGrid.add(mB);
					mB.setBorder(BorderFactory.createEmptyBorder());
					mB.setFocusable(false);
					mB.setXcor(i);
					mB.setYcor(j);mB.addActionListener(aL);
				}
				else if(task.getMap()[i][j] instanceof ChampionCell && (i == l.x + 1 && j == l.y)){
					ChampionCell cc = (ChampionCell)c;
					Champion champ2= ((ChampionCell) cc).getChamp();
					if(champ2 instanceof GryffindorWizard){
						GryffindorWizard gw = (GryffindorWizard) champ2;

						if(gw.getGender().equals("Male")){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/GBT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castBackward");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/GGT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castBackward");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}

					}
					else if(champ2 instanceof SlytherinWizard){
						SlytherinWizard sw= (SlytherinWizard) champ2;
						if(sw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/SBT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castBackward");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/SGT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castBackward");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}

					}
					else if(champ2 instanceof RavenclawWizard){
						RavenclawWizard rw = (RavenclawWizard)champ2;
						if(rw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/RBT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castBackward");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/RGT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castBackward");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
					}
					else{
						HufflepuffWizard hw = (HufflepuffWizard)champ2;
						if(hw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/HBT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castBackward");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/HGT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castBackward");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
					}
				}
				else if(task.getMap()[i][j] instanceof ChampionCell && (i == l.x - 1 && j == l.y)){
					ChampionCell cc = (ChampionCell)c;
					Champion champ2= ((ChampionCell) cc).getChamp();
					if(champ2 instanceof GryffindorWizard){
						GryffindorWizard gw = (GryffindorWizard) champ2;

						if(gw.getGender().equals("Male")){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/GBT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castForward");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/GGT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castForward");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}

					}
					else if(champ2 instanceof SlytherinWizard){
						SlytherinWizard sw= (SlytherinWizard) champ2;
						if(sw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/SBT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castForward");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/SGT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castForward");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}

					}
					else if(champ2 instanceof RavenclawWizard){
						RavenclawWizard rw = (RavenclawWizard)champ2;
						if(rw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/RBT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castForward");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/RGT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castForward");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
					}
					else{
						HufflepuffWizard hw = (HufflepuffWizard)champ2;
						if(hw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/HBT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castForward");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/HGT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castForward");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
					}
				}
				else if(task.getMap()[i][j] instanceof ChampionCell && (i == l.x && j == l.y + 1)){
					ChampionCell cc = (ChampionCell)c;
					Champion champ2= ((ChampionCell) cc).getChamp();
					if(champ2 instanceof GryffindorWizard){
						GryffindorWizard gw = (GryffindorWizard) champ2;

						if(gw.getGender().equals("Male")){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/GBT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castRight");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/GGT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castRight");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}

					}
					else if(champ2 instanceof SlytherinWizard){
						SlytherinWizard sw= (SlytherinWizard) champ2;
						if(sw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/SBT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castRight");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/SGT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castRight");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}

					}
					else if(champ2 instanceof RavenclawWizard){
						RavenclawWizard rw = (RavenclawWizard)champ2;
						if(rw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/RBT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castRight");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/RGT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castRight");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
					}
					else{
						HufflepuffWizard hw = (HufflepuffWizard)champ2;
						if(hw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/HBT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castRight");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/HGT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castRight");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
					}
				}
				else if(task.getMap()[i][j] instanceof ChampionCell && (i == l.x && j == l.y - 1)){
					ChampionCell cc = (ChampionCell)c;
					Champion champ2= ((ChampionCell) cc).getChamp();
					if(champ2 instanceof GryffindorWizard){
						GryffindorWizard gw = (GryffindorWizard) champ2;

						if(gw.getGender().equals("Male")){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/GBT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castLeft");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/GGT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castLeft");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}

					}
					else if(champ2 instanceof SlytherinWizard){
						SlytherinWizard sw= (SlytherinWizard) champ2;
						if(sw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/SBT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castLeft");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/SGT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castLeft");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}

					}
					else if(champ2 instanceof RavenclawWizard){
						RavenclawWizard rw = (RavenclawWizard)champ2;
						if(rw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/RBT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castLeft");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/RGT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castLeft");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
					}
					else{
						HufflepuffWizard hw = (HufflepuffWizard)champ2;
						if(hw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/HBT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castLeft");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/HGT31.jpg");
							MapButton mB = new MapButton(icon);
							mB.setActionCommand("castLeft");
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
					}
				}
				else if(c instanceof EmptyCell){
					ImageIcon icon = new ImageIcon("./Images/Cells/Task3/ET3.jpg");
					MapButton mB = new MapButton(icon);
					mB.setVisible(true);
					mapGrid.add(mB);
					mB.setBorder(BorderFactory.createEmptyBorder());
					mB.setFocusable(false);
					mB.setXcor(i);
					mB.setYcor(j);mB.addActionListener(aL);
				}
				else if(c instanceof ChampionCell){
					ChampionCell cc = (ChampionCell)c;
					Champion champ= ((ChampionCell) cc).getChamp();
					if(champ instanceof GryffindorWizard){
						GryffindorWizard gw = (GryffindorWizard) champ;

						if(gw.getGender().equals("Male")){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/GBT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/GGT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}

					}
					else if(champ instanceof SlytherinWizard){
						SlytherinWizard sw= (SlytherinWizard) champ;
						if(sw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/SBT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/SGT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}

					}
					else if(champ instanceof RavenclawWizard){
						RavenclawWizard rw = (RavenclawWizard)champ;
						if(rw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/RBT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/RGT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
					}
					else{
						HufflepuffWizard hw = (HufflepuffWizard)champ;
						if(hw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/HBT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/HGT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
					}
				}
				else if(c instanceof ObstacleCell){
					ImageIcon icon = new ImageIcon("./Images/Cells/Task3/OT3.jpg");
					MapButton mB = new MapButton(icon);
					mB.setVisible(true);
					mapGrid.add(mB);
					mB.setBorder(BorderFactory.createEmptyBorder());
					mB.setToolTipText("" + ((Obstacle)((ObstacleCell)c).getObstacle()).getHp());
					mB.setFocusable(false);
					mB.setXcor(i);
					mB.setYcor(j);mB.addActionListener(aL);
				}
				else{
					ImageIcon icon = new ImageIcon("./Images/Cells/Task3/ET3.jpg");
					MapButton mB = new MapButton(icon);
					mB.setVisible(true);
					mapGrid.add(mB);
					mB.setBorder(BorderFactory.createEmptyBorder());
					mB.setFocusable(false);
					mB.setXcor(i);
					mB.setYcor(j);mB.addActionListener(aL);
				}

			}
		}
		ChampPanel();
		updateSpells();
	}

	public void setChampionOrder(){
		int count = 0; 
		for (Champion champ : task.getChampions()) {
			Wizard w = (Wizard)champ;
			switch(count){
			case 0: champ1 = new ChampJPanel3(aL, w);add(champ1);champ1.setBounds(1280,0,960,1080);break;
			case 1: champ2 = new ChampJPanel3(aL, w);add(champ2);champ2.setBounds(1280,0,960,1080);break;
			case 2: champ3 = new ChampJPanel3(aL, w);add(champ3);champ3.setBounds(1280,0,960,1080);break;
			case 3: champ4 = new ChampJPanel3(aL, w);add(champ4);champ4.setBounds(1280,0,960,1080);break;
			}
			count++;
		}
	}

	public void ChampPanel(){
		if((Wizard)task.getCurrentChamp() == champ1.getWizard()){
			champ1.setVisible(true);
			champ2.setVisible(false);
				champ3.setVisible(false);
			champ4.setVisible(false);
		}
		else if((Wizard)task.getCurrentChamp() == champ2.getWizard()){
			champ1.setVisible(false);
			champ2.setVisible(true);
			champ3.setVisible(false);
			champ4.setVisible(false);
		}
		else if((Wizard)task.getCurrentChamp() == champ3.getWizard()){
			champ1.setVisible(false);
			champ2.setVisible(false);
			champ3.setVisible(true);
			champ4.setVisible(false);
		}
		else{
			champ1.setVisible(false);
			champ2.setVisible(false);
			champ3.setVisible(false);
			champ4.setVisible(true);
		}
		champ1.update();
		champ2.update();
		champ3.update();
		champ4.update();
	}

	public ThirdTask getTask() {
		return task;
	}

	public void setTask(ThirdTask task) {
		this.task = task;
	}

	public ArrayList<Champion> getOrderChamps() {
		return orderChamps;
	}

	public void setOrderChamps(ArrayList<Champion> orderChamps) {
		this.orderChamps = orderChamps;
	}

	public void updateSpells(){
		spells.removeAll();
		for (Spell spell : ((Wizard)task.getCurrentChamp()).getSpells()) {
			JButton b = new JButton(spell.getName() + "  CD: " + spell.getCoolDown());
			styleSpellButton(b,spell);
		}
	}

	private void styleSpellButton(JButton b, Spell spell) {
		b.setFont(new Font("Harry P", Font.PLAIN, 24));
		b.setBorder(BorderFactory.createEmptyBorder());
		b.setContentAreaFilled(false);
		spells.add(b);
		b.setOpaque(false);
		b.setVisible(true);
		b.setFocusable(false);
	}

	public void castTarget(Spell s){
		generateMapTarget();
	}

	public void castRelocating(Spell s){
		RelocatingSpell spell = (RelocatingSpell) s;
		Point l = ((Wizard)task.getCurrentChamp()).getLocation();
		mapGrid.removeAll();
		Cell[][] cells = task.getMap();
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				Cell c = cells[i][j];
				if(((l.x == i && ((l.y + spell.getRange() >= j && j + spell.getRange() >= l.y) || (l.y - spell.getRange() <= j && j - spell.getRange() <= l.y))) || (l.y == j && ((l.x + spell.getRange() >= i && i + spell.getRange() >= l.x) || (l.x - spell.getRange() <= i && i - spell.getRange() <= l.x)))) && 
						(!(c instanceof ChampionCell || c instanceof ObstacleCell || c instanceof CollectibleCell || c instanceof CupCell || c instanceof TreasureCell))){
					ImageIcon icon = new ImageIcon("./Images/Cells/Task3/ET31.jpg");
					MapButton mB = new MapButton(icon);
					mB.setVisible(true);
					mapGrid.add(mB);
					mB.setActionCommand("Cast");
					mB.setBorder(BorderFactory.createEmptyBorder());
					mB.addActionListener(aL);
					mB.setFocusable(false);
					mB.setXcor(i);
					mB.setYcor(j);mB.addActionListener(aL);
				}
				else if(c instanceof EmptyCell){
					ImageIcon icon = new ImageIcon("./Images/Cells/Task3/ET3.jpg");
					MapButton mB = new MapButton(icon);
					mB.setVisible(true);
					mapGrid.add(mB);
					mB.setBorder(BorderFactory.createEmptyBorder());
					mB.setFocusable(false);
					mB.setXcor(i);
					mB.setYcor(j);mB.addActionListener(aL);
				}
				else if(c instanceof ChampionCell){
					ChampionCell cc = (ChampionCell)c;
					Champion champ= ((ChampionCell) cc).getChamp();
					if(champ instanceof GryffindorWizard){
						GryffindorWizard gw = (GryffindorWizard) champ;

						if(gw.getGender().equals("Male")){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/GBT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/GGT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}

					}
					else if(champ instanceof SlytherinWizard){
						SlytherinWizard sw= (SlytherinWizard) champ;
						if(sw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/SBT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/SGT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}

					}
					else if(champ instanceof RavenclawWizard){
						RavenclawWizard rw = (RavenclawWizard)champ;
						if(rw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/RBT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/RGT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
					}
					else{
						HufflepuffWizard hw = (HufflepuffWizard)champ;
						if(hw.getGender() == "Male"){
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/HBT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
						else{
							ImageIcon icon = new ImageIcon("./Images/Cells/Task3/HGT3.jpg");
							MapButton mB = new MapButton(icon);
							mB.setVisible(true);
							mapGrid.add(mB);
							mB.setBorder(BorderFactory.createEmptyBorder());
							mB.setToolTipText("" + ((Wizard)((ChampionCell)c).getChamp()).getHp());
							mB.setFocusable(false);
							mB.setXcor(i);
							mB.setYcor(j);mB.addActionListener(aL);
						}
					}
				}
				else if(c instanceof ObstacleCell){
					ImageIcon icon = new ImageIcon("./Images/Cells/Task3/OT3.jpg");
					MapButton mB = new MapButton(icon);
					mB.setVisible(true);
					mapGrid.add(mB);
					mB.setBorder(BorderFactory.createEmptyBorder());
					mB.setToolTipText("" + ((Obstacle)((ObstacleCell)c).getObstacle()).getHp());
					mB.setFocusable(false);
					mB.setXcor(i);
					mB.setYcor(j);mB.addActionListener(aL);
				}
				else{
					ImageIcon icon = new ImageIcon("./Images/Cells/Task3/ET3.jpg");
					MapButton mB = new MapButton(icon);
					mB.setVisible(true);
					mapGrid.add(mB);
					mB.setBorder(BorderFactory.createEmptyBorder());
					mB.setFocusable(false);
					mB.setXcor(i);
					mB.setYcor(j);mB.addActionListener(aL);
				}
			}
		}
		mapGrid.revalidate();
		mapGrid.repaint();
	}

	public void castSpell1(){
		Spell s = ((Wizard)task.getCurrentChamp()).getSpells().get(0);
		if(s instanceof HealingSpell){
			try {
				task.castHealingSpell((HealingSpell) s);
				generateMap();
			} catch (InCooldownException e) {
				// TODO Auto-generated catch block
			} catch (NotEnoughIPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			castTarget(s);
		}
	}

	public void castSpell2(){
		Spell s = ((Wizard)task.getCurrentChamp()).getSpells().get(1);
		if(s instanceof HealingSpell){
			try {
				task.castHealingSpell((HealingSpell) s);
				generateMap();
			} catch (InCooldownException e) {
				// TODO Auto-generated catch block
			} catch (NotEnoughIPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			castTarget(s);
		}
	}

	public void castSpell3(){
		Spell s = ((Wizard)task.getCurrentChamp()).getSpells().get(2);
		if(s instanceof HealingSpell){
			try {
				task.castHealingSpell((HealingSpell) s);
				generateMap();
			} catch (InCooldownException e) {
				// TODO Auto-generated catch block
			} catch (NotEnoughIPException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else{
			castTarget(s);
		}
	}

	public int getRelRange() {
		return relRange;
	}

	public void setRelRange(int x, int y) {
		Point l = ((Wizard)task.getCurrentChamp()).getLocation();
		if(x > l.x){
			relRange = x - l.x;
		}
		else if(x < l.x){
			relRange = l.x - x;
		}
		else if(y > l.y){
			relRange = y - l.y;
		}
		else{
			relRange = l.y - y;
		}
	}

	public Direction getRelDir() {
		return relDir;
	}

	public void setRelDir(int x, int y) {
		Point l = ((Wizard)task.getCurrentChamp()).getLocation();
		if(x > l.x){
			relDir = Direction.BACKWARD;
		}
		else if(x < l.x){
			relDir = Direction.FORWARD;
		}
		else if(y > l.y){
			relDir = Direction.RIGHT;
		}
		else{
			relDir = Direction.LEFT;
		}
		
	}

	public void useTrait(){
		Wizard w = (Wizard)task.getCurrentChamp();
		if(w instanceof RavenclawWizard){
			
		}
		else if(w instanceof SlytherinWizard){
			
		}
		else{
			try {
				w.useTrait();
			} catch (InCooldownException | OutOfBordersException | InvalidTargetCellException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
