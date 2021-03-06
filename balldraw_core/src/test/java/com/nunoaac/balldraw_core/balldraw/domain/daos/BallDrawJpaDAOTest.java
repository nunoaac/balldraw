package com.nunoaac.balldraw_core.balldraw.domain.daos;

import com.nunoaac.balldraw_core.balldraw.domain.beans.BallDraw;
import com.nunoaac.balldraw_core.balldraw.domain.beans.Client;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author nunocosta
 * @param <ID>
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
        System.out.println("JUnit Test - BallDrawJpaDAO - Create ball draw");

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
        System.out.println("JUnit Test - BallDrawJpaDAO - Create Ball Draw without user");

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
        System.out.println("JUnit Test - BallDrawJpaDAO - Delete Ball Draw");

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
        assertEquals("testDestroy is not working correctly. Number of ball draw rows is not the same", (numberOfDraws - 1), newNumberOfDraws);
    }

    @Test
    public void testFindBallDrawById() throws Exception {
        System.out.println("JUnit Test - BallDrawJpaDAO - Find BallDraw by ID");

        Client client = new Client("test" + RandomStringUtils.random(10, true, false), "123qwe");
        auxiliaryPersistClient(client);

        BallDraw newDraw = auxiliaryRandomGenerateManualBallDraw(client);
        bdDrawDao.create(newDraw);

        BallDraw retrievedBallDraw = (BallDraw) bdDrawDao.findById(newDraw.getUid());
        assertTrue("testFindBallDrawById is returning a different ball draw that was expected", newDraw.equals(retrievedBallDraw));
    }

    @Test
    public void testGetAllDrawsFromClient() throws Exception {
        System.out.println("JUnit Test - BallDrawJpaDAO - Get all BallDraws from client (using client's username and client's id");
        
        
        Client client = new Client("test" + RandomStringUtils.random(10, true, false), "123qwe");
        auxiliaryPersistClient(client);
        
        BallDraw newDraw = auxiliaryRandomGenerateManualBallDraw(client);
        bdDrawDao.create(newDraw);
        
        newDraw = auxiliaryRandomGenerateManualBallDraw(client);
        bdDrawDao.create(newDraw);
        
        List<BallDraw> listBall = bdDrawDao.findBallDrawsFromClientByUsername(client.getUsername());
        assertEquals("List of client's ball draws is not equal to the list got from the DB (via client's username)", client.getDraws(), listBall);
        
        List<BallDraw> listBall2 = bdDrawDao.findBallDrawsFromClientById(client.getId()); 
        assertEquals("List of client's ball draws is not equal to the list got from the DB (via client's id)", client.getDraws(), listBall2);
    }
    
    @Test
    public void testEdit() throws Exception {
        System.out.println("JUnit Test - BallDrawJpaDAO - Test forbidden edit");
        
        Client client = new Client("test" + RandomStringUtils.random(10, true, false), "123qwe");
        auxiliaryPersistClient(client);
        
        BallDraw newDraw = auxiliaryRandomGenerateManualBallDraw(client);
        bdDrawDao.create(newDraw);
        
        thrown.expect(UnsupportedOperationException.class);
        bdDrawDao.edit(newDraw);
    }
    
    //TODO - Test destroy client with ball draws
    //TODO - CLean up this suite 
}
