package com.game.src.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/**
 * class with the behavior of the ball
 * 
 * @author Dominik Huerrig
 * @version 1.0
 *
 */
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

	/**
	 * defines the movements of ball
	 * and losing condition
	 * 	
	 *
	 */
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

	/**
	 * render a blue ball
	 * 	
	 * @param g Graphics object
	 *
	 */
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillOval((int)x,(int)y, BALL_RADIUS, BALL_RADIUS);
	}
	
	/**
	 * get x of ball
	 * 	
	 * @return returns x coordinate of ball
	 *
	 */
	public int getX() {
		return (int)x;
	}
	/**
	 * get y of ball
	 * 	
	 * @return returns y coordinate of ball
	 *
	 */
	public int getY() {
		return (int)y;
	}
	/**
	 * get x velocity of ball
	 * 	
	 * @return returns x velocity of ball
	 *
	 */
	public int getVelX() {
		return (int)velX;
	}
	/**
	 * get y velocity of ball
	 * 	
	 * @return returns y velocity of ball
	 *
	 */
	public int getVelY() {
		return (int)velY;
	}
	/**
	 * set x velocity of ball
	 * 	
	 * @param velX x velocity of ball
	 *
	 */
	public void setVelX(double velX) {
		this.velX = velX;
	}
	/**
	 * set y velocity of ball
	 * 	
	 * @param velY y velocity of ball
	 *
	 */
	public void setVelY(double velY) {
		this.velY = velY;
	}

}
