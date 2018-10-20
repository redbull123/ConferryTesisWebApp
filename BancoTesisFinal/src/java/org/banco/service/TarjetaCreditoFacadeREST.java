package org.banco.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import org.banco.controller.MovimientoTarjetaFacade;
import org.banco.controller.TarjetaCreditoFacade;
import org.banco.model.MovimientoCredito;
import org.banco.model.MovimientoTarjeta;
import org.banco.model.TarjetaCredito;

/**
 *
 * @author rjsan
 */
@Stateless
@Path("tarjetacredito")
public class TarjetaCreditoFacadeREST extends AbstractFacade<TarjetaCredito> {

    @EJB
    private MovimientoTarjetaFacade movimientoTarjetaFacade1;

    int secondPassed=0;
    Timer myTimer = new Timer();

    @EJB
    private MovimientoTarjetaFacade movimientoTarjetaFacade;

    @EJB
    private TarjetaCreditoFacade tarjetaCreditoFacade;

    @PersistenceContext(unitName = "BancoTesisFinalPU")
    private EntityManager em;

    public TarjetaCreditoFacadeREST() {
        super(TarjetaCredito.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(TarjetaCredito entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, TarjetaCredito entity) {
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
    public TarjetaCredito find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_JSON})
    public List<TarjetaCredito> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_JSON})
    public List<TarjetaCredito> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @POST
    @Path("/check/{monto}/{fecha}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Boolean checkPay(@PathParam("monto") double monto, @PathParam("fecha") String fecha,TarjetaCredito tarjetaCredito){
        System.out.println("LLEGO");
        
        boolean check= false;
        MovimientoTarjeta movimientoTarjeta = new MovimientoTarjeta();
        MovimientoCredito movimientoCredito = new MovimientoCredito();
        Date date = null;
        String str= null; 
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy");   
        SimpleDateFormat inputFormat = new SimpleDateFormat("MMM. dd, yyyy");
        System.out.println("llego a declarar las variables"+tarjetaCredito.getNumero());
        TarjetaCredito tarjetaCreditoFinded = tarjetaCreditoFacade.findNumeroTDC(tarjetaCredito.getNumero());
     
        System.out.println("devolvio"+tarjetaCreditoFinded.getNumero());
        System.out.println("fechasinf   "+fecha);
       /* try {
            date = inputFormat.parse(fecha);
            str = outputFormat.format(date);
        } catch (ParseException e) {
        }*/
        //System.out.println("fechaconf  "+);
         System.out.println("cambio el formato de fecha");
        if(tarjetaCreditoFinded.getNumero().equals(tarjetaCredito.getNumero())){
            System.out.println("comprobo el numero");
            System.out.println("fecha servidor"+changeFormat(tarjetaCreditoFinded.getFechaVencimiento())+"fecha del ususario");
            if(changeFormat(tarjetaCreditoFinded.getFechaVencimiento()).equals(fecha)){
                            System.out.println("comprobo fecha");
                            System.out.println("nombre"+tarjetaCreditoFinded.getClienteId().getNombre()+"y el otro"+tarjetaCredito.getClienteId().getNombre());
                if(tarjetaCreditoFinded.getClienteId().getNombre().equals(tarjetaCredito.getClienteId().getNombre())){
                    System.out.println("nombre y apellido"+tarjetaCreditoFinded.getClienteId().getApellido()+"y el otro"+tarjetaCredito.getClienteId().getApellido());
                    if(tarjetaCreditoFinded.getClienteId().getApellido().equals(tarjetaCredito.getClienteId().getApellido())){
                        System.out.println("apellido");
                        System.out.println("Marca del servidor ="+tarjetaCreditoFinded.getMarca()+"\n Marca de la app="+tarjetaCredito.getMarca());
                        if(tarjetaCreditoFinded.getMarca().equals(tarjetaCredito.getMarca())){
                            if(tarjetaCreditoFinded.getCvc() == tarjetaCredito.getCvc()){
                                if(tarjetaCreditoFinded.getSaldo()>monto){
                                  
                                    registroPago(tarjetaCreditoFinded, monto);
                                    check=true;
                                }
                                
                                else if(!(tarjetaCreditoFinded.getSaldo()>monto)){
            System.out.println("No tiene monto suficiente");
        }
                            }
                            else if(!(tarjetaCreditoFinded.getCvc() == tarjetaCredito.getCvc())){
            System.out.println("No coincide el codigo CVC");
        }
                        }
                        else if(!tarjetaCreditoFinded.getMarca().equals(tarjetaCredito.getMarca())){
            System.out.println("No conincide la marca");
        }
                    }
                    else if(!tarjetaCreditoFinded.getClienteId().getApellido().equals(tarjetaCredito.getClienteId().getApellido())){
            System.out.println("No coincide el apellido del tarjetahabiente");
        }
                }
                else if(!tarjetaCreditoFinded.getClienteId().getNombre().equals(tarjetaCredito.getClienteId().getNombre())){
            System.out.println("No coincide el nombre de la tarjeta habiente");
        }
            }
                    else if(!changeFormat(tarjetaCreditoFinded.getFechaVencimiento()).equals(fecha)){
            System.out.println("tarjeta Vencida");
        }
        }
        
        else if(!tarjetaCreditoFinded.getNumero().equals(tarjetaCredito.getNumero())){
            System.out.println("No se consiguio el numero de tarjeta");
        }
        
       
        return check;
                
    }
    
    @POST
    @Path("/start/")
    @Consumes({MediaType.APPLICATION_JSON})
    public Boolean starting(){
        Boolean started=true;
        
        TimerTask task = new TimerTask(){
        @Override
        public void run(){
            secondPassed++;
            System.out.println("segundos del banco= "+secondPassed);
            if(secondPassed==120){
                myTimer.cancel();
                myTimer.purge();
            }
        }
    };
        myTimer.scheduleAtFixedRate(task, 1000, 1000); 
        return started;
        
    }
        private String changeFormat(Date date){
        String outputPattern = "MMM dd, yyyy";
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        String str= null;
        str = outputFormat.format(date);
        return str;
    }
        
        
       private void registroPago(TarjetaCredito tarjetaCredito, double monto){
        
        Date mDate = new Date();

                                System.out.println("motno en string"+ monto);
                                System.out.println(monto);
                                System.out.println(tarjetaCredito.getSaldo()-monto);
                                tarjetaCredito.setSaldo(tarjetaCredito.getSaldo()-monto);
                                edit(tarjetaCredito);
                                   System.out.println("se edito");
                                   System.out.println("fondo en cuenta"+ tarjetaCredito.getSaldo());
                                MovimientoTarjeta movimientoTarjeta = new MovimientoTarjeta();
                                movimientoTarjeta.setMonto(monto);
                                movimientoTarjeta.setFecha(mDate);
                                movimientoTarjeta.setTipo(1);
                                movimientoTarjeta.setTarjetaCreditoId(tarjetaCredito);
                                movimientoTarjetaFacade.create(movimientoTarjeta);
                                System.out.println("se registro el moviemiento");
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

