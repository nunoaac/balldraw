/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nunoaac.balldraw_core.balldraw.tutorial;

import com.nunoaac.balldraw_core.balldraw.tutorial.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Persistence;

/**
 *
 * @author support
 * @param <T>
 * @param <ID>
 * @param <K>
 */
public abstract class GenericJpaDAO<T, ID, K> {

    private EntityManagerFactory emf = null;
    private EntityManager em;
    private final Class<T> entityClass;

    public GenericJpaDAO(Class<T> entityClass) {
        this.entityClass = entityClass;

        emf = Persistence.createEntityManagerFactory("com.nunoaac_balldraw_core_jar_1.0-SNAPSHOTPU");
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(T entity) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(T entity, ID id) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            entity = em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                if (findById(entity, id) == null) {
                    throw new NonexistentEntityException("The purchase with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public T findById(T entity, ID id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }
    
    public List<T>getRowByFieldJPQL(T entity, K field, String jpqlName) {
        return null;
    }

}
