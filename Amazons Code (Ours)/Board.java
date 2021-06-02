/*
 * Sources used:
 * Billy Spelch Tic Tac Toe
 * https://stackoverflow.com/questions/46694289/sorting-for-2-arraylist-that-each-of-them-represent-x-and-y-coordinate
 * https://github.com/miken22/322-Amazon-AI/blob/master/src/ai/Board.java
 */
package ubc.cosc322;

import java.util.ArrayList;

import cern.colt.list.BooleanArrayList;
import ygraph.ai.smartfox.games.BoardGameModel;
import ygraph.ai.smartfox.games.GamePlayer;

public  class Board {
	// constants
	public static final int ROWS = 10;
	public static final int COLS = 10;
	
	public static final int AVAILABLE = 0;
	public static final int ARROW = 1;
	public static final int BLACK_QUEEN = 2;
	public static final int WHITE_QUEEN = 3;
	
	boolean freeSpace;
	
	public static int arrowCounter = 0; 
	protected int [][] board;
	private ArrayList<Integer> gameState = new ArrayList<>();
	ArrayList<XYCoordinates> whitePos = new ArrayList<>();
	ArrayList<XYCoordinates> blackPos = new ArrayList<>();
	ArrayList<XYCoordinates> whiteTrappedPos = new ArrayList<>();
	ArrayList<XYCoordinates> blackTrappedPos = new ArrayList<>();
	
	// array lists for moves 
	ArrayList<Integer> queenToMoveLocation = new ArrayList<>();
	ArrayList<Integer> queenMovedToLocation = new ArrayList<>();
	ArrayList<Integer> arrowThrownLocation = new ArrayList<>();

	
	// constructor
	// creates empty board with queens (Amazons) in starting positions
	public Board() {
		board = new int[ROWS][COLS];
		whitePos = new ArrayList<>();
		blackPos = new ArrayList<>();
		whiteTrappedPos = new ArrayList<>();
		blackTrappedPos = new ArrayList<>();
	}
	
	// clone board constructor
	public Board(Board cloned) {
		this();
		for(XYCoordinates whiteCoord : cloned.getWhitePos()) {
			whitePos.add(whiteCoord);
		}
		for(XYCoordinates blackCoord : cloned.getBlackPos()) {
			blackPos.add(blackCoord);
		}
		// these will be empty at start
		for(XYCoordinates whiteTrappedCoord : cloned.getWhiteTrappedPos()) {
			whiteTrappedPos.add(whiteTrappedCoord);
		}
		for(XYCoordinates blackTrappedCoord : cloned.getBlackTrappedPos()) {
			blackTrappedPos.add(blackTrappedCoord);
		}	
	}
	
	// clone board method -- do we need this?
	public void cloneBoard(Board cloned) {
		System.arraycopy(cloned.board, 0, board, 0, COLS);
	}
	
		
	//print board
	public void printState() {
		board[0][3] = BLACK_QUEEN;
		board[0][6] = BLACK_QUEEN;
		board[3][0] = BLACK_QUEEN;
		board[3][9] = BLACK_QUEEN;
		
		board[6][0] = WHITE_QUEEN;
		board[9][3] = WHITE_QUEEN;
		board[9][6] = WHITE_QUEEN;
		board[6][9] = WHITE_QUEEN;
	
		for(int i = 0; i < 10 ; i++) {
			System.out.print("| ");
			for(int j = 0; j <10; j++) {
				if(board[i][j] == WHITE_QUEEN) {
					System.out.print(" W");
					whitePos.add(new XYCoordinates(i, j));
					freeSpace = false;
				}else if(board[i][j] == BLACK_QUEEN) {
					System.out.print(" B");
					blackPos.add(new XYCoordinates(i, j));
					freeSpace = false;
				}else if (board[i][j] == ARROW) {
					System.out.print(" A");
					freeSpace = false;
				}else {
					System.out.print(" -");
					freeSpace = true;
				}
			}
			System.out.println(" |");
		}
	}

//	public boolean isWhiteTrapped(XYCoordinates pos) {
//		if(whiteTrappedPos.contains(pos)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//	
//	public boolean isBlackTrapped(XYCoordinates pos) {
//		if(blackTrappedPos.contains(pos)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
	
	
	public void updateState(ArrayList<Integer> queenPos, ArrayList<Integer> queenNext, ArrayList<Integer> arrowPos) {
		
	}
	
	public void setState(ArrayList<Integer> gameState) {
		
	}
	
	// getter and setter methods
	public ArrayList<XYCoordinates> getWhitePos() {
		return whitePos;
	}
	
	public ArrayList<XYCoordinates> getBlackPos() {
		return blackPos;
	}
	
	public ArrayList<XYCoordinates> getWhiteTrappedPos() {
		return whiteTrappedPos;
	}
	
	public ArrayList<XYCoordinates> getBlackTrappedPos() {
		return blackTrappedPos;
	}

	public void setWhitePos(ArrayList<XYCoordinates> whitePos) {
		this.whitePos = whitePos;
	}

	public void setBlackPos(ArrayList<XYCoordinates> blackPos) {
		this.blackPos = blackPos;
	}

	public void setWhiteTrappedPos(ArrayList<XYCoordinates> whiteTrappedPos) {
		this.whiteTrappedPos = whiteTrappedPos;
	}

	public void setBlackTrappedPos(ArrayList<XYCoordinates> blackTrappedPos) {
		this.blackTrappedPos = blackTrappedPos;
	}
}
