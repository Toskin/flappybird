package cz.uhk.pro2.flappybird.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import cz.uhk.pro2.flappybird.game.Tile;

public class BonusTile extends AbstractTile{
	Tile emptyTile;
	public BonusTile(Image image, Tile emptyTile){
		super(image);
		this.emptyTile = emptyTile;
	}
	
	@Override
	public void draw(Graphics g, int x, int y) {
		//g.drawRect(x, y, Tile.SIZE, Tile.SIZE);
		emptyTile.draw(g, x, y);
		
	}
}
