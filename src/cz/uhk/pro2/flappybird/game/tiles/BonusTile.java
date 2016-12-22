package cz.uhk.pro2.flappybird.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import cz.uhk.pro2.flappybird.game.Bird;
import cz.uhk.pro2.flappybird.game.Tile;

public class BonusTile extends AbstractTile{
	Tile emptyTile;
	boolean active = true;
	
	public BonusTile(Image image, Tile emptyTile){
		super(image);
		this.emptyTile = emptyTile;
	}
	@Override
	public void draw(Graphics g, int x, int y) {
		emptyTile.draw(g, x, y);
		}
	
	@Override
	public int testColisionObtainBonusPoint(Bird bird, int x, int y) {
		if (bird.collidesWithRectangle(x, y, Tile.SIZE, Tile.SIZE) && active){
			active = false;
			return 1000;

		}else{
			return 0;
		}
	}
	@Override
	public void refresh() {
		active = true;
	}
}
