package com.ingg.balldraw_core.balldraw.logic.algorithm;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.ingg.balldraw_core.balldraw.domain.BallDraw;


public class SimpleRandomDraw implements BallDrawAlgorithmInterface {

    @Override
    public BallDraw returnBallDraw(Integer pool, Integer selection) {

        //using Java 8 optionals to check if Integer params are null. If so, assign them with default values (pool = 50, selection = 20)
        pool = pool != null ? pool : DEFAULT_POOL_SIZE;
        selection = selection != null ? selection : DEFAULT_DRAW_SIZE;
        
        List<Integer> drawNumbers = simpleBallDrawGenerator(pool, selection);
        BallDraw newDraw = new BallDraw(pool, drawNumbers, DrawAlgorithm.SIMPLERANDOM);

        return newDraw;
    }

    private List<Integer> simpleBallDrawGenerator(Integer pool, Integer selection) {
        
        Set<Integer> set = new HashSet<Integer>();
        Random rand = new Random();

        while (set.size() < selection) {
            set.add(rand.nextInt((pool - 1) + 1));           //formula here: http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java
        }
        return new ArrayList<Integer>(set);
    }

}
