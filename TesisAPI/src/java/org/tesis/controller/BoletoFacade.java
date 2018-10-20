package org.tesis.controller;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.tesis.model.Boleto;

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
    
    public List<Boleto> findByFecha(Date fecha){
          List<Boleto> listBoleto = null;
        
        try {
            TypedQuery<Boleto> q = em.createNamedQuery("Boleto.findByFecha", Boleto.class);
            q.setParameter("fecha", fecha);
            listBoleto= q.getResultList();
        } catch (NoResultException e) {
            System.err.println("No se encontro boletos con fecha: " + fecha);
        }
        return listBoleto;  
    }
    public List<Boleto> findByHora(Date hora){
        List<Boleto> listBoleto = null;
        
        try {
            TypedQuery<Boleto> q = em.createNamedQuery("Boleto.findByHora", Boleto.class);
            q.setParameter("hora", hora);
            listBoleto= q.getResultList();
        } catch (NoResultException e) {
            System.err.println("No se encontro boletos con hora: " + hora);
        }
        return listBoleto;
    }
}
