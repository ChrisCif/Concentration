import javax.swing.JButton;

public class CardButton extends JButton{

	private int pos;
	
	/**
	 * Constructor
	 * 
	 */
	public CardButton(int myPos){
		pos = myPos;
	}
	
	/**
	 * getPos
	 * 
	 */
	public int getPos(){ return pos; }
	
	
	
}
