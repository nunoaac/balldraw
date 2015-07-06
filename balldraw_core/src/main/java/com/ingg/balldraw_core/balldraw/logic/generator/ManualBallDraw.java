/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ingg.balldraw_core.balldraw.logic.generator;

import com.ingg.balldraw_core.balldraw.domain.BallDraw;
import com.ingg.balldraw_core.balldraw.logic.algorithm.BallDrawAlgorithmInterface;
import com.ingg.balldraw_core.balldraw.logic.algorithm.FisherYatesDraw;
import com.ingg.balldraw_core.balldraw.logic.algorithm.SimpleRandomDraw;

/**
 *
 * @author support
 */
public class ManualBallDraw {

    public enum DrawAlgorithm {

        FISHERYATES, SIMPLERANDOM
    }

    public static BallDraw getBallDraw(int pool, int selection, DrawAlgorithm algorithm) {

        BallDrawAlgorithmInterface ballDraw;
//        switch (algorithm) {
//            case FISHERYATES:
//                ballDrawGenerator = new FisherYatesDraw();
//                break;
//            case SIMPLERANDOM:
//                ballDrawGenerator = new SimpleRandomDraw();
//                break;
//            default:
//                return null;
//        }
        ballDraw = new FisherYatesDraw();
        BallDraw newDraw = ballDraw.returnBallDraw(pool, selection);

        return newDraw;
    }

}
