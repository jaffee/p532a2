package breakout;
/*
 * Code adapted from http://zetcode.com/tutorials/javagamestutorial/basics/
 */

import javax.swing.JFrame;

public class Breakout extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Breakout(){
		JFrame jFrame;
		add(new Board());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800,580);
        setResizable(false);
		setLocationRelativeTo(null);
		setTitle("Breakout");
		setVisible(true);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Breakout();
	}

}
