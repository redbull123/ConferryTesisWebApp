/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tesis.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.tesis.model.Usuario;
import org.tesis.utils.JpaUtil;

/**
 *
 * @author rjsan
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "Tesis1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        if (null == em) {
            em = JpaUtil.getEMF().createEntityManager();
        }
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
     public Usuario findByUsuarioname(String username) {
        Usuario user = null;
        try {
            String query = "FROM Usuario user WHERE user.usuario = :username";
            TypedQuery<Usuario> q = getEntityManager().createQuery(query, Usuario.class);
            q.setParameter("username", username);
            user = q.getSingleResult();
        } catch (NoResultException e) {
            System.err.println("Usuario " + username + " not found");
        }

        return user;
    }
}
