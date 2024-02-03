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
        // esto se refiere a la cantidad de
        // elementos producidos de cada tipo?
        this.guiones = 0;
        this.escenarios = 0;
        this.animaciones = 0;
        this.doblajes = 0;
        this.plottwists = 0;
        this.capitulos = 0;
    }
    
    public void addPart(int tipo){ //añade al drive segun el tipo
        switch (tipo) {
            case 0:
                this.guiones += 1;
                Interfaces.VentanaSimulacion.cantidadGuionesCN.setText(Integer.toString(guiones));
                System.out.println("guiones disponibles:" + this.guiones);
                break;
            case 1:
                this.escenarios += 1;
                System.out.println("escenarios disponibles:" + this.escenarios);
                break;
            case 2:
                this.animaciones += 1;
                System.out.println("animaciones disponibles:" + this.animaciones);
                break;
            case 3:
                this.doblajes += 5;
                System.out.println("doblajes disponibles:" + this.doblajes);
                break;
            case 4:
                this.plottwists += 1;
                System.out.println("plot twists disponibles:" + this.plottwists);
                break;
            case 5:
                this.capitulos += 1;
                System.out.println("capitulos disponibles:" + this.capitulos);
                break;
            default:
                break;
        }
    }
}
