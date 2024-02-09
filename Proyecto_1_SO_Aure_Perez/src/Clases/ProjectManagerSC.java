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
public class ProjectManagerSC extends Thread{
    
    private volatile boolean activo;
    private int tipo;
    private int salarioTotal;
    private int duracionDia;
    private int salario = 40;
    private int horasAnimeRevisando;
    // 30 minutos viendo anime en dias
    private double minutosAnime = 1.0/48;
    // 30 minutos revisando el proyecto en dias
    private double minutosRevisando = 1.0/48;
    private double horasContador = 1.0/3;
    private int horasPagadas = 24;
    // Mutex para cambiar los dias del countdown
    private Semaphore mutexCountdown;
    private JLabel salarioInterfaz;
    
    public ProjectManagerSC(int duracionDia, Semaphore mutexCountdown, JLabel salarioInterfaz){
        this.activo = true;
        this.horasAnimeRevisando = 0;
        this.duracionDia = duracionDia;
        this.mutexCountdown = mutexCountdown;
        this.salarioInterfaz = salarioInterfaz;
    }
    
    @Override
    public void run(){
        while(Interfaces.VentanaSimulacion.diasDespachoSC > 0) {
            
            try {
                // Primeras 16 horas (2/3 del dia) viendo anime (se repite el ver anime y 
                // revisar el proyecto 16 veces ya que hacer ambas acciones una vez equivale a 1 hora)
                while ((horasAnimeRevisando != 16) && (Interfaces.VentanaSimulacion.diasDespachoSC > 0)) {                    
                    // Ve anime
                    Interfaces.VentanaSimulacion.estadoProjectManagerSC.setText("Viendo anime");
                    sleep((long) (this.duracionDia*minutosAnime));
                    
                    // Ve revisa el proyecto
                    Interfaces.VentanaSimulacion.estadoProjectManagerSC.setText("Revisando el proyecto");
                    sleep((long) (this.duracionDia*minutosRevisando));
                    horasAnimeRevisando ++;
                }
 
                // Ultimas 8 horas (1/3 del dia) cambia el contador
                sleep((long) (this.duracionDia*horasContador));
                descontarDia();
                horasAnimeRevisando = 0;
                obtenerSalario();
                if (Interfaces.VentanaSimulacion.diasDespachoSC == 0){
                    Interfaces.VentanaSimulacion.agregarGuionistaSC.setEnabled(false);
                    Interfaces.VentanaSimulacion.agregarDisenadorSC.setEnabled(false);
                    Interfaces.VentanaSimulacion.agregarAnimadorSC.setEnabled(false);
                    Interfaces.VentanaSimulacion.agregarActorSC.setEnabled(false);
                    Interfaces.VentanaSimulacion.agregarPlotTwistSC.setEnabled(false);
                    Interfaces.VentanaSimulacion.agregarEnsambladorSC.setEnabled(false);
        
                    Interfaces.VentanaSimulacion.eliminarGuionistaSC.setEnabled(false);
                    Interfaces.VentanaSimulacion.eliminarDisenadorSC.setEnabled(false);
                    Interfaces.VentanaSimulacion.eliminarAnimadorSC.setEnabled(false);
                    Interfaces.VentanaSimulacion.eliminarActorSC.setEnabled(false);
                    Interfaces.VentanaSimulacion.eliminarPlotTwistSC.setEnabled(false);
                    Interfaces.VentanaSimulacion.eliminarEnsambladorSC.setEnabled(false);
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(ProjectManagerSC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void obtenerSalario(){
        
        this.salarioTotal += this.salario*horasPagadas;
        Interfaces.VentanaSimulacion.salarioProjectManagerSC.setText(Integer.toString(salarioTotal));
        
    }
    
    public void descontarDia(){
        try {
            // Accede al contador (seccion critica) y si quedan dias lo va bajando
            mutexCountdown.acquire();
            if (Interfaces.VentanaSimulacion.diasDespachoSC != 0) {
                Interfaces.VentanaSimulacion.diasDespachoSC --;
                Interfaces.VentanaSimulacion.diasLanzamientoSC.setText(Integer.toString(Interfaces.VentanaSimulacion.diasDespachoSC));
            }
            mutexCountdown.release();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ProjectManagerSC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
