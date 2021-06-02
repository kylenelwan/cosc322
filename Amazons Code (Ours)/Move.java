package ubc.cosc322;

import java.util.ArrayList;
//Source used https://github.com/cosc-322-main-team/322GameOfAmazons/blob/7d3ff936baec290392193b74cdb7ac10a15643c6/team-01/src/main/java/ubc/cosc322/AmazonsAction.java
public class Move {

	public XYCoordinates queenCur;
	public XYCoordinates queenNext;
	public XYCoordinates arrowPos;

	public Move(XYCoordinates queenCur, XYCoordinates queenNext, XYCoordinates arrowPos) {
		this.queenCur = queenCur;
		this.queenNext = queenNext;
		this.arrowPos = arrowPos;
	}
	
}
