package com.game.src.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {

	public int map[][];
	public int brickWidth;
	public int brickHeight;

	public MapGenerator(int row, int col) {
		map = new int[row][col];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = 1;
			}
		}
		brickWidth = 580 / col;
		brickHeight = 200 / row;
	}

	public void render(Graphics2D g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] > 0) {
					g.setColor(Color.BLUE);
					g.fillRect(j * brickWidth + 30, i * brickHeight + 20, brickWidth, brickHeight);

					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * brickWidth + 30, i * brickHeight + 20, brickWidth, brickHeight);
				}
			}
		}
	}

	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;

	}

}
