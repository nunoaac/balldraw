package com.ingg.balldraw_core.balldraw.logic.algorithm;

import java.util.List;

import com.ingg.balldraw_core.balldraw.domain.BallDraw;
import com.ingg.balldraw_core.balldraw.logic.algorithm.BallDrawGeneratorAlgorithmInterface;

public class FisherYatesDraw implements BallDrawGeneratorAlgorithmInterface {

	public BallDraw returnBallDraw(int pool, int selection) {
		
		BallDraw newDraw = null;
		/*
		BallDrawGenerator ballDrawGenerator = new RandomBallDrawGenerator();
		
		try {

			List<Integer> draw = ballDrawGenerator.generate(pool, selection);
			newDraw = new BallDraw(pool, draw);
			System.out.println(draw);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		return newDraw;
	}
}
