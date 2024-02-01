/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author sebas
 */
public class Productor extends Thread{
    
    private String nombre; //por que privado?
    private int tipo; // quejesto
    private int duracionDia;
    private int contadorDias = 0;
    private DriveCN drive;
    private float salarioTotal;
    private float contador;
    private Semaphore mutex;
    private JLabel salario;
    
    
    private int salarioGuionistas = 20; 
    private int salarioTotalGuionistas;
    //private float contadorGuionistas;
    //private Semaphore mutexGuionistas;
    
    private int salarioDisenadores = 26; 
    private int salarioTotalDisenadores;
    //private float contadorDisenadores;
    //private Semaphore mutexDisenadores;
    
    private int salarioAnimadores = 40; 
    private int salarioTotalAnimadores;
    //private float contadorAnimadores;
    //private Semaphore mutexAnimadores;
    
    private int salarioActores = 16; 
    private int salarioTotalActores;
    //private float contadorActores;
    //private Semaphore mutexActores;
    
    private int salarioPlotTwist = 34; 
    private int salarioTotalPlotTwist;
    //private float contadorPlotTwist;
    //private Semaphore mutexPlotTwist;
    
    private int salarioEnsambladores = 50; 
    private int salarioTotalEnsambladores;
    //private float contadorEnsambladores;
    //private Semaphore mutexEnsambladores;
    
    // Constructor 
    public Productor(int tipo, int duracion, String nombre, DriveCN d, Semaphore m, JLabel salario){
        this.tipo = tipo;
        this.salarioTotal = 0;
        this.duracionDia = duracion;
        this.nombre = nombre;
        this.drive = d;
        this.contador = 0;
        this.mutex = m;
        this.salario = salario;
    }
    
    @Override
    public void run(){
        while (true) {            
            
            try {
                contadorDias ++;
                obtenerSalario(tipo);
                //trabajar();
                System.out.println("Productor: " + this.nombre + " gana: " + this.salario.getText() + "$");
                System.out.println("Dias: " + this.contadorDias);
                sleep(this.duracionDia);
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void obtenerSalario(int tipo){
        switch (tipo) {
            case 0:
                this.salarioTotalGuionistas += this.salarioGuionistas*24; // por que por 24 (creo que representa un dia no?)
                this.salario.setText(Integer.toString(this.salarioTotalGuionistas));
                break;
            case 1:
                this.salarioTotalDisenadores += this.salarioDisenadores*24;
                this.salario.setText(Integer.toString(this.salarioTotalDisenadores));
                break;
            case 2:
                this.salarioTotalAnimadores += this.salarioAnimadores*24;
                this.salario.setText(Integer.toString(this.salarioTotalAnimadores));
                break;
            case 3:
                this.salarioTotalActores += this.salarioActores*24;
                this.salario.setText(Integer.toString(this.salarioTotalActores));
                break;
            case 4:
                this.salarioTotalPlotTwist += this.salarioPlotTwist*24;
                this.salario.setText(Integer.toString(this.salarioTotalPlotTwist));
                break;
            case 5:
                this.salarioTotalEnsambladores += this.salarioEnsambladores*24;
                this.salario.setText(Integer.toString(this.salarioTotalEnsambladores));
                break;
            default:
                break;
        }
        
    }
    
    public void trabajar(){
        this.contador += 0.34; //quejeste 0.34
        if (this.contador >= 1){
            try {
                this.mutex.acquire(); //wait
                this.drive.addPart(tipo);//critica
                this.mutex.release();// signal
                this.contador = 0;
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
