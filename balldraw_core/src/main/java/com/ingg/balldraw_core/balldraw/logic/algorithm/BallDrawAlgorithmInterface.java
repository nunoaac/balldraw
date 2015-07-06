package com.ingg.balldraw_core.balldraw.logic.algorithm;

import com.ingg.balldraw_core.balldraw.domain.BallDraw;

public interface BallDrawAlgorithmInterface {
    
        public enum DrawAlgorithm {

            FISHERYATES, SIMPLERANDOM
        }
        
        public final int  DEFAULT_POOL_SIZE = 50;
        public final int  DEFAULT_DRAW_SIZE = 20;

	public BallDraw returnBallDraw(Integer pool, Integer selection);
        

}
