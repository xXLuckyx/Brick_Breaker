package com.game.src.main;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * class which loads and selects menu images
 * 
 * @author Dominik Huerrig
 * @version 1.0
 *
 */
public class Menu {

	private Image img1, img2, img3, img4, img5, img6;

	/**
	 * render blue game board
	 * 	
	 * @param g Graphics object
	 * @throws IOException throws exception when file couldn't be opened
	 *
	 */
	public void render(Graphics g) throws IOException {
		if (img1 == null) {
			img1 = ImageIO.read(new File("res/main_menu.png"));
			img2 = ImageIO.read(new File("res/level.png"));
			img3 = ImageIO.read(new File("res/opt_control.png"));
			img4 = ImageIO.read(new File("res/pause.png"));
			img5 = ImageIO.read(new File("res/win.png"));
			img6 = ImageIO.read(new File("res/lose.png"));
		}

		if (Game.State == Game.STATE.MENU) {
			g.drawImage(img1, 0, 0, null);
		} else if (Game.State == Game.STATE.LEVEL) {
			g.drawImage(img2, 0, 0, null);
		} else if (Game.State == Game.STATE.OPTION) {
			g.drawImage(img3, 0, 0, null);
		} else if (Game.State == Game.STATE.PAUSE) {
			g.drawImage(img4, 0, 0, null);
		} else if (Game.State == Game.STATE.WON) {
			g.drawImage(img5, 0, 0, null);
		} else if (Game.State == Game.STATE.LOSE) {
			g.drawImage(img6, 0, 0, null);
		}

	}
}
