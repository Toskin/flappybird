package cz.uhk.pro2.flappybird.game;

import java.awt.Graphics;
import java.awt.Image;

import cz.uhk.pro2.flappybird.game.tiles.WallTile;

public class GameBoard implements TickAware {
	Tile[][] tiles;
	int shiftX;
	int widthPix;
	Bird bird;
	boolean gameOver;
	Image imageOfTheBird;
	
	public GameBoard() {
		tiles = new Tile[20][10];
		//tiles[2][1] = new WallTile();
		bird = new Bird(100,100, imageOfTheBird);
	}
	public GameBoard(Tile[][] tiles, Image imageOfTheBird){
		this.tiles = tiles;
		this.imageOfTheBird = imageOfTheBird;
		reset();
	}
	
	public void setWidthPix(int widthPix) {
		this.widthPix = widthPix;
	}
	/**
	 * Vykresleni herni plochy
	 * @param g
	 */
	public void drawAndDetectCollisions(Graphics g) {
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
					if (t instanceof WallTile){
						if (bird.collidesWithRectangle(viewportX, viewportY, Tile.SIZE, Tile.SIZE)){
							gameOver = true;
						}
					}
				}
			}
		}
		bird.draw(g);
	
	}
@Override
public void tick(long ticksSinceStart){
	if (!gameOver){
	shiftX = (int)ticksSinceStart;
	bird.tick(ticksSinceStart);
	}
}

public void kickTheBird(){
	bird.kick();
}

public void reset(){
	gameOver = false;
	bird = new Bird(100,100, imageOfTheBird);
}
}


