/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingg.balldraw_core.balldraw.logic.generator;

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
	
	public void generateAutomaticDraws() throws InterruptedException {

		drawGenerator = new SimpleRandomDraw();
		
		for(;;) {
			System.out.println(drawGenerator.returnBallDraw(pool, selection));
			Thread.sleep(DRAW_FREQUENCY);
		}
	}
}