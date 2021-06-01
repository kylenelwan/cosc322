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
	
	protected int [][] board;
	private ArrayList<Integer> gameState = new ArrayList<>();
	ArrayList<XYCoordinates> whitePos = new ArrayList<>();
	ArrayList<XYCoordinates> blackPos = new ArrayList<>();
	ArrayList<XYCoordinates> whiteTrappedPos = new ArrayList<>();
	ArrayList<XYCoordinates> blackTrappedPos = new ArrayList<>();
	
	// constructor
	// creates empty board with queens (Amazons) in starting positions
	public Board() {
		board = new int[ROWS][COLS];
		board[0][3] = WHITE_QUEEN;
		board[0][6] = WHITE_QUEEN;
		board[3][0] = WHITE_QUEEN;
		board[3][9] = WHITE_QUEEN;
		
		board[6][0] = BLACK_QUEEN;
		board[9][3] = BLACK_QUEEN;
		board[9][6] = BLACK_QUEEN;
		board[6][9] = BLACK_QUEEN;
	
		whitePos = new ArrayList<>();
		blackPos = new ArrayList<>();
		whiteTrappedPos = new ArrayList<>();
		blackTrappedPos = new ArrayList<>();
	}
	
	//print board
	
	public void printState() {
	
		
		for(int i = 0; i < 10 ; i++) {
			System.out.print("| ");
			for(int j = 0; j <10; j++) {
				if(board[i][j] == WHITE_QUEEN) {
					System.out.print(" W");
				}else if(board[i][j] == BLACK_QUEEN) {
					System.out.print(" B");
				}else if (board[i][j] == ARROW) {
					System.out.print(" A");
				}else {
					System.out.print(" -");
				}
			}
			System.out.println(" |");
		}
		
	}
	public void updateState(ArrayList<Integer> queenPos, ArrayList<Integer> queenNext, ArrayList<Integer> arrowPos) {
		
	}
	public void setState(ArrayList<Integer> gameState) {
		
	}
	
	
	// clone board constructor
	public Board(Board cloned) {
		this();
		System.arraycopy(cloned.board, 0, board, 0, COLS);
	}
	
	// clone board method
	public void cloneBoard(Board cloned) {
		System.arraycopy(cloned.board, 0, board, 0, COLS);
	}
	
}
