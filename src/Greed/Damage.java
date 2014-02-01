package Greed;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class Damage extends JPanel {

	private Image damageImage;
	private int damageX;
	private int damageY;
	private final int damageWidth = 13;
	private final int damageHeight = 13;
	private Rectangle hitbox;
	
	public Damage(int randomX, int randomY) {
		ImageIcon dii = new ImageIcon(this.getClass().getResource("damage.png"));
		damageImage = dii.getImage();
		damageX = randomX;
		damageY = randomY;
		hitbox = new Rectangle(damageX, damageY, damageWidth, damageHeight);
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}
	
	public Image getDamageImage() {
		return damageImage;
	}
	
	public int getDamageX() {
		return damageX;
	}
	public int getDamageY() {
		return damageY;
	}
	public int getDamageWidth() {
		return damageWidth;
	}
	public int getDamageHeight() {
		return damageHeight;
	}
}