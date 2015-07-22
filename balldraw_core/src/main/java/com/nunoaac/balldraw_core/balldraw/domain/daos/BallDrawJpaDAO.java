/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nunoaac.balldraw_core.balldraw.domain.daos;

import com.nunoaac.balldraw_core.balldraw.domain.beans.BallDraw;
import com.nunoaac.balldraw_core.balldraw.domain.daos.exceptions.NonexistentEntityException;
import com.nunoaac.balldraw_core.balldraw.domain.daos.exceptions.PreexistingEntityException;
import com.nunoaac.balldraw_core.balldraw.tutorial.GenericJpaDAO;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *S
 * @author nunocosta
 */
public class BallDrawJpaDAO extends GenericDAO implements Serializable {

    public BallDrawJpaDAO() {
        super(BallDraw.class);
    }

    /**
    public void create(BallDraw ballDraw) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ballDraw);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBallDraw(ballDraw.getUid()) != null) {
                throw new PreexistingEntityException("BallDraw " + ballDraw + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
*/
    
    public void edit(BallDraw ballDraw) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ballDraw = em.merge(ballDraw);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = ballDraw.getUid();
                if (findBallDraw(id) == null) {
                    throw new NonexistentEntityException("The ballDraw with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
/*
    public void destroy(UUID id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BallDraw ballDraw;
            try {
                ballDraw = em.getReference(BallDraw.class, id);
                ballDraw.getUid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ballDraw with id " + id + " no longer exists.", enfe);
            }
            em.remove(ballDraw);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
*/
    public List<BallDraw> findBallDrawEntities() {
        return findBallDrawEntities(true, -1, -1);
    }

    public List<BallDraw> findBallDrawEntities(int maxResults, int firstResult) {
        return findBallDrawEntities(false, maxResults, firstResult);
    }

    private List<BallDraw> findBallDrawEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BallDraw.class));
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

    public BallDraw findBallDraw(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BallDraw.class, id);
        } finally {
            em.close();
        }
    }

    public int getBallDrawCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BallDraw> rt = cq.from(BallDraw.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<BallDraw> findBallDrawsFromClientByUsername (String username) throws NoResultException {
        
        TypedQuery<BallDraw> q3 = getEntityManager().createQuery("SELECT b FROM BallDraw b WHERE b.client.username = :userParam", BallDraw.class);
        q3.setParameter("userParam", username);
        
        return q3.getResultList();
    }
    
    public List<BallDraw> findBallDrawsFromClientById (Long client_id) throws NoResultException {
        
        TypedQuery<BallDraw> q3 = getEntityManager().createQuery("SELECT b FROM BallDraw b WHERE b.client.id = :userParam", BallDraw.class);
        q3.setParameter("userParam", client_id);
        
        return q3.getResultList();
    }
    
}
