/*
 * Sources used:
 * https://www.baeldung.com/java-monte-carlo-tree-search
 * https://medium.com/swlh/tic-tac-toe-at-the-monte-carlo-a5e0394c7bc2
 */
package ubc.cosc322;

import java.util.ArrayList;

import cern.colt.list.BooleanArrayList;


public class MonteCarlo {
	
	public Board findNextMove(Board board, int player) {
		Tree tree = new Tree();
		return board;
		
	}

}

// Note: the following methods are from "Monte Carlo Tree Search for Tic-Tac-Toe Game in Java" (the first article sourced)
class Node {
	State state;
	Node parent;
	ArrayList<Node> child;
	public State getState() {
		return state;
	}
	public void setState(State state) {
		this.state = state;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public ArrayList<Node> getChild() {
		return child;
	}
	public void setChild(ArrayList<Node> child) {
		this.child = child;
	}
}

class Tree {
	Node root;
}

class State {
	Board board;
	int player;
	int visit;
	int score;
	
	// do we need this constructor?
	public State(Board board, int player, int visit, int score) {
		super();
		this.board = board;
		this.player = player;
		this.visit = visit;
		this.score = score;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public int getPlayer() {
		return player;
	}
	public void setPlayer(int player) {
		this.player = player;
	}
	public int getVisit() {
		return visit;
	}
	public void setVisit(int visit) {
		this.visit = visit;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<State> getAllPossibleStates() {
		return null;
		// constructs a list of all possible states from current state	
	}
	
	public void randomPlay() {
        /* get a list of all possible positions on the board and 
           play a random move */
    }
}










