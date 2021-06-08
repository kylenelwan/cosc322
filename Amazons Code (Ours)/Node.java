package ubc.cosc322;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Node {
	State state;
	Node parent;
	ArrayList<Node> childArray;
	
	public Node(Node node) {
		this.childArray = new ArrayList<>();
		this.state = node.state;
		if (node.getParent() != null)
	            this.parent = node.getParent();
		ArrayList<Node> childArray = node.getChildArray();
	    for (Node child : childArray) {
	          this.childArray.add(new Node(child));
	    }
	    
	}
	public Node() {
		this.state = new State();
		this.childArray = new ArrayList<Node>();
		
	}
	public Node(State state) {
		this.state = state;
		this.childArray = new ArrayList<Node>();
		
	}
	
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
	public ArrayList<Node> getChildArray() {
		return childArray;
	}
	public void setChildArray(ArrayList<Node> childArray) {
		this.childArray = childArray;
	}
	
	public Node getRandomChildNode() {
		Random rand = new Random();
		int randomNum = rand.nextInt(childArray.size());
		Node ranChild = childArray.get(randomNum);
		return ranChild;
	}
	
	public Node getChildWithMaxScore() {
		  return Collections.max(this.childArray, Comparator.comparing(c -> {
	            return c.getState().getVisitCount();
	        }));
	}
	
}



