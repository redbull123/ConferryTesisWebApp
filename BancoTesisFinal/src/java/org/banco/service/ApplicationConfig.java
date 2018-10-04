/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.banco.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author rjsan
 */
@javax.ws.rs.ApplicationPath("apibanco")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(org.banco.service.ClienteFacadeREST.class);
        resources.add(org.banco.service.CuentasCreditoFacadeREST.class);
        resources.add(org.banco.service.CuentasFacadeREST.class);
        resources.add(org.banco.service.MovimientoTarjetaFacadeREST.class);
        resources.add(org.banco.service.MovimientosCuentaFacadeREST.class);
        resources.add(org.banco.service.TarjetaCreditoFacadeREST.class);
        resources.add(org.banco.service.TarjetaDebitoFacadeREST.class);
    }
    
}
