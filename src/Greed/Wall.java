package Greed;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Wall {
	
	private Image wallVerticle;
	private int wallVerticleX;
	private int wallVerticleY;
	private final int wallVerticleWidth = 8;
	private final int wallVerticleHeight = 48;
	
	private Image wallHorizontal;
	private int wallHorizontalX;
	private int wallHorizontalY;
	private final int wallHorizontalWidth = 48;
	private final int wallHorizontalHeight = 8;
	
	private Rectangle hitbox;
	
	public Wall(int randomX, int randomY, boolean isHorizontal) {
		
		if (isHorizontal) {
			
			ImageIcon iiw = new ImageIcon(this.getClass().getResource("wallHorizontal.png"));
			wallHorizontal = iiw.getImage();
			wallHorizontalX = randomX;
			wallHorizontalY = randomY;
			hitbox = new Rectangle(wallHorizontalX, wallHorizontalY, wallHorizontalWidth, wallHorizontalHeight);
			
		}
		
		else {
			
			ImageIcon iiw = new ImageIcon(this.getClass().getResource("wallVerticle.png"));
			wallVerticle = iiw.getImage();
			wallVerticleX = randomX;
			wallVerticleY = randomY;
			hitbox = new Rectangle(wallVerticleX, wallVerticleY, wallVerticleWidth, wallVerticleHeight);
			
		}
		
	}
		
	public int getWallVerticleX() {
		return wallVerticleX;
	}
	
	public int getWallVerticleY() {
		return wallVerticleY;
	}
	
	public int getWallVerticleWidth() {
		return wallVerticleWidth;
	}
	
	public int getWallVerticleHeight() {
		return wallVerticleHeight;
	}
	
	public Image getVerticleImage() {
		return wallVerticle;
	}
	
	public int getWallHorizontalX() {
		return wallHorizontalX;
	}
	
	public int getWallHorizontalY() {
		return wallHorizontalY;
	}
	
	public int getWallHorizontalWidth() {
		return wallHorizontalWidth;
	}
	
	public int getWallHorizontalHeight() {
		return wallHorizontalHeight;
	}
	
	public Image getHorizontalImage() {
		return wallHorizontal;
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}

}
