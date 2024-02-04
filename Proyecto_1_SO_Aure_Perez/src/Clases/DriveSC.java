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
public class DriveSC {
    
    public int guiones;
    public int escenarios;
    public int animaciones;
    public int doblajes;
    public int plottwists;
    public int capitulos;
    
    public DriveSC(){ 
        
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
                Interfaces.VentanaSimulacion.cantidadGuionesSC.setText(Integer.toString(guiones));
                System.out.println("guiones disponibles SC:" + this.guiones);
                break;
            case 1: // Escenario
                this.escenarios += 1; 
                Interfaces.VentanaSimulacion.cantidadEscenariosSC.setText(Integer.toString(escenarios));
                System.out.println("escenarios disponibles SC:" + this.escenarios);
                break;
            case 2: // Animacion
                this.animaciones += 1;
                Interfaces.VentanaSimulacion.cantidadAnimacionesSC.setText(Integer.toString(animaciones));
                System.out.println("animaciones disponibles SC:" + this.animaciones);
                break;
            case 3: // Doblaje
                this.doblajes += 1;
                Interfaces.VentanaSimulacion.cantidadDoblajesSC.setText(Integer.toString(doblajes));
                System.out.println("doblajes disponibles SC:" + this.doblajes);
                break;
            case 4: // PlotTwist
                this.plottwists += 1;
                Interfaces.VentanaSimulacion.cantidadPlotTwistsSC.setText(Integer.toString(plottwists));
                System.out.println("plot twists disponibles SC:" + this.plottwists);
                break;
            case 5: // Capitulo
                this.capitulos += 1;
                Interfaces.VentanaSimulacion.cantidadCapitulosSC.setText(Integer.toString(capitulos));
                System.out.println("capitulos disponibles SC:" + this.capitulos);
                break;
            default:
                break;
        }
    }
}
