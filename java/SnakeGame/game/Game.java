package game;

import java.awt.BorderLayout;
import util.Global;
import javax.swing.*;
import view.*;
import controller.*;
import entities.*;

public class Game {

	public static void main(String[] args) {
		Snake snake = new Snake();
		Food food = new Food();
		Stone stone = new Stone();
		GamePanel gamePanel = new GamePanel();
		gamePanel.setSize(Global.CELL_SIZE * Global.WIDTH, Global.CELL_SIZE * Global.HEIGHT);
		Controller controller = new Controller(snake, food, stone, gamePanel);
		JFrame frame = new JFrame("snake game");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, Global.CELL_SIZE * Global.WIDTH + 8, Global.CELL_SIZE * Global.HEIGHT + 32);
		frame.add(gamePanel, BorderLayout.CENTER);
		frame.addKeyListener(controller);
		frame.setVisible(true);
		controller.startGame();
		
	}

}
