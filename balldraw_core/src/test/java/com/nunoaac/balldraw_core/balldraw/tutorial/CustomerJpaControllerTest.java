/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nunoaac.balldraw_core.balldraw.tutorial;

import java.util.List;
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
public class CustomerJpaControllerTest {
    
    public CustomerJpaControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.out.println("Test ........................");
    }
    
    @After
    public void tearDown() {
        System.out.println("........................ Test");
    }

    /**
     * Test of create method, of class CustomerJpaDAO.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Customer customer = new Customer("Nuno", "Costa");
        CustomerJpaDAO instance = new CustomerJpaDAO();
        instance.create(customer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class CustomerJpaDAO.
     */
    @Test
    public void testEdit() throws Exception {
        System.out.println("edit");
        Customer customer = null;
        CustomerJpaDAO instance = new CustomerJpaDAO();
        instance.edit(customer);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class CustomerJpaDAO.
     */
    @Test
    public void testDestroy() throws Exception {
        System.out.println("destroy");
        Long id = null;
        CustomerJpaDAO instance = new CustomerJpaDAO();
        instance.destroy(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findCustomerEntities method, of class CustomerJpaDAO.
     */
    @Test
    public void testFindCustomerEntities_0args() {
        System.out.println("findCustomerEntities");
        CustomerJpaDAO instance = new CustomerJpaDAO();
        List<Customer> expResult = null;
        List<Customer> result = instance.findCustomerEntities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findCustomerEntities method, of class CustomerJpaDAO.
     */
    @Test
    public void testFindCustomerEntities_int_int() {
        System.out.println("findCustomerEntities");
        int maxResults = 0;
        int firstResult = 0;
        CustomerJpaDAO instance = new CustomerJpaDAO();
        List<Customer> expResult = null;
        List<Customer> result = instance.findCustomerEntities(maxResults, firstResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findCustomer method, of class CustomerJpaDAO.
     */
    @Test
    public void testFindCustomer() {
        System.out.println("findCustomer");
        Long id = null;
        CustomerJpaDAO instance = new CustomerJpaDAO();
        Customer expResult = null;
        Customer result = instance.findCustomer(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCustomerCount method, of class CustomerJpaDAO.
     */
    @Test
    public void testGetCustomerCount() {
        System.out.println("getCustomerCount");
        CustomerJpaDAO instance = new CustomerJpaDAO();
        int expResult = 0;
        int result = instance.getCustomerCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
