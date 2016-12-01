package cz.uhk.pro2.flappybird.game;

/**
 * Rozhrani pro objekty, ktere potrebuji vedet, kolik casu (ticku) ubehlo od zacatku hry.
 * @author stepape2
 *
 */

public interface TickAware {
	/**
	 * Zmeni stav herni entity s ohledem na zmenu herniho casu
	 * @param tickSinceStart
	 */
	void tick(long ticksSinceStart);

}
