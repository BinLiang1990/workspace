package entities;

import java.awt.*;

import util.Global;

public class Stone {
	
	//draw stone wall
	public void draw(Graphics g) {
		g.setColor(Color.red);
		for(int i = 0; i < Global.WIDTH; ++i) {
			for(int j = 0; j < Global.HEIGHT; ++j) {
				if(i == 0 || i == Global.WIDTH - 1 || j == 0 || j == Global.HEIGHT - 1) {
					g.fill3DRect(i * Global.CELL_SIZE, j * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
				}
			}
		}
	}
}
