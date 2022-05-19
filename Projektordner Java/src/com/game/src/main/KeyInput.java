package com.game.src.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * class which filters key events
 * 
 * @author Dominik Hürrig
 * @version 1.0
 *
 */
public class KeyInput extends KeyAdapter {

	Game game;

	/**
	 * constructor
	 * 
	 * @param game Game object
	 *
	 */
	public KeyInput(Game game) {
		this.game = game;
	}

	/**
	 * event when key is pressed
	 * 
	 * @param e event when key is pressed
	 *
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
	}

	/**
	 * event when key is released
	 * 
	 * @param e event when key is released
	 *
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}

}
