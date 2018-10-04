package org.tesis.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.ejb.EJB;
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
import org.tesis.controller.BoletoFacade;
import org.tesis.controller.ItinerarioFacade;
import org.tesis.model.Barco;
import org.tesis.model.Boleto;
import org.tesis.model.Confirmation;
import org.tesis.model.Itinerario;
import org.tesis.model.Ticket;
import org.tesis.model.TipoBoleto;
import org.tesis.model.Usuario;

@Stateless
@Path("boleta")
public class BoletoFacadeREST extends AbstractFacade<Boleto> {

    @EJB
    private org.tesis.controller.TipoBoletoFacade ejbFacade;
    @EJB
    private ItinerarioFacade itinerarioFacade;

    private static int flag = 0;
    private static final int REPE = 90000;

    @EJB
    private BoletoFacade boletoFacade;

    @PersistenceContext(unitName = "Tesis1.0PU")
    private EntityManager em;

    public BoletoFacadeREST() {
        super(Boleto.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(Boleto entity) {
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
    public List<Boleto> ticket(@PathParam("id") Integer id) {

        return boletoFacade.findAllBoletosByItinerario(id);
//        
//        List<Boleto> boleto = findAll();
//        List<Boleto> hasBoleto = new ArrayList<>();
//
//        for (Boleto newTicket : boleto) {
//
//            if (newTicket.getItinerarioId().getId() == id) {
//                hasBoleto.add(newTicket);
//            }6
//        }
//        return hasBoleto;
    }

    @GET
    @Path("capacidadPuesto/{id}/")
    @Produces({MediaType.APPLICATION_JSON})
    public Ticket capacidadPuesto(@PathParam("id") int itinerarioId) {
// Apartir itinerario se consigue el barco y devuelve un objeto tipo barco
        int persona = 0;
        int autos = 0;
        int carga = 0;
        int autobus = 0;
        int motos = 0;
        Barco ship = itinerarioFacade.findShip(itinerarioId);
        List<Boleto> boletoList = boletoFacade.findAllBoletosByItinerario(itinerarioId);

        for (Boleto rec : boletoList) {
            if (rec.getTipoBoletoId().getNombre().equals("Adulto") || rec.getTipoBoletoId().getNombre().equals("Niño")) {
                persona++;
            }
            if (rec.getTipoBoletoId().getNombre().equals("Automovil")) {
                autos++;
            }
            if (rec.getTipoBoletoId().getNombre().equals("Carga")) {
                carga++;
            }
            if (rec.getTipoBoletoId().getNombre().equals("Autobus")) {
                autobus++;
            }
            if (rec.getTipoBoletoId().getNombre().equals("Moto")) {
                motos++;
            }
        }

        Ticket ticketModel = new Ticket();
        ticketModel.setCapacidadPersonas(ship.getCapacidadPersonas() - persona);
        ticketModel.setCapacidadAutos(ship.getCapacidadAutos() - autos);
        ticketModel.setCapacidadCarga(ship.getCapacidadCarga() - carga);
        ticketModel.setCapacidadAutobus(ship.getCapacidadAutobus() - autobus);
        ticketModel.setCapacidadMotos(ship.getCapacidadMoto() - motos);
        return ticketModel;
    }

    @POST
    @Path("confirmation/{id}/{uid}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Confirmation confirmationTicket(@PathParam("id") int itinerarioId, Ticket ticketCompra, @PathParam("uid") int uid) {

        Confirmation confirmation = new Confirmation();
        Ticket ticket = new Ticket();
        int flagAmount = 0;
        ticket = amountTicket(itinerarioId);
        
        if (ticketCompra.getCapacidadPersonas() <= ticket.getCapacidadPersonas()) {
            flagAmount++;
        }
        if (ticketCompra.getCapacidadAutos() <= ticket.getCapacidadAutos()) {
            flagAmount++;
        }
        if (ticketCompra.getCapacidadAutobus() <= ticket.getCapacidadAutobus()) {
            flagAmount++;
        }
        if (ticketCompra.getCapacidadMotos() <= ticket.getCapacidadMotos()) {
            flagAmount++;
        }
        if (ticketCompra.getCapacidadCarga() <= ticket.getCapacidadCarga()) {
            flagAmount++;
        }

        if (flagAmount == 5) {
            confirmation.setConfirmation(true);
        } else if (flagAmount != 5) {
            confirmation.setConfirmation(false);
        }

        holdTicket(confirmation.isConfirmation(), ticketCompra, uid, itinerarioId);
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

    private Ticket amountTicket(int itinerarioId) {
        Ticket ticket = new Ticket();
        int persona = 0;
        int autos = 0;
        int carga = 0;
        int autobus = 0;
        int motos = 0;
        Barco ship = itinerarioFacade.findShip(itinerarioId);
        List<Boleto> boletos = boletoFacade.findAllBoletosByItinerario(itinerarioId);

        for (Boleto rec : boletos) {
            if (rec.getTipoBoletoId().getNombre().equals("Adulto") || rec.getTipoBoletoId().getNombre().equals("Niño")) {
                persona++;
            }
            if (rec.getTipoBoletoId().getNombre().equals("Automovil")) {
                autos++;
            }
            if (rec.getTipoBoletoId().getNombre().equals("Carga")) {
                carga++;
            }
            if (rec.getTipoBoletoId().getNombre().equals("Autobus")) {
                autobus++;
            }
            if (rec.getTipoBoletoId().getNombre().equals("Moto")) {
                motos++;
            }
        }

        ticket.setCapacidadPersonas(ship.getCapacidadPersonas() - persona);
        ticket.setCapacidadAutos(ship.getCapacidadAutos() - autos);
        ticket.setCapacidadCarga(ship.getCapacidadCarga() - carga);
        ticket.setCapacidadAutobus(ship.getCapacidadAutobus() - autobus);
        ticket.setCapacidadMotos(ship.getCapacidadMoto() - motos);

        return ticket;
    }

    private void holdTicket(boolean confirmation, Ticket ticket, int usuarioId, int itinerarioId) {
 
        if(confirmation){
        if(ticket.getCapacidadPersonas()!=0){
            holdForNombre("Adulto", ticket.getCapacidadPersonas(),usuarioId, itinerarioId);
        }
        if(ticket.getCapacidadAutos()!=0){
            holdForNombre("Automovil", ticket.getCapacidadAutos(),usuarioId, itinerarioId);
        }
        if(ticket.getCapacidadAutobus()!=0){
            holdForNombre("Autobus", ticket.getCapacidadAutobus(),usuarioId, itinerarioId);
        }
        if(ticket.getCapacidadMotos()!=0){
            holdForNombre("Moto", ticket.getCapacidadMotos(),usuarioId, itinerarioId);
        }
        if(ticket.getCapacidadCarga()!=0){
            holdForNombre("Carga", ticket.getCapacidadCarga(),usuarioId, itinerarioId);
        }}
    }
    private void holdForNombre(String nombre, int cantidadBoleto, int usuarioId, int itinerarioId){
        Date d = new Date();
        List<Boleto> perList = new ArrayList<>();
        int algo = 2;
        Itinerario itinerario = new Itinerario();
        TipoBoleto tipoBoleto = new TipoBoleto();
        Usuario user = new Usuario();
        int tipoBoletoId = ejbFacade.getIdentificador(nombre);
        
        while (cantidadBoleto != 0) {
            Boleto boleto = new Boleto();
            user.setId(usuarioId);
            itinerario.setId(itinerarioId);
            tipoBoleto.setId(tipoBoletoId);
            boleto.setHora(d);
            boleto.setFecha(d);
            boleto.setItinerarioId(itinerario);
            boleto.setTipoBoletoId(tipoBoleto);
            boleto.setUsuarioId(user);
            create(boleto);
            perList.add(boleto);
            cantidadBoleto--;
            algo++;
        }
    }
}
