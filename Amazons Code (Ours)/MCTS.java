package ubc.cosc322;

import java.util.ArrayList;
import java.util.Random;

public class NewMonteCarlo {
	static final int WIN_SCORE = 10;
	int level;
	int opponent;
	
	public Move findNextMove(Board board, int playerNo) {
		opponent = 3 - playerNo;
		System.out.println("In monte");
		//board.printState();
		Tree tree = new Tree();
		Node rootNode = tree.getRoot();
		rootNode.getState().setBoard(board);
		rootNode.getState().setPlayerNo(playerNo);
		//System.out.println(rootNode.childArray.isEmpty());
		//rootNode.getState().getBoard().printState();
		long startTime = System.currentTimeMillis();
		while((System.currentTimeMillis()-startTime)<1000) {
			//System.out.println("Phase 1");
			//Phase 1
			Node promisingNode = selectPromisingNode(rootNode);
			//Phase 2
			if(promisingNode.getState().getBoard().checkGame() == -1) {
				expandNode(promisingNode);
			}
			//Phase 3
			Node nodeToExplore = promisingNode;
			if(promisingNode.getChildArray().size()>0) {
				nodeToExplore = promisingNode.getRandomChildNode();
			}
			
			int playoutResult = simulateRandomPlayout(nodeToExplore);
			//System.out.println(playoutResult);
			backPropogation(nodeToExplore, playoutResult);
		}
		//System.out.println(rootNode.getChildArray().size());
		for(Node testNode: rootNode.getChildArray()) {
			System.out.println(testNode.state.winScore);
		}
		Node winnerNode = rootNode.getChildWithMaxScore();
		//System.out.println(winnerNode.state.winScore);
		tree.setRoot(winnerNode);
		return winnerNode.getState().move;
	}
	
	
	
	private Node selectPromisingNode(Node rootNode) {
		Node node = rootNode;
		
		 while (node.getChildArray().size() != 0) {
	            node = UCT.findBestNodeWithUCT(node);
	     }
		return node;
	}
	
	private void expandNode(Node node) {
		
		ArrayList<State> possibleStates = node.getState().getAllPossibleStates();
		for(State state: possibleStates) {
			Node newNode = new Node(state);
			newNode.setParent(node);
			newNode.getState().setPlayerNo(node.getState().getOpponent());
			node.getChildArray().add(newNode);
		}
		
		
	}
	private int simulateRandomPlayout(Node node) {
		Node tempNode = new Node(node);
		State tempState = tempNode.getState();
		int boardStatus = tempState.getBoard().checkGame();
		
		if(boardStatus == opponent) {
			tempNode.getParent().getState().setWinScore(Integer.MIN_VALUE);
			return boardStatus;
		}
		
		while(boardStatus == -1) {
			tempState.togglePlayer();
			boardStatus = tempState.randomPlay();
			//boardStatus = tempState.getBoard().checkGame();
		}
	
		return boardStatus;
	}
	
	private void backPropogation(Node nodeToExplore, int playerNo) {
		Node tempNode = nodeToExplore;
		while(tempNode != null) {
			tempNode.getState().incrementVisit();
			if(tempNode.getState().getPlayerNo() == playerNo) {
				tempNode.getState().addScore(WIN_SCORE);
			}
			tempNode = tempNode.getParent();
		}
	}
}

