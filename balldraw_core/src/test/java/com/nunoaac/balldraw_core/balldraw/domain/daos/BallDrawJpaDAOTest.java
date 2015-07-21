/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nunoaac.balldraw_core.balldraw.domain.daos;

import com.nunoaac.balldraw_core.balldraw.domain.beans.BallDraw;
import com.nunoaac.balldraw_core.balldraw.domain.beans.Client;
import java.util.Map;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author nunocosta
 */
public class BallDrawJpaDAOTest<ID> extends JpaDAOTest {
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public BallDrawJpaDAOTest() {
        super();
    }
    
    @Override
    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testCreateBallDraw() throws Exception {

        Client user = new Client("test" + RandomStringUtils.random(10, true, false), "123qwe");
        auxiliaryPersistClient(user);

        int numberOfDraws = bdDrawDao.getEntityCount();

        BallDraw newDraw = auxiliaryRandomGenerateManualBallDraw(user);
        bdDrawDao.create(newDraw);

        toDelete.put(bdDrawDao, (ID) newDraw.getUid());
        int newNumberOfDraws = bdDrawDao.getEntityCount();
        assertEquals("testCreateBallDraw is not working correctly. Number of draw rows has not incremented with a createDraw call", (numberOfDraws + 1), newNumberOfDraws);
    }

    @Test
    public void testCreateBallDrawWithoutUser() throws Exception {
        int numberOfDraws = bdDrawDao.getEntityCount();
        
        BallDraw newDraw = auxiliaryRandomGenerateManualBallDraw(null);
        
        thrown.expect(javax.persistence.RollbackException.class);
        thrown.expectMessage("ERROR: null value in column \"client_id\" violates not-null constraint");
        auxiliaryPersistBallDraw(newDraw);
        
        int newNumberOfDraws = bdDrawDao.getEntityCount();
        assertEquals("testCreateBallDrawWithoutUser is not working correctly. Draw without client has been persisted.", numberOfDraws, newNumberOfDraws);
    }
    
    @Test
    public void testDestroyBallDraw() throws Exception {
        
        Client user = new Client("test" + RandomStringUtils.random(10, true, false), "123qwe");
        auxiliaryPersistClient(user);

        BallDraw newDraw = auxiliaryRandomGenerateManualBallDraw(user);
        bdDrawDao.create(newDraw);

        int numberOfDraws = bdDrawDao.getEntityCount();
        bdDrawDao.destroy(newDraw.getUid());
        
        //thrown.expect(javax.persistence.EntityNotFoundException.class);
        BallDraw retrievedBallDraw = (BallDraw) bdDrawDao.findById(newDraw.getUid());
        assertNull("testDestroy is not deleting the ball draw from the database", retrievedBallDraw);
        int newNumberOfDraws = bdDrawDao.getEntityCount();
        assertEquals("testDestroy is not working correctly. Number of ball draw rows is not the same", (numberOfDraws-1), newNumberOfDraws);
    }

}
