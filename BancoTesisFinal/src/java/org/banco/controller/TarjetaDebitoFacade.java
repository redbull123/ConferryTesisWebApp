/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banco.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.banco.model.TarjetaDebito;

/**
 *
 * @author rjsan
 */
@Stateless
public class TarjetaDebitoFacade extends AbstractFacade<TarjetaDebito> {

    @PersistenceContext(unitName = "BancoTesisFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TarjetaDebitoFacade() {
        super(TarjetaDebito.class);
    }
    
    public TarjetaDebito findNumeroTDD(String numero){
        TarjetaDebito tarjetaDebito= new TarjetaDebito();
        try {
            TypedQuery<TarjetaDebito> q = em.createNamedQuery("TarjetaDebito.findByNumero", TarjetaDebito.class);
            q.setParameter("numero", numero);
            tarjetaDebito = q.getSingleResult();
        } catch (NoResultException e) {
            System.err.println("No se encontro un barco con el nombre: " + numero);
        }
        return tarjetaDebito;
    }
   
}
