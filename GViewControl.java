import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

public class GViewControl extends JFrame implements Observer{
	
	private static ConcentrationModel cModel;
	private JTextField topText;
	private JPanel middlePanel;
	private JPanel bottomPanel;
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
	
	
	/**
	 * Constructor
	 * 
	 */
	public GViewControl(ConcentrationModel myModel){
		cModel = myModel;
		
		init();
		
	}
	
	
	/**
	 * update
	 * 
	 * Update the window when the model indicates
		 an update is required. Update changes the
		 color and string content of a CardButton
		 based on the CardFaces in the model, and it
		 changes the text in the label based on the
		 model state.
	 * 
	 */
	public void update(Observable t, Object o){
		
		ConcentrationModel cModel = (ConcentrationModel)t;
		
		// FIIXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		String whichCard = "";
		if(cModel.howManyCardsUp() == 1){
			whichCard = "second";
		}
		else{
			whichCard = "first";
		}
		topText.setText("Moves: " + cModel.getMoveCount() +  "   Select " + whichCard + " card");
		
		
		
		ArrayList<CardFace> cards = cModel.getCards();
		Component[] butts = middlePanel.getComponents();
		//System.out.println(butts.length);
		for(int i = 0; i < butts.length; i++){
			CardButton thisButt = (CardButton)butts[i]; 
			thisButt.setText(cards.get(i).toString());
			
			if(cards.get(i).isFaceUp()){
				thisButt.setBackground(colors[cards.get(i).getNumber()]);
			}
			else{
				thisButt.setBackground(null);
			}

		}
		
		/*
		System.out.println("==========================================");
		for(CardFace c : cards){
			System.out.println(c.isFaceUp()+"");
		}
		*/
		
	}
	
	/**
	 * main
	 * 
	 */
	public static void main(String[] args){
		
		ConcentrationModel myModel = new ConcentrationModel();
		
		GViewControl gFrame = new GViewControl(myModel);
		
		myModel.addObserver(gFrame);
		//System.out.println("Observer Added");
		
	}
	
	/**
	 * init
	 * 
	 */
	public void init(){
		
		setSize(500, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Chris Cifolelli: Concentration Game");
		
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel(new BorderLayout());
		topText = new JTextField("Moves: 0   Select first card");
		topPanel.add(topText);
				
		middlePanel = new JPanel(new GridLayout(cModel.BOARD_SIZE, cModel.BOARD_SIZE));
		bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		
		container.add(topPanel,  BorderLayout.NORTH);
		container.add(middlePanel, BorderLayout.CENTER);
		container.add(bottomPanel, BorderLayout.SOUTH);
		
		
		
		ArrayList<CardFace> cards = cModel.getCards();
		for(int i = 0; i < cards.size(); i++){
			
			 final CardButton butt = new CardButton(i);
			 
			 butt.setText(cards.get(i).toString());
			 butt.setContentAreaFilled(false);
			 butt.setOpaque(true);
			 
			 butt.addActionListener(new ActionListener(){
				 public void actionPerformed(ActionEvent e){
					
					 //SELECT THIS CARD
					 cModel.selectCard(butt.getPos());
					 
				 }
			 });
			 
			 middlePanel.add(butt);
			 
		}
		
		
		
		JButton resetButt = new JButton("Reset");
		resetButt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				// RESET GAME
				cModel.reset();
				
			}
		});
		
		JButton cheatButt = new JButton("Cheat");
		cheatButt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				// CHEAT
				ArrayList<CardButton> cButts = new ArrayList<CardButton>();
				ArrayList<CardFace> cFaces = cModel.cheat();
				
				for(CardFace cf : cFaces){
					cButts.add( new CardButton(cf.getNumber()) );
				}
				
				CheatFrame cFrame = new CheatFrame(cButts, cModel.BOARD_SIZE);
				
			}
		});
		
		JButton undoButt = new JButton("Undo");
		undoButt.addActionListener(new ActionListener (){
			public void actionPerformed(ActionEvent e){
				
				// UNDO
				cModel.undo();
				
			}
		});
		

		bottomPanel.add(resetButt);
		bottomPanel.add(cheatButt);
		bottomPanel.add(undoButt);
		
		
		
		setVisible(true);
		
	}
		
	
}
