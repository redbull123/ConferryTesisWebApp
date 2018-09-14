package org.tesis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.tesis.controller.BoletoFacade;
import org.tesis.model.Barco;
import org.tesis.model.Boleto;
import org.tesis.model.Confirmation;
import org.tesis.model.Itinerario;
import org.tesis.model.Ticket;
import org.tesis.model.TipoBoleto;
import org.tesis.model.Usuario;
import org.tesis.utils.Amount;
import org.tesis.view.BoletoController;

/**
 *
 * @author rjsan
 */
@Stateless
@Path("boleta")
public class BoletoFacadeREST extends AbstractFacade<Boleto> {

    static int flag = 0;
    static final int REPE = 90000;
    @PersistenceContext(unitName = "Tesis1.0PU")
    private EntityManager em;

    public BoletoFacadeREST() {
        super(Boleto.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Boleto entity) {
        System.out.println("Boleto: "+ entity.getId()+ "\n"+
                entity.getFecha()+ "\n"+entity.getItinerarioId() +"\n"+
                entity.getHora()+ "\n"+ 
                entity.getTipoBoletoId()+ "\n"+
                entity.getUsuarioId()+ "\n");
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Boleto entity) {
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
    public Boleto find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    
    @GET
    @Path("ticket/{id}/")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Boleto> ticket(@PathParam("id") Integer id) {  // Busca el barco con este id
        
        List<Boleto> ticket = findAll();
        List<Boleto> hasTicket = new ArrayList<>();
        
        for(Boleto newTicket: ticket){
            
            if(newTicket.getItinerarioId().getId() == id){
                hasTicket.add(newTicket);
            }
        }
        return hasTicket;
    }

    @GET
    @Path("capacidadPuesto/{id}/")
    @Produces({MediaType.APPLICATION_JSON})
    public Ticket capacidadPuesto(@PathParam("id") int id) {   // Apartir itinerario se consigue el barco y devuelve un objeto tipo barco
        Barco ship = new Barco();
        List<Boleto> boletoList = findAll();
        int persona = 0;
        int autos = 0;
        int carga = 0;
        int autobus = 0;
        int motos = 0;

        System.out.println("Declaro las variables");
        List<Boleto> ticketList = new ArrayList();
        for (Boleto rec : boletoList) {
            if (rec.getItinerarioId().getId() == id) {
                ticketList.add(rec);
            }
        }
        
        System.out.println("Lleno la lista de ticket");

        for (Boleto rec : ticketList) {
            if (rec.getTipoBoletoId().getId() == 4 || rec.getTipoBoletoId().getId() == 3) {
                persona++;
                System.out.println("cntidad de persona");
            }
            if (rec.getTipoBoletoId().getId() == 6) {
                autos++;
            }
            if (rec.getItinerarioId().getId() == 5) {
                carga++;
            }
            if (rec.getTipoBoletoId().getId() == 2) {
                autobus++;
            }
            if (rec.getTipoBoletoId().getId() == 1) {
                motos++;
            }
        }
        
        System.out.println("Conto la cantidad de boletos");
        
        ship = find(1).getItinerarioId().getBarcoId();

        System.out.println("se trajo barco");
//   Se debe agregar un IF para ship   
        Ticket ticketModel = new Ticket();
        ticketModel.setCapacidadPersonas(ship.getCapacidadPersonas() - persona);
        ticketModel.setCapacidadAutos(ship.getCapacidadAutos() - autos);
        ticketModel.setCapacidadCarga(ship.getCapacidadCarga() - carga);
        ticketModel.setCapacidadAutobus(ship.getCapacidadAutobus() - autobus);
        ticketModel.setCapacidadMotos(ship.getCapacidadMoto() - motos);

        System.out.println("construye el siguiente ticket"+ ticketModel);
        return ticketModel;
    }
    
    @POST
    @Path("confirmation/{id}/{uid}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Confirmation confirmationTicket(@PathParam("id") int id, Ticket cant, @PathParam("uid") int uid) {
        
        Confirmation confirmation = new Confirmation();
        Ticket amount = new Ticket();
        int flagAmount = 0;
        amount = amountTicket(id);

        if(cant.getCapacidadPersonas()<=amount.getCapacidadPersonas()){
            flagAmount++;
        }
        if(cant.getCapacidadAutos()<=amount.getCapacidadAutos()){
            flagAmount++;
        }
        if(cant.getCapacidadAutobus()<=amount.getCapacidadAutobus()){
            flagAmount++;
        }
        if(cant.getCapacidadMotos()<=amount.getCapacidadMotos()){
            flagAmount++;
        }
        if(cant.getCapacidadCarga()<=amount.getCapacidadCarga()){
            flagAmount++;
        }
        
        if(flagAmount==5){
            confirmation.setConfirmation(true);
        }        
        else if(flagAmount!=5){
            confirmation.setConfirmation(false);
        }

        holdTicket(confirmation.isConfirmation(), cant, uid, id);
        return confirmation;
        
    }

    
    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<Boleto> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Boleto> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
  
    public Ticket amountTicket(int id){
            
        Barco ship = new Barco();
        
        
           System.out.println("asklghaslkhfgbjaetrkyguknmsikhh");
       
           List<Boleto> boletoList = new ArrayList<>();
           System.out.println("no creado");
           boletoList = findAll();
           System.out.println("creado");
        
        for(Boleto usr: boletoList){
            
           System.out.println("todo los boletos son:"+ usr);
        
        }
        
        
        int persona = 0;
        int autos = 0;
        int carga = 0;
        int autobus = 0;
        int motos = 0;

        
            System.out.println("se crearon las variables");
        List<Boleto> ticketList = new ArrayList();
        for (Boleto rec : boletoList) {
            if (rec.getItinerarioId().getId() == id) {
                ticketList.add(rec);
            }
        }
        
            System.out.println("SE lleno la lista de ticket");

        for (Boleto rec : ticketList) {
            if (rec.getTipoBoletoId().getId() == 4 || rec.getTipoBoletoId().getId() == 3) {
                persona++;
                System.out.println("cntidad de persona"+ rec.getHora()+ "\n" + rec.getFecha());
            }
            if (rec.getTipoBoletoId().getId() == 6) {
                autos++;
            }
            if (rec.getItinerarioId().getId() == 5) {
                carga++;
            }
            if (rec.getTipoBoletoId().getId() == 2) {
                autobus++;
            }
            if (rec.getTipoBoletoId().getId() == 1) {
                motos++;
                System.out.println("\n" + "MOTOS: " + rec.getHora() + "\n" + rec.getFecha()
                + "\n" + rec.getTipoBoletoId().getId()
                + "\n" + rec.getUsuarioId().getId());

            }
        }
            System.out.println("se conto los boletos");
        ship = find(1).getItinerarioId().getBarcoId();

            System.out.println("se trajo barco");
            
//   Se debe agregar un IF para ship   
        Ticket ticketModel = new Ticket();
        ticketModel.setCapacidadPersonas(ship.getCapacidadPersonas() - persona);
        ticketModel.setCapacidadAutos(ship.getCapacidadAutos() - autos);
        ticketModel.setCapacidadCarga(ship.getCapacidadCarga() - carga);
        ticketModel.setCapacidadAutobus(ship.getCapacidadAutobus() - autobus);
        ticketModel.setCapacidadMotos(ship.getCapacidadMoto() - motos);

            System.out.println("esto devuelve"+ ticketModel);
        
            
            return ticketModel;
        
    }
    
    public void holdTicket(boolean confirmation, Ticket ticket, int uid, int id){
        
        
        if(confirmation){
        flag = 0;
        
        Date d = new Date();
        List<Boleto> perList = new ArrayList<>(); //Lista de boletos de personas.
        List<Boleto> vehList = new ArrayList<>(); //Lista de boletos de vehiculos.

        Timer timer = new Timer();
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                int algo = 2;
                int per = ticket.getCapacidadPersonas(); 
                int veh = ticket.getCapacidadAutos();
                if (flag == 0) {
                    System.out.println("Prueba de Timer");
                    while (per != 0) {
                        System.out.println("persona: "+ per);
                        Boleto boleto = new Boleto();
                            TipoBoleto tb = new TipoBoleto();
                            Itinerario itinerario = new Itinerario();
                            Usuario user= new Usuario();
                            user.setId(1);
                            itinerario.setId(id);
                            tb.setNombre("Adulto");
                            

                           
                            
                            boleto.setId(2);
                            boleto.setHora(d);
                            boleto.setFecha(d);
                            boleto.setItinerarioId(itinerario);
                            boleto.setTipoBoletoId(tb);
                            boleto.setUsuarioId(user);
                           System.out.println( boleto.getUsuarioId().getId());
                            
                            create(boleto);
                            
                        perList.add(boleto);
                        per--;
                        algo++;
                    }
                    while (veh != 0) {
                        //Debo agregar boletos en la base de datos, y ademas almacenar dentro de un arreglo tipo lista que contenga los boletos de vehiculos.    
                        Boleto boleto = new Boleto();
                        boleto.setFecha(d);
                        getBoletoFacade().create(boleto);
                        vehList.add(boleto);
                        veh--;
                    }
                } else {
                    //Quitar boletos agregados.
                }
                flag = 1;
            }
        };
        
        timer.schedule(tarea, 0, REPE);
        //(tarea, retraso, cada cuanto se repite)
        //1 y medio para repetir la tarea, al entrar debe  
        }
    }
        private BoletoFacade getBoletoFacade() {
        BoletoFacade bf = new BoletoFacade();
        return bf;
    }
}