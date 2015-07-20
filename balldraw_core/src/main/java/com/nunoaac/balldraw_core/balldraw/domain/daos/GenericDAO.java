package com.nunoaac.balldraw_core.balldraw.domain.daos;

import com.nunoaac.balldraw_core.balldraw.domain.beans.Client;
import com.nunoaac.balldraw_core.balldraw.domain.daos.exceptions.NonexistentEntityException;
import com.nunoaac.balldraw_core.balldraw.domain.daos.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author nunocosta
 */
public abstract class GenericDAO<T, ID> {

    private EntityManagerFactory emf = null;
    private final Class<T> entityClass;

    public GenericDAO(Class<T> entityClass) {
        emf = Persistence.createEntityManagerFactory("com.nunoaac_balldraw_core_jar_1.0-SNAPSHOTPU");
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(T entity) throws Exception {
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
                if (findById(id) == null) {
                    throw new NonexistentEntityException("The " + entityClass.getName() + " with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ID id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            T returnedRow = em.getReference(entityClass, id);
            if (returnedRow == null) {
                throw new NonexistentEntityException("The " + entityClass.getSimpleName() + " with id " + id + " no longer exists.");
            }
            em.remove(returnedRow);
            em.getTransaction().commit();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public T findById(ID id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(entityClass, id);
        } finally {
            em.close();
        }
    }

    public int getEntityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Client> rt = cq.from(entityClass);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<T> findUserEntities() {
        return findEntities(true, -1, -1);
    }

    public List<T> findUserEntities(int maxResults, int firstResult) {
        return findEntities(false, maxResults, firstResult);
    }

    private List<T> findEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(entityClass));
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
}
