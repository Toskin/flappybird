package cz.uhk.pro2.flappybird.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Ellipse2D;

public class Bird implements TickAware {
	static final double koefUp = -5.0;
	static final double koefDown = 2.0;
	static final int ticksFlyingUp = 4;
	
	int viewportX;
	double viewportY;
	
	double velocityY = koefDown;
	int ticksToFall = 0;
	
	Image image;
	
	public Bird(int initialX, int initialY, Image image){
		this.viewportX = initialX;
		this.viewportY = initialY;
		this.image = image;
	}
	public void kick(){
		velocityY = koefUp;
		ticksToFall = ticksFlyingUp;
	}
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		//g.fillOval(viewportX-Tile.SIZE/2,(int)viewportY-Tile.SIZE/2,Tile.SIZE,Tile.SIZE);
		//nakresli obrazek ptaka
		g.drawImage(image, viewportX-Tile.SIZE/2,(int)viewportY-Tile.SIZE/2,null);
		g.setColor(Color.BLACK);
		g.drawString(viewportX+", "+(int)viewportY, viewportX,(int)viewportY);
	}
	
	@Override
	public void tick(long ticksSinceStart){
		
		viewportY += velocityY;
		if (ticksToFall > 0){
			ticksToFall--;
		} else {
			velocityY = koefDown;
		}
	}
	public boolean collidesWithRectangle(int x, int y, int w, int h){
		Ellipse2D.Float birdBoundary = new Ellipse2D.Float(viewportX-Tile.SIZE/2,(int)viewportY-Tile.SIZE/2,Tile.SIZE,Tile.SIZE);
		return birdBoundary.intersects(x, y, w, h);
		
	}
}
