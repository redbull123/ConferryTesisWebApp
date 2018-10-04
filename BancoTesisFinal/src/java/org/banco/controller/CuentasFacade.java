/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banco.controller;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.banco.model.Cuentas;

/**
 *
 * @author rjsan
 */
@Stateless
public class CuentasFacade extends AbstractFacade<Cuentas> {

    @PersistenceContext(unitName = "BancoTesisFinalPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuentasFacade() {
        super(Cuentas.class);
    }
}
