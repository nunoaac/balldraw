/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nunoaac.balldraw_core.balldraw.domain.daos;

import com.nunoaac.balldraw_core.balldraw.domain.beans.BallDraw;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author nunocosta
 */
public class BallDrawJpaDAOTest {
    
    static BallDrawJpaDAO bdDao;
    
    public BallDrawJpaDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        bdDao = new BallDrawJpaDAO();
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
     * Test of create method, of class BallDrawJpaDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("JUnit Test - BallDrawDAO - Create normal ball draw");
        
    }

    /**
     * Test of edit method, of class BallDrawJpaDAO.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        BallDraw ballDraw = null;
        BallDrawJpaDAO instance = null;
        instance.edit(ballDraw);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class BallDrawJpaDAO.
     */
    @Test
    public void testDestroy() throws Exception {
        System.out.println("destroy");
        UUID id = null;
        BallDrawJpaDAO instance = null;
        instance.destroy(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findBallDrawEntities method, of class BallDrawJpaDAO.
     */
    @Test
    public void testFindBallDrawEntities_0args() {
        System.out.println("findBallDrawEntities");
        BallDrawJpaDAO instance = null;
        List<BallDraw> expResult = null;
        List<BallDraw> result = instance.findBallDrawEntities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findBallDrawEntities method, of class BallDrawJpaDAO.
     */
    @Test
    public void testFindBallDrawEntities_int_int() {
        System.out.println("findBallDrawEntities");
        int maxResults = 0;
        int firstResult = 0;
        BallDrawJpaDAO instance = null;
        List<BallDraw> expResult = null;
        List<BallDraw> result = instance.findBallDrawEntities(maxResults, firstResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findBallDraw method, of class BallDrawJpaDAO.
     */
    @Test
    public void testFindBallDraw() {
        System.out.println("findBallDraw");
        String id = "";
        BallDrawJpaDAO instance = null;
        BallDraw expResult = null;
        BallDraw result = instance.findBallDraw(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBallDrawCount method, of class BallDrawJpaDAO.
     */
    @Test
    public void testGetBallDrawCount() {
        System.out.println("getBallDrawCount");
        BallDrawJpaDAO instance = null;
        int expResult = 0;
        int result = instance.getBallDrawCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
