/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banco.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.banco.model.MovimientosCuenta;

/**
 *
 * @author rjsan
 */
@Stateless
public class MovimientosCuentaFacade extends AbstractFacade<MovimientosCuenta> {

    @PersistenceContext(unitName = "BancoTesisFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientosCuentaFacade() {
        super(MovimientosCuenta.class);
    }
    
}
