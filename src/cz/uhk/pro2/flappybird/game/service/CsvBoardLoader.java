package cz.uhk.pro2.flappybird.game.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.logging.Level;
import java.util.logging.Logger;

import cz.uhk.pro2.flappybird.game.GameBoard;
import cz.uhk.pro2.flappybird.game.Tile;
import cz.uhk.pro2.flappybird.game.tiles.WallTile;

public class CsvBoardLoader implements BoardLoader {
	static final Logger logger = Logger.getLogger(CsvBoardLoader.class.getName());
	InputStream is;
	
	public CsvBoardLoader(InputStream is) {
		this.is = is;
	}

	@Override
	public GameBoard getGameBoard() {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"))
				) {
			String[] line = br.readLine().split(";");
			int numberOfTypes = Integer.parseInt(line[0]);
			//logger.log(Level.FINE,"Number of tile types:" + numberOfTypes);
			System.out.println("Number of tile types:" +numberOfTypes );
			for (int i = 0; i < numberOfTypes; i++){
				line = br.readLine().split(";");
				String type = line[0];
				String clazz = line[1];
				int spriteX = Integer.parseInt(line[2]);
				int spriteY = Integer.parseInt(line[3]);
				int spriteWidth = Integer.parseInt(line[4]);
				int spriteHeight = Integer.parseInt(line[5]);
				String url = line[6];
				
			}
			line = br.readLine().split(";");
			int rows = Integer.parseInt(line[0]);
			int columns = Integer.parseInt(line[1]);
			//System.out.println(x);
			
			Tile[][] tiles = new Tile[rows][columns];
			
			for (int i = 0; i < rows; i++){
				line = br.readLine().split(";");
				for (int j = 0; j < columns; j++){
					String t;
					if (j < line.length){
					t = line[j];
					}else {
						t = "";
					}
					if (!"".equals(t)){
						tiles[i][j] = new WallTile();
					}
				}
			}
			GameBoard gb = new GameBoard(tiles);
			return gb;
		} catch (IOException e) {
			throw new RuntimeException("Chyba pri cteni souboru s levelem");
		}
		
		
		

	}

}
