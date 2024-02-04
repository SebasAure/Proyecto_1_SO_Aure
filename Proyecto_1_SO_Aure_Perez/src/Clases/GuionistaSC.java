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
public class GuionistaSC extends Thread{
    
    private int tipo;
    private int salarioTotal;
    private int duracionDia;
    private int salario = 20;
    private DriveSC drive;
    // Mutex
    private Semaphore mutex;
    // Semaforo que controla el espacio disponible del Drive
    private Semaphore driveDisponible;
    // Semaforo que controla las partes disponibles para armar capitulos
    private Semaphore partesDisponibles;
    private JLabel salarioInterfaz;
    
    public GuionistaSC(int tipo, int duracionDia, DriveSC drive, Semaphore mutex, Semaphore driveDisponible, Semaphore partesDisponibles, JLabel salarioInterfaz){
        this.tipo = tipo;
        this.salarioTotal = 0;
        this.duracionDia = duracionDia;
        this.drive = drive;
        this.mutex = mutex;
        this.driveDisponible = driveDisponible;
        this.partesDisponibles = partesDisponibles;
        this.salarioInterfaz = salarioInterfaz;
    }
    
    @Override
    public void run(){
        while(true) {
            
            try {
                // Produce cada 4 dias
                sleep(this.duracionDia * 4);
                obtenerSalario();
                trabajar();
            } catch (InterruptedException ex) {
                Logger.getLogger(GuionistaSC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void obtenerSalario(){
        this.salarioTotal += this.salario*24;
    }
    
    public void trabajar(){
        try {
            // Se verifica si hay espacio disponible para producir   
            this.driveDisponible.acquire();
            // Exclusion mutua para que solo pueda ingresar un trabajador a la vez
            this.mutex.acquire(); //wait
            // Seccion critica donde se anade una nueva parte del capitulo
            this.drive.addPart(this.tipo);//critica
            // Se libera el acceso a la seccion critica
            this.mutex.release();// signal
            // Se anade la parte del capitulo para poder ser ensamblada posteriormente
            this.partesDisponibles.release();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(GuionistaSC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
