/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tesis.utils;

import java.util.ArrayList;
import java.util.List;
import org.tesis.controller.BoletoFacade;
import org.tesis.model.Barco;
import org.tesis.model.Boleto;
import org.tesis.model.Ticket;
import org.tesis.view.BoletoController;

/**
 *
 * @author rjsan
 */
public class Amount {
   
            public Ticket amountTicket(int id){
            
                    System.out.println("HEREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");

        Barco ship = new Barco();
        
        
           System.out.println("asklghaslkhfgbjaetrkyguknmsikhh");
       
           List<Boleto> boletoList = new ArrayList<>();
           System.out.println("no creado");
           System.out.println("creado");
        
        for(Boleto usr: boletoList){
            
           System.out.println("todo los boletos son:"+ usr);
        
        }
        
        
        int persona = 0;
        int autos = 0;
        int carga = 0;
        int autobus = 0;
        int motos = 0;
//
//        
//            System.out.println("se crearon las variables");
//        List<Boleto> ticketList = new ArrayList();
//        for (Boleto rec : boletoList) {
//            if (rec.getItinerarioId().getId() == id) {
//                ticketList.add(rec);
//            }
//        }
//        
//            System.out.println("SE lleno la lista de ticket");
//
//        for (Boleto rec : ticketList) {
//            if (rec.getTipoBoletoId().getId() == 4 || rec.getTipoBoletoId().getId() == 3) {
//                persona++;
//                System.out.println("cntidad de persona");
//            }
//            if (rec.getTipoBoletoId().getId() == 6) {
//                autos++;
//            }
//            if (rec.getItinerarioId().getId() == 5) {
//                carga++;
//            }
//            if (rec.getTipoBoletoId().getId() == 2) {
//                autobus++;
//            }
//            if (rec.getTipoBoletoId().getId() == 1) {
//                motos++;
//            }
//        }
//            System.out.println("se conto los boletos");
//        ship = boletoFacade.find(1).getItinerarioId().getBarcoId();
//
//            System.out.println("se trajo barco");
////   Se debe agregar un IF para ship   
        Ticket ticketModel = new Ticket();
//        ticketModel.setCapacidadPersonas(ship.getCapacidadPersonas() - persona);
//        ticketModel.setCapacidadAutos(ship.getCapacidadAutos() - autos);
//        ticketModel.setCapacidadCarga(ship.getCapacidadCarga() - carga);
//        ticketModel.setCapacidadAutobus(ship.getCapacidadAutobus() - autobus);
//        ticketModel.setCapacidadMotos(ship.getCapacidadMoto() - motos);
//
//            System.out.println("esto devuelve"+ ticketModel);
        return ticketModel;
        
    }
    private List<Boleto> getBoletoFacade() {
        BoletoFacade bf = new BoletoFacade();
        return bf.findAll();
    }    
    
}
