package org.tesis.controller;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.tesis.model.Boleto;

/**
 *
 * @author rjsan
 */
@Stateless
public class BoletoFacade extends AbstractFacade<Boleto> {

    @PersistenceContext(unitName = "Tesis1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BoletoFacade() {
        super(Boleto.class);
    }
    
    public List<Boleto> findAllBoletosByItinerario(int itinerarioId) {
        List<Boleto> boletos = null;
        try {
            String query = "FROM Boleto b WHERE b.itinerarioId.id = :itinerarioId";
            Query q = em.createQuery(query);
            q.setParameter("itinerarioId", itinerarioId);
            boletos = q.getResultList();
        } catch (Exception e) {
            System.err.println("Error buscando los boletos del Itinerario: " + itinerarioId);
        }
        return boletos;
    }
}
