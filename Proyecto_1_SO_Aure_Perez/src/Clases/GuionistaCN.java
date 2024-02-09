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
    
    private volatile boolean activo;
    private int tipo;
    private int salarioTotal;
    private int duracionDia;
    private int salario = 20;
    private int diasProduccion = 4;
    private int horasPagadas = 24;
    private DriveCN drive;
    // Mutex
    private Semaphore mutex;
    // Semaforo que controla el espacio disponible del Drive
    private Semaphore driveDisponible;
    // Semaforo que controla las partes disponibles para armar capitulos
    private Semaphore partesDisponibles;
    private JLabel salarioInterfaz;
    
    public GuionistaCN(int tipo, int duracionDia, DriveCN drive, Semaphore mutex, Semaphore driveDisponible, Semaphore partesDisponibles, JLabel salarioInterfaz){
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
        while(this.activo) {
            
            try {
                // Produce cada 4 dias
                sleep(this.duracionDia * diasProduccion);
                obtenerSalario();
                trabajar();
            } catch (InterruptedException ex) {
                Logger.getLogger(GuionistaCN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void obtenerSalario(){
        this.salarioTotal += this.salario*horasPagadas*diasProduccion;
        Interfaces.VentanaSimulacion.salarioGuionistasCN.setText(Integer.toString(salarioTotal));
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
            Logger.getLogger(GuionistaCN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void parar(){
        this.activo = false;
    }
}
