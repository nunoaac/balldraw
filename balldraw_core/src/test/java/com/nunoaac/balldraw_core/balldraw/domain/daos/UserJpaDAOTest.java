/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nunoaac.balldraw_core.balldraw.domain.daos;

import com.nunoaac.balldraw_core.balldraw.domain.beans.BallDraw;
import com.nunoaac.balldraw_core.balldraw.domain.beans.Client;
import java.util.List;
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
public class UserJpaDAOTest {

    static UserJpaDAO bdDao;

    public UserJpaDAOTest() {
        bdDao = new UserJpaDAO();
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

    @Test
    public void testCreate() throws Exception {
        
        System.out.println("JUnit Test - UserJpaDAO - Create normal user");

        Client nuno = new Client("nuno", "123qwe");
        EntityManager transactionManager = bdDao.getEntityManager();
        transactionManager.getTransaction().begin();
        bdDao.create(nuno);
        transactionManager.getTransaction().rollback();
                
        assertNotNull("testCreate is not working correctly. ID of created row is null", nuno.getId());
    }

//    /**
//     * Test of edit method, of class UserJpaDAO.
//     */
//    @Test
//    public void testEdit() throws Exception {
//        System.out.println("edit");
//        Client user = null;
//        UserJpaDAO instance = new UserJpaDAO();
//        instance.edit(user);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of destroy method, of class UserJpaDAO.
//     */
//    @Test
//    public void testDestroy() throws Exception {
//        System.out.println("destroy");
//        long id = 0L;
//        UserJpaDAO instance = new UserJpaDAO();
//        instance.destroy(id);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findUserEntities method, of class UserJpaDAO.
//     */
//    @Test
//    public void testFindUserEntities_0args() {
//        System.out.println("findUserEntities");
//        UserJpaDAO instance = new UserJpaDAO();
//        List<Client> expResult = null;
//        List<Client> result = instance.findUserEntities();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findUserEntities method, of class UserJpaDAO.
//     */
//    @Test
//    public void testFindUserEntities_int_int() {
//        System.out.println("findUserEntities");
//        int maxResults = 0;
//        int firstResult = 0;
//        UserJpaDAO instance = new UserJpaDAO();
//        List<Client> expResult = null;
//        List<Client> result = instance.findUserEntities(maxResults, firstResult);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of findUser method, of class UserJpaDAO.
//     */
//    @Test
//    public void testFindUser() {
//        System.out.println("findUser");
//        long id = 0L;
//        UserJpaDAO instance = new UserJpaDAO();
//        Client expResult = null;
//        Client result = instance.findUser(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getUserCount method, of class UserJpaDAO.
//     */
//    @Test
//    public void testGetUserCount() {
//        System.out.println("getUserCount");
//        UserJpaDAO instance = new UserJpaDAO();
//        int expResult = 0;
//        int result = instance.getUserCount();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
