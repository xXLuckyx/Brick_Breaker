package com.game.src.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * class with the behavior of game board
 * 
 * @author Dominik Huerrig
 * @version 1.0
 *
 */
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

	/**
	 * updates position of game board
	 * 	
	 *
	 */
	public void tick() {
		x += velX;

		if (x <= 0) {
			x = 0;
		}
		if (x >= 640 - PLAYER_WIDTH) {
			x = 640 - PLAYER_WIDTH;
		}
	}

	/**
	 * render blue game board
	 * 	
	 * @param g Graphics object
	 *
	 */
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int) x, (int) y, PLAYER_WIDTH, PLAYER_HEIGHT);
	}

	/**
	 * get x of board
	 * 	
	 * @return returns x coordinate of board
	 *
	 */
	public double getX() {
		return x;
	}

	/**
	 * get y of board
	 * 	
	 * @return returns y coordinate of board
	 *
	 */
	public double getY() {
		return y;
	}

	/**
	 * get x velocity of board
	 * 	
	 * @param x set x coordinate of board
	 *
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * set x velocity of board
	 * 	
	 * @param velX x velocity of board
	 *
	 */
	public void setVelX(double velX) {
		this.velX = velX;
	}

}
