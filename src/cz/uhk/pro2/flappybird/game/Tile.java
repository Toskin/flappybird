package cz.uhk.pro2.flappybird.game;

import java.awt.Graphics;

/**
 * Spolecny interface for dlazdice
 * @author stepape2
 *
 */

public interface Tile {
	/**
	 * Velikost dlazdice v px
	 */
	public static final int SIZE = 20;
	
	/**
	 * Vykresleni dlazdice na platno g
	 * @param g
	 */
	void draw(Graphics g, int x, int y);
}
