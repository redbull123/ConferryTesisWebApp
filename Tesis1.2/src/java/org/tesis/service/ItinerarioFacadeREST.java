package org.tesis.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.tesis.model.Itinerario;

/**
 *
 * @author rjsan
 */
@Stateless
@Path("itinerario")
public class ItinerarioFacadeREST extends AbstractFacade<Itinerario> {
    
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
    @Path("findschedule/{itinerarios}/")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Itinerario> findItinerario(@PathParam("itinerarios") String date) {
        String changDate= null;
        List<Itinerario> scheduleList = findAll();
        List<Itinerario> hasSchedule= new ArrayList<>();
        for(Itinerario newSchedule : scheduleList){
            if(changeFormatOne(newSchedule.getFecha().toString()).equals(changeFormat(date))){
                  hasSchedule.add(newSchedule);}}
    return hasSchedule;}
    
    private String changeFormatOne(String time){
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
    private String changeFormat(String time){
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