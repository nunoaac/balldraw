/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingg.balldraw_core.balldraw.logic.generator;

import com.ingg.balldraw_core.balldraw.domain.BallDraw;
import com.ingg.balldraw_core.balldraw.logic.algorithm.SimpleRandomDraw;

/**
 * @author support
 *
 */

public class AutomaticBallDraw {

	private final int DRAW_FREQUENCY = 1000;  //1000 miliseconds
	private final int pool = 100;
	private final int selection = 50;
	private SimpleRandomDraw drawGenerator;
	
	public void generateAutomaticDraws(boolean verbose) throws InterruptedException {

		drawGenerator = new SimpleRandomDraw();
		
		for(;;) {
			BallDraw newDraw = drawGenerator.returnBallDraw(pool, selection);
                        if(verbose)
                            newDraw.toString();
                        
			Thread.sleep(DRAW_FREQUENCY);
		}
	}
}