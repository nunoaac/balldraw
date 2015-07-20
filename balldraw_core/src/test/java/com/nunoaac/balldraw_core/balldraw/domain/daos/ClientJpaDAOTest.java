package com.nunoaac.balldraw_core.balldraw.domain.daos;

import com.nunoaac.balldraw_core.balldraw.domain.beans.BallDraw;
import com.nunoaac.balldraw_core.balldraw.domain.beans.Client;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.persistence.exceptions.DatabaseException;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/**
 *
 * @author nunocosta
 */
public class ClientJpaDAOTest<ID> {

    static ClientJpaDAO bdDao;
    HashMap<GenericDAO, ID> toDelete;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public ClientJpaDAOTest() {
        bdDao = new ClientJpaDAO();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        toDelete = new HashMap<GenericDAO, ID>();
    }

    @After
    public void tearDown() throws Exception {

        for (Map.Entry<GenericDAO, ID> entry : toDelete.entrySet()) {
            GenericDAO dao = entry.getKey();
            ID id = entry.getValue();
            dao.destroy(id);
        }
    }

    @Test
    public void testCreateClient() throws Exception {
        System.out.println("JUnit Test - ClientJpaDAO - Create normal client");

        int numberOfClients = bdDao.getEntityCount();

        Client client = new Client("test" + RandomStringUtils.random(10, true, false), "123qwe");
        bdDao.create(client);

        assertNotNull("testCreate is not working correctly. ID of created row is null", client.getId());
        toDelete.put(bdDao, (ID) client.getId());
        int newNumberOfClients = bdDao.getEntityCount();
        assertEquals("testCreate is not working correctly. Number of client rows has not incremented with a createClient call", (numberOfClients + 1), newNumberOfClients);
    }

    @Test
    public void testCreateClientWithSameUsername() throws Exception {
        System.out.println("JUnit Test - ClientJpaDAO - Create client with same username");

        int numberOfClients = bdDao.getEntityCount();

        String randomUsername = "test" + RandomStringUtils.random(10, true, false);
        Client client = new Client(randomUsername, "123qwe");
        bdDao.create(client);

        assertNotNull("testCreate is not working correctly. ID of created row is null", client.getId());
        toDelete.put(bdDao, (ID) client.getId());

        client = new Client(randomUsername, "qwe123");

        thrown.expect(javax.persistence.RollbackException.class);
        thrown.expectMessage("ERROR: duplicate key value violates unique constraint \"client_username_key\"");
        bdDao.create(client);

        int newNumberOfClients = bdDao.getEntityCount();
        assertEquals("testCreateClientWithSameUsername is not working correctly. Number of client rows has not incremented only by 1", (numberOfClients + 1), newNumberOfClients);
    }

    @Test
    public void testFindClientById() throws Exception {
        System.out.println("JUnit Test - ClientJpaDAO - Find client by ID");

        Client client = new Client("test" + RandomStringUtils.random(10, true, false), "123qwe");
        auxiliaryPersistClient(client);

        Client retrievedClient = (Client) bdDao.findById(client.getId());
        assertTrue("testFindClientById is returning a different client that was expected", client.equals(retrievedClient));
    }

    @Test
    public void testEditClient() throws Exception {
        System.out.println("JUnit Test - ClientJpaDAO - Edit client");

        Client client = new Client("test" + RandomStringUtils.random(10, true, false), "123qwe");
        auxiliaryPersistClient(client);

        client.setHashPassword("newPassword");
        bdDao.edit(client, client.getId());

        Client retrievedClient = (Client) bdDao.findById(client.getId());
        assertTrue("testFindClientById is returning a different client that was expected", client.equals(retrievedClient));

    }

    @Test
    public void testDestroy() throws Exception {
        System.out.println("JUnit Test - ClientJpaDAO - Delete client");

        int numberOfClients = bdDao.getEntityCount();
        Client client = new Client("test" + RandomStringUtils.random(10, true, false), "123qwe");
        auxiliaryPersistClient(client);

        bdDao.destroy(client.getId());

        Client retrievedClient = (Client) bdDao.findById(client.getId());
        thrown.expect(javax.persistence.EntityNotFoundException.class);
        assertNull("testDestroy is not deleting the client from the database", retrievedClient);
        int newNumberOfClients = bdDao.getEntityCount();
        assertEquals("testDestroy is not working correctly. Number of client rows is not the same", numberOfClients, newNumberOfClients);
    }

    @Test
    public void testGetHashPassword() throws Exception {
        System.out.println("JUnit Test - ClientJpaDAO - Get Hash Password from Client");

        String randomHashPassword = RandomStringUtils.random(20, true, true);
        String randomUsername = "test" + RandomStringUtils.random(10, true, false);
        Client client = new Client(randomUsername, randomHashPassword);
        auxiliaryPersistClient(client);

        assertEquals("testGetHashPassword not working correctly. Passwords don't match", bdDao.getHashPasswordForClient(client.getUsername()), randomHashPassword);
    }

    @Test
    public void testGetHasPasswordInvalidClient() throws Exception {
        System.out.println("JUnit Test - ClientJpaDAO - Get Hash Password from uniextant Client");
        
        String randomUsername = RandomStringUtils.random(10, true, false);
        thrown.expect(javax.persistence.NoResultException.class);
        bdDao.getHashPasswordForClient(randomUsername);
    }

    public void auxiliaryPersistClient(Client client) throws Exception {
        bdDao.create(client);
        assertNotNull("auxiliaryCreateClient is not working correctly. ID of created row is null", client.getId());
        toDelete.put(bdDao, (ID) client.getId());
    }
}
