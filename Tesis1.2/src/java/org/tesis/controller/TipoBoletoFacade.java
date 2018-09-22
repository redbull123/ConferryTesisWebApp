package org.tesis.controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.tesis.model.TipoBoleto;
/**
 *
 * @author rjsan
 */
@Stateless
public class TipoBoletoFacade extends AbstractFacade<TipoBoleto>{

    @PersistenceContext(unitName = "Tesis1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    public TipoBoletoFacade() {
        super(TipoBoleto.class);
    }
    
    public int getIdentificador(String nombre) {
        TipoBoleto tipoBoleto = new TipoBoleto();
        try {
            TypedQuery<TipoBoleto> q = em.createNamedQuery("TipoBoleto.findByNombre", TipoBoleto.class);
            q.setParameter("nombre", nombre);
            tipoBoleto = q.getSingleResult();
        } catch (NoResultException e) {
            System.err.println("No se encontro un barco con el nombre: " + nombre);
        }
        return tipoBoleto.getId();
}  
}
