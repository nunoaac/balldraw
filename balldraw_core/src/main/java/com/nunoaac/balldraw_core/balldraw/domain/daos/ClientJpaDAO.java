/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nunoaac.balldraw_core.balldraw.domain.daos;

import com.nunoaac.balldraw_core.balldraw.domain.beans.Client;
import com.nunoaac.balldraw_core.balldraw.domain.daos.exceptions.NonexistentEntityException;
import com.nunoaac.balldraw_core.balldraw.domain.daos.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

/**
 *
 * @author nunocosta
 */
public class ClientJpaDAO extends GenericDAO implements Serializable {

    public ClientJpaDAO() {
        super(Client.class);
    }
    
    public String getHashPasswordForClient (String username) throws NoResultException {
           
        Query q3 = getEntityManager().createQuery("SELECT c.hashPassword FROM Client c WHERE c.username = :userParam", Client.class);
        q3.setParameter("userParam", username);
        String password = (String)q3.getSingleResult();
               
        return password;
    }
}
