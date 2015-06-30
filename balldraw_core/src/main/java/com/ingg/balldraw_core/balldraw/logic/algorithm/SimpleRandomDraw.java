package com.ingg.balldraw_core.balldraw.logic.algorithm;

import java.util.Arrays;
import java.util.List;

import com.ingg.balldraw_core.balldraw.domain.BallDraw;

public class SimpleRandomDraw implements BallDrawAlgorithmInterface {

	public BallDraw returnBallDraw(int pool, int selection) {
		
		BallDraw newDraw = null;
		
		List<Integer> drawNumbers = Arrays.asList(29, 7, 22, 1, 3, 49, 33, 90, 76, 9, 11, 72, 4);
		newDraw = new BallDraw(pool, drawNumbers);
		
		return newDraw;
	}

}
