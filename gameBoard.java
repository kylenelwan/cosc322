package ubc.cosc322;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import ygraph.ai.smartfox.games.*;



public class gameBoard extends JPanel{

	private int rows = 10, cols = 10;
	int width = 500;
	int height = 500;
	int cell = width/10;
	int offset = width/20;
	
	int posX = -10,posY = -10;
	int r = 0, c = 0;
	
	private BoardGameModel gameModel = null;
	Amazon game = null;
	
	boolean playerAMove;
	
	public gameBoard (Amazon game) {
		this.game = game;
		gameModel = new BoardGameModel(this.rows +1 , this.cols +1);
		
		addMouseListener(new GameEventHandler());
		test(true);
	
	}
	public void test (boolean isPlayerA) {
		String white = BoardGameModel.POS_MARKED_WHITE;
		String black = BoardGameModel.POS_MARKED_BLACK;
		
		gameModel.gameBoard[1][4] = white;
		gameModel.gameBoard[1][7] = white;
		gameModel.gameBoard[3][1] = white;
		gameModel.gameBoard[3][10] = white;
		
		gameModel.gameBoard[8][1] = black;
		gameModel.gameBoard[8][10] = black;
		gameModel.gameBoard[10][4] = black;
		gameModel.gameBoard[10][7] = black;
		
	}
	public boolean markPosition(int qRow, int qCol, int aRow,int aCol, int qFr, int qFc, boolean opponentMove) {
		System.out.println(qRow + ", " + qCol + ", " + aRow + ", " + aCol + ", " + qFr + ", " + qFc);
		
		
		if (gameModel.positionMarked(qRow, qCol, aRow, aCol, qFr, qFc, opponentMove)) repaint();
		else return gameModel.positionMarked(qRow, qCol, aRow, aCol, qFr, qFc, opponentMove);
		
		return opponentMove;
		
	}
	protected Dimension paintComponent(Graphics g2) {
		Graphics g = (Graphics2D) g2;
		
		for(int i =0; i<rows;i++) {
			g.drawLine(offset + i*cell, offset, offset + i*cell, rows*cell+offset);
			g.drawLine(offset, i*cell + offset, cols*cell + offset, i*cell + offset);
		}	
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					posX = j*cell + offset;
					posY = (9-i)*cell + offset;
					
					if(gameModel.gameBoard[i + 1][j + 1].equalsIgnoreCase(BoardGameModel.POS_AVAILABLE)){
						g.clearRect(posX + 1, posY + 1, 49, 49);
					}

					if(gameModel.gameBoard[i + 1][j + 1].equalsIgnoreCase(
							  BoardGameModel.POS_MARKED_BLACK)){
						g.fillOval(posX, posY, 50, 50);
					}
					else if(gameModel.gameBoard[i + 1][j + 1].equalsIgnoreCase(
						  BoardGameModel.POS_MARKED_ARROW)) {
						g.clearRect(posX + 1, posY + 1, 49, 49);
						g.drawLine(posX, posY, posX + 50, posY + 50);
						g.drawLine(posX, posY + 50, posX + 50, posY);
					}
					else if(gameModel.gameBoard[i + 1][j + 1].equalsIgnoreCase(BoardGameModel.POS_MARKED_WHITE)){
						g.drawOval(posX, posY, 50, 50);
					}
				  }
			}
	}
	public Dimension getPreferredSize() {
		return new Dimension (600,800);
	}
							
		}
	

