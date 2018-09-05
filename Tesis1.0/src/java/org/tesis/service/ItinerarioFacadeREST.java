package org.tesis.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.tesis.model.Barco;
import org.tesis.model.Boleto;
import org.tesis.model.Itinerario;
import org.tesis.model.Ticket;
import org.tesis.utils.SendMailUsingAuthentication;

/**
 *
 * @author rjsan
 */
@Stateless
@Path("itinerario")
public class ItinerarioFacadeREST extends AbstractFacade<Itinerario> {
    
        private static final String[] emailList = {"endri.a.13@gmail.com"};

    @PersistenceContext(unitName = "Tesis1.0PU")
    private EntityManager em;

    public ItinerarioFacadeREST() {
        super(Itinerario.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Itinerario entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Itinerario entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Itinerario find(@PathParam("id") Integer id) {
        return super.find(id);}
    
    @GET
    @Path("capacidadPuesto/{id}/")
    @Produces({MediaType.APPLICATION_JSON})
    public Ticket capacidadPuesto(@PathParam("id") Integer id) {   // Apartir itinerario se consigue el barco y devuelve un objeto tipo barco
        
        Itinerario schedule = super.find(id);
        Barco ship = new Barco();
        BoletoFacadeREST ticket = new BoletoFacadeREST();
        
        List<Boleto> tit = new ArrayList();
        
        int persona=0;
        int autos=0;
        int carga=0;
        int autobus=0;
        int motos=0;
        
          ship = schedule.getBarcoId();
        
        Ticket avaible = new Ticket();
        
        tit=ticket.findAll();
        List<Boleto> listTicket = new ArrayList();
        
        for(Boleto rec : tit){
            
            if(rec.getItinerarioId().getId()== id){
                listTicket.add(rec);
            }
        }
        
        for(Boleto rec : listTicket){
            if(rec.getTipoBoletoId().getId() ==1){
                persona++;
                System.out.println("Cantidad de PERSONAS= "+ persona);
            }
            if(rec.getTipoBoletoId().getId() ==3){
                autos++;
            }
            if(rec.getItinerarioId().getId() == 4){
                carga++;
            }
            if(rec.getTipoBoletoId().getId()==5){
                autobus++;
            }
            if(rec.getTipoBoletoId().getId()==6){
                motos++;
            }
        }
     
     avaible.setCapacidadPersonas(ship.getCapacidadPersonas()-persona);
     avaible.setCapacidadAutos(ship.getCapacidadAutos() - autos);
     avaible.setCapacidadCarga(ship.getCapacidadCarga() -carga);
     avaible.setCapacidadAutobus(ship.getCapacidadAutobus() - autobus);
     avaible.setCapacidadMotos(ship.getCapacidadMoto() - motos);
        
        return avaible;
    }
    
    
    @GET
    @Path("findschedule/{itinerarios}/")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Itinerario> findItinerario(@PathParam("itinerarios") String date) {
        String changDate= null;
        List<Itinerario> scheduleList = findAll();
        List<Itinerario> hasSchedule= new ArrayList<>();
        for(Itinerario newSchedule : scheduleList){
            System.out.println("primera"+ changeFormatOne(newSchedule.getFecha().toString()));
            System.out.println("segundo"+ changeFormat(date));
            if(changeFormatOne(newSchedule.getFecha().toString()).equals(changeFormat(date))){
                  hasSchedule.add(newSchedule);}}
        
        for(Itinerario newl : hasSchedule){
                   
        System.out.println("esto imprime"+ newl.getFecha());

    }
        
        
        try {
            SendMailUsingAuthentication.main(emailList);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasSchedule;}
    
    public String changeFormatOne(String time){
        String inputPattern = "EEE MMM d HH:mm:ss z yyyy";
        String outputPattern = "MMM d, yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str= null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }
    public String changeFormat(String time){
        String inputPattern = "yyyy-MM-dd'T'HH:mm:ssZ";
        String outputPattern = "MMM d, yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String str= null;
        try {
            date = inputFormat.parse(time);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Itinerario> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Itinerario> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
