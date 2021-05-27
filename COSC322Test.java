package ubc.cosc322;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;

import ygraph.ai.smartfox.games.*;
import sfs2x.client.entities.Room;
import ygraph.ai.smartfox.games.amazons.AmazonsGameMessage;

/**
 * An example illustrating how to implement a GamePlayer
 * @author Yong Gao (yong.gao@ubc.ca)
 * Jan 5, 2021
 *
 */
public class COSC322Test extends GamePlayer{

    private GameClient gameClient = null; 
    private BaseGameGUI gamegui = null;
    private GameBoard board = null;
	private boolean gameStarted = false;
    private String userName = null;
    private String passwd = null;

 
	
    /**
     * The main method
     * @param args for name and passwd (current, any string would work)
     */
    public static void main(String[] args) {				 
    	COSC322Test player = new COSC322Test(args[0], args[1]);
    	
    	if(player.getGameGUI() == null) {
    		player.Go();
    	}
    	else {
    		BaseGameGUI.sys_setup();
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                	player.Go();
                }
            });
    	}
    }
	
    /**
     * Any name and passwd 
     * @param userName
      * @param passwd
     */
    public COSC322Test(String userName, String passwd) {
    	this.userName = userName;
    	this.passwd = passwd;
    	
    	//To make a GUI-based player, create an instance of BaseGameGUI
    	//and implement the method getGameGUI() accordingly
    	//this.gamegui = new BaseGameGUI(this);
    }
 


    @Override
    public void onLogin() {
    	//warm up 1
//    	System.out.println("Congratualations!!! "
//    			+ "I am called because the server indicated that the login is successfully");
//    	System.out.println("The next step is to find a room and join it: "
//    			+ "the gameClient instance created in my constructor knows how!"); 
//    	System.out.println((gameClient.getRoomList()));
//    	gameClient.joinRoom("Okanagan Lake");
    	userName = gameClient.getUserName();
    	if(gamegui != null) {
    		gamegui.setRoomInformation(gameClient.getRoomList());
    	}
    	
    }

    @Override
    public boolean handleGameMessage(String messageType, Map<String, Object> msgDetails) {

    	
    	if(messageType.equalsIgnoreCase(GameMessage.GAME_STATE_BOARD)) {
    		ArrayList<Integer> gameState = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.GAME_STATE);
            this.gamegui.setGameState(gameState);
    	}
    	if(messageType.equalsIgnoreCase(GameMessage.GAME_ACTION_MOVE)) {
    		
    		ArrayList<Integer> queenPos = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.QUEEN_POS_CURR);
            ArrayList<Integer> queenPosNext  = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.Queen_POS_NEXT);
            ArrayList<Integer> arrowPos = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.ARROW_POS);
            this.gamegui.updateGameState(queenPos, queenPosNext,arrowPos);
            
    	}
    	if(messageType.equalsIgnoreCase(GameMessage.GAME_ACTION_START)) {
    	   ArrayList<Integer> gameActionStart = (ArrayList<Integer>) msgDetails.get(AmazonsGameMessage.GAME_STATE);
		   System.out.println(msgDetails.get(AmazonsGameMessage.PLAYER_WHITE));
    	   System.out.println(msgDetails.get(AmazonsGameMessage.PLAYER_BLACK));
        }
    	
    	
    	//For a detailed description of the message types and format, 
    	//see the method GamePlayer.handleGameMessage() in the game-client-api document. 
    	 
    	return true;   	
    }
    
    
    @Override
    public String userName() {
    	return userName;
    }

	@Override
	public GameClient getGameClient() {
		// TODO Auto-generated method stub
		return this.gameClient;
	}

	@Override
	public BaseGameGUI getGameGUI() {
		// TODO Auto-generated method stub
		return  null;
	}

	@Override
	public void connect() {
		// TODO Auto-generated method stub
    	gameClient = new GameClient(userName, passwd, this);			
	}

	public class GameBoard extends JPanel{

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
		
		public GameBoard (Amazon game) {
			this.game = game;
			gameModel = new BoardGameModel(this.rows +1 , this.cols +1);
			
			addMouseListener(new gameEventHandler());
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
		protected void paintComponent(Graphics g2) {
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
	public class gameEventHandler extends MouseAdapter {
		
		int counter = 0;
		int qRow = 0, qCol = 0;
		int qFr = 0, qFc = 0;
		int aRow = 0, aCol = 0;
		
		public void mousePressed(MouseEvent e) {
			if (!gameStarted) return;
			
			int x = e.getX();
			int y = e.getY();
			
			if(((x-gameBoard.offset)<-5 || (y-offset)<-5)) return;
			
			int row = (y-offset) / cell +1;
			int col =  (x-offset)/ cell +1;
			
			if (counter == 0 ) {
				qFr = row;
				qFc = col;
				
				qFr = 11 - qFr;
				counter ++;
			}
			else if (counter == 1) {
				qRow = row;
				qCol = col;
				
				qRow = 11 - qRow;
				counter++;
			}
			else if (counter == 2) {
				aRow = row;
				aCol = col;
				
				aRow = 11 - aRow;
				counter++;
			}
			
			else if (counter == 3) {
				counter = 0;
				boolean validMove = markPosition(qRow,qCol,aCol,qFr,qFc,false);
				
			if (validMove) {
				game.playerMove(qRow,qCol,aRow,aCol,qFr,qFc);
			}
			qRow = 0;
			qCol = 0;
			aRow = 0;
			aCol = 0;
			
			}
		}
		
	}
}//end of class