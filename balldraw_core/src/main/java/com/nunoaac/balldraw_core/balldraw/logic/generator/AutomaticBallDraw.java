package com.nunoaac.balldraw_core.balldraw.logic.generator;

import com.nunoaac.balldraw_core.balldraw.domain.beans.BallDraw;
import com.nunoaac.balldraw_core.balldraw.logic.algorithm.BallDrawAlgorithmInterface;
import com.nunoaac.balldraw_core.balldraw.logic.algorithm.BallDrawAlgorithmInterface.DrawAlgorithm;
import com.nunoaac.balldraw_core.balldraw.logic.algorithm.FisherYatesDraw;
import com.nunoaac.balldraw_core.balldraw.logic.algorithm.InvalidDrawParametersException;
import com.nunoaac.balldraw_core.balldraw.logic.algorithm.SimpleRandomDraw;

/**
 * Keeps the program generating Ball Draws every (DRAW_FREQUENCY) seconds.
 * Algorithm, Pool Size and Selection are configurable. Those draws will not be
 * delivered to any user and will be discarded. This only happens to make the
 * seed "active".
 *
 * @author Nuno Costa (nunoaac@msn.com)
 *
 */
public class AutomaticBallDraw {

    private final int DRAW_FREQUENCY = 1000;  //1000 miliseconds = 1 second
    private BallDrawAlgorithmInterface drawGenerator;

    //verboser - If true, the generated ball draws are printed to the stdout
    public void generateAutomaticDraws(boolean verbose, DrawAlgorithm algorithm, Integer pool, Integer selection) throws InterruptedException, InvalidDrawParametersException {

        if (algorithm == algorithm.SIMPLERANDOM) {
            drawGenerator = new SimpleRandomDraw();
        } else {
            drawGenerator = new FisherYatesDraw();
        }

            //To Do - need a Start and Stop mechanism for this
        //To Do - may need to change the draw params in the middle of the loop
        while (true) {
            BallDraw newDraw = drawGenerator.returnBallDraw(pool, selection);
            if (verbose) {
                System.out.println(newDraw.toString());
            }

            Thread.sleep(DRAW_FREQUENCY);
        }
    }
}
