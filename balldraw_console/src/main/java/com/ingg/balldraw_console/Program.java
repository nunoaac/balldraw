/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingg.balldraw_console;

import com.ingg.balldraw_core.balldraw.logic.generator.AutomaticBallDraw;

public class Program {
    
    public static void main (String[] args) throws InterruptedException {
        System.out.println("START");
		
        AutomaticBallDraw ballDrawGenerator = new AutomaticBallDraw();
	ballDrawGenerator.generateAutomaticDraws();
		
	System.out.println("END");
    }
    
}
