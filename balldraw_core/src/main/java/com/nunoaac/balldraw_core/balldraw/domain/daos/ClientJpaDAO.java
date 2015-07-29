/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nunoaac.balldraw_core.balldraw.domain.daos;

import com.nunoaac.balldraw_core.balldraw.domain.beans.BallDraw;
import com.nunoaac.balldraw_core.balldraw.domain.beans.Client;
import com.nunoaac.balldraw_core.balldraw.domain.daos.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.NoResultException;

/**
 *
 * @author nunocosta
 */
public class ClientJpaDAO extends GenericDAO implements Serializable {

    public ClientJpaDAO() {
        super(Client.class);
    }

    public void destroy(Long id) throws NonexistentEntityException {

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Client cl = em.getReference(Client.class, id);
            if (cl == null) {
                throw new NonexistentEntityException("The Client with id " + id + " no longer exists.");
            }

            Query q3 = em.createNamedQuery("BallDraw.DeleteByClientId");
            q3.setParameter("clientId", id);
            int delRows = q3.executeUpdate();

            em.remove(cl);
            em.getTransaction().commit();

        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    public String getHashPasswordForClient(String username) throws NoResultException {

        Query q3 = getEntityManager().createQuery("SELECT c.hashPassword FROM Client c WHERE c.username = :userParam", Client.class);
        q3.setParameter("userParam", username);
        String password = (String) q3.getSingleResult();

        return password;
    }

}
