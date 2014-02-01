package Greed;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Powerup extends JPanel {

	private Image powerupImage;
	private int powerupX;
	private int powerupY;
	private final int powerupWidth = 13;
	private final int powerupHeight = 13;
	private Rectangle hitbox;

	public Powerup(int randomX, int randomY) {
		ImageIcon dii = new ImageIcon(this.getClass().getResource("powerup.png"));
		powerupImage = dii.getImage();
		powerupX = randomX;
		powerupY = randomY;
		hitbox = new Rectangle(powerupX, powerupY, powerupWidth, powerupHeight);
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}
	
	public Image getPowerupImage() {
		return powerupImage;
	}
	
	public int getPowerupX() {
		return powerupX;
	}
	
	public int getPowerupY() {
		return powerupY;
	}
	public int getPowerupWidth() {
		return powerupWidth;
	}
	public int getPowerupHeight() {
		return powerupHeight;
	}
}