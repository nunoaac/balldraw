package com.ingg.balldraw_core.balldraw.logic.algorithm;

import java.util.List;

import com.ingg.balldraw_core.balldraw.domain.BallDraw;
import com.ingg.RandomBallDrawGenerator;
import com.ingg.BallDrawGenerator;

public class FisherYatesDraw implements BallDrawAlgorithmInterface {

    @Override
    public BallDraw returnBallDraw(Integer pool, Integer selection) {

        //using Java 8 optionals to check if Integer params are null. If so, assign them with default values (pool = 50, selection = 20)
        pool = pool != null ? pool : DEFAULT_POOL_SIZE;
        selection = selection != null ? selection : DEFAULT_DRAW_SIZE;

        BallDraw newDraw = null;

        BallDrawGenerator ballDrawGenerator = new RandomBallDrawGenerator();

        try {
            List<Integer> draw = ballDrawGenerator.generate(pool, selection);
            newDraw = new BallDraw(pool, draw, DrawAlgorithm.FISHERYATES);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return newDraw;
    }
}
