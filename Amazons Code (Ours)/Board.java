package ubc.cosc322;

import java.util.ArrayList;

import cern.colt.list.BooleanArrayList;
import ygraph.ai.smartfox.games.BoardGameModel;
import ygraph.ai.smartfox.games.GamePlayer;

public  class Board {
	// constants
	public static final int ROWS = 11;
	public static final int COLS = 11;
	
	public static final int AVAILABLE = 0;
	public static final int ARROW = 1;
	public static final int BLACK_QUEEN = 2;
	public static final int WHITE_QUEEN = 3;
	
	public static int arrowCounter = 0; 
	protected int [][] board;
//	private ArrayList<Integer> gameState = new ArrayList<>();
	ArrayList<XYCoordinates> whitePos = new ArrayList<>();
	ArrayList<XYCoordinates> blackPos = new ArrayList<>();
	ArrayList<XYCoordinates> trappedWhitePos = new ArrayList<>();
	ArrayList<XYCoordinates> trappedBlackPos = new ArrayList<>();

	

	
	// constructor
	// creates empty board with queens (Amazons) in starting positions
	public Board() {
		
		
		board = new int[ROWS][COLS];
		board[1][4] = WHITE_QUEEN;
		board[1][7] = WHITE_QUEEN;
		board[4][1] = WHITE_QUEEN;
		board[4][10] = WHITE_QUEEN;
		
		board[7][1] = BLACK_QUEEN;
		board[10][4] = BLACK_QUEEN;
		board[10][7] = BLACK_QUEEN;
		board[7][10] = BLACK_QUEEN;
		whitePos = new ArrayList<>();
		blackPos = new ArrayList<>();
		initPositions();

	}
	public void initPositions() {
		for(int i = 1; i < 11 ; i++) {
			for(int j = 1; j <11; j++) {
		if(board[i][j] == WHITE_QUEEN) {
			whitePos.add(new XYCoordinates(i, j));
		}else if(board[i][j] == BLACK_QUEEN) {
			blackPos.add(new XYCoordinates(i, j));
		}
			}
		}
	}

	
	// clone board constructor
	public Board(Board cloned) {
		this();
	}
	
	// clone board method -- do we need this?
	public void cloneBoard(Board cloned) {
		System.arraycopy(cloned.board, 0, board, 0, COLS);
	}
	
		
	//print board
	public void printState() {
		
		for(int i = 10; i > 0 ; i--) {
			System.out.print("| ");
			for(int j = 1; j < 11; j++) {
				if(board[i][j] == WHITE_QUEEN) {
					System.out.print(" W ");
					//whitePos.add(new XYCoordinates(i, j));
				}else if(board[i][j] == BLACK_QUEEN) {
					System.out.print(" B ");
					//blackPos.add(new XYCoordinates(i, j));
				}else if (board[i][j] == ARROW) {
					System.out.print(" A ");
				}else {
					System.out.print(" - ");
				}
			}
			System.out.println(" |");
			
			
		}
		System.out.println(" ");
		System.out.println(" ");
	}


	public void getOppMove(ArrayList<Integer> queenPos, ArrayList<Integer> queenNext, ArrayList<Integer> arrowPos) {
		XYCoordinates oldPos = new XYCoordinates(queenPos.get(0), queenPos.get(1));
		XYCoordinates newPos = new XYCoordinates(queenNext.get(0), queenNext.get(1));
		XYCoordinates newArrow = new XYCoordinates(arrowPos.get(0), arrowPos.get(1));
		updateState(oldPos, newPos, newArrow);
		
	}
	
	
	public void updateState(XYCoordinates queenPos, XYCoordinates queenNext, XYCoordinates arrowPos) {
//		System.out.println(queenPos.x + "," + queenPos.y);
//		System.out.println(queenNext.x + "," + queenNext.y);
		int queen = board[queenPos.x][queenPos.y];
		board[queenPos.x][queenPos.y] = AVAILABLE;
		board[queenNext.x][queenNext.y] = queen;
		board[arrowPos.x][arrowPos.y] = ARROW;
//		if(queen == BLACK_QUEEN) {
//			ArrayList<XYCoordinates> newblackPos = getBlackPos();
//			for(int i = 0; i < newblackPos.size(); i++ ) {
//				if(newblackPos.get(i).y ==queenNext.y && newblackPos.get(i).x == queenNext.x) {
//					newblackPos.set(i, queenNext);
//				}
//			}
//			setBlackPos(blackPos);
//		}else if(queen == WHITE_QUEEN) {
//			ArrayList<XYCoordinates> whitePos = getWhitePos();
//			for(int i = 0; i < whitePos.size(); i++ ) {
//				if(whitePos.get(i).y ==queenNext.y && whitePos.get(i).x == queenNext.x) {
//					whitePos.set(i, queenNext);
//				}
//			}
//			//setWhitePos(whitePos);
//		}
		whitePos.clear();
		blackPos.clear();
		for(int i = 10; i > 0 ; i--) {
			for(int j = 1; j < 11; j++) {
				if(board[i][j] == WHITE_QUEEN) {
					whitePos.add(new XYCoordinates(i, j));
				}else if(board[i][j] == BLACK_QUEEN) {
					blackPos.add(new XYCoordinates(i, j));
				}
			}
		}
	
		
	}
	public int getGamePos(int x, int y) {
		return board[x][y];
	}
	
	public void setState(ArrayList<Integer> gameState) {
		
	}
	
	public void isTrappedBlack(int index) {
		XYCoordinates queenPos = blackPos.remove(index);
		trappedBlackPos.add(queenPos);
	}
	
	public void isTrappedWhite(int index) {
		XYCoordinates queenPos = whitePos.remove(index);
		trappedWhitePos.add(queenPos);
	}
	
	public int checkGame() {
		if(trappedBlackPos.size() == 4) {
			return 1;
		}else if(trappedWhitePos.size() == 4) {
			return 2;
		}else {
			return -1;
		}
	}
	// getter and setter methods
	public ArrayList<XYCoordinates> getWhitePos() {
		return whitePos;
	}
	
	public ArrayList<XYCoordinates> getBlackPos() {
		return blackPos;
	}



	public void setWhitePos(ArrayList<XYCoordinates> whitePos) {
		this.whitePos = whitePos;
	}

	public void setBlackPos(ArrayList<XYCoordinates> blackPos) {
		this.blackPos = blackPos;
	}


}
