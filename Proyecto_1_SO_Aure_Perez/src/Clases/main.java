/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;
import Interfaces.VentanaSimulacion;
import java.util.concurrent.Semaphore;
import Clases.ArchivoCSV;
/**
 *
 * @author sebas
 */
public class main {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        
        VentanaSimulacion simulacion = new VentanaSimulacion();
        simulacion.setVisible(true);
        
    }
    
}
