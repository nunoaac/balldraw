package com.nunoaac.balldraw_core.balldraw.logic.algorithm;

import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.nunoaac.balldraw_core.balldraw.domain.beans.BallDraw;

/**
 * Simple Random implementation of a Ball Draw
 *
 * @author Nuno Costa (nunoaac@msn.com)
 */
public class SimpleRandomDraw implements BallDrawAlgorithmInterface {

    @Override
    public BallDraw returnBallDraw(Integer pool, Integer selection) throws InvalidDrawParametersException {
        
        validateBallDraw(pool, selection);          //this will throw an error if the validation fails. It will not continue the ball draw generation method.

        //using Java 8 optionals to check if Integer params are null. If so, assign them with default values (pool = 50, selection = 20)
        pool = pool != null ? pool : DEFAULT_POOL_SIZE;
        selection = selection != null ? selection : DEFAULT_DRAW_SIZE;

        List<Integer> drawNumbers = simpleBallDrawGenerator(pool, selection);
        BallDraw newDraw = new BallDraw(pool, drawNumbers, DrawAlgorithm.SIMPLERANDOM);

        return newDraw;
    }

    /**
     * Mechanism to generate ball draws (adapted from
     * http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java)
     *
     * @param pool Max value of the generated balls
     * @param selection Number of generated Balls
     * @return List with integers representing balls in a draw
     */
    private List<Integer> simpleBallDrawGenerator(Integer pool, Integer selection) {

        Set<Integer> set = new HashSet<Integer>();
        Random rand = new Random();

        while (set.size() < selection) {
            set.add(rand.nextInt(pool) + 1);           //formula here: http://stackoverflow.com/questions/363681/generating-random-integers-in-a-range-with-java
        }
        
        return new ArrayList<Integer>(set);
    }

    /**
     * Mechaist to validate SimpleRandom ball draw settings
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
        
        if((pool > Integer.MAX_VALUE))
            throw new InvalidDrawParametersException("Pool Max Value is " + Integer.MAX_VALUE);
               
    }
    
    

}
