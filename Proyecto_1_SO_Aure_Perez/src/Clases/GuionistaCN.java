/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author sebas
 */
public class GuionistaCN extends Thread{
    private String nombre;
    private int tipo;
    private int salarioTotal;
    private int duracionDia;
    private int salario = 20;
    private DriveCN drive;
    private float contador;
    private Semaphore mutex;
    private JLabel salarioInterfaz;
    
    public GuionistaCN(int tipo, int duracion, String nombre, DriveCN d, Semaphore m, JLabel salarioInterfaz){
        this.tipo = tipo;
        this.salarioTotal = 0;
        this.duracionDia = duracion;
        this.nombre = nombre;
        this.drive = d;
        this.contador = 0;
        this.mutex = m;
        this.salarioInterfaz = salarioInterfaz;
    }
    
    @Override
    public void run(){
        while(true) {
            
            try {
                obtenerSalario();
                trabajar();
                System.out.println("Trabajador: "+ this.nombre + " gana: "+this.salarioTotal+"$");
                sleep(this.duracionDia);
            } catch (InterruptedException ex) {
                Logger.getLogger(GuionistaCN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void obtenerSalario(){
        this.salarioTotal += this.salario*24;
        this.salarioInterfaz.setText(Integer.toString(this.salarioTotal));
    }
    
    public void trabajar(){
        this.contador += 0.34;
        if (this.contador >= 1){
            try {
                this.mutex.acquire(); //wait
                this.drive.addPart(this.tipo);//critica
                this.mutex.release();// signal
                this.contador = 0;
                
            } catch (InterruptedException ex) {
                Logger.getLogger(GuionistaCN.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
        
    }
}
