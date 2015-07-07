package com.ingg.balldraw_core.balldraw.logic.algorithm;

import com.ingg.balldraw_core.balldraw.domain.BallDraw;

/**
 * Every algorithm used on this project should implement this interface. After
 * the implementation, it needs to be added to the DrawAlgorithm enum
 *
 * @author Nuno Costa (nunooaac@msn.com)
 */
public interface BallDrawAlgorithmInterface {

    public enum DrawAlgorithm {
        FISHERYATES, SIMPLERANDOM
    }

    //if not provided, we used the default values.
    public final int DEFAULT_POOL_SIZE = 50;
    public final int DEFAULT_DRAW_SIZE = 20;

    public BallDraw returnBallDraw(Integer pool, Integer selection);

}
