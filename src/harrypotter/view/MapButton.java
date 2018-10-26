package harrypotter.view;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import harrypotter.model.world.Cell;

@SuppressWarnings("serial")
public class MapButton extends JButton{

	Cell cell;
	private int xcor;
	private int ycor;
	public MapButton(){
		super();
	}

	public MapButton(ImageIcon icon) {
		super(icon);
	}

	public MapButton(String string) {
		super(string);
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public int getXcor() {
		return xcor;
	}

	public void setXcor(int xcor) {
		this.xcor = xcor;
	}

	public int getYcor() {
		return ycor;
	}

	public void setYcor(int ycor) {
		this.ycor = ycor;
	}
	
}
