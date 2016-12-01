package cz.uhk.pro2.flappybird.game;

import java.awt.Color;
import java.awt.Graphics;

public class Bird implements TickAware {
	static final double koefUp = -5.0;
	static final double koefDown = 2.0;
	static final int ticksFlyingUp = 4;
	
	int viewportX;
	double viewportY;
	
	double velocityY = koefDown;
	int ticksToFall = 0;
	
	public Bird(int initialX, int initialY){
		this.viewportX = initialX;
		this.viewportY = initialY;
	}
	public void kick(){
		velocityY = koefUp;
		ticksToFall = ticksFlyingUp;
	}
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		g.fillOval(viewportX-Tile.SIZE/2,(int)viewportY-Tile.SIZE/2,Tile.SIZE,Tile.SIZE);
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
}
