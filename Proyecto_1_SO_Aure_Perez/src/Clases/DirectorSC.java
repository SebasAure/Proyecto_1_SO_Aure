/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.VentanaSimulacion;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author sebas
 */
public class DirectorSC extends Thread{
    private int tipo;
    private int salarioTotal;
    private int duracionDia;
    private int salario = 60;
    private double maxTiempo = 1.0;
    private double minTiempo = 0.0;
    private int horasPagadas = 24;
    private int tiempoEnvio = 1;

    // Mutex para revisar los dias del countdown
    private Semaphore mutexCountdown;
    private JLabel salarioInterfaz;
    
    public DirectorSC(int duracionDia, Semaphore mutexCountdown, JLabel salarioInterfaz){
        this.duracionDia = duracionDia;
        this.mutexCountdown = mutexCountdown;
        this.salarioInterfaz = salarioInterfaz;
    }
    @Override
    public void run(){
        while(Interfaces.VentanaSimulacion.diasDespachoSC > 0) {
            try {
                obtenerSalario();
                Random num_aleatorio= new Random();
                double superior=  (1f)*duracionDia;
                double inferior=  (0f)*duracionDia;
                superior= Math.round(superior);
                inferior= Math.round(inferior);
                int l_superior = (new Double(superior)).intValue();
                int l_inferior = (new Double(inferior)).intValue();
                int random= num_aleatorio.nextInt(l_superior - l_inferior + 1) + l_inferior;
                long periodo = (new Long(random)).longValue();
                long restante= duracionDia - periodo;
                mutexCountdown.acquire();
                sleep(restante);
                mutexCountdown.release();
                long periodo_real= periodo;
              
                while (Interfaces.VentanaSimulacion.diasDespachoSC > 0) {
                    // 34 minutos
                    double min_inferior= (7/720f)*duracionDia;
                    // 36 minutos
                    double min_superior= (1/40f)*duracionDia;
                  
                    min_inferior= Math.round(min_inferior);
                    min_superior= Math.round(min_superior);
                    int m_superior = (new Double(min_superior)).intValue();
                    int m_inferior = (new Double(min_inferior)).intValue();

                    int random_m= (int) (num_aleatorio.nextInt(m_superior-m_inferior+1) + min_inferior);
                    long sorpresa = (new Long(random_m)).longValue();
                    
                    sleep(sorpresa);
                    periodo_real-=sorpresa;
                    if (VentanaSimulacion.jugando==true) {
                        VentanaSimulacion.sueldoProjectManagerSC -= 100;
                        VentanaSimulacion.faltasSC ++;
                        VentanaSimulacion.faltasProjectManagerSC.setText(Integer.toString(VentanaSimulacion.faltasSC));
                        VentanaSimulacion.estadoDirectorSC.setText("Descubrio al PM");
                    }
                    else {
                        VentanaSimulacion.estadoDirectorSC.setText("Labores Admin");
                    }
                    random= num_aleatorio.nextInt(l_superior-l_inferior+1) + l_inferior;
                    periodo = new Long(random);
                    periodo_real= periodo;                 
                }
                if (Interfaces.VentanaSimulacion.diasDespachoSC == 0){
                    int gastos = (Integer.parseInt(Interfaces.VentanaSimulacion.salarioGuionistasSC.getText()) + 
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioDisenadoresSC.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioAnimadoresSC.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioActoresSC.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioPlotTwistsSC.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioEnsambladoresSC.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioProjectManagerSC.getText()));
                    Interfaces.VentanaSimulacion.gastosSC.setText(Integer.toString(gastos));
                    
                    int ganancias = (Integer.parseInt(Interfaces.VentanaSimulacion.cantidadCapitulosSC.getText())*350000)+
                                    (Integer.parseInt(Interfaces.VentanaSimulacion.cantidadCapitulosPTSC.getText())* 800000);
                    Interfaces.VentanaSimulacion.gananciasSC.setText(Integer.toString(ganancias));
                    
                    int neto = (Integer.parseInt(Interfaces.VentanaSimulacion.cantidadCapitulosSC.getText())*350000)+
                                    (Integer.parseInt(Interfaces.VentanaSimulacion.cantidadCapitulosPTSC.getText())* 800000)-
                                    (Integer.parseInt(Interfaces.VentanaSimulacion.salarioGuionistasSC.getText()) + 
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioDisenadoresSC.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioAnimadoresSC.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioActoresSC.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioPlotTwistsSC.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioEnsambladoresSC.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioProjectManagerSC.getText()));
                    Interfaces.VentanaSimulacion.netoSC.setText(Integer.toString(neto)); 
                } 
            } catch (InterruptedException ex) {
                Logger.getLogger(DirectorSC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void obtenerSalario(){
        this.salarioTotal += this.salario*horasPagadas;
        Interfaces.VentanaSimulacion.salarioDirectorSC.setText(Integer.toString(salarioTotal));
    }
}
