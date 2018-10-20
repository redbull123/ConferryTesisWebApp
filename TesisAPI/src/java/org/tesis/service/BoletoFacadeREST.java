package org.tesis.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private static int f = 0;
    private static Date date;
    int secondPassed = 0;
    Timer myTimer = new Timer();
    private static final int REPE = 10000;
    private static List<Boleto> perList = new ArrayList<>();
    private static List<Boleto> vehList = new ArrayList<>();
    private static List<Boleto> autList = new ArrayList<>();    
    private static List<Boleto> motList = new ArrayList<>();    
    private static List<Boleto> cargList = new ArrayList<>();
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
    }

    @GET
    @Path("compras/{fecha}/{usuario}/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Boleto> boletosByUsuario(@PathParam("fecha") String fecha, @PathParam("usuario")String usuario){
        List<Boleto> boletosList= new ArrayList<>();
        
        System.out.println("fecha es =" + fecha);
        System.out.println("Usuario es = "+ usuario);
        
        String outputPattern = "yyyy-MM-dd'T'HH:mm:ssZ";
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        try {
            Date date = outputFormat.parse(fecha);
        } catch (ParseException ex) {
            Logger.getLogger(BoletoFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Boleto> boletosFechaList= boletoFacade.findByFecha(date);
        
        for(Boleto boleto: boletosFechaList){
            if(boleto.getUsuarioId().getUsuario().equals(usuario)){
                boletosList.add(boleto);
            }
        }
        
        return boletosList;
    }
    
    @GET
    @Path("capacidadPuesto/{id}/")
    @Produces({MediaType.APPLICATION_JSON})
    public Ticket capacidadPuesto(@PathParam("id") int itinerarioId) {
    // Itinerario se consigue el barco y devuelve un objeto tipo barco
        int persona = 0;
        int autos = 0;
        int carga = 0;
        int autobus = 0;
        int motos = 0;
        Barco ship = itinerarioFacade.findShip(itinerarioId);
        List<Boleto> boletoList = boletoFacade.findAllBoletosByItinerario(itinerarioId);
        for (Boleto rec : boletoList) {
            if (rec.getTipoBoletoId().getNombre().equals("Adulto") || 
                rec.getTipoBoletoId().getNombre().equals("Niño") || 
                rec.getTipoBoletoId().getNombre().equals("Adulto Mayor")) {
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
        System.out.println("llego a confirmation");
        Confirmation confirmation = new Confirmation();
        Ticket ticket = new Ticket();
        List<Integer> list = new ArrayList<>();
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
            System.out.println("esta en el if");
            list= holdTicket( ticketCompra, uid, itinerarioId);
            confirmation.setList(list);

            for(int id: list){
                System.out.println("id"+id);
            }

        } else if (flagAmount != 5) {
            list = null;
        }
        //Paso para verificar que hay existencia de boletos para comprar todavia.
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
            if (rec.getTipoBoletoId().getNombre().equals("Adulto") || 
                rec.getTipoBoletoId().getNombre().equals("Niño") ||
                rec.getTipoBoletoId().getNombre().equals("Adulto Mayor")) {
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
    @Produces(MediaType.APPLICATION_JSON)
    private List<Integer> holdTicket( Ticket ticket, int usuarioId, int itinerarioId) {
        Date date = new Date();
        boolean prueba = true;
        List<Integer> idBoletosHolder = new ArrayList<>();
        
                        if(ticket.getCapacidadPersonas()!=0){
                            perList = holdForNombre("Adulto", ticket.getCapacidadPersonas(),usuarioId, itinerarioId);
                            System.out.println("perList"+perList.size());
                            for(Boleto boleto: perList){
                                idBoletosHolder.add(boleto.getId());
                            }
                        }
                        if(ticket.getCapacidadAutos()!=0){
                            System.out.println("Reteniendo Autos");
                            vehList = holdForNombre("Automovil", ticket.getCapacidadAutos(),usuarioId, itinerarioId);
                            System.out.println("vehList"+vehList.size());
                            for(Boleto boleto: vehList){
                                idBoletosHolder.add(boleto.getId());
                            }
                        }
                        if(ticket.getCapacidadAutobus()!=0){
                            autList = holdForNombre("Autobus", ticket.getCapacidadAutobus(),usuarioId, itinerarioId);
                            for(Boleto boleto: autList){
                                idBoletosHolder.add(boleto.getId());
                            }
                        }
                        if(ticket.getCapacidadMotos()!=0){
                            motList = holdForNombre("Moto", ticket.getCapacidadMotos(),usuarioId, itinerarioId);
                            for(Boleto boleto: motList){
                                idBoletosHolder.add(boleto.getId());
                            }
                        }
                        if(ticket.getCapacidadCarga()!=0){
                            cargList = holdForNombre("Carga", ticket.getCapacidadCarga(),usuarioId, itinerarioId);
                            for(Boleto boleto: cargList){
                                idBoletosHolder.add(boleto.getId());
                            }
                        }
                    
        System.out.println("esta en retener boleto");
        
    TimerTask task = new TimerTask(){
        @Override
        public void run(){
            if(secondPassed==240){
                myTimer.cancel();
                myTimer.purge();
                deleteBoleto(idBoletosHolder);
            }
        }
    };
        myTimer.scheduleAtFixedRate(task, 1000, 1000);      
        return idBoletosHolder;
    }
    
    private List<Boleto> holdForNombre(String nombre, int cantidadBoleto, int usuarioId, int itinerarioId){
        Date d = new Date();
        date=d;
        List<Boleto> bolList = new ArrayList<>();
        Boleto boleto = new Boleto();
        Itinerario itinerario = new Itinerario();
        TipoBoleto tipoBoleto = new TipoBoleto();
        Usuario user = new Usuario();
        int tipoBoletoId = ejbFacade.getIdentificador(nombre);
        
        while (cantidadBoleto != 0) {
            user.setId(usuarioId);
            itinerario.setId(itinerarioId);
            tipoBoleto.setId(tipoBoletoId);
            boleto.setHora(d);
            boleto.setFecha(d);
            boleto.setItinerarioId(itinerario);
            boleto.setTipoBoletoId(tipoBoleto);
            boleto.setUsuarioId(user);
            create(boleto);
            bolList.add(boleto);
            cantidadBoleto--;
        }
        List<Boleto> returnBoleto= new ArrayList<>();
        List<Boleto> listaBoletos = boletoFacade.findByHora(d);
        System.out.println("tamano boletos"+ listaBoletos.size());
        for(Boleto boletoByHora: listaBoletos){
            System.out.println("entra en el for");
            System.out.println("cedula servidor"+boletoByHora.getUsuarioId().getCi());
            System.out.println("cedula telefono"+user.getUsuario());
            if(Objects.equals(boletoByHora.getUsuarioId().getId(), user.getId())){
                System.out.println("entra en el if del for");
                returnBoleto.add(boletoByHora);
            }
        }
        
        return returnBoleto; 
    }
    private void deleteBoleto(List<Integer> idBoletos){
       
        for(int id: idBoletos){
            boletoFacade.remove(find(id));
        }
    }
           
    @POST
    @Path("movimiento/{numeroReferencia}/{usuarioId}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void creditoPago(@PathParam("numeroReferencia") boolean referencia, @PathParam("usuarioId") int usuarioId){
        boolean confir= false;
        if(referencia){
        myTimer.cancel();
        myTimer.purge();}
    }
    
}
