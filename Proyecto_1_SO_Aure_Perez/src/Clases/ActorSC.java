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
public class ActorSC extends Thread{
    
    private volatile boolean activo;
    private int tipo;
    private int salarioTotal;
    private int duracionDia;
    private int salario = 16;
    private double diasProduccion = 1.0/3;
    private int horasPagadas = 24;
    private DriveSC drive;
    // Mutex
    private Semaphore mutex;
    // Semaforo que controla el espacio disponible del Drive
    private Semaphore driveDisponible;
    // Semaforo que controla las partes disponibles para armar capitulos
    private Semaphore partesDisponibles;
    private JLabel salarioInterfaz;
    
    public ActorSC(int tipo, int duracionDia, DriveSC drive, Semaphore mutex, Semaphore driveDisponible, Semaphore partesDisponibles, JLabel salarioInterfaz){
        this.activo = true;
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
        while(this.activo && (Interfaces.VentanaSimulacion.diasDespachoSC > 0)) {
            
            try {
                // Produce cada dia (pero como produce 3 al dia es lo mismo que produzca 1 parte cada tercio del dia)
                sleep((long) (this.duracionDia*diasProduccion));
                obtenerSalario();
                trabajar();
            } catch (InterruptedException ex) {
                Logger.getLogger(ActorSC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void obtenerSalario(){
        this.salarioTotal += this.salario*horasPagadas*diasProduccion;
        Interfaces.VentanaSimulacion.salarioActoresSC.setText(Integer.toString(salarioTotal));
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
            Logger.getLogger(ActorSC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void parar(){
        this.activo = false;
    }
}
