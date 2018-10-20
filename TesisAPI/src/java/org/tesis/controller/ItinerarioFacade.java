package org.tesis.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.tesis.model.Barco;
import org.tesis.model.Itinerario;

@Stateless
public class ItinerarioFacade extends AbstractFacade<Itinerario> {

    @PersistenceContext(unitName = "Tesis1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItinerarioFacade() {
        super(Itinerario.class);
    }
    public Barco findShip(int id){        
        Itinerario it = find(id);
        return it.getBarcoId();
    }
    
}
