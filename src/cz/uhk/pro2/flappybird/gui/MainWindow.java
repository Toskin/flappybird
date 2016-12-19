package cz.uhk.pro2.flappybird.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.TimerTask;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import cz.uhk.pro2.flappybird.game.GameBoard;
import cz.uhk.pro2.flappybird.game.service.BoardLoader;
import cz.uhk.pro2.flappybird.game.service.CsvBoardLoader;

public class MainWindow extends JFrame {
	
	BoardPanel pnl = new BoardPanel();
	GameBoard gameBoard;
	private double x = 0;
	private double mult = 1;
	private boolean hard;
	class BoardPanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			gameBoard.drawAndDetectCollisions(g);
		}
	}
	
	public MainWindow() {
		try (InputStream is = new FileInputStream("level.csv")){
			BoardLoader loader = new CsvBoardLoader(is);
			gameBoard = loader.getGameBoard();
		} catch (FileNotFoundException e1) {
			gameBoard = new GameBoard();
			e1.printStackTrace();
		} catch (IOException e1) {
			gameBoard = new GameBoard();
			e1.printStackTrace();
		}
		
		Object[] options = {"Lehka","Tezka"};
		if(JOptionPane.showOptionDialog(pnl, "Zvol obtiznost", "Obtiznost", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]) == 1){
			hard = true;
		}
	
		pnl.setPreferredSize(new Dimension(300, 200));
		add(pnl, BorderLayout.CENTER);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		gameBoard.setWidthPix(pnl.getWidth());
		
		Timer t = new Timer(20, e -> {
			gameBoard.tick((long) ((x++)+x*mult));
			pnl.repaint();
		});
		Timer u = new Timer(1000, e -> {
			System.out.println(mult);
			mult = mult + 0.1;
		});
		
		addMouseListener (new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){

				if(e.getButton() == MouseEvent.BUTTON1){
					//V pripade ze timer nebezi
					if (!t.isRunning()){
						t.start();
						if(hard == true){
							u.start();
						}
					}else{
				gameBoard.kickTheBird();
					}
				} else if (e.getButton() == MouseEvent.BUTTON3){
					gameBoard.reset();
					x = 0;
					gameBoard.tick(0);
					pnl.repaint();
					t.stop();
					u.stop();
					mult = 1;
					
				}
				
			}
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(()-> {
			MainWindow w = new MainWindow();
			w.setVisible(true);
		});
	}

}
