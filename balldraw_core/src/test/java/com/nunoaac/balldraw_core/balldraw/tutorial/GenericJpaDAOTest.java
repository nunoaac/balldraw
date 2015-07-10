/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nunoaac.balldraw_core.balldraw.tutorial;

import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author support
 */
public class GenericJpaDAOTest {
    
    public GenericJpaDAOTest() {
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
     * Test of create method, of class GenericJpaDAO.
     */
    @org.junit.Test
    public void testCreate() {
        System.out.println("create");
        Object entity = null;
        GenericJpaDAO instance = null;
        instance.create(entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class GenericJpaDAO.
     */
    @org.junit.Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        Object entity = null;
        Object id = null;
        GenericJpaDAO instance = null;
        instance.edit(entity, id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findById method, of class GenericJpaDAO.
     */
    @org.junit.Test
    public void testFindById() {
        System.out.println("findById");
        Object entity = null;
        Object id = null;
        GenericJpaDAO instance = null;
        Object expResult = null;
        Object result = instance.findById(entity, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class GenericJpaControllerImpl extends GenericJpaDAO {

        public GenericJpaControllerImpl() {
            super(null);
        }
    }
    
}
