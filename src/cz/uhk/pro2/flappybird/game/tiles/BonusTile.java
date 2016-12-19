package cz.uhk.pro2.flappybird.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import cz.uhk.pro2.flappybird.game.Tile;

public class BonusTile extends AbstractTile{
	private boolean isCollectable = true;
	Tile emptyTile;
	public void setIsCollectable(boolean value) {
		isCollectable = value;
	}
	@Override
	public void draw(Graphics g, int x, int y) {
		if(isCollectable){
			super.draw(g, x, y);
		}else{
		emptyTile.draw(g, x, y);
		}
	}
	public BonusTile(Image image, Tile emptyTile){
		super(image);
		this.emptyTile = emptyTile;
	}
}
