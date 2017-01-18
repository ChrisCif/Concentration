import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CheatFrame extends JFrame{
	
	private ArrayList<CardButton> cardButtons;
	private int size;
	private Color[] colors = {
			Color.BLUE,
			Color.GREEN,
			Color.RED,
			Color.WHITE,
			Color.PINK,
			Color.YELLOW,
			Color.MAGENTA,
			Color.CYAN,
	};
	
	public CheatFrame(ArrayList<CardButton> myCardButtons, int mySize){
		
		cardButtons = myCardButtons;
		size = mySize;
		
		//JPanel(new GridLayout(cModel.BOARD_SIZE, cModel.BOARD_SIZE))
		JPanel buttonPanel = new JPanel(new GridLayout(size, size));
		add(buttonPanel);
		
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Cheat Concentration Game");
		
		for(CardButton cButt : cardButtons){
			
			//I'll get to this......
			//cButt.setIcon(new ImageIcon("src\\" + cButt.getPos() + ".png"));
			cButt.setText("-" + cButt.getPos() + "-");
			cButt.setBackground(colors[cButt.getPos()]);
			cButt.setContentAreaFilled(false);
			cButt.setOpaque(true);
			
			buttonPanel.add(cButt);
			
		}
		
		setVisible(true);
		
	}

}
