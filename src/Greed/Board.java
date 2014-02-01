package Greed;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener {

	private Timer timer;
	private Charc charc;
	
	//is the game live?
	private boolean inGame = false;
	private boolean deadGame = false;
	private boolean startScreen = true;
	private boolean splash2Screen = false;
	
	//info to pass to the wall class
	private boolean isHorizontal;

	//background image
	private String bgImageLoc = "background.png";
	private Image bgImage;
	
	//splash image
	private String splashImageLoc = "splash.png";
	private Image splashImage;
	
	//game over image
	private String splash2ImageLoc = "splash2.png";
	private Image splash2Image;
		
	//instructions image
	private String instructionsImageLoc = "instructions.png";
	private Image instructionsImage;
	
	//game over image
	private String gameOverImageLoc = "gameover.png";
	private Image gameOverImage;
	
	//arrays that hold walls, damage and powerups information
	ArrayList<Wall> wallsArray = new ArrayList<Wall>();
	ArrayList<Damage> damagesArray = new ArrayList<Damage>();
	ArrayList<Powerup> powerupsArray = new ArrayList<Powerup>();
	
	//random number generator
	Random generator = new Random();
		
	//font for the game over screen
	Font font = new Font("Sanserif", Font.BOLD, 36);
	
	public Board() {
		
		addKeyListener(new TAdapter());
		setFocusable(true);
		
		//background image
		ImageIcon bgii = new ImageIcon(this.getClass().getResource(bgImageLoc));
		bgImage = bgii.getImage();
		
		//splash image
		ImageIcon sii = new ImageIcon(this.getClass().getResource(splashImageLoc));
		splashImage = sii.getImage();
		
		//splash image
		ImageIcon s2ii = new ImageIcon(this.getClass().getResource(splash2ImageLoc));
		splash2Image = s2ii.getImage();
				
		//game over image
		ImageIcon iii = new ImageIcon(this.getClass().getResource(instructionsImageLoc));
		instructionsImage = iii.getImage();
		
		//game over image
		ImageIcon goii = new ImageIcon(this.getClass().getResource(gameOverImageLoc));
		gameOverImage = goii.getImage();
		
		//double buffered
		setDoubleBuffered(true);
		
		//creating a charcter
		charc = new Charc(this);
		
		timer = new Timer(5, this);
		timer.start();
	
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		//paint the start screen if it's not a dead game
		
		if (startScreen && !splash2Screen && !inGame && !deadGame) {
			startScreen(g);	
		}
		else if (!startScreen && splash2Screen && !inGame && !deadGame) {
			splash2Screen(g);
		}
		else if (!startScreen && !splash2Screen && !inGame && !deadGame) {
			instructonsScreen(g);
		}
		else if (inGame) {
			//background image in the back
			g.drawImage(bgImage, 0, 0, this);
			//get the font and color
			g.setFont(new Font("sansserif", Font.BOLD, 24));
			g.setColor(Color.blue);
			//Score on top of background
			g.drawString("Score: " + charc.getCharc1Score(), 10, 30);
			//Health on top of background
			g.drawString("Health: " + charc.getCharc1Health(), 210, 30);
			
			//Character 1 on top of the score/health
			g2d.drawImage(charc.getCharc1Image(), charc.getCharc1X(), charc.getCharc1Y(), this);
			
			//walls damage and powerups on the top of the screen
			drawWalls(g);
			drawPowerup(g);
			drawDamage(g);
			
			//check to see if the player is alive
			if(charc.getCharc1Health() <= 0) {
				deadGame = true;
				inGame = false;
			}
			//game is dead, stop it
		}
		else if (deadGame) {
			//paint game over screen
			powerupsArray.clear();
			wallsArray.clear();
			damagesArray.clear();
			drawGameOver(g);
		}
		//garbage stuff
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//if the game is active, listen for actions
		if (inGame){
			//character will be listening
			charc.move(wallsArray, powerupsArray, damagesArray);
		}
		//redraw the screen when an action has taken place
		repaint();
	}
	
	private class TAdapter extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			//find out what key is being pressed
			int key = e.getKeyCode();
			//if it's enter, start the game
			if (key == KeyEvent.VK_ENTER && !inGame) {
				if (startScreen) {
					startScreen = false;
					splash2Screen = true;
				}
				else if (splash2Screen) {
					splash2Screen = false;
				}
				else {
					startGame();
				}
			}
			if (key == KeyEvent.VK_Q && !deadGame) {
				inGame = false;
				deadGame = true;
			}
			//or move the character if it is not enter
			charc.keyPressed(e);
		}
	}
	//setting the parameters for an active game
	public void startGame() {
		inGame = true;
		deadGame = false;
		timer.start();
		charc.left = false;
		charc.right = true;
		charc.up = false;
		charc.down = false;
		charc.charc1Score = 0;
		charc.charc1Health = 3;
		charc.charc1X = 10;
		charc.charc1Y = 25;
		
		//starting the game off with some walls, damage and power ups
		createVerticleWall();
		createVerticleWall();
		createHorizontalWall();
		createHorizontalWall();
		createDamage();
		createDamage();
		createPowerup();
		createPowerup();
		createPowerup();
		createPowerup();
		createPowerup();
	}
	//drawing the start screen
	public void startScreen(Graphics g) {
		g.drawImage(splashImage, 0, 0, this);
	}
	
	public void instructonsScreen(Graphics g) {
		g.drawImage(instructionsImage, 0, 0, this);
	}
	
	public void splash2Screen(Graphics g) {
		g.drawImage(splash2Image, 0, 0, this);
	}

	public void drawGameOver (Graphics g) {
		inGame = false;
		//background
		g.drawImage(gameOverImage, 0, 0, this);
		//score with font
		g.setFont(font);
		g.setColor(Color.white);
		g.drawString(Integer.toString(charc.getCharc1Score()), 219, 271);
	}
	//creating verticle walls
	public void createVerticleWall() {
		//verticle != horizontal... duh
		isHorizontal = false;
		//get some random coords to plug it into
		int randomX = generator.nextInt(424);
		int randomY = generator.nextInt(424);
		randomX = (randomX / 48) * 48;
		randomY = (randomY / 48) * 48;
		//create a new wall in the arraylist
		Wall wall = new Wall(randomX, randomY, isHorizontal);
		//add it to the arraylist
		wallsArray.add(wall);
	}
	//creating horizontal walls
	public void createHorizontalWall() {
		//hey look, it IS horizontal this time!
		isHorizontal = true;
		//get some random coors to plug it into
		int randomX = generator.nextInt(384);
		int randomY = generator.nextInt(424);
		randomX = (randomX / 48) * 48;
		randomY = (randomY / 48) * 48;
		//create a new wall in they arraylist
		Wall wall = new Wall(randomX, randomY, isHorizontal);
		//add it to the arraylist
		wallsArray.add(wall);
	}
	//drawing walls
	public void drawWalls(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		//drawing walls on the board
		for (Wall wall: wallsArray) {
			//if verticle
			g2d.drawImage(wall.getVerticleImage(), wall.getWallVerticleX(), wall.getWallVerticleY(), this);
			//if horizontal
			g2d.drawImage(wall.getHorizontalImage(), wall.getWallHorizontalX(), wall.getWallHorizontalY(), this);
		}	
	}
	//creating damaging drops
	public void createDamage() {
		//getting some random coords to drop it into
		int randomX = generator.nextInt(424);
		int randomY = generator.nextInt(424);
		randomX = (randomX / 13) * 13;
		randomY = (randomY / 13) * 13;
		//create a new damage in the arraylist
		Damage damage = new Damage(randomX, randomY);
		//add it to the list
		damagesArray.add(damage);
	}
	//drawing damage
	public void drawDamage(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		for (Damage damage: damagesArray) {
			//draw the damage on the board
			g2d.drawImage(damage.getDamageImage(), damage.getDamageX(), damage.getDamageY(), this);
		}	
	}
	//creating powerups or score
	public void createPowerup() {
		//get some random coords
		int randomX = generator.nextInt(424);
		int randomY = generator.nextInt(424);
		randomX = (randomX / 13) * 13;
		randomY = (randomY / 13) * 13;
		//creating a new powerup in the arraylist 
		Powerup powerup = new Powerup(randomX, randomY);
		//add it to the list
		powerupsArray.add(powerup);
	}
	//draw the powerups
	public void drawPowerup(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		for (Powerup powerup: powerupsArray) {
			//draw the powerups to the board.
			g2d.drawImage(powerup.getPowerupImage(), powerup.getPowerupX(), powerup.getPowerupY(), this);
		}	
	}
	//if the player has done something, progress the game... make it harder, but with more points!
	public void gameProgress() {
		//throwing these new items into their arrays
		createDamage();
		createPowerup();
		createPowerup();
		createPowerup();
		//alternate walls when the player picks up a powerup
		if (charc.getCharc1Score() % 2 == 0) {
			createVerticleWall();
			createVerticleWall();
		}
		else {
			createHorizontalWall();
			createHorizontalWall();
		}					
	}	
}

