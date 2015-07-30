package com.nunoaac.balldraw_core.balldraw.logic.algorithm;

import java.util.List;

import com.nunoaac.balldraw_core.balldraw.domain.beans.BallDraw;
import com.ingg.RandomBallDrawGenerator;
import com.ingg.BallDrawGenerator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FisherYates implementation of a ball draw (using external JARs) Implements
 * BallDrawAlgorithmInterface that forces implementations to generate ball draws
 * with a specific pool size and selection.
 *
 * @author Nuno Costa (nunoaac@msn.com)
 */
public class FisherYatesDraw implements BallDrawAlgorithmInterface {
    
    public final int MAX_VALUE = 100;

    @Override
    public BallDraw returnBallDraw(Integer pool, Integer selection) throws InvalidDrawParametersException {

        this.validateBallDraw(pool, selection);

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
    
    /**
     * Mechaist to validate FisherYates ball draw settings
     * @param pool Max value of the generated balls
     * @param selection Number of generated Balls
     * @throws InvalidDrawParametersException Throws exception if validation fails
     */
    public void validateBallDraw(Integer pool, Integer selection) throws InvalidDrawParametersException {
        
        if(((pool == null) && (selection != null)) || ((pool !=null) && (selection ==null)))
            throw new InvalidDrawParametersException("You should either give both pool and selection values or none.");
            
        if(selection > pool)
            throw new InvalidDrawParametersException("Selection size cannot be greater than pool size");
        
        if((pool < 1) || (selection < 1)) 
            throw new InvalidDrawParametersException("Pool/Selection values should be greater than 0");
        
        if((pool > this.MAX_VALUE))
            throw new InvalidDrawParametersException("Pool Max Value is " + Integer.MAX_VALUE);
               
    }
}
