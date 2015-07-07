/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingg.balldraw_console;

import com.ingg.balldraw_core.balldraw.domain.BallDraw;
import com.ingg.balldraw_core.balldraw.logic.algorithm.BallDrawAlgorithmInterface.DrawAlgorithm;
import com.ingg.balldraw_core.balldraw.logic.generator.AutomaticBallDraw;
import com.ingg.balldraw_core.balldraw.logic.generator.ManualBallDraw;

public class Program {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("START");

        BallDraw newDraw;

        ManualBallDraw manualBallDrawGenerator = new ManualBallDraw();
        newDraw = manualBallDrawGenerator.getBallDraw(50, 20, DrawAlgorithm.FISHERYATES);
        System.out.println(newDraw.toString());
        newDraw = manualBallDrawGenerator.getBallDraw(50, 20, DrawAlgorithm.SIMPLERANDOM);
        System.out.println(newDraw.toString());

        AutomaticBallDraw ballDrawGenerator = new AutomaticBallDraw();
        ballDrawGenerator.generateAutomaticDraws(true, DrawAlgorithm.SIMPLERANDOM, 50, 20);

        System.out.println("END");
    }

}
