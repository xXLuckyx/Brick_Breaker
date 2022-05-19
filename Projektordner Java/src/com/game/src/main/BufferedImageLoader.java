package com.game.src.main;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * class to pre-load images
 * 
 * @author Dominik H�rrig
 * @version 1.0
 *
 */
public class BufferedImageLoader {
	private BufferedImage image;
	
	/**
	 * pre-loads image
	 * 
	 * @param path path of the image
	 * @return returns image
	 * @exception throws error if image couldn't be loaded
	 *
	 */
	public BufferedImage loadImage(String path) throws IOException {
		image = ImageIO.read(getClass().getResource(path));
		return image;
	}
	
}
