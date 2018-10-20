
package org.banco.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.banco.controller.CuentasFacade;
import org.banco.controller.MovimientosCuentaFacade;
import org.banco.controller.TarjetaDebitoFacade;
import org.banco.model.Cuentas;
import org.banco.model.MovimientosCuenta;
import org.banco.model.TarjetaDebito;

/**
 *
 * @author rjsan
 */
@Stateless
@Path("tarjetadebito")
public class TarjetaDebitoFacadeREST extends AbstractFacade<TarjetaDebito> {

    @EJB
    private CuentasFacade cuentasFacade;

    @EJB
    private MovimientosCuentaFacade movimientosCuentaFacade;

    @EJB
    private TarjetaDebitoFacade tarjetaDebitoFacade;

    @PersistenceContext(unitName = "BancoTesisFinalPU")
    private EntityManager em;

    public TarjetaDebitoFacadeREST() {
        super(TarjetaDebito.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_JSON})
    public void create(TarjetaDebito entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, TarjetaDebito entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public TarjetaDebito find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<TarjetaDebito> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces( MediaType.APPLICATION_JSON)
    public List<TarjetaDebito> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("/check")
    public Boolean checkPay(@QueryParam("data") String datos){
        
        String monto="";
        String numero="";
        String vencimiento= "";
        String cvc="";
        String nombre="";
        String apellido="";
        String cedula="";
        String tipo="";
        String marca="";
        TarjetaDebito tarjetaDebito = new TarjetaDebito();
        Boolean check = false;
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy");           
        String inputPattern = "MMM. dd, yyyy";
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        int contador=0;
        System.out.println("datos en cadena="+ datos);
        for(int i=0; i<datos.length(); i++){
           
            if(datos.charAt(i)=='@'){
                contador++;
            }
            else if(datos.charAt(i)!='@'){
                if(contador==1){
                    numero =numero+datos.charAt(i);
                }
                if(contador==2){
                    vencimiento=vencimiento+datos.charAt(i);
                }
                if(contador==3){
                    cvc=cvc+datos.charAt(i);
                            }
                if(contador==4){
                    nombre=nombre+datos.charAt(i);
                }
                if(contador==5){
                    apellido=apellido+datos.charAt(i);
                }
                if(contador==6){
                    cedula=cedula+datos.charAt(i);
                }
                if(contador==7){
                    tipo=tipo+datos.charAt(i);
                }
                if(contador==8){
                    monto=monto+datos.charAt(i);
                }
                if(contador==9){
                    marca=marca+datos.charAt(i);
                }
            }
        }
        
//
//        Date date = null;
//        String str= null;
//        try {
//            date = inputFormat.parse(vencimiento);
//            str = outputFormat.format(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//      
                
        System.out.println("MONTO"+ monto);
        System.out.println("fecha desde la app estaaaaaaa "+vencimiento+"con el formtato"+
                "\n numero de tarjeta: "+ numero+
                " \n tarjetahabiente "+nombre+
                " \n marca"+ marca+
                " \n cvc"+cvc+
                " \n cedula"+ cedula+
                "\n tipo" +tipo);
               
        TarjetaDebito tarjetaDebitoFinded = tarjetaDebitoFacade.findNumeroTDD(numero);
        
        System.out.println("fecha recibida"+ changeFormat(tarjetaDebitoFinded.getFechaVencimiento())+
                "Nuemro de vuenta del servidor"+tarjetaDebitoFinded.getNumero()+
                "Marca del servidor"+tarjetaDebitoFinded.getMarca()+"nombre"+tarjetaDebitoFinded.getCuentasId().getClienteId().getNombre());
        
        if(tarjetaDebitoFinded.getNumero().equals(numero)){
            if(changeFormat(tarjetaDebitoFinded.getFechaVencimiento()).equals(vencimiento)){
                if(tarjetaDebitoFinded.getCodigoSeguridad()==Integer.parseInt(cvc)){
                if(tarjetaDebitoFinded.getCuentasId().getClienteId().getNombre().equals(nombre)){
                    if(tarjetaDebitoFinded.getCuentasId().getClienteId().getApellido().equals(apellido)){
                    if(tarjetaDebitoFinded.getCuentasId().getClienteId().getCi().equals(cedula)){
                        if(tarjetaDebitoFinded.getMarca().equals(marca)){
                            if(tarjetaDebitoFinded.getCuentasId().getTipo()== Integer.parseInt(tipo)){
                                //monto
                               if(tarjetaDebitoFinded.getCuentasId().getFondo()> Double.parseDouble(monto)){
                                check = true;  
                                registroPago(tarjetaDebitoFinded, monto);

                               }
                               else if(!(tarjetaDebitoFinded.getCuentasId().getFondo()>Integer.parseInt(monto))){
                                System.out.println("Fondos insuficientr");
                            }
                                
                            }
                            
                            else if(!(tarjetaDebitoFinded.getCuentasId().getTipo()== Integer.parseInt(tipo))){
                                System.out.println("no se consiguio la cuenta");
                            }
                        }
                        else if(!tarjetaDebitoFinded.getMarca().equals(marca)){
                            System.out.println("no se consiguio la tarjeta");
                        }
                    }
                    else if(!tarjetaDebitoFinded.getCuentasId().getClienteId().getCi().equals(cedula)){
                        System.out.println("No coincide las cedulas");
                    }}
                    else if(!tarjetaDebitoFinded.getCuentasId().getClienteId().getApellido().equals(apellido)){
                        System.out.println("No coincide el apeliido");
                    }
                }
                else if(!tarjetaDebitoFinded.getCuentasId().getClienteId().getNombre().equals(nombre)){
                    System.out.println("no coincide el nombre");
                }}
                else if(!(tarjetaDebitoFinded.getCodigoSeguridad()==Integer.parseInt(cvc))){
                                        System.out.println("no coincide el cvc");
                }
            }
            else if(!changeFormat(tarjetaDebitoFinded.getFechaVencimiento()).equals(vencimiento)){
                System.out.println("tarjeta vencida");
            }
        }
        else if(!tarjetaDebitoFinded.getNumero().equals(numero)){
            System.out.println("No se consiguio el numero");
        }
        
        
        System.out.println("se envio a la app"+ check);
        return check;
                
    }
    
    private void registroPago(TarjetaDebito tarjetaDebitoFinded, String monto){
        
        Date mDate = new Date();
                                Cuentas cuentas = new Cuentas();
                                cuentas = tarjetaDebitoFinded.getCuentasId();
                                System.out.println("motno en string"+ monto);
                                System.out.println(Double.parseDouble(monto));
                                System.out.println(cuentas.getFondo()-Double.parseDouble(monto));
                                cuentas.setFondo(cuentas.getFondo()-Double.parseDouble(monto));
                                cuentasFacade.edit(cuentas);
                                   System.out.println("se edito");
                                   System.out.println("fondo en cuenta"+ cuentas.getFondo());
                                MovimientosCuenta movimientosCuenta = new MovimientosCuenta();
                                movimientosCuenta.setMonto(Double.parseDouble(monto));
                                movimientosCuenta.setFecha(mDate);
                                movimientosCuenta.setTipo(1);
                                movimientosCuenta.setCuentasId(cuentas);
                                movimientosCuentaFacade.create(movimientosCuenta);
                                System.out.println("se registro el moviemiento");
    }

    private String changeFormat(Date date){
        String outputPattern = "MMM dd, yyyy";
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);
        String str= null;
        str = outputFormat.format(date);
        return str;
    }
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
