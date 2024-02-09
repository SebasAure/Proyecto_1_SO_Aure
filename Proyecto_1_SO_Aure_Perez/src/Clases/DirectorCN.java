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
public class DirectorCN extends Thread{
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
    
    public DirectorCN(int duracionDia, Semaphore mutexCountdown, JLabel salarioInterfaz){
        this.duracionDia = duracionDia;
        this.mutexCountdown = mutexCountdown;
        this.salarioInterfaz = salarioInterfaz;
    }
    @Override
    public void run(){
        while(Interfaces.VentanaSimulacion.diasDespachoCN > 0) {
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
              
                while (Interfaces.VentanaSimulacion.diasDespachoCN > 0) {
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
                        VentanaSimulacion.sueldoProjectManagerCN -= 100;
                        VentanaSimulacion.faltasCN ++;
                        VentanaSimulacion.faltasProjectManagerCN.setText(Integer.toString(VentanaSimulacion.faltasCN));
                        VentanaSimulacion.estadoDirectorCN.setText("Descubrio al PM");
                    }
                    else {
                        VentanaSimulacion.estadoDirectorCN.setText("Labores Admin");
                    }
                    random= num_aleatorio.nextInt(l_superior-l_inferior+1) + l_inferior;
                    periodo = new Long(random);
                    periodo_real= periodo;                 
                }
                if (Interfaces.VentanaSimulacion.diasDespachoCN == 0){
                    
                    int gastos = (Integer.parseInt(Interfaces.VentanaSimulacion.salarioGuionistasCN.getText()) + 
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioDisenadoresCN.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioAnimadoresCN.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioActoresCN.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioPlotTwistsCN.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioEnsambladoresCN.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioProjectManagerCN.getText()));
                    Interfaces.VentanaSimulacion.gastosCN.setText(Integer.toString(gastos));
                    
                    int ganancias = (Integer.parseInt(Interfaces.VentanaSimulacion.cantidadCapitulosCN.getText())*300000)+
                                    (Integer.parseInt(Interfaces.VentanaSimulacion.cantidadCapitulosPTCN.getText())* 650000);
                    Interfaces.VentanaSimulacion.gananciasCN.setText(Integer.toString(ganancias));
                    
                    int neto = (Integer.parseInt(Interfaces.VentanaSimulacion.cantidadCapitulosCN.getText())*300000)+
                                    (Integer.parseInt(Interfaces.VentanaSimulacion.cantidadCapitulosPTCN.getText())* 650000)-
                                    (Integer.parseInt(Interfaces.VentanaSimulacion.salarioGuionistasCN.getText()) + 
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioDisenadoresCN.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioAnimadoresCN.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioActoresCN.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioPlotTwistsCN.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioEnsambladoresCN.getText()) +
                                      Integer.parseInt(Interfaces.VentanaSimulacion.salarioProjectManagerCN.getText()));
                    Interfaces.VentanaSimulacion.netoCN.setText(Integer.toString(neto));   
                } 
            } catch (InterruptedException ex) {
                Logger.getLogger(DirectorCN.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void obtenerSalario(){
        this.salarioTotal += this.salario*horasPagadas;
        Interfaces.VentanaSimulacion.salarioDirectorCN.setText(Integer.toString(salarioTotal));
    }
}
