package org.tesis.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.tesis.controller.BoletoFacade;
import org.tesis.controller.PasajeroFacade;
import org.tesis.model.Boleto;
import org.tesis.model.Confirmation;
import org.tesis.model.Pasajero;

@Stateless
@Path("pasajero")
public class PasajeroFacadeREST extends AbstractFacade<Pasajero> {

    @EJB
    private BoletoFacade boletoFacade;

    @EJB
    private PasajeroFacade pasajeroFacade;

    @PersistenceContext(unitName = "Tesis1.0PU")
    private EntityManager em;

    public PasajeroFacadeREST() {
        super(Pasajero.class);
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Pasajero find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @POST
    @Path("putBoleto/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Boolean putBoleto(@PathParam("id") String id, List<Pasajero> pasajerosList){
        boolean verify = false;
        System.out.println("Llegooo al servidor");
        List<Integer> listId = new ArrayList<>(); 
        List<Integer> listIdNR = new ArrayList<>();
        String var = new String();
        List<Boleto> boletosEncontrados = new ArrayList<>();
        System.out.println("esto tiene id "+id);
        if(id != null){
            System.out.println("antes del primer for del if"+ id.length());
            for(int i=0; i<id.length(); i++){
                if(id.charAt(i)!='@'){
                    var= var+id.charAt(i);
                }
                else if(id.charAt(i)=='@'){
                    listId.add(Integer.parseInt(var));
                    var=new String();
                }
            }
            
            Set<Integer> hs = new HashSet<>();
            hs.addAll(listId);
            listId.clear();
            listId.addAll(hs);
            
                System.out.println("antes del segundo for del if");
            for(int x: listId){
                System.out.println("id "+x);
                boletosEncontrados.add(boletoFacade.find(x));
            }  
            System.out.println("Tamano "+boletosEncontrados.get(1).getTipoBoletoId().getNombre());
        }
            System.out.println("antes de segundo if");
            if(boletosEncontrados.size()>0 && pasajerosList.size()>0){
                System.out.println("en el segunfo if");
                String var1;
                String var2;
        int x =0;

                    for(Boleto boleto: boletosEncontrados){     
                    System.out.println("nombre servidor1"+pasajerosList.get(x).getBoletoId().getTipoBoletoId().getNombre());
                   
                    var1 = pasajerosList.get(x).getBoletoId().getTipoBoletoId().getNombre();
                    var2=boleto.getTipoBoletoId().getNombre();
                    
                    if(var1.equals(var2)){
                        pasajerosList.get(x).setBoletoId(boleto);
                                    pasajeroFacade.create(pasajerosList.get(x));
                                                verify = true;
                    }
                    else{
                        System.out.println("no entro en el if");
                    }
                    x++;
                }
            }
        
        return verify;
    }
    
    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Pasajero> findAll() {
        return super.findAll();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
