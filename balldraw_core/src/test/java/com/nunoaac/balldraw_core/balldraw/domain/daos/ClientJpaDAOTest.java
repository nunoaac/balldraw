package com.nunoaac.balldraw_core.balldraw.domain.daos;

import com.nunoaac.balldraw_core.balldraw.domain.beans.BallDraw;
import com.nunoaac.balldraw_core.balldraw.domain.beans.Client;
import static com.nunoaac.balldraw_core.balldraw.domain.daos.JpaDAOTest.bdDrawDao;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author nunocosta
 * @param <ID>
 */
public class ClientJpaDAOTest<ID> extends JpaDAOTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public ClientJpaDAOTest() {
        super();
    }

    @Test
    public void testCreateClient() throws Exception {
        System.out.println("JUnit Test - ClientJpaDAO - Create normal client");

        int numberOfClients = bdClientDao.getEntityCount();

        Client client = new Client("test" + RandomStringUtils.random(10, true, false), "123qwe");
        bdClientDao.create(client);

        assertNotNull("testCreate is not working correctly. ID of created row is null", client.getId());
        toDelete.put(bdClientDao, (ID) client.getId());
        int newNumberOfClients = bdClientDao.getEntityCount();
        assertEquals("testCreate is not working correctly. Number of client rows has not incremented with a createClient call", (numberOfClients + 1), newNumberOfClients);
    }

    @Test
    public void testCreateClientWithSameUsername() throws Exception {
        System.out.println("JUnit Test - ClientJpaDAO - Create client with same username");

        int numberOfClients = bdClientDao.getEntityCount();

        String randomUsername = "test" + RandomStringUtils.random(10, true, false);
        Client client = new Client(randomUsername, "123qwe");
        bdClientDao.create(client);

        assertNotNull("testCreate is not working correctly. ID of created row is null", client.getId());
        toDelete.put(bdClientDao, (ID) client.getId());

        client = new Client(randomUsername, "qwe123");

        thrown.expect(javax.persistence.RollbackException.class);
        thrown.expectMessage("ERROR: duplicate key value violates unique constraint \"client_username_key\"");
        bdClientDao.create(client);

        int newNumberOfClients = bdClientDao.getEntityCount();
        assertEquals("testCreateClientWithSameUsername is not working correctly. Number of client rows has not incremented only by 1", (numberOfClients + 1), newNumberOfClients);
    }

    @Test
    public void testFindClientById() throws Exception {
        System.out.println("JUnit Test - ClientJpaDAO - Find client by ID");

        Client client = new Client("test" + RandomStringUtils.random(10, true, false), "123qwe");
        auxiliaryPersistClient(client);

        Client retrievedClient = (Client) bdClientDao.findById(client.getId());
        assertTrue("testFindClientById is returning a different client that was expected", client.equals(retrievedClient));
    }

    @Test
    public void testEditClient() throws Exception {
        System.out.println("JUnit Test - ClientJpaDAO - Edit client");

        Client client = new Client("test" + RandomStringUtils.random(10, true, false), "123qwe");
        auxiliaryPersistClient(client);

        client.setHashPassword("newPassword");
        bdClientDao.edit(client, client.getId());

        Client retrievedClient = (Client) bdClientDao.findById(client.getId());
        assertTrue("testFindClientById is returning a different client that was expected", client.equals(retrievedClient));

    }

    @Test
    public void testDestroy() throws Exception {
        System.out.println("JUnit Test - ClientJpaDAO - Delete client");

        int numberOfClients = bdClientDao.getEntityCount();
        Client client = new Client("test" + RandomStringUtils.random(10, true, false), "123qwe");
        auxiliaryPersistClient(client);

        bdClientDao.destroy(client.getId());

        thrown.expect(javax.persistence.EntityNotFoundException.class);
        Client retrievedClient = (Client) bdClientDao.findById(client.getId());
        assertNull("testDestroy is not deleting the client from the database", retrievedClient);
        int newNumberOfClients = bdClientDao.getEntityCount();
        assertEquals("testDestroy is not working correctly. Number of client rows is not the same", numberOfClients, newNumberOfClients);
    }

    @Test
    public void testGetHashPassword() throws Exception {
        System.out.println("JUnit Test - ClientJpaDAO - Get Hash Password from Client");

        String randomHashPassword = RandomStringUtils.random(20, true, true);
        String randomUsername = "test" + RandomStringUtils.random(10, true, false);
        Client client = new Client(randomUsername, randomHashPassword);
        auxiliaryPersistClient(client);

        assertEquals("testGetHashPassword not working correctly. Passwords don't match", bdClientDao.getHashPasswordForClient(client.getUsername()), randomHashPassword);
    }

    @Test
    public void testGetHasPasswordInvalidClient() throws Exception {
        System.out.println("JUnit Test - ClientJpaDAO - Get Hash Password from uniextant Client");
        
        String randomUsername = RandomStringUtils.random(10, true, false);
        thrown.expect(javax.persistence.NoResultException.class);
        bdClientDao.getHashPasswordForClient(randomUsername);
    }
    
    @Test
    public void testDestroyClientWithBallDraws() throws Exception {
        System.out.println("JUnit Test - ClientJpaDAO - Test Cascade Delete");
        
        Client client = new Client("test" + RandomStringUtils.random(10, true, false), "123qwe");
        auxiliaryPersistClient(client);
        
        BallDraw newDraw = auxiliaryRandomGenerateManualBallDraw(client);
        bdDrawDao.create(newDraw);
        
        bdClientDao.destroy(client);
        
        BallDraw retrievedBallDraw = (BallDraw) bdDrawDao.findById(newDraw.getUid());
        assertNull("testDestroyClientWithBallDraws is not deleting the client's ball draw from the database", retrievedBallDraw);
        
        Client retrievedClient = (Client) bdClientDao.findById(client.getId());
        assertNull("testDestroyClientWithBallDraws is not deleting the client from the database, after deleting his ball draws", retrievedClient);
    }
}
