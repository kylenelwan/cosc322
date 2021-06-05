/*
 * Sources used:
 * https://www.baeldung.com/java-monte-carlo-tree-search
 */
package ubc.cosc322;

import java.util.ArrayList;

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
