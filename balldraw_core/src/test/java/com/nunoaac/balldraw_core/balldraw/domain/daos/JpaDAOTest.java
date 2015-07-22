package com.nunoaac.balldraw_core.balldraw.domain.daos;

import com.nunoaac.balldraw_core.balldraw.domain.beans.BallDraw;
import com.nunoaac.balldraw_core.balldraw.domain.beans.Client;
import com.nunoaac.balldraw_core.balldraw.logic.algorithm.BallDrawAlgorithmInterface.DrawAlgorithm;
import com.nunoaac.balldraw_core.balldraw.logic.generator.ManualBallDraw;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author support
 * @param <ID>
 */
public class JpaDAOTest<ID> {

    static BallDrawJpaDAO bdDrawDao;
    static ClientJpaDAO bdClientDao;
    HashMap<GenericDAO, ID> toDelete;

    public JpaDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        bdClientDao = new ClientJpaDAO();
        bdDrawDao = new BallDrawJpaDAO();
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

    public void auxiliaryPersistClient(Client client) throws Exception {
        bdClientDao.create(client);
        assertNotNull("auxiliaryCreateClient is not working correctly. ID of created row is null", client.getId());
        toDelete.put(bdClientDao, (ID) client.getId());
    }

    public void auxiliaryPersistBallDraw(BallDraw bdraw) throws Exception {
        int numberOfDraws = bdDrawDao.getEntityCount();
        bdDrawDao.create(bdraw);
        int newNumberOfDraws = bdDrawDao.getEntityCount();
        assertEquals("testCreateBallDraw is not working correctly. Number of draw rows has not incremented with a createDraw call", (numberOfDraws + 1), newNumberOfDraws);
        toDelete.put(bdDrawDao, (ID) bdraw.getUid());
    }

    public BallDraw auxiliaryRandomGenerateManualBallDraw(Client drawOwner) {

        Random r = new Random();
        int poolMinValue = 10;
        int poolMaxValue = 100;

        int pool = r.nextInt(poolMaxValue - poolMinValue) + poolMinValue;

        int sizeMinValue = 1;
        int sizeMaxValue = pool;

        int size = r.nextInt(sizeMaxValue - sizeMinValue) + sizeMinValue;

        List<DrawAlgorithm> algorithmPool = Collections.unmodifiableList(Arrays.asList(DrawAlgorithm.values()));
        DrawAlgorithm algorithm = algorithmPool.get(r.nextInt(algorithmPool.size()));
        
        BallDraw newDraw = auxiliaryGenerateManualBallDraw(pool, size, algorithm);
        if(drawOwner != null)
            drawOwner.addBallDraw(newDraw);
        
        return newDraw;
    }

    public BallDraw auxiliaryGenerateManualBallDraw(int pool, int size, DrawAlgorithm algorithm) {

        ManualBallDraw drawGen = new ManualBallDraw();
        BallDraw newDraw = drawGen.getBallDraw(pool, size, algorithm);
        return newDraw;
    }

}
