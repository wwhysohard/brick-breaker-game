package game;

import javax.swing.JFrame;

import game.visualization.GameLogic;

public class Main {

	public static void main(String[] args) {
		JFrame frame=new JFrame();
		GameLogic gameLogic = new GameLogic();
		
		frame.setBounds(10, 10, 700, 600);
		frame.setTitle("Brick Breaker");		
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(gameLogic);
        frame.setVisible(true);
	}

}
