package ubc.cosc322;

import java.util.ArrayList;

public class AllPossibleActions {

	
	
	public ArrayList<Move> getAllBlackQueens(Board board) {
		//if(user is black)
		ArrayList<Move> allMoves = new ArrayList<Move>();
		ArrayList<XYCoordinates> queenPosBlack = board.blackPos;
		while(!queenPosBlack.isEmpty()) {
			XYCoordinates queenPos = queenPosBlack.remove(0);
			ArrayList<XYCoordinates> allQueenMoves = getTargets(queenPos.getY(), queenPos.getX(), board);
			while(!allQueenMoves.isEmpty()) {
				XYCoordinates queenNext = allQueenMoves.remove(0);
				ArrayList<XYCoordinates> allTargets = getTargets(queenNext.getY(), queenNext.getX(), board);
				
				while(!allTargets.isEmpty()) {
					XYCoordinates arrowPos = allTargets.remove(0);
					
					allMoves.add(new Move(queenPos, queenNext, arrowPos));
				}
			}
			
		}
		
		return allMoves;
	
		
	}
	public ArrayList<Move> getAllWhiteQueens(Board board) {
		//if(user is black)
		ArrayList<Move> allMoves = new ArrayList<Move>();
		ArrayList<XYCoordinates> queenPosWhite = board.whitePos;
		while(!queenPosWhite.isEmpty()) {
			XYCoordinates queenPos = queenPosWhite.remove(0);
			ArrayList<XYCoordinates> allQueenMoves = getTargets(queenPos.getY(), queenPos.getX(), board);
			while(!allQueenMoves.isEmpty()) {
				XYCoordinates queenNext = allQueenMoves.remove(0);
				ArrayList<XYCoordinates> allTargets = getTargets(queenNext.getY(), queenNext.getX(), board);
				
				while(!allTargets.isEmpty()) {
					XYCoordinates arrowPos = allTargets.remove(0);
					
					allMoves.add(new Move(queenPos, queenNext, arrowPos));
				}
			}
			
		}
		return allMoves;
		
		
		
	}
	
	
	public ArrayList<XYCoordinates> getTargets(int X, int Y, Board board) {
		ArrayList<XYCoordinates> target = new ArrayList<XYCoordinates>();
		int count = 8;
		Actions action = new Actions();
		ArrayList<XYCoordinates> actionList = action.getActions();
		for(int i = 0; i < actionList.size(); i++) {
			int x = X + actionList.get(i).getX();
			int y = Y + actionList.get(i).getY();
			if(x >10 || x < 0 || y >10 || y < 0 ) { // is it in the bounds of the board
				i += count;
				count = 8;
			}else if(board.getGamePos(x,y) != 0) { //If returns 0 it is a free space if not it can't move
				i += count;
				count = 8;
			}else {
				target.add(new XYCoordinates(x, y));
				
			}
		}
		
		
		return target;
	}
}
