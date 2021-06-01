
package ubc.cosc322;

import java.util.ArrayList;

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
	
	//print board
	
	public void printState() {
		for(int i = 0; i <10 ; i++) {
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
		
		//for (int i = 0; i < 10; i++) {
			//System.out.println(gameState.subList(i * ROWS + 12, (i + 1) * COLS + 11));
		//}
		System.out.println();
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
