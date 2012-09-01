package edu.indiana.cs.p532;

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
	private JLabel scoreLabel;
	private int currentScore = 0;

	public ScoreBoard() {
		score = new JTextField(5);
		score.setEditable(false);
		score.setFont(new Font("Times New Roman", Font.BOLD, 18));
		score.setText("0");
		score.setHorizontalAlignment(JTextField.CENTER);
		currentScore = 0;
		
		JPanel scorePanel = new JPanel();
		scoreLabel = new JLabel();
		scoreLabel.setForeground(Color.WHITE);
		scoreLabel.setText("Score: ");
		scorePanel.add(scoreLabel);
		scorePanel.add(score);
		scorePanel.setBackground(Color.BLACK);

		this.setLayout(new GridLayout(1, 1));
		this.add(scorePanel);

	}

	public JTextField getScore() {
		return this.score;
	}

	public int getCurrentScore() {
		return this.currentScore;
	}

	public void setScore(String currentScore) {
		this.score.setText("" + currentScore);
	}

}
