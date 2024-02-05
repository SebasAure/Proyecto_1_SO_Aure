/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Interfaces.VentanaSimulacion;

/**
 *
 * @author sebas
 */
public class DriveCN {
    
    public int guiones;
    public int escenarios;
    public int animaciones;
    public int doblajes;
    public int plottwists;
    public int capitulos;
    
    public DriveCN(){ 
        
        // Cantidad de partes producidas de cada tipo
        this.guiones = 0;
        this.escenarios = 0;
        this.animaciones = 0;
        this.doblajes = 0;
        this.plottwists = 0;
        this.capitulos = 0;
    }
    
    public void addPart(int tipo){ //añade al drive segun el tipo
        switch (tipo) {
            case 0: // Guion
                this.guiones += 1;
                Interfaces.VentanaSimulacion.cantidadGuionesCN.setText(Integer.toString(guiones));
                System.out.println("guiones disponibles CN:" + this.guiones);
                break;
            case 1: // Escenario
                this.escenarios += 1;
                Interfaces.VentanaSimulacion.cantidadEscenariosCN.setText(Integer.toString(escenarios));
                System.out.println("escenarios disponibles CN:" + this.escenarios);
                break;
            case 2: // Animacion
                this.animaciones += 1;
                Interfaces.VentanaSimulacion.cantidadAnimacionesCN.setText(Integer.toString(animaciones));
                System.out.println("animaciones disponibles CN:" + this.animaciones);
                break;
            case 3: // Doblaje
                this.doblajes += 1;
                Interfaces.VentanaSimulacion.cantidadDoblajesCN.setText(Integer.toString(doblajes));
                System.out.println("doblajes disponibles CN:" + this.doblajes);
                break;
            case 4: // PlotTwist
                this.plottwists += 1;
                Interfaces.VentanaSimulacion.cantidadPlotTwistsCN.setText(Integer.toString(plottwists));
                System.out.println("plot twists disponibles CN:" + this.plottwists);
                break;
            case 5: // Capitulo
                this.capitulos += 1;
                Interfaces.VentanaSimulacion.cantidadCapitulosCN.setText(Integer.toString(capitulos));
                System.out.println("capitulos disponibles CN:" + this.capitulos);
                break;
            default:
                break;
        }
    }
    
    public void takePart(int tipo){ //toma del drive segun el tipo
        // Para producir un capitulo de Cartoon Network se requieren 1 guion, 2 escenarios, 6 animaciones, 5 doblajes (y 1 plot twist en el caso dado) 
        switch (tipo) {
            case 0: // Guion
                this.guiones -= 1;
                Interfaces.VentanaSimulacion.cantidadGuionesCN.setText(Integer.toString(guiones));
                System.out.println("guiones disponibles CN:" + this.guiones);
                break;
            case 1: // Escenario
                this.escenarios -= 2;
                Interfaces.VentanaSimulacion.cantidadEscenariosCN.setText(Integer.toString(escenarios));
                System.out.println("escenarios disponibles CN:" + this.escenarios);
                break;
            case 2: // Animacion
                this.animaciones -= 6;
                Interfaces.VentanaSimulacion.cantidadAnimacionesCN.setText(Integer.toString(animaciones));
                System.out.println("animaciones disponibles CN:" + this.animaciones);
                break;
            case 3: // Doblaje
                this.doblajes -= 5;
                Interfaces.VentanaSimulacion.cantidadDoblajesCN.setText(Integer.toString(doblajes));
                System.out.println("doblajes disponibles CN:" + this.doblajes);
                break;
            case 4: // PlotTwist
                this.plottwists -= 1;
                Interfaces.VentanaSimulacion.cantidadPlotTwistsCN.setText(Integer.toString(plottwists));
                System.out.println("plot twists disponibles CN:" + this.plottwists);
                break;
            default:
                break;
        }
    }
}
