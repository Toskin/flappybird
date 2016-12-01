package cz.uhk.pro2.flappybird.game.service;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import cz.uhk.pro2.flappybird.game.GameBoard;
import cz.uhk.pro2.flappybird.game.Tile;
import cz.uhk.pro2.flappybird.game.tiles.EmptyTile;
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
			Map<String, Tile> tileTypes = new HashMap<>();
			for (int i = 0; i < numberOfTypes; i++){
				line = br.readLine().split(";");
				String type = line[0];
				String clazz = line[1];
				int spriteX = Integer.parseInt(line[2]);
				int spriteY = Integer.parseInt(line[3]);
				int spriteWidth = Integer.parseInt(line[4]);
				int spriteHeight = Integer.parseInt(line[5]);
				String url = line[6];
				Tile tile = createTile(clazz, spriteX, spriteY, spriteWidth, spriteHeight, url);
				tileTypes.put(type, tile);
				//TODO
				
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
						tiles[i][j] = tileTypes.get(t);
					}
				}
			}
			GameBoard gb = new GameBoard(tiles);
			return gb;
		} catch (IOException e) {
			throw new RuntimeException("Chyba pri cteni souboru s levelem", e);
		}
		
		
		

	}

	private Tile createTile(String clazz, int spriteX, int spriteY, int spriteWidth, int spriteHeight, String url) throws MalformedURLException, IOException {
		//Nacist pic z URL
		BufferedImage originalImage = ImageIO.read(new URL(url));
		//Vzit potrebnou cast obrazku
		BufferedImage croppedImage = originalImage.getSubimage(spriteX, spriteY, spriteWidth, spriteHeight);
		//zvetsit/zmensit sprite aby pasoval
		BufferedImage resizedImage = new BufferedImage(Tile.SIZE, Tile.SIZE, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D)resizedImage.getGraphics();
		g.drawImage(croppedImage, 0, 0, Tile.SIZE, Tile.SIZE,null);
		//podle typu vytvorit instanci tridy
		switch (clazz){
		case "Wall":
			return new WallTile(resizedImage);
		case "Floor":
			return new WallTile(resizedImage);
		case "Empty":
			return new EmptyTile(resizedImage);
		default:
			throw new RuntimeException("Neznama trida dlazdice " + clazz);
		}
		
	}

}
