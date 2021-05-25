package game.visualization;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.KeyListener;
import java.awt.event.ActionListener;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;

import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import game.objects.Ball;
import game.objects.Paddle;

public class GameLogic extends JPanel implements KeyListener, ActionListener {
	
	private boolean isGameActive = false;
	private int totalBricks = 36;
	private int score = 0;
	
	private Timer timer;
	private int delay = 8;

	private Ball ball;
	private Paddle paddle;
	private MapGenerator map;
	
	public GameLogic() {		
		ball = new Ball();
		paddle = new Paddle();
		map = new MapGenerator(4, totalBricks / 4);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
		timer.start();
	}
	
	public void paint(Graphics graphics) {    		
		// background
		graphics.setColor(Colors.BACKGROUND_COLOR);
		graphics.fillRect(1, 1, 698, 592);
		
		// drawing map
		map.draw((Graphics2D) graphics, Colors.BACKGROUND_COLOR);
		
		// borders
		graphics.setColor(Color.WHITE);
		graphics.fillRect(0, 0, 0, 592);
		graphics.fillRect(0, 0, 692, 0);
		graphics.fillRect(692, 0, 0, 592);
		
		// the scores 		
		graphics.setColor(Color.WHITE);
		graphics.setFont(new Font("serif",Font.BOLD, 25));
		graphics.drawString("Score: " + score, 586, 30);
		
		// the paddle
		graphics.setColor(paddle.getColor());
		graphics.fillRect((int)paddle.getX(), (int)paddle.getY(), (int)paddle.getWidth(), (int)paddle.getHeight());
		
		// the ball
		graphics.setColor(ball.getColor());
		graphics.fillOval((int)ball.getX(), (int)ball.getY(), ball.getDiameter(), ball.getDiameter());

		// when you won the game
		if(totalBricks <= 0) {
			isGameActive = false;
			ball.setDirX(0);
			ball.setDirY(0);

            graphics.setColor(Colors.FIRST_LINE_COLOR);
            graphics.setFont(new Font("serif",Font.BOLD, 30));
            graphics.drawString("You Won!", 260, 300);
            
            graphics.setColor(Colors.FIRST_LINE_COLOR);
            graphics.setFont(new Font("serif",Font.BOLD, 20));           
            graphics.drawString("Press 'Enter' to Restart", 230, 350);  
		}
		
		// when you lose the game
		if(ball.getY() > 570) {
			isGameActive = false;
            ball.setDirX(0);
			ball.setDirY(0);

            graphics.setColor(Colors.FIRST_LINE_COLOR);
            graphics.setFont(new Font("serif",Font.BOLD, 30));
            graphics.drawString("Game Over! Score: " + score, 190, 300);
            
            graphics.setColor(Colors.FIRST_LINE_COLOR);
            graphics.setFont(new Font("serif",Font.BOLD, 20));           
            graphics.drawString("Press 'Enter' to Restart", 230, 350);        
        }
		
		graphics.dispose();
	}	

	public void keyPressed(KeyEvent event) {
		if (event.getKeyCode() == KeyEvent.VK_RIGHT) {      
			isGameActive = true;  
			paddle.moveRight();
        }
		
		if (event.getKeyCode() == KeyEvent.VK_LEFT) {          
			isGameActive = true;
			paddle.moveLeft();
        }		

		if (event.getKeyCode() == KeyEvent.VK_ENTER) {          
			if(!isGameActive) {
				isGameActive = true;
				ball.init();
				paddle.x = 310;
				score = 0;
				totalBricks = 36;
				map = new MapGenerator(4, totalBricks / 4);
				
				repaint();
			}
        }		
	}
	
	public void actionPerformed(ActionEvent e) {
		timer.start();
		
		if(isGameActive) {			
			if(ball.intersects(paddle.leftPart())) {
				ball.inverseDirY();
				ball.setDirX(-2);
			} else if(ball.intersects(paddle.rightPart())) {
				ball.inverseDirY();
				ball.setDirX(ball.getDirX() + 1);
			} else if(ball.intersects(paddle.middlePart())) {
				ball.inverseDirY();
			}
			
			// check map collision with the ball		
			OUTER: for (int row = 0; row < map.getMapLength(); row++) {
				for (int col = 0; col < map.getMapWidth(); col++) {				
					if (!map.getBrick(row, col).isBroken()) {					
						if(ball.intersects(map.getBrick(row, col))) {					
							if (map.hitBrick(row, col)) {
								score += 5;	
								totalBricks--;	
							}
							
							// when ball hit right or left of the brick
							if(ball.getX() + 19 <= map.getBrick(row, col).getX() || ball.getX() + 1 >= map.getBrick(row, col).getX() + map.getBrick(row, col).getWidth()) {
								ball.inverseDirX();
							}

							// when ball hits top or bottom of the brick
							else {
								ball.inverseDirY();				
							}
							
							break OUTER;
						}
					}
				}
			}
			
			ball.x += ball.getDirX();
			ball.y += ball.getDirY();
			
			// if ball hits left side of the grid
			if (ball.getX() < 0) {
				ball.inverseDirX();
			}

			// if ball hits top of the grid
			if (ball.getY() < 0) {
				ball.inverseDirY();
			}

			// if ball hits right side if the grid
			if (ball.getX() > 670) {
				ball.inverseDirX();
			}		
			
			repaint();		
		}
	}

	// Unused inherited methods
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

}
