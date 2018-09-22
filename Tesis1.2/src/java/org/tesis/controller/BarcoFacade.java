package org.tesis.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.tesis.model.Barco;

/**
 *
 * @author rjsan
 */
@Stateless
public class BarcoFacade extends AbstractFacade<Barco> {

    @PersistenceContext(unitName = "Tesis1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BarcoFacade() {
        super(Barco.class);
    }
       
    public Barco findByName(String name){
        Barco barco = null;
        
        try {
            TypedQuery<Barco> q = em.createNamedQuery("Barco.findByNombre", Barco.class);
            q.setParameter("nombre", name);
            barco = q.getSingleResult();
        } catch (NoResultException e) {
            System.err.println("No se encontro un barco con el nombre: " + name);
        }
        return barco;        
    }
}
