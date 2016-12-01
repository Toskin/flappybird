package cz.uhk.pro2.flappybird.game;

import java.awt.Graphics;

import cz.uhk.pro2.flappybird.game.tiles.WallTile;

public class GameBoard implements TickAware {
	Tile[][] tiles;
	int shiftX;
	int widthPix;
	Bird bird;
	
	public GameBoard() {
		tiles = new Tile[20][10];
		//tiles[2][1] = new WallTile();
		bird = new Bird(100,100);
	}
	public GameBoard(Tile[][] tiles){
		this.tiles = tiles;
		bird = new Bird(100,100);
	}
	
	public void setWidthPix(int widthPix) {
		this.widthPix = widthPix;
	}
	/**
	 * Vykresleni herni plochy
	 * @param g
	 */
	public void draw(Graphics g) {
		int minJ = shiftX/Tile.SIZE;
		int countJ = widthPix/Tile.SIZE + 2;
		for (int i = 0; i < tiles.length; i++) {
			for (int j = minJ; j < minJ+countJ; j++){
				int modJ = j % tiles[0].length;
				Tile t = tiles [i][modJ];
				if (t != null){
					int viewportX = j*Tile.SIZE - shiftX;
					int viewportY = i*Tile.SIZE;
					t.draw(g, viewportX, viewportY);
				}
			}
		}
		bird.draw(g);
	
	}
@Override
public void tick(long ticksSinceStart){
	shiftX = (int)ticksSinceStart;
	bird.tick(ticksSinceStart);
}

public void kickTheBird(){
	bird.kick();
}
}


