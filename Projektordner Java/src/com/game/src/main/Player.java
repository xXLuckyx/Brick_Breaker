package com.game.src.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player {

	// starting x position
	private double x = 320 - PLAYER_WIDTH / 2;
	// starting y position
	private double y = 450;
	// height of board
	public static final int PLAYER_HEIGHT = 10;
	// width of board
	public static final int PLAYER_WIDTH = 92;

	// velocity of x
	private double velX = 0;

	// updates every tick
	public void tick() {
		x += velX;

		if (x <= 0) {
			x = 0;
		}
		if (x >= 640 - PLAYER_WIDTH) {
			x = 640 - PLAYER_WIDTH;
		}
	}

	// render object
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int) x, (int) y, PLAYER_WIDTH, PLAYER_HEIGHT);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

}
