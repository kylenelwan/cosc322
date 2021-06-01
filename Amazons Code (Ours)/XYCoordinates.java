package ubc.cosc322;

public class XYCoordinates {
	public int x, y;
	
	// constructor
	public XYCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return x + "," + y;
	}
}
