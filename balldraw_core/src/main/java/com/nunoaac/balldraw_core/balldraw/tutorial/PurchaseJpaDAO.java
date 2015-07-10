/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nunoaac.balldraw_core.balldraw.tutorial;

import com.nunoaac.balldraw_core.balldraw.tutorial.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author support
 */
public class PurchaseJpaDAO extends GenericJpaDAO implements Serializable {

    public PurchaseJpaDAO() {
        super(PurchaseJpaDAO.class);
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Purchase purchase;
            try {
                purchase = em.getReference(Purchase.class, id);
                purchase.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The purchase with id " + id + " no longer exists.", enfe);
            }
            em.remove(purchase);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Purchase> findPurchaseEntities() {
        return findPurchaseEntities(true, -1, -1);
    }

    public List<Purchase> findPurchaseEntities(int maxResults, int firstResult) {
        return findPurchaseEntities(false, maxResults, firstResult);
    }

    private List<Purchase> findPurchaseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Purchase.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Purchase findPurchase(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Purchase.class, id);
        } finally {
            em.close();
        }
    }

    public int getPurchaseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Purchase> rt = cq.from(Purchase.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
