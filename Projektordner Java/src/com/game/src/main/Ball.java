package com.game.src.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Ball {

	// starting x position
	private double x = 320 - BALL_RADIUS / 2;
	// starting y position
	private double y = 432;
	Random random = new Random();
	int n = random.nextInt(11) - 5;
	// velocity of x
	private double velX = n == 0 ? 1 : n;
	// velocity of y
	private double velY = -2;
	// radius of ball
	public static final int BALL_RADIUS = 16;

	public void tick() {
		x += velX;
		y += velY;

		if (x <= 0) {
			x = 0;
			velX = -velX;
		}
		if (x >= 640 - BALL_RADIUS) {
			x = 640 - BALL_RADIUS;
			velX = -velX;
		}
		if (y <= 0) {
			y = 0;		
			velY = -velY;
		}
		if (y >= 640 - BALL_RADIUS) {
			y = 640 - BALL_RADIUS;
			//game end
			Game.State = Game.STATE.LOSE;
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillOval((int)x,(int)y, BALL_RADIUS, BALL_RADIUS);
	}
	
	public int getX() {
		return (int)x;
	}
	public int getY() {
		return (int)y;
	}
	public int getVelX() {
		return (int)velX;
	}
	public int getVelY() {
		return (int)velY;
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	public void setVelY(double velY) {
		this.velY = velY;
	}

}
