/*
 * Sources used:
 * https://www.baeldung.com/java-monte-carlo-tree-search
 */
package ubc.cosc322;

import java.util.ArrayList;

public class MCTS {
	
	public Board findNextMove(Board board, int playerNo) {
		int blackPlayer = 1;
		int whitePlayer = 2;
		Tree tree = new Tree();
		Node root = tree.getRoot();
		root.getState().setBoard(board);
		if(playerNo == 1) {
			root.getState().setPlayerNo(whitePlayer); // note: setPlayerNo is set to "opponent" on the source article
		} else if (playerNo == 2) {
			root.getState().setPlayerNo(blackPlayer); 
		}
		Node promisingNode = selectPromising(root);
		long startTime = System.currentTimeMillis();
		long endTime = startTime + 30*1000; // 30 second time limit
		while (startTime < endTime) {
			if(promisingNode.getState().getBoard().checkGame() == -1) {
				expand(promisingNode);
			}
			Node nodeExplore = promisingNode;
			
		   // add rest
		}
		return board; // change later
	}
	
	private Node selectPromising(Node root) {
		Node node = root;
		while(node.getChild().size() > 0) {
			node = UCT.uctFindBestNode(node);
		}
		return node;
	}
	
	private void expand(Node node) {
		ArrayList<State> allPossibleStates = node.getState().getAllPossibleStates();
		for (State state : allPossibleStates) {
			Node newNode = new Node();
			newNode.setParent(node);
			newNode.getState().setPlayerNo(node.getState().getOpponent());
	        node.getChild().add(newNode);
		}
	}
	
	private int similate(Node node) {
		return 0;
	}
	
	private void backPropogate(Node nodeExplore, int playerNo) {
	}
	
	
}
