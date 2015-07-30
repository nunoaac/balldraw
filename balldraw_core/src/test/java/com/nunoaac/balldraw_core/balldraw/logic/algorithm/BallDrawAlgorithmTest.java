/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nunoaac.balldraw_core.balldraw.logic.algorithm;

import com.nunoaac.balldraw_core.balldraw.domain.beans.BallDraw;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nunocosta
 * @param <T>
 */
public abstract class BallDrawAlgorithmTest <T>{
    
    private final Class<T> entityClass;
    
    public BallDrawAlgorithmTest(Class<T> entityClass) {
        
        this.entityClass = entityClass;
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of returnBallDraw method, of class BallDrawAlgorithmInterface.
     */
    @Test
    public void testReturnBallDraw() throws Exception {
        
       
        
    }

    /**
     * Test of validateBallDraw method, of class BallDrawAlgorithmInterface.
     */
    @Test
    public void testValidateBallDraw() throws Exception {
       
    }

    
    
}
