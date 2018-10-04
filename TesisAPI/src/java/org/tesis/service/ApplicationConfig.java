package org.tesis.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author rjsan
 */
@javax.ws.rs.ApplicationPath("api")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(org.tesis.service.BarcoFacadeREST.class);
        resources.add(org.tesis.service.BoletoFacadeREST.class);
        resources.add(org.tesis.service.ItinerarioFacadeREST.class);
        resources.add(org.tesis.service.PasajeroFacadeREST.class);
        resources.add(org.tesis.service.RutaFacadeREST.class);
        resources.add(org.tesis.service.TipoBoletoFacadeREST.class);
        resources.add(org.tesis.service.UsuarioFacadeREST.class);
    }
    
}
