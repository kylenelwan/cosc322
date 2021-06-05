package ubc.cosc322;

import java.awt.List;
import java.util.ArrayList;

public class State {
	
	    Board board;
	    int playerNo;
	    int visitCount;
	    double winScore;
	    AllPossibleActions actions;
	    // copy constructor, getters, and setters
	    public State(Board board, int playerNo, int visitCount, double winScore) {
	    	this.board = new Board(board);
	    	this.playerNo = playerNo;
	    	this.visitCount = visitCount;
	    	this.winScore = winScore;
	    }
	    public ArrayList<State> getAllPossibleStates() {
	        // constructs a list of all possible states from current state
	    	ArrayList<Move> allActions = new ArrayList<Move>();
	    	if(playerNo == 1) {
	    		allActions = actions.getAllBlackQueens(board);
	    	}else {
	    		allActions = actions.getAllWhiteQueens(board);
	    	}
	    	ArrayList<State> allStates = new ArrayList<State>();
	    	for(Move moves: allActions) {
	    		Board newBoard = new Board(board);
	    		newBoard.updateState(moves.queenPos, moves.queenNext, moves.arrowPos);
	    		playerNo = playerNo == 2? 1 :2;
	    		allStates.add(new State(newBoard, playerNo, visitCount, winScore));
	    	}
	    	return allStates;
	    	
	    }
	    public Board getBoard() {
			return board;
		}
		public void setBoard(Board board) {
			this.board = board;
		}
		public int getPlayerNo() {
			return playerNo;
		}
		public void setPlayerNo(int playerNo) {
			this.playerNo = playerNo;
		}
		public void randomPlay() {
	        /* get a list of all possible positions on the board and 
	           play a random move */
	    	ArrayList<State> allPos = getAllPossibleStates();
	    	
	    }
		
		// does this make sense?
		public int getOpponent() {
			if (playerNo == 1) {
				return 2;
			} else {
				return 1;
			}
		}
	}



