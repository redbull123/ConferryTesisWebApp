/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banco.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.banco.model.CuentasCredito;

/**
 *
 * @author rjsan
 */
@Stateless
public class CuentasCreditoFacade extends AbstractFacade<CuentasCredito> {

    @PersistenceContext(unitName = "BancoTesisFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentasCreditoFacade() {
        super(CuentasCredito.class);
    }
    
}
