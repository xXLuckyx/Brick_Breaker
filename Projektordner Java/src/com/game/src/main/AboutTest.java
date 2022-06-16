package com.game.src.main;


import java.awt.Desktop;
import java.io.File;

import org.testng.annotations.Test;



/**
 * class with Test cases (About button test case)
 * 
 * @author Dominik Huerrig
 * @version 1.0
 *
 */
public class AboutTest {

	
	/**
	 * tests if the file exists
	 * 	
	 * 
	 *
	 */
  @Test
  public void testCaseAbout() {
	  try{
			File pdfFile = new File("res/firm.pdf");
			if (pdfFile.exists()) {
				if (Desktop.isDesktopSupported()){
					Desktop.getDesktop().open(pdfFile);
					System.out.println("Pdf konnte geöffnet werden");
				} else {
					System.out.println("Awt Desktop is not supported!");
				}
			} else {
				System.out.println("File does not exist");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	  
  }

}
