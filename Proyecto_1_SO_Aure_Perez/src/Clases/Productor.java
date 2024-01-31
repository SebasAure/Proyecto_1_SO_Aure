/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sebas
 */
public class Productor extends Thread{
    
    private String nombre; //por que privado?
    private int tipo; // quejesto
    private int duracionDia;
    private Drive drive;
    private float salarioTotal;
    private float contador;
    private Semaphore mutex;
    
    private int salarioGuionistas = 20; 
    private float salarioTotalGuionistas;
    //private float contadorGuionistas;
    //private Semaphore mutexGuionistas;
    
    private int salarioDisenadores = 26; 
    private float salarioTotalDisenadores;
    //private float contadorDisenadores;
    //private Semaphore mutexDisenadores;
    
    private int salarioAnimadores = 40; 
    private float salarioTotalAnimadores;
    //private float contadorAnimadores;
    //private Semaphore mutexAnimadores;
    
    private int salarioActores = 16; 
    private float salarioTotalActores;
    //private float contadorActores;
    //private Semaphore mutexActores;
    
    private int salarioPlotTwist = 34; 
    private float salarioTotalPlotTwist;
    //private float contadorPlotTwist;
    //private Semaphore mutexPlotTwist;
    
    private int salarioEnsambladores = 50; 
    private float salarioTotalEnsambladores;
    //private float contadorEnsambladores;
    //private Semaphore mutexEnsambladores;
    
    // Constructor 
    public Productor(int tipo, int duracion, String nombre, Drive d, Semaphore m){
        this.tipo = tipo;
        this.salarioTotal = 0;
        this.duracionDia = duracion;
        this.nombre = nombre;
        this.drive = d;
        this.contador = 0;
        this.mutex = m;
    }
    
    @Override
    public void run(){
        while (true) {            
            
            try {
                obtenerSalario(tipo);
                trabajar();
                System.out.println("Productor: " + this.nombre + " gana: " + this.salarioTotal + "$");
                sleep(this.duracionDia);
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void obtenerSalario(int tipo){
        if (tipo == 0){
            this.salarioTotalGuionistas += this.salarioGuionistas*24; // por que por 24 (creo que representa un dia no?)
        } else if (tipo == 1){
            this.salarioTotalDisenadores += this.salarioDisenadores*24; 
        } else if (tipo == 2){
            this.salarioTotalAnimadores += this.salarioAnimadores*24;
        } else if (tipo == 3){
            this.salarioTotalActores += this.salarioActores*24;
        } else if (tipo == 4){
            this.salarioTotalPlotTwist += this.salarioPlotTwist*24;
        } else if (tipo == 5){
            this.salarioTotalEnsambladores += this.salarioEnsambladores*24;
        }
        
    }
    
    public void trabajar(){
        this.contador += 0.34; //quejeste 0.34
        if (this.contador >= 1){
            try {
                this.mutex.acquire(); //wait
                this.drive.addPart(this.tipo);//critica
                this.mutex.release();// signal
                this.contador = 0;
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
