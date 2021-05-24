package game.visualization;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import game.objects.Brick;

public class MapGenerator {

	private Brick[][] bricks;
	
	public MapGenerator (int row, int col) {
		initBricks(row, col);
	}
	
	private void initBricks(int row, int col) {
		bricks = new Brick[row][col];

		for (int i = 0; i < bricks.length; i++) {
			for (int j = 0; j < bricks[i].length; j++) {
				bricks[i][j] = new Brick(150 / row, 660 / col, false);
				
				if (i == 0) {
					bricks[i][j].setColor(Colors.FIRST_LINE_COLOR);
				} else if (i == 1) {
					bricks[i][j].setColor(Colors.SECOND_LINE_COLOR);
				} else if (i == 2) {
					bricks[i][j].setColor(Colors.THIRD_LINE_COLOR);
				} else {
					bricks[i][j].setColor(Colors.FOURTH_LINE_COLOR);
				}

				bricks[i][j].setLocation(
					(int)(j * bricks[i][j].getWidth() + 25),
					(int)(i * bricks[i][j].getHeight() + 50));
			}
		}
	}
	
	public void draw(Graphics2D graphics, Color backgroundColor) {
		for (int i = 0; i < bricks.length; i++) {
			for (int j = 0; j < bricks[i].length; j++) {
				if (!bricks[i][j].isBroken()) {
					graphics.setColor(bricks[i][j].getColor());
					graphics.fillRect((int)(j * bricks[i][j].getWidth() + 25), (int)(i * bricks[i][j].getHeight() + 50),
							(int)(bricks[i][j].getWidth()), (int)(bricks[i][j].getHeight()));
					//brick separation
					graphics.setStroke(new BasicStroke(15));
					graphics.setColor(backgroundColor);
					graphics.drawRect((int)(j * bricks[i][j].getWidth() + 25), (int)(i * bricks[i][j].getHeight() + 50),
							(int)(bricks[i][j].getWidth()), (int)(bricks[i][j].getHeight()));
				}
			}
		}
	}

	public int getMapLength() {
		return bricks.length;
	}

	public int getMapWidth() {
		return bricks[0].length;
	}

	public Brick getBrick(int row, int col) {
		return bricks[row][col];
	}
	
	public boolean hitBrick(int row, int col) {
		bricks[row][col].hit();
		return bricks[row][col].isBroken();
	}

}
