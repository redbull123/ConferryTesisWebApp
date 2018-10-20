package org.tesis.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.tesis.model.Usuario;
import org.tesis.utils.JpaUtil;

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
      public Usuario findByUsuario(String username) {
        Usuario user = null;
        try {
            String query = "FROM Usuario user WHERE user.usuario = :username";
            TypedQuery<Usuario> q = getEntityManager().createQuery(query, Usuario.class);
            q.setParameter("username", username);
            user = q.getSingleResult();
        } catch (NoResultException e) {
            System.err.println("Usuario " + username + ", no encontrado.");
        }
        return user;
    }
     public boolean findByUsuarioname(String username) {
        Usuario user = null;
        boolean r = false;
        try {
            String query = "FROM Usuario user WHERE user.usuario = :username";
            TypedQuery<Usuario> q = getEntityManager().createQuery(query, Usuario.class);
            q.setParameter("username", username);
            user = q.getSingleResult();
        } catch (NoResultException e) {
            System.err.println("Usuario " + username + " not found");
        }
        if(user != null){
            r = true;
        }
        return r;
    }
     
     public boolean findByCI(int ci){
         boolean r = false;
         Usuario user = null;
         try{
             TypedQuery<Usuario> q = em.createNamedQuery("Usuario.findByCi", Usuario.class);
            q.setParameter("ci", ci);
            user = q.getSingleResult();
         }catch (NoResultException e) {
            System.err.println("No se encontro un ususario con la cedula: " + ci);
     }
         if(user != null){
             r = true;
         }
         return r;
     }
     
     public void creando(Usuario us){
     create(us);
     }
}

