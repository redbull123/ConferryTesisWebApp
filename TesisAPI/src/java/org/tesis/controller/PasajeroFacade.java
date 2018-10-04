package org.tesis.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.tesis.model.Pasajero;

/**
 *
 * @author rjsan
 */
@Stateless
public class PasajeroFacade extends AbstractFacade<Pasajero> {

    @PersistenceContext(unitName = "Tesis1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PasajeroFacade() {
        super(Pasajero.class);
    }
    
}
