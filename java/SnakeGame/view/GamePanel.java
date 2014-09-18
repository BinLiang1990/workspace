package view;

import java.awt.*;
import javax.swing.*;

import util.Global;
import entities.*;

//draw everything
public class GamePanel extends JPanel implements Runnable {
	
	private Snake snake;
	private Food food;
	private Stone stone;
	
	public void display(Snake snake, Food food, Stone stone) {
		this.snake = snake;
		this.food = food;
		this.stone = stone;
	}
	
	public void paint(Graphics g) {
		//clear current field
		super.paint(g);
		if(snake != null && food != null) {
			stone.draw(g);
			snake.draw(g);
			food.draw(g);
		}
	}

	@Override
	public void run() {
		while(true) {
			if(!snake.isAlive()) {
				JOptionPane.showMessageDialog(null, "game over");
				break;
			}
			if(food.isEaten(snake)) {
				snake.eatFood();
				food.newFood();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
		}
	}
	
}

