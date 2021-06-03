/*
 * Sources used:
 * https://www.baeldung.com/java-monte-carlo-tree-search
 * https://medium.com/swlh/tic-tac-toe-at-the-monte-carlo-a5e0394c7bc2
 * https://web.archive.org/web/20160307012535/http://mcts.ai/code/java.html
 * https://www.baeldung.com/java-stop-execution-after-certain-time
 * https://www.geeksforgeeks.org/ml-monte-carlo-tree-search-mcts
 * https://web.archive.org/web/20160307012535/http://mcts.ai/code/java.html/
 */

package ubc.cosc322;

import java.util.ArrayList;

import cern.colt.list.BooleanArrayList;


public class MonteCarlo{
	
	Actions action = new Actions();
	ArrayList<XYCoordinates> allPossibleActions = action.getActions();
	
	public Board findNextMove(Board board, int player) {
		TreeNode tree = new TreeNode();
		Node root = tree.getRoot();
		
		
		// stops execution after 30 seconds time limit has passed
		long start = System.currentTimeMillis();
		long end = start + 30*1000;
		while (start < end) {
		    Node promisingNode = selectPromisingNode(root);
//		    expandNode(promisingNode);
		
		    Node traverseNode = promisingNode;
//		    if(promisingNode.getChild().size() > 0) {
//		    	traverseNode = promisingNode.getChild();
//		    }
//		    simulationResult = rollout(promisingNode);
//		    backPropogate(promisingNode, simulationResult);
		}
		return board;	
	}
	
	private Node selectPromisingNode (Node root){
		while(root.getChild().size() > 0) {
			root = UCT.findBestNodeWithUCT(root);
		}
		return root;
	}
	
	private void expandNode(Node[] node) {
		node = new Node[allPossibleActions.size()];
		for (Node node2 : node) {
			node2 = new Node();
		}
	}
	
	private void backPropogation(Node traverseNode, int playerNo) {
		
	}
	
	private int simulate(Node node) {
		return 0;
		
	}
}

class UCT{
	public static double uctValue(int totalNumSimulations, double winScore, int NodeNumSimulations){
		double uctVal = ((double)winScore / (double) NodeNumSimulations) + (Math.sqrt(2)*(Math.sqrt(Math.log(totalNumSimulations))/ (double)NodeNumSimulations));
		if(NodeNumSimulations == 0) {
			return Integer.MAX_VALUE;
		} else {
			return uctVal;
		}
	}
	
	public static Node findBestNodeWithUCT(Node node) {
		return node;
	}
}


// Note: the following methods are from "Monte Carlo Tree Search for Tic-Tac-Toe Game in Java" (the first article sourced)
class Node {
	State state;
	Node parent;
	ArrayList<Node> child;
	
	// getters and setters
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

class TreeNode {
	Node root;
	// getters and setters
	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
	
}

// represents the board state
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
	
	//getters and setters
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


