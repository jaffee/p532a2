package breakout_assgn_2_v_1_0;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This class to keep the track of the score during the game play and to update
 * it
 * 
 * @param score
 *            Object of the JTextField
 * @param scoreLabel
 *            Object of the Jlabel class
 * @param currentScore
 *            contains the current score in the game
 */

public class ScoreBoard extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField score;
	private JTextField clock;
	private JLabel scoreLabel;
	private int currentScore = 0;
	private int clockTick=0;
	private int clockTime=0;
	private String clockTimeString=null;
	private JLabel clockLabel;
	public ScoreBoard() {
		score = new JTextField(5);
		score.setEditable(false);
		score.setFont(new Font("Times New Roman", Font.BOLD, 18));
		score.setText("Score "+"0");
		score.setHorizontalAlignment(JTextField.CENTER);
		currentScore = 0;
		
		clockTick=0;
		clockTime=(int)(clockTick/10.0);
		clockTimeString=new Double(clockTime).toString();
		
		clock=new JTextField(5);
		clock.setEditable(false);
		clock.setFont(new Font("Times New Roman", Font.BOLD, 18));
		clock.setText("Time "+clockTimeString);
		clock.setHorizontalAlignment(JTextField.CENTER);
		
		this.setBackground(Color.RED);
		this.setLayout(new GridLayout(2, 1));
		this.add(score);
		this.add(clock);

	}

	public JTextField getScore() {
		return this.score;
	}

	public int getCurrentScore() {
		return this.currentScore;
	}

	public void setScore(String currentScore) {
		this.score.setText("Score " + currentScore);
	}
	
	public void setClock(String currentTime){
		
		this.clock.setText("Time "+currentTime);
	}
	

}
