package Greed;

import javax.swing.JFrame;

public class Greed extends JFrame {
	
	/*
	 * music gathered from www.mattmcfarland.com
	 * Thanks for the help: Frosty_Freeze, Peppera, Pushupek84
	 */

	//initialize the game
	public Greed() {
		//initialize the board, charc, damage & powerups
		add(new Board());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(438,460);
		setLocationRelativeTo(null);
		setTitle("Greed - LD25 - Tate's entry");
		setResizable(false);
		setVisible(true);

		SoundEffects.init();
		SoundEffects.volume = SoundEffects.volume.LOW;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Greed();
	}

}
