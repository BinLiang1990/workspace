package controller;

import java.awt.event.*;
import entities.*;
import view.*;

//start game and listen direction key
public class Controller extends KeyAdapter {
	
	private Snake snake;
	private Food food;
	private Stone stone;
	private GamePanel gamePanel;
	
	public Controller(Snake snake, Food food, Stone stone, GamePanel gamePanel) {
		this.snake = snake;
		this.food = food;
		this.stone = stone;
		this.gamePanel = gamePanel;
	}

	//listen direction key
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_UP:
			snake.changeDirection(Snake.UP);
			break;
		case KeyEvent.VK_DOWN:
			snake.changeDirection(Snake.DOWN);
			break;
		case KeyEvent.VK_LEFT:
			snake.changeDirection(Snake.LEFT);
			break;
		case KeyEvent.VK_RIGHT:
			snake.changeDirection(Snake.RIGHT);
			break;
		}
	}
	
	public void startGame() {
		gamePanel.display(snake, food, stone);
		Thread snakeThread = new Thread(snake);
		snakeThread.start();
		Thread panelThread = new Thread(gamePanel);
		panelThread.start();
		food.newFood();
		if(!panelThread.isAlive()) {
			System.out.println("thread is dead");
		}
	}
}
