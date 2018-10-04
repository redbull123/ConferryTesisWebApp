/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tesis.utils;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author rjsan
 */
public class Temporizador {
    
        private static final int REPE = 10000;
        static int f=0;
        static Timer timer = new Timer();
public void callTimer(){  
    System.out.println("QELOQ");
    
    TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Entro en el Timerggggggggggggg");
                flag();
            }
        };
    timer.schedule(tarea, 0, REPE);
}

    public static void flag(){
        if(f!=0){
        timer.cancel();
            System.out.println("Deteniendo");
        }
        f++;
    }
}