package ubc.cosc322;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;

public class gameEventHandler extends MouseAdapter {
	
	int counter = 0;
	int qRow = 0, qCol = 0;
	int qFr = 0, qFc = 0;
	int aRow = 0, aCol = 0;
	
	public void mousePressed(MouseEvent e) {
		if (!gameStarted) return;
		
		int x = e.getX();
		int y = e.getY();
		
		if(((x-offset)<-5 || (y-offset)<-5)) return;
		
		if (counter == 0 ) {
			qFr = row;
			qFc = col;
			
			qFr = 11 - qFr;
			counter ++;
		}
		else if (counter == 2) {
			aRow = row;
			aCol = col;
			
			aRow = 11 - aRow;
			counter++;
		}
		
		if (counter == 3) {
			counter = 0;
			boolean validMove = markPosition(qRow,qCol,aCol,qFr,qFc,false);
			
		if (validMove) {
			game.playerMove(qRow,qCol,aRow,aCol,qFr,qFc);
		}
		qRow = 0;
		qCol = 0;
		aRow = 0;
		aCol = 0;
		
		}
	}
	
}
