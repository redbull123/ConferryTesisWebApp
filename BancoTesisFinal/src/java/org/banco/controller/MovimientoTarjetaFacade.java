package org.banco.controller;

import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.banco.model.MovimientoTarjeta;

/**
 *
 * @author rjsan
 */
@Stateless
public class MovimientoTarjetaFacade extends AbstractFacade<MovimientoTarjeta> {

    @PersistenceContext(unitName = "BancoTesisFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientoTarjetaFacade() {
        super(MovimientoTarjeta.class);
    }
    
    public int findByFecha( Date date, int id){
        
        int tdcId= 0;
                  
        try {
            TypedQuery<MovimientoTarjeta> q = em.createNamedQuery("MovimientoTarjeta.findByFecha", MovimientoTarjeta.class);
            q.setParameter("fecha", date);
            List<MovimientoTarjeta> moviList =  q.getResultList();
            for(MovimientoTarjeta mt: moviList){
                if(mt.getTarjetaCreditoId().getClienteId().getId()== id){
                    tdcId= mt.getId();
                }
            }
        } catch (NoResultException e) {
            System.err.println("No se encontro un movimiento con esta fecha: " + date);
        }
        return tdcId;        
    
    }
    
}
