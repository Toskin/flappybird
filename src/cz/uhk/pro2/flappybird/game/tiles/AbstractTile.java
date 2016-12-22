package cz.uhk.pro2.flappybird.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import cz.uhk.pro2.flappybird.game.Bird;
import cz.uhk.pro2.flappybird.game.Tile;

public abstract class AbstractTile implements Tile {
	Image image;
	public AbstractTile(Image image){
		this.image = image;
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		//g.drawRect(x, y, Tile.SIZE, Tile.SIZE);
		g.drawImage(image, x, y, null);
		
	}
	@Override
	public boolean testColisionHasDied(Bird bird, int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int testColisionObtainBonusPoint(Bird bird, int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}
