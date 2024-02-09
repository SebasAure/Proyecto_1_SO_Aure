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
public class EnsambladorSC extends Thread{
    
    private volatile boolean activo;
    private int tipo;
    private int salarioTotal;
    private int duracionDia;
    private int salario = 50;
    private int diasProduccion = 2;
    private int horasPagadas = 24;
    //Cantidad de partes necesarias para ensamblar un capitulo para Star Channel
    private int numGuiones = 2;
    private int numEscenarios = 3;
    private int numAnimaciones = 4;
    private int numDoblajes = 6;
    private int numPlotTwists = 5;
    
    private DriveSC drive;
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
    
    public EnsambladorSC(int tipo, int duracionDia, DriveSC drive, Semaphore mutex, Semaphore mutexGuiones, Semaphore driveDisponibleGuiones, Semaphore partesDisponiblesGuiones, Semaphore mutexEscenarios, Semaphore driveDisponibleEscenarios, Semaphore partesDisponiblesEscenarios, Semaphore mutexAnimaciones, Semaphore driveDisponibleAnimaciones, Semaphore partesDisponiblesAnimaciones, Semaphore mutexDoblajes, Semaphore driveDisponibleDoblajes, Semaphore partesDisponiblesDoblajes, Semaphore mutexPlotTwists, Semaphore driveDisponiblePlotTwists, Semaphore partesDisponiblesPlotTwists, JLabel salarioInterfaz){
        this.activo = true;
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
        while(this.activo) {
            
            try {
                // Produce cada 2 dias
                sleep(this.duracionDia * diasProduccion);
                obtenerSalario();
                ensamblar();
            } catch (InterruptedException ex) {
                Logger.getLogger(GuionistaCN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void obtenerSalario(){
        this.salarioTotal += this.salario*horasPagadas*diasProduccion;
        Interfaces.VentanaSimulacion.salarioEnsambladoresSC.setText(Integer.toString(salarioTotal));
    }
    
    public void ensamblar(){
        try {
            if (drive.contadorCapitulosEstandar == 6) {
                // Ensamblado capitulos con Plot Twist 
                
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
                
                // Plot Twists
                // Se toma la parte del capitulo para poder ensamblar   
                this.partesDisponiblesPlotTwists.acquire(numPlotTwists);
                // Exclusion mutua para que solo pueda ingresar un ensamblador a la vez
                this.mutexPlotTwists.acquire(); //wait
                // Seccion critica donde se anade una nueva parte del capitulo
                this.drive.takePart(4);//critica
                // Se libera el acceso a la seccion critica
                this.mutexPlotTwists.release();// signal
                // Se anade un espacio disponible para la produccion 
                this.driveDisponiblePlotTwists.release(numPlotTwists);
            
                this.mutex.acquire();
                this.drive.addPart(6);
                this.mutex.release();
                
                // Se reinicia el contador de Capitulos estandar, para permitir que se vuelvan a ensamblar capitulos Estandar 
                drive.contadorCapitulosEstandar = 0;
                
            } else {
                
                // Ensamblado capitulos normales
                
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
            }
            

            
            
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(EnsambladorSC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void parar(){
        this.activo = false;
    }
}
