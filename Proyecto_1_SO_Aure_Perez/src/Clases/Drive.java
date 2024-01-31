/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author sebas
 */
public class Drive {
    
    public int guiones;
    public int escenarios;
    public int animaciones;
    public int doblajes;
    public int plottwists;
    public int capitulos;
    
    public Drive(){ 
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
        if (tipo == 0){
            this.guiones += 1;
            System.out.println("guiones disponibles:" + this.guiones);
        } else if (tipo == 1){
            this.escenarios += 1;
            System.out.println("escenarios disponibles:" + this.escenarios);
        } else if (tipo == 2){
            this.animaciones += 1;
            System.out.println("animaciones disponibles:" + this.animaciones);
        } else if (tipo == 3){
            this.doblajes += 5;
            System.out.println("doblajes disponibles:" + this.doblajes);
        } else if (tipo == 4){
            this.plottwists += 1;
            System.out.println("plot twists disponibles:" + this.plottwists);
        } else if (tipo == 5){
            this.capitulos += 1;
            System.out.println("capitulos disponibles:" + this.capitulos);
        }
    }
}
