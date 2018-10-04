package org.banco.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.banco.model.TarjetaCredito;

/**
 *
 * @author rjsan
 */
@Stateless
public class TarjetaCreditoFacade extends AbstractFacade<TarjetaCredito> {

    @PersistenceContext(unitName = "BancoTesisFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TarjetaCreditoFacade() {
        super(TarjetaCredito.class);
    }
    
       public TarjetaCredito findNumeroTDC(String numero) {
        TarjetaCredito tarjetaCredito = new TarjetaCredito();
        try {
            TypedQuery<TarjetaCredito> q = em.createNamedQuery("TarjetaCredito.findByNumero", TarjetaCredito.class);
            q.setParameter("numero", numero);
            tarjetaCredito = q.getSingleResult();
        } catch (NoResultException e) {
            System.err.println("No se encontro un tarjeta con ese numero: " + numero);
        }
        return tarjetaCredito;
} 
}
