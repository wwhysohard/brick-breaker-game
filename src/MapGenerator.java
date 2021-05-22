import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {

	public int map[][];
	public int brickWidth;
	public int brickHeight;
	
	public MapGenerator (int row, int col) {
		map = new int[row][col];		
		for(int i = 0; i<map.length; i++) {
			for(int j =0; j<map[0].length; j++) {
				map[i][j] = 1;
			}			
		}
		
		brickWidth = 660/col;
		brickHeight = 150/row;
	}	
	
	public void draw(Graphics2D graphics, Color backgroundColor) {
		Color firstLineColor = new Color(240, 201, 76);
		Color secondLineColor = new Color(226, 122, 63);
		Color thirdLineColor = new Color(223, 73, 74);
		Color fourthLineColor = new Color(51, 77, 93);

		for(int i = 0; i<map.length; i++) {
			for(int j =0; j<map[0].length; j++) {
				if(map[i][j] > 0) {
					if (i == 0) {
						graphics.setColor(firstLineColor);
					} else if (i == 1) {
						graphics.setColor(secondLineColor);
					} else if (i == 2) {
						graphics.setColor(thirdLineColor);
					} else {
						graphics.setColor(fourthLineColor);
					}

					graphics.fillRect(j * brickWidth + 25, i * brickHeight + 50, brickWidth, brickHeight);
					
					//brick separation
					graphics.setStroke(new BasicStroke(15));
					graphics.setColor(backgroundColor);
					graphics.drawRect(j * brickWidth + 25, i * brickHeight + 50, brickWidth, brickHeight);				
				}
			}
		}
	}
	
	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
	}

}
