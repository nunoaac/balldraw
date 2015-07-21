package com.nunoaac.balldraw_console;

import com.nunoaac.balldraw_core.balldraw.domain.beans.BallDraw;
import com.nunoaac.balldraw_core.balldraw.domain.daos.BallDrawJpaDAO;
import com.nunoaac.balldraw_core.balldraw.domain.beans.Client;
import com.nunoaac.balldraw_core.balldraw.domain.daos.ClientJpaDAO;
import com.nunoaac.balldraw_core.balldraw.logic.algorithm.BallDrawAlgorithmInterface.DrawAlgorithm;
import com.nunoaac.balldraw_core.balldraw.logic.generator.AutomaticBallDraw;
import com.nunoaac.balldraw_core.balldraw.logic.generator.ManualBallDraw;
import com.nunoaac.balldraw_core.balldraw.tutorial.Customer;
import com.nunoaac.balldraw_core.balldraw.tutorial.CustomerJpaDAO;
import com.nunoaac.balldraw_core.balldraw.tutorial.Product;
import com.nunoaac.balldraw_core.balldraw.tutorial.ProductJpaDAO;
import com.nunoaac.balldraw_core.balldraw.tutorial.Purchase;
import com.nunoaac.balldraw_core.balldraw.tutorial.PurchaseJpaDAO;
import java.util.ArrayList;

import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Program {

    public static void main(String[] args) throws InterruptedException, Exception {

        testBallDrawDao();
        /*
       
        Client user = new Client("nunoaac", "123qwe");
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.nunoaac_balldraw_core_jar_1.0-SNAPSHOTPU");
        ClientJpaDAO jpac = new ClientJpaDAO();
        jpac.create(user);
        
        jpac.getHashPasswordForClient("nunoaac");
        */
        /*
        BallDraw newDraw;
        ManualBallDraw drawGen = new ManualBallDraw();
        newDraw = drawGen.getBallDraw(50, 20, DrawAlgorithm.SIMPLERANDOM);
        newDraw.setClient(user);
        BallDrawJpaDAO jpab = new BallDrawJpaDAO();
        jpab.create(newDraw);
        
        Client retrievedClient = jpac.findUser(user.getId());
        System.out.println(retrievedClient.toString());
        */
        //testBallDrawDao();
        //testDaoAndPatterns();
        //testManualAndAutoDraw();
    }

    public static void testManualAndAutoDraw() throws InterruptedException {
        System.out.println("START");

        BallDraw newDraw;

        ManualBallDraw manualBallDrawGenerator = new ManualBallDraw();
        newDraw = manualBallDrawGenerator.getBallDraw(50, 20, DrawAlgorithm.FISHERYATES);
        System.out.println(newDraw.toString());
        newDraw = manualBallDrawGenerator.getBallDraw(50, 20, DrawAlgorithm.SIMPLERANDOM);
        System.out.println(newDraw.toString());

        AutomaticBallDraw ballDrawGenerator = new AutomaticBallDraw();
        ballDrawGenerator.generateAutomaticDraws(true, DrawAlgorithm.SIMPLERANDOM, 50, 20);

        System.out.println("END");
    }

    public static void testDaoAndPatterns() throws Exception {
        Customer diogo = new Customer("Diogo", "Ferreira", 123);
        CustomerJpaDAO custController = new CustomerJpaDAO();
        custController.create(diogo);
        diogo.setLastName("F.");
        custController.edit(diogo, diogo.getId());
        custController.destroy(diogo.getId());
        Customer filipa = new Customer("Filipa", "Alpendre", 789);
        custController.create(filipa);
        custController.destroy(filipa.getId());

        Customer nuno = new Customer("Nuno", "Costa", 234);
        custController.create(nuno);

        Product product = new Product();
        product.setName("gelado");
        product.setPrice(5.00);
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.nunoaac_balldraw_core_jar_1.0-SNAPSHOTPU");
        ProductJpaDAO prodController = new ProductJpaDAO(emf);
        prodController.create(product);
        product.setPrice(6.01);
        prodController.edit(product);

        Purchase purchase = new Purchase();
        purchase.setCustomer(nuno);
        purchase.setPurchaseTimestamp(new java.util.Date());
        PurchaseJpaDAO purController = new PurchaseJpaDAO();
        purController.create(purchase);

        List<Product> productList = new ArrayList<Product>();
        productList.add(product);
        purchase.setProductList(productList);
        purController.edit(purchase, purchase.getId());

        List<Product> list = prodController.findProductEntities();
        List<Product> list2 = prodController.getPurchaseByIdWithCriteriaJPQL(list.get(0).getId());
        System.out.println(list2.get(0).toString());

        /*
        
         Customer nuno = new Customer("Nuno", "Costa", 456);
         custController.create(nuno);
        
         Product product = new Product();
         product.setName("gelasdo");
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.nunoaac_balldraw_core_jar_1.0-SNAPSHOTPU");
         ProductJpaDAO prodController = new ProductJpaDAO(emf);
         prodController.create(product);
         prodController.findProduct(1L);

         Purchase purchase = new Purchase();
         purchase.setName("bolachas");
         PurchaseJpaDAO purController = new PurchaseJpaDAO();
         purController.create(purchase);

         List<Purchase>purchaseList = purController.getPurchaseByNameWithCriteriaJPQL("bolachas");
         List<Product> productList = prodController.findProductEntities();
         System.out.println(purchaseList.size());
         */
    }
    
    public static void testBallDrawDao() throws Exception {
        
        Client cl = new Client("nunoaac", "123qwe");
        ClientJpaDAO clientDao = new ClientJpaDAO();
        clientDao.create(cl);
        
        BallDraw newDraw;
        ManualBallDraw drawGen = new ManualBallDraw();
        newDraw = drawGen.getBallDraw(50, 20, DrawAlgorithm.SIMPLERANDOM);
        
        List<BallDraw>oldList = cl.getDraws();
        oldList.add(newDraw);
        cl.setDraws(oldList);
        newDraw.setClient(cl);
        
        BallDrawJpaDAO jpac = new BallDrawJpaDAO();
        jpac.create(newDraw);
        
        System.out.println(newDraw.toString());
        
        BallDraw secondDraw = jpac.findBallDraw(newDraw.getUid());
        System.out.println(secondDraw.toString());
        
        clientDao.destroy(cl.getId());
    }

}
