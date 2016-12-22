package cz.uhk.pro2.flappybird.game.tiles;


import java.awt.Image;

import cz.uhk.pro2.flappybird.game.Bird;
import cz.uhk.pro2.flappybird.game.Tile;


public class WallTile extends AbstractTile {
	Image image;
	public WallTile(Image image){
		super(image);
	}
@Override
public boolean testColisionHasDied(Bird bird, int x, int y) {
	
	return bird.collidesWithRectangle(x, y, Tile.SIZE, Tile.SIZE);
}

}
