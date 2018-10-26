package harrypotter.view;

import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SecondTaskInstruction extends JPanel{
	public SecondTaskInstruction(ActionListener aL) throws IOException{
		JLabel Background=new JLabel(new ImageIcon("./Images/Backgrounds/Task2Backg.jpg"));
		setLayout(null);
		add(Background);
		Background.setBounds(0, 0, 1920, 1080);
		JButton next = new JButton("Start Second Task");
		next.addActionListener(aL);
		Background.add(next);
		next.setBounds(1400, 650, 200, 100);
		next.setVisible(true);
		setVisible(true);
	}
}
