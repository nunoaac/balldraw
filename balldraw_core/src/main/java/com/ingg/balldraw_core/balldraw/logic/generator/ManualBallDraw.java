package com.ingg.balldraw_core.balldraw.logic.generator;

import com.ingg.balldraw_core.balldraw.domain.BallDraw;
import com.ingg.balldraw_core.balldraw.logic.algorithm.BallDrawAlgorithmInterface;
import com.ingg.balldraw_core.balldraw.logic.algorithm.BallDrawAlgorithmInterface.DrawAlgorithm;
import static com.ingg.balldraw_core.balldraw.logic.algorithm.BallDrawAlgorithmInterface.DrawAlgorithm.FISHERYATES;
import static com.ingg.balldraw_core.balldraw.logic.algorithm.BallDrawAlgorithmInterface.DrawAlgorithm.SIMPLERANDOM;
import com.ingg.balldraw_core.balldraw.logic.algorithm.FisherYatesDraw;
import com.ingg.balldraw_core.balldraw.logic.algorithm.SimpleRandomDraw;

/**
 * Represents a Ball Draw manually generated by a request from a user User can
 * pick Pool Size, Pool Selection and one of the implemented algorithms
 *
 * @author Nuno Costa (nunoaac@msn.com)
 */
public class ManualBallDraw {

    /**
     * Generate a new instance of a Ball Draw with all information User can pick
     * Pool Size, Selection and Algorithm
     *
     * @param pool Pool Size - Max number that a draw can contain
     * @param selection Draw Size - Number of generated balls
     * @param algorithm Algorithm to pick and shuffle the Draw
     * @return Ball Draw object that includes all info related with a draw
     */
    public static BallDraw getBallDraw(int pool, int selection, DrawAlgorithm algorithm) {

        BallDrawAlgorithmInterface ballDrawGenerator;
        switch (algorithm) {
            case FISHERYATES:
                ballDrawGenerator = new FisherYatesDraw();
                break;
            case SIMPLERANDOM:
                ballDrawGenerator = new SimpleRandomDraw();
                break;
            default:
                return null;
        }
        BallDraw newDraw = ballDrawGenerator.returnBallDraw(pool, selection);

        return newDraw;
    }
}
