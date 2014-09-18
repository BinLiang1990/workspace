package entities;

import java.awt.*;
import java.util.Random;

import util.Global;

public class Food extends Point {
	
	public boolean isEaten(Snake snake) {
		return this.equals(snake.getHead());
	}
	
	public void newFood() {
		Random random = new Random();
		while(true) {
			x = random.nextInt(Global.WIDTH - 2);
			y = random.nextInt(Global.HEIGHT - 2);
			if(x > 0 && y > 0) {
				break;
			}
		}
	}
	
	public void draw(Graphics g) {
		g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
	}
}
