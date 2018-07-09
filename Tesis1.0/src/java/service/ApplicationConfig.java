/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author rjsan
 */
@javax.ws.rs.ApplicationPath("webresources")
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
        resources.add(service.BarcoFacadeREST.class);
        resources.add(service.BoletoFacadeREST.class);
        resources.add(service.ItinerarioFacadeREST.class);
        resources.add(service.PasajeroFacadeREST.class);
        resources.add(service.RutaFacadeREST.class);
        resources.add(service.TipoBoletoFacadeREST.class);
        resources.add(service.UsuarioFacadeREST.class);
    }
    
}
