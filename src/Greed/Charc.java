package Greed;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Charc  {
	
	private Board board;

	private String charc1front = "charc1front.png";
	private String charc1back = "charc1back.png";
	private String charc1left = "charc1left.png";
	private String charc1right = "charc1right.png";
	public int charc1X = 10;
	public int charc1Y = 25;
	private int charc1Width = 13;
	private int charc1Height = 20;
	public int charc1Score = 0;
	public int charc1Health = 3;
	
	public boolean left = false;
	public boolean right = true;
	public boolean up = false;
	public boolean down = false;
	
	private int speed = 1;
	
	private Image charc1Image;
	private Rectangle hitbox;
	
	public Charc(Board board2) {
		
		board = board2;
		getCharc1Image();
		hitbox = new Rectangle(charc1X, charc1Y, charc1Width, charc1Height);
		
	}
	
	public Image getCharc1Image() {
		if (right) {
			ImageIcon c1ii = new ImageIcon(this.getClass().getResource(charc1right));
			charc1Image = c1ii.getImage();
		}
		if (left) {
			ImageIcon c1ii = new ImageIcon(this.getClass().getResource(charc1left));
			charc1Image = c1ii.getImage();
		}
		if (up) {
			ImageIcon c1ii = new ImageIcon(this.getClass().getResource(charc1back));
			charc1Image = c1ii.getImage();
		}
		if (down) {
			ImageIcon c1ii = new ImageIcon(this.getClass().getResource(charc1front));
			charc1Image = c1ii.getImage();
		}
		return charc1Image;
	}
	
	public void move(ArrayList<Wall> wallsArray, ArrayList<Powerup> powerupsArray, ArrayList<Damage> damagesArray) {
		
		if (left) {
			for (Wall wall: wallsArray) {
				if (this.hitbox.intersects(wall.getHitbox())) {
					charc1X = ((wall.getHitbox().x + wall.getHitbox().width) +1);
					speed = 0;
					hitbox = new Rectangle(charc1X, charc1Y, charc1Width, charc1Height);
				}
			}
			if (charc1X <= 1) {
				charc1X += (431 - charc1Width);
			}
			charc1X -= speed;
			hitbox = new Rectangle(charc1X, charc1Y, charc1Width, charc1Height);
		}
		
		if (right) {
			for (Wall wall: wallsArray) {
				if (this.hitbox.intersects(wall.getHitbox())) {
					charc1X = (wall.getHitbox().x - 1 - this.charc1Width);
					speed = 0;
					hitbox = new Rectangle(charc1X, charc1Y, charc1Width, charc1Height);
				}
			}
			if (charc1X >= (431 - charc1Width)) {
				charc1X -= (431 - charc1Width);
			}
			charc1X += speed;
			hitbox = new Rectangle(charc1X, charc1Y, charc1Width, charc1Height);
		}


		if (up) {
			for (Wall wall: wallsArray) {
				if (this.hitbox.intersects(wall.getHitbox())) {
					charc1Y = ((wall.getHitbox().y + wall.getHitbox().height) +1);
					speed = 0;
					hitbox = new Rectangle(charc1X, charc1Y, charc1Width, charc1Height);
				}
			}
			if (charc1Y <= 1) {
				charc1Y += 431;
			}
			charc1Y -= speed;
			hitbox = new Rectangle(charc1X, charc1Y, charc1Width, charc1Height);
		}
		
		if (down) {
			for (Wall wall: wallsArray) {
				if (this.hitbox.intersects(wall.getHitbox())) {
					charc1Y = (wall.getHitbox().y - 1 - this.charc1Height);
					speed = 0;
					hitbox = new Rectangle(charc1X, charc1Y, charc1Width, charc1Height);
				}
			}
			if (charc1Y > (431 - charc1Height)) {
				charc1Y -= (431 - charc1Height);
			}
			charc1Y += speed;
			hitbox = new Rectangle(charc1X, charc1Y, charc1Width, charc1Height);
		}
		
		for(int i = 0; i < powerupsArray.size(); i++) {
			Powerup powerup = powerupsArray.get(i);
			 
			if (this.hitbox.intersects(powerup.getHitbox())) {
				powerupsArray.remove(i);
				charc1Score++;
				board.gameProgress();
				SoundEffects.POWERUP.play();
				//SOUND HERE
			}
		}
		
		for(int i = 0; i < damagesArray.size(); i++) {
			Damage damage = damagesArray.get(i);
			
			if (this.hitbox.intersects(damage.getHitbox())) {
				damagesArray.remove(i);
				charc1Health--;
				board.gameProgress();
				SoundEffects.DAMAGE.play();
				//SOUND HERE
			}
		}
		
	}
	
	public int getCharc1X() {
		return charc1X;
	}
	
	public int getCharc1Y() {
		return charc1Y;
	}
	
	public int getCharc1Width() {
		return charc1Width;
	}
	
	public int getCharc1Height() {
		return charc1Height;
	}
	
	public int getCharc1Score() {
		return charc1Score;
	}
	
	public int getCharc1Health() {
		return charc1Health;
	}
	
	public Rectangle getCharcHitbox() {
		return hitbox;
	}
		
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && (!right)) {
			left = true;
			up = false;
			down = false;		
			speed = 1;
		}
		
		if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && (!left)) {
			right = true;
			up = false;
			down = false;
			speed = 1;
		}
		
		if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && (!down)) {
			left = false;
			right = false;
			up = true;
			speed = 1;
		}
		
		if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && (!up)) {
			left = false;
			right = false;
			down = true;
			speed = 1;
		}
	}
}
