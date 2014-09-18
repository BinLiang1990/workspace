package entities;

import java.awt.*;
import java.util.*;
import util.Global;

public class Snake implements Runnable { 
	
	private int direction, lastDirection;
	public static final int UP = 1, RIGHT = 2, DOWN = -1, LEFT = -2;
	private LinkedList<Point> body;
	
	public Snake() {
		body = new LinkedList<Point>();
		int x = Global.WIDTH / 2, y = x;
		for(int i = 0; i < 3; ++i) {
			body.addFirst(new Point(x--, y));
		}
		direction = lastDirection = -2;
	} 
	
	public Point getHead() {
		return body.getFirst();
	}
	
	public void move() {
		//avoid snake move forward opposite direction
		if(direction + lastDirection != 0) {
			lastDirection = direction;
		}
		body.removeLast();
		int x = body.getFirst().x;
		int y = body.getFirst().y;
		switch(lastDirection) {
		case UP:
			--y;
			y = y < 0 ? Global.HEIGHT - 1 : y;
			break;
		case RIGHT:
			++x;
			x = x > Global.WIDTH ? 0 : x;
			break;
		case DOWN:
			++y;
			y = y > Global.HEIGHT ? 0 : y;
			break;
		case LEFT:
			--x;
			x = x < 0 ? Global.WIDTH - 1 : x;
			break;
		}
		body.addFirst(new Point(x, y));
	}
	
	public void changeDirection(int direction) {
			this.direction = direction;
	}
	
	public void eatFood() {
		int x = body.getFirst().x;
		int y = body.getFirst().y;
		switch(lastDirection) {
		case UP:
			--y;
			y = y < 0 ? Global.HEIGHT - 1 : y;
			break;
		case RIGHT:
			++x;
			x = x > Global.WIDTH ? 0 : x;
			break;
		case DOWN:
			++y;
			y = y > Global.HEIGHT ? 0 : y;
			break;
		case LEFT:
			--x;
			x = x < 0 ? Global.WIDTH - 1 : x;
			break;
		}
		body.addFirst(new Point(x, y));
	}
	
	public boolean eatOneself() {
		for(int i = 1; i < body.size(); ++i) {
			if(getHead().equals(body.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	public boolean eatStone() {
		if(getHead().x == 0 || getHead().x == Global.WIDTH - 1 || getHead().y == 0 || getHead().y == Global.HEIGHT - 1) {
			return true;
		}
		return false;
	}
	
	public boolean isAlive() {
		if(eatOneself() || eatStone()) {
			return false;
		}
		return true;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		for(Point p : body) {
			g.fill3DRect(p.x * Global.CELL_SIZE, p.y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
		}
	}

	@Override
	public void run() {
		while(true) {
			if(!isAlive()) {
				break;
			}
			move();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
