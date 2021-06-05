/*
 * Sources used:
 * https://www.baeldung.com/java-monte-carlo-tree-search
 * https://www.baeldung.com/java-random-list-element
 */
package ubc.cosc322;

import java.util.ArrayList;
import java.util.Random;

public class Node {
	State state;
	Node parent;
	ArrayList<Node> Child;
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
		return Child;
	}
	public void setChild(ArrayList<Node> child) {
		Child = child;
	}
	
	// double check this!!! does it make sense?
	// should it be a new array list?
	public Node getRandomChildNode() {
	    Random rand = new Random();
	    Node randomNode = Child.get(rand.nextInt(Child.size()));
		return randomNode;	
	}
}

class Tree {
	Node root;

	public Node getRoot() {
		return root;
	}
	public void setRoot(Node root) {
		this.root = root;
	}
	
	
}
