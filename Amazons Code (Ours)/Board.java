/*
Sources used:
	tic-tac-toe files (Billy Spelch)
*/
package ubc.cosc322;

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
	
	// constructor
	// creates empty board with queens (Amazons) in starting positions
	public Board() {
		//******* idk if this is right ******
		board = new int[ROWS][COLS];
		
		board[0][3] = WHITE_QUEEN;
		board[0][6] = WHITE_QUEEN;
		board[3][0] = WHITE_QUEEN;
		board[3][9] = WHITE_QUEEN;
		
		board[6][0] = BLACK_QUEEN;
		board[9][3] = BLACK_QUEEN;
		board[9][6] = BLACK_QUEEN;
		board[6][9] = BLACK_QUEEN;
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
