package com.game.src.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * class with the click behavior of buttons
 * 
 * @author Dominik Huerrig
 * @version 1.0
 *
 */
public class MouseInput implements MouseListener {


	/**
	 * sets STATE according to clicked button
	 * 	
	 * @param e event when mouse is pressed
	 *
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (Game.State == Game.STATE.MENU) {
			// Play Button
			if (mx >= Game.WIDTH / 2 + 50 && mx <= Game.WIDTH / 2 + 270) {
				if (my >= 160 && my <= 210) {

					Game.State = Game.STATE.GAME;

				} else if (my >= 227 && my <= 277) {

					Game.State = Game.STATE.LEVEL;

				} else if (my >= 297 && my <= 347) {

					Game.State = Game.STATE.OPTION;

				} else if (my >= 398 && my <= 448) {

					Game.State = Game.STATE.STOP;

				}
			}

		} else if (Game.State == Game.STATE.LEVEL) {
			if (mx >= 50 && mx <= 120) {
				if (my >= 50 && my <= 85) {
					Game.State = Game.STATE.MENU;
				}
			} else if (my >= 159 && my <= 217) {
				if (mx >= 92 && mx <= 146) {
					Game.Level = Game.LEVEL.ONE;
					Game.State = Game.STATE.RESTART;
				} else if (mx >= 215 && mx <= 269) {
					Game.Level = Game.LEVEL.TWO;
					Game.State = Game.STATE.RESTART;
				} else if (mx >= 335 && mx <= 389) {
					Game.Level = Game.LEVEL.TREE;
					Game.State = Game.STATE.RESTART;
				} else if (mx >= 465 && mx <= 519) {
					Game.Level = Game.LEVEL.FOUR;
					Game.State = Game.STATE.RESTART;
				}

			} else if (my >= 261 && my <= 319) {
				if (mx >= 92 && mx <= 146) {
					Game.Level = Game.LEVEL.FIVE;
					Game.State = Game.STATE.RESTART;
				} else if (mx >= 218 && mx <= 272) {
					Game.Level = Game.LEVEL.SIX;
					Game.State = Game.STATE.RESTART;
				} else if (mx >= 338 && mx <= 392) {
					Game.Level = Game.LEVEL.SEVEN;
					Game.State = Game.STATE.RESTART;
				} else if (mx >= 465 && mx <= 519) {
					Game.Level = Game.LEVEL.EIGHT;
					Game.State = Game.STATE.RESTART;
				}

			}

		} else if (Game.State == Game.STATE.OPTION) {
			if (mx >= 50 && mx <= 120) {
				if (my >= 50 && my <= 85) {
					Game.State = Game.STATE.MENU;
				}
			}

		} else if (Game.State == Game.STATE.PAUSE) {
			if (mx >= Game.WIDTH / 2 + 50 && mx <= Game.WIDTH / 2 + 270) {
				if (my >= 168 && my <= 218) {

					Game.State = Game.STATE.GAME;

				} else if (my >= 236 && my <= 286) {

					Game.State = Game.STATE.RESTART;

				} else if (my >= 305 && my <= 355) {

					Game.State = Game.STATE.OPTION;

				} else if (my >= 372 && my <= 422) {

					Game.State = Game.STATE.MENU;

				}
			}

		} else if (Game.State == Game.STATE.WON || Game.State == Game.STATE.LOSE) {
			if (mx >= Game.WIDTH / 2 + 50 && mx <= Game.WIDTH / 2 + 270) {
				if (my >= 236 && my <= 286) {

					Game.State = Game.STATE.RESTART;

				} else if (my >= 306 && my <= 356) {

					Game.State = Game.STATE.LEVEL;

				} else if (my >= 378 && my <= 428) {

					Game.State = Game.STATE.MENU;

				}
			}

		}

	}

	/**
	 * event when mouse is released
	 * 	
	 * @param e event when mouse is released
	 *
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * event when mouse is enters window
	 * 	
	 * @param e event when mouse enters
	 *
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * event when mouse is exits window
	 * 	
	 * @param e event when mouse exits
	 *
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * event when mouse is clicked
	 * 	
	 * @param e event when mouse is clicked
	 *
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
