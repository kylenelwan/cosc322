/*
 * Sources used:
 * https://www.baeldung.com/java-monte-carlo-tree-search
 */
package ubc.cosc322;

public class UCT {
	public static double uctVal(int totalNumSimulations, double numWins, int numSimulations) {
		double explorationParameter = Math.sqrt(2);
		double lnt = Math.log(totalNumSimulations);
		if(numSimulations == 0 ) {
			return Integer.MAX_VALUE;
		} else {
			double uctValue = ((double)numWins/(double)numSimulations) + explorationParameter * (Math.sqrt(lnt / (double)numSimulations));
			return uctValue;	
		}
	}
	
	public static Node uctFindBestNode(Node node) {
		return node;
	}
}
