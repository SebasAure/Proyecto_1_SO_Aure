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
public class EnsambladorCN extends Thread{
    
    private int tipo;
    private int salarioTotal;
    private int duracionDia;
    private int salario = 50;
    //Cantidad de partes necesarias para ensamblar un capitulo para Cartoon Network
    private int numGuiones = 1;
    private int numEscenarios = 2;
    private int numAnimaciones = 6;
    private int numDoblajes = 5;
    
    private DriveCN drive;
    // Mutex para el ensamblado de capitulos
    private static Semaphore mutex;
    // Guiones
    private static Semaphore mutexGuiones;
    private static Semaphore driveDisponibleGuiones;
    private static Semaphore partesDisponiblesGuiones;
    // Escenarios
    private static Semaphore mutexEscenarios;
    private static Semaphore driveDisponibleEscenarios;
    private static Semaphore partesDisponiblesEscenarios;
    // Animaciones
    private static Semaphore mutexAnimaciones;
    private static Semaphore driveDisponibleAnimaciones;
    private static Semaphore partesDisponiblesAnimaciones;
    // Doblajes
    private static Semaphore mutexDoblajes;
    private static Semaphore driveDisponibleDoblajes;
    private static Semaphore partesDisponiblesDoblajes;
    // Plot Twists
    private static Semaphore mutexPlotTwists;
    private static Semaphore driveDisponiblePlotTwists;
    private static Semaphore partesDisponiblesPlotTwists;
    private JLabel salarioInterfaz;
    
    public EnsambladorCN(int tipo, int duracionDia, DriveCN drive, Semaphore mutex, Semaphore mutexGuiones, Semaphore driveDisponibleGuiones, Semaphore partesDisponiblesGuiones, Semaphore mutexEscenarios, Semaphore driveDisponibleEscenarios, Semaphore partesDisponiblesEscenarios, Semaphore mutexAnimaciones, Semaphore driveDisponibleAnimaciones, Semaphore partesDisponiblesAnimaciones, Semaphore mutexDoblajes, Semaphore driveDisponibleDoblajes, Semaphore partesDisponiblesDoblajes, Semaphore mutexPlotTwists, Semaphore driveDisponiblePlotTwists, Semaphore partesDisponiblesPlotTwists, JLabel salarioInterfaz){
        this.tipo = tipo;
        this.salarioTotal = 0;
        this.duracionDia = duracionDia;
        this.drive = drive;
        this.mutex = mutex;
        this.mutexGuiones = mutexGuiones;
        this.driveDisponibleGuiones = driveDisponibleGuiones;
        this.partesDisponiblesGuiones = partesDisponiblesGuiones;
        this.mutexEscenarios = mutexEscenarios;
        this.driveDisponibleEscenarios = driveDisponibleEscenarios;
        this.partesDisponiblesEscenarios = partesDisponiblesEscenarios;
        this.mutexAnimaciones = mutexAnimaciones;
        this.driveDisponibleAnimaciones = driveDisponibleAnimaciones;
        this.partesDisponiblesAnimaciones = partesDisponiblesAnimaciones;
        this.mutexDoblajes = mutexDoblajes;
        this.driveDisponibleDoblajes = driveDisponibleDoblajes;
        this.partesDisponiblesDoblajes = partesDisponiblesDoblajes;
        this.mutexPlotTwists = mutexPlotTwists;
        this.driveDisponiblePlotTwists = driveDisponiblePlotTwists;
        this.partesDisponiblesPlotTwists = partesDisponiblesPlotTwists;
        this.salarioInterfaz = salarioInterfaz;
    }
    
    @Override
    public void run(){
        while(true) {
            
            try {
                // Produce cada 2 dias
                sleep(this.duracionDia * 2);
                obtenerSalario();
                ensamblar();
            } catch (InterruptedException ex) {
                Logger.getLogger(GuionistaCN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void obtenerSalario(){
        this.salarioTotal += this.salario*24;
    }
    
    public void ensamblar(){
        try {
            // Guiones
            // Se toma la parte del capitulo para poder ensamblar   
            this.partesDisponiblesGuiones.acquire(numGuiones);
            // Exclusion mutua para que solo pueda ingresar un ensamblador a la vez
            this.mutexGuiones.acquire(); //wait
            // Seccion critica donde se anade una nueva parte del capitulo
            this.drive.takePart(0);//critica
            // Se libera el acceso a la seccion critica
            this.mutexGuiones.release();// signal
            // Se anade un espacio disponible para la produccion 
            this.driveDisponibleGuiones.release(numGuiones);
            
            // Escenarios
            // Se toma la parte del capitulo para poder ensamblar   
            this.partesDisponiblesEscenarios.acquire(numEscenarios);
            // Exclusion mutua para que solo pueda ingresar un ensamblador a la vez
            this.mutexEscenarios.acquire(); //wait
            // Seccion critica donde se anade una nueva parte del capitulo
            this.drive.takePart(1);//critica
            // Se libera el acceso a la seccion critica
            this.mutexEscenarios.release();// signal
            // Se anade un espacio disponible para la produccion 
            this.driveDisponibleEscenarios.release(numEscenarios);
            
            // Animaciones
            // Se toma la parte del capitulo para poder ensamblar   
            this.partesDisponiblesAnimaciones.acquire(numAnimaciones);
            // Exclusion mutua para que solo pueda ingresar un ensamblador a la vez
            this.mutexAnimaciones.acquire(); //wait
            // Seccion critica donde se anade una nueva parte del capitulo
            this.drive.takePart(2);//critica
            // Se libera el acceso a la seccion critica
            this.mutexAnimaciones.release();// signal
            // Se anade un espacio disponible para la produccion 
            this.driveDisponibleAnimaciones.release(numAnimaciones);
            
            // Doblajes
            // Se toma la parte del capitulo para poder ensamblar   
            this.partesDisponiblesDoblajes.acquire(numDoblajes);
            // Exclusion mutua para que solo pueda ingresar un ensamblador a la vez
            this.mutexDoblajes.acquire(); //wait
            // Seccion critica donde se anade una nueva parte del capitulo
            this.drive.takePart(3);//critica
            // Se libera el acceso a la seccion critica
            this.mutexDoblajes.release();// signal
            // Se anade un espacio disponible para la produccion 
            this.driveDisponibleDoblajes.release(numDoblajes);
            
            this.mutex.acquire();
            this.drive.addPart(this.tipo);
            this.mutex.release();

            
            
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(EnsambladorCN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
