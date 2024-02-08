/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Clases.ActorCN;
import Clases.ActorSC;
import Clases.AnimadorCN;
import Clases.AnimadorSC;
import Clases.ArchivoCSV;
import Clases.DisenadorCN;
import Clases.DisenadorSC;
import Clases.DriveCN;
import Clases.DriveSC;
import Clases.EnsambladorCN;
import Clases.EnsambladorSC;
import Clases.GuionistaCN;
import Clases.GuionistaSC;
import Clases.PlotTwistCN;
import Clases.PlotTwistSC;
import Clases.Productor;
import java.util.concurrent.Semaphore;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
/**
 *
 * @author sebas
 */
public class VentanaSimulacion extends javax.swing.JFrame {

    
    //Parametros generales
    public static int duracionDia;
    public static int diasDespacho;
    public static int capacidadGuiones;
    public static int capacidadEscenarios;
    public static int capacidadAnimaciones;
    public static int capacidadDoblajes;
    public static int capacidadPlotTwists;
    public static DriveCN driveCN;
    public static DriveSC driveSC;
    
    // Semaforos Cartoon Network
    // Guiones
    private static Semaphore mutexGuionesCN;
    private static Semaphore driveDisponibleGuionesCN;
    private static Semaphore partesDisponiblesGuionesCN;
    // Escenarios
    private static Semaphore mutexEscenariosCN;
    private static Semaphore driveDisponibleEscenariosCN;
    private static Semaphore partesDisponiblesEscenariosCN;
    // Animaciones
    private static Semaphore mutexAnimacionesCN;
    private static Semaphore driveDisponibleAnimacionesCN;
    private static Semaphore partesDisponiblesAnimacionesCN;
    // Doblajes
    private static Semaphore mutexDoblajesCN;
    private static Semaphore driveDisponibleDoblajesCN;
    private static Semaphore partesDisponiblesDoblajesCN;
    // Plot Twists
    private static Semaphore mutexPlotTwistsCN;
    private static Semaphore driveDisponiblePlotTwistsCN;
    private static Semaphore partesDisponiblesPlotTwistsCN;
    // Ensambladores
    private static Semaphore mutexCapitulosCN;
    
    
    // Trabajadores Cartoon Network
    public static GuionistaCN guionistasCN [];
    public static DisenadorCN disenadoresCN [];
    public static AnimadorCN animadoresCN [];
    public static ActorCN actoresCN [];
    public static PlotTwistCN plottwistsCN [];
    public static EnsambladorCN ensambladoresCN [];
    
    
    //Elementos producidos por Cartoon Network
    public static volatile int guionesCN;
    public static volatile int escenariosCN;
    public static volatile int animacionesCN;
    public static volatile int doblajesCN;
    public static volatile int plotTwistsCN;
    public static volatile int capitulosProducidosCN;
    
    //Salarios trabajadores Cartoon Network
    public static int sueldoGuionistasCN;
    public static int sueldoDisenadoresCN;
    public static int sueldoAnimadoresCN;
    public static int sueldoActoresCN;
    public static int sueldoPlotTwistsCN;
    public static int sueldoEnsambladoresCN;
    
    // Semaforos Star Channel
    // Guiones
    private static Semaphore mutexGuionesSC;
    private static Semaphore driveDisponibleGuionesSC;
    private static Semaphore partesDisponiblesGuionesSC;
    // Escenarios
    private static Semaphore mutexEscenariosSC;
    private static Semaphore driveDisponibleEscenariosSC;
    private static Semaphore partesDisponiblesEscenariosSC;
    // Animaciones
    private static Semaphore mutexAnimacionesSC;
    private static Semaphore driveDisponibleAnimacionesSC;
    private static Semaphore partesDisponiblesAnimacionesSC;
    // Doblajes
    private static Semaphore mutexDoblajesSC;
    private static Semaphore driveDisponibleDoblajesSC;
    private static Semaphore partesDisponiblesDoblajesSC;
    // Plot Twists
    private static Semaphore mutexPlotTwistsSC;
    private static Semaphore driveDisponiblePlotTwistsSC;
    private static Semaphore partesDisponiblesPlotTwistsSC;
    // Ensambladores
    private static Semaphore mutexCapitulosSC;
    
    // Trabajadores Star Channel
    public static GuionistaSC guionistasSC [];
    public static DisenadorSC disenadoresSC [];
    public static AnimadorSC animadoresSC [];
    public static ActorSC actoresSC [];
    public static PlotTwistSC plottwistsSC [];
    public static EnsambladorSC ensambladoresSC [];
    
    //Elementos producidos por Star Channel
    public static volatile int guionesSC;
    public static volatile int escenariosSC;
    public static volatile int animacionesSC;
    public static volatile int doblajesSC;
    public static volatile int plotTwistsSC;
    public static volatile int capitulosProducidosSC;
    
    //Salarios trabajadores Star Channel
    public static int sueldoGuionistasSC;
    public static int sueldoDisenadoresSC;
    public static int sueldoAnimadoresSC;
    public static int sueldoActoresSC;
    public static int sueldoPlotTwistsSC;
    public static int sueldoEnsambladoresSC;
    
    // Funciones ArchivoCSV
    public static ArchivoCSV csv;
    public static String infoEstudios [];
    
    public VentanaSimulacion() {
        initComponents();
        
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        // Se deshabilitan los botones de ambos estudios
        // Botones agregar/eliminar trabajadores Cartoon Network
        agregarGuionistaCN.setEnabled(false);
        agregarDisenadorCN.setEnabled(false);
        agregarAnimadorCN.setEnabled(false);
        agregarActorCN.setEnabled(false);
        agregarPlotTwistCN.setEnabled(false);
        agregarEnsambladorCN.setEnabled(false);
        
        eliminarGuionistaCN.setEnabled(false);
        eliminarDisenadorCN.setEnabled(false);
        eliminarAnimadorCN.setEnabled(false);
        eliminarActorCN.setEnabled(false);
        eliminarPlotTwistCN.setEnabled(false);
        eliminarEnsambladorCN.setEnabled(false);
        
        // Botones agregar/eliminar trabajadores Star Channel
        agregarGuionistaSC.setEnabled(false);
        agregarDisenadorSC.setEnabled(false);
        agregarAnimadorSC.setEnabled(false);
        agregarActorSC.setEnabled(false);
        agregarPlotTwistSC.setEnabled(false);
        agregarEnsambladorSC.setEnabled(false);
        
        eliminarGuionistaSC.setEnabled(false);
        eliminarDisenadorSC.setEnabled(false);
        eliminarAnimadorSC.setEnabled(false);
        eliminarActorSC.setEnabled(false);
        eliminarPlotTwistSC.setEnabled(false);
        eliminarEnsambladorSC.setEnabled(false);
        
        this.driveCN = new DriveCN();
        this.driveSC = new DriveSC();
        // Se crea el objeto csv
        this.csv = new ArchivoCSV();
        
        // Se lee el csv y se guarda en infoEstudios
        String infoEstudios [] = csv.leerCSV();
        
        // Variables estaticas de la simulacion
        this.duracionDia = Integer.parseInt(infoEstudios[0]);
        this.diasDespacho = Integer.parseInt(infoEstudios[1]);
        this.capacidadGuiones = Integer.parseInt(infoEstudios[2]);
        this.capacidadEscenarios = Integer.parseInt(infoEstudios[3]);
        this.capacidadAnimaciones = Integer.parseInt(infoEstudios[4]);
        this.capacidadDoblajes = Integer.parseInt(infoEstudios[5]);
        this.capacidadPlotTwists = Integer.parseInt(infoEstudios[6]);
        
        // Variables dinamicas de la simulacion
        // Cartoon Network
        this.guionesCN = 0;
        this.escenariosCN = 0;
        this.animacionesCN = 0;
        this.doblajesCN = 0;
        this.plotTwistsCN = 0;
        this.capitulosProducidosCN = 0;
        
        this.sueldoGuionistasCN = 0;
        this.sueldoDisenadoresCN = 0;
        this.sueldoAnimadoresCN = 0;
        this.sueldoActoresCN = 0;
        this.sueldoPlotTwistsCN = 0;
        this.sueldoEnsambladoresCN = 0;
        
        // Star Channel
        this.guionesSC = 0;
        this.escenariosSC = 0;
        this.animacionesSC = 0;
        this.doblajesSC = 0;
        this.plotTwistsSC = 0;
        this.capitulosProducidosSC = 0;
    
        this.sueldoGuionistasSC = 0;
        this.sueldoDisenadoresSC = 0;
        this.sueldoAnimadoresSC = 0;
        this.sueldoActoresSC = 0;
        this.sueldoPlotTwistsSC = 0;
        this.sueldoEnsambladoresSC = 0;
        
        // Semaforos Cartoon Network
        // Guiones
        this.mutexGuionesCN = new Semaphore(1, true);
        this.driveDisponibleGuionesCN = new Semaphore(capacidadGuiones, true);
        this.partesDisponiblesGuionesCN = new Semaphore(0, true);
        // Escenarios
        this.mutexEscenariosCN = new Semaphore(1, true);
        this.driveDisponibleEscenariosCN = new Semaphore(capacidadEscenarios, true);
        this.partesDisponiblesEscenariosCN = new Semaphore(0, true);
        // Animaciones
        this.mutexAnimacionesCN = new Semaphore(1, true);
        this.driveDisponibleAnimacionesCN = new Semaphore(capacidadAnimaciones, true);
        this.partesDisponiblesAnimacionesCN = new Semaphore(0, true);
        // Doblajes
        this.mutexDoblajesCN = new Semaphore(1, true);
        this.driveDisponibleDoblajesCN = new Semaphore(capacidadDoblajes, true);
        this.partesDisponiblesDoblajesCN = new Semaphore(0, true);
        // PlotTwists
        this.mutexPlotTwistsCN = new Semaphore(1, true);
        this.driveDisponiblePlotTwistsCN = new Semaphore(capacidadPlotTwists, true);
        this.partesDisponiblesPlotTwistsCN = new Semaphore(0, true);
        // Capitulos
        this.mutexCapitulosCN = new Semaphore(1, true);
        
        // Arrays de objetos Trabajadores/Ensambladores Cartoon Network
        this.guionistasCN = new GuionistaCN[VentanaParametros.maxTrabajadoresCN];
        this.disenadoresCN = new DisenadorCN[VentanaParametros.maxTrabajadoresCN];
        this.animadoresCN = new AnimadorCN[VentanaParametros.maxTrabajadoresCN];
        this.actoresCN = new ActorCN[VentanaParametros.maxTrabajadoresCN];
        this.plottwistsCN = new PlotTwistCN[VentanaParametros.maxTrabajadoresCN];
        this.ensambladoresCN = new EnsambladorCN[VentanaParametros.maxTrabajadoresCN];
        
        // Semaforos Star Channel
        // Guiones
        this.mutexGuionesSC = new Semaphore(1, true);
        this.driveDisponibleGuionesSC = new Semaphore(capacidadGuiones, true);
        this.partesDisponiblesGuionesSC = new Semaphore(0, true);
        // Escenarios
        this.mutexEscenariosSC = new Semaphore(1, true);
        this.driveDisponibleEscenariosSC = new Semaphore(capacidadEscenarios, true);
        this.partesDisponiblesEscenariosSC = new Semaphore(0, true);
        // Animaciones
        this.mutexAnimacionesSC = new Semaphore(1, true);
        this.driveDisponibleAnimacionesSC = new Semaphore(capacidadAnimaciones, true);
        this.partesDisponiblesAnimacionesSC = new Semaphore(0, true);
        // Doblajes
        this.mutexDoblajesSC = new Semaphore(1, true);
        this.driveDisponibleDoblajesSC = new Semaphore(capacidadDoblajes, true);
        this.partesDisponiblesDoblajesSC = new Semaphore(0, true);
        // PlotTwists
        this.mutexPlotTwistsSC = new Semaphore(1, true);
        this.driveDisponiblePlotTwistsSC = new Semaphore(capacidadPlotTwists, true);
        this.partesDisponiblesPlotTwistsSC = new Semaphore(0, true);
        // Capitulos
        this.mutexCapitulosSC = new Semaphore(1, true);
        
        // Arrays de objetos Trabajadores/Ensambladores Star Channel
        this.guionistasSC = new GuionistaSC[VentanaParametros.maxTrabajadoresSC];
        this.disenadoresSC = new DisenadorSC[VentanaParametros.maxTrabajadoresSC];
        this.animadoresSC = new AnimadorSC[VentanaParametros.maxTrabajadoresSC];
        this.actoresSC = new ActorSC[VentanaParametros.maxTrabajadoresSC];
        this.plottwistsSC = new PlotTwistSC[VentanaParametros.maxTrabajadoresSC];
        this.ensambladoresSC = new EnsambladorSC[VentanaParametros.maxTrabajadoresSC];

        
        // Asignacion de valores en interfaz
        
        // Dias lanzamiento
        diasLanzamiento.setText(Integer.toString(diasDespacho));
        
        // Partes producidas por Cartoon Network
        cantidadGuionesCN.setText(Integer.toString(guionesCN));
        cantidadEscenariosCN.setText(Integer.toString(escenariosCN));
        cantidadAnimacionesCN.setText(Integer.toString(animacionesCN));
        cantidadDoblajesCN.setText(Integer.toString(doblajesCN));
        cantidadPlotTwistsCN.setText(Integer.toString(plotTwistsCN));
        cantidadCapitulosCN.setText(Integer.toString(capitulosProducidosCN));
        
        // Partes producidas por Star Channel
        cantidadGuionesSC.setText(Integer.toString(guionesSC));
        cantidadEscenariosSC.setText(Integer.toString(escenariosSC));
        cantidadAnimacionesSC.setText(Integer.toString(animacionesSC));
        cantidadDoblajesSC.setText(Integer.toString(doblajesSC));
        cantidadPlotTwistsSC.setText(Integer.toString(plotTwistsSC));
        cantidadCapitulosSC.setText(Integer.toString(capitulosProducidosSC));
        
        // Trabajadores Cartoon Network
        numGuionistasCN.setText(infoEstudios[7]);
        numDisenadoresCN.setText(infoEstudios[8]);
        numAnimadoresCN.setText(infoEstudios[9]);
        numActoresCN.setText(infoEstudios[10]);
        numPlotTwistsCN.setText(infoEstudios[11]);
        numEnsambladoresCN.setText(infoEstudios[12]);
        
        // Trabajadores Star Channel
        numGuionistasSC.setText(infoEstudios[13]);
        numDisenadoresSC.setText(infoEstudios[14]);
        numAnimadoresSC.setText(infoEstudios[15]);
        numActoresSC.setText(infoEstudios[16]);
        numPlotTwistsSC.setText(infoEstudios[17]);
        numEnsambladoresSC.setText(infoEstudios[18]);
        
        // Sueldos Cartoon Network
        salarioGuionistasCN.setText(Integer.toString(sueldoGuionistasCN));
        salarioDisenadoresCN.setText(Integer.toString(sueldoDisenadoresCN));
        salarioAnimadoresCN.setText(Integer.toString(sueldoAnimadoresCN));
        salarioActoresCN.setText(Integer.toString(sueldoActoresCN));
        salarioPlotTwistsCN.setText(Integer.toString(sueldoPlotTwistsCN));
        salarioEnsambladoresCN.setText(Integer.toString(sueldoEnsambladoresCN));
        
        // Sueldos Star Channel
        salarioGuionistasSC.setText(Integer.toString(sueldoGuionistasSC));
        salarioDisenadoresSC.setText(Integer.toString(sueldoDisenadoresSC));
        salarioAnimadoresSC.setText(Integer.toString(sueldoAnimadoresSC));
        salarioActoresSC.setText(Integer.toString(sueldoActoresSC));
        salarioPlotTwistsSC.setText(Integer.toString(sueldoPlotTwistsSC));
        salarioEnsambladoresSC.setText(Integer.toString(sueldoEnsambladoresSC));
        
        // Se anaden al array de trabajadores los hilos correspondientes a la cantidad de trabajadores activos
        
        // Cartoon Network
        // Guionistas iniciales
        for (int i = 0; i < Integer.parseInt(infoEstudios[7]); i++) {
            GuionistaCN hiloGuionistaCN= new GuionistaCN(0, duracionDia, driveCN, mutexGuionesCN, driveDisponibleGuionesCN, partesDisponiblesGuionesCN, salarioGuionistasCN);
            guionistasCN[i] = hiloGuionistaCN;
            
        }
        // Disenadores iniciales
        for (int i = 0; i < Integer.parseInt(infoEstudios[8]); i++) {
            DisenadorCN hiloDisenadorCN= new DisenadorCN(1, duracionDia, driveCN, mutexEscenariosCN, driveDisponibleEscenariosCN, partesDisponiblesEscenariosCN, salarioDisenadoresCN);
            disenadoresCN[i] = hiloDisenadorCN;
            
        }
        // Animadores iniciales
        for (int i = 0; i < Integer.parseInt(infoEstudios[9]); i++) {
            AnimadorCN hiloAnimadorCN= new AnimadorCN(2, duracionDia, driveCN, mutexAnimacionesCN, driveDisponibleAnimacionesCN, partesDisponiblesAnimacionesCN, salarioAnimadoresCN);
            animadoresCN[i] = hiloAnimadorCN;
            
        }
        // Actores iniciales
        for (int i = 0; i < Integer.parseInt(infoEstudios[10]); i++) {
            ActorCN hiloActorCN= new ActorCN(3, duracionDia, driveCN, mutexDoblajesCN, driveDisponibleDoblajesCN, partesDisponiblesDoblajesCN, salarioActoresCN);
            actoresCN[i] = hiloActorCN;
            
        }
        // Guionistas de PlotTwists iniciales
        for (int i = 0; i < Integer.parseInt(infoEstudios[11]); i++) {
            PlotTwistCN hiloPlotTwistCN= new PlotTwistCN(4, duracionDia, driveCN, mutexPlotTwistsCN, driveDisponiblePlotTwistsCN, partesDisponiblesPlotTwistsCN, salarioPlotTwistsCN);
            plottwistsCN[i] = hiloPlotTwistCN;
            
        }
        // Ensambladores iniciales
        for (int i = 0; i < Integer.parseInt(infoEstudios[12]); i++) {
            EnsambladorCN hiloEnsambladorCN= new EnsambladorCN(5, duracionDia, driveCN, mutexCapitulosCN, mutexGuionesCN, driveDisponibleGuionesCN, partesDisponiblesGuionesCN, mutexEscenariosCN, driveDisponibleEscenariosCN, partesDisponiblesEscenariosCN, mutexAnimacionesCN, driveDisponibleAnimacionesCN, partesDisponiblesAnimacionesCN, mutexDoblajesCN, driveDisponibleDoblajesCN, partesDisponiblesDoblajesCN, mutexPlotTwistsCN, driveDisponiblePlotTwistsCN, partesDisponiblesPlotTwistsCN, salarioEnsambladoresCN);
            ensambladoresCN[i] = hiloEnsambladorCN;
   
        }
        
        // Star Channel
        // Guionistas iniciales
        for (int i = 0; i < Integer.parseInt(infoEstudios[13]); i++) {
            GuionistaSC hiloGuionistaSC= new GuionistaSC(0, duracionDia, driveSC, mutexGuionesSC, driveDisponibleGuionesSC, partesDisponiblesGuionesSC, salarioGuionistasSC);
            guionistasSC[i] = hiloGuionistaSC;
            
        }
        // Disenadores iniciales
        for (int i = 0; i < Integer.parseInt(infoEstudios[14]); i++) {
            DisenadorSC hiloDisenadorSC= new DisenadorSC(1, duracionDia, driveSC, mutexEscenariosSC, driveDisponibleEscenariosSC, partesDisponiblesEscenariosSC, salarioDisenadoresSC);
            disenadoresSC[i] = hiloDisenadorSC;
            
        }
        // Animadores iniciales
        for (int i = 0; i < Integer.parseInt(infoEstudios[15]); i++) {
            AnimadorSC hiloAnimadorSC= new AnimadorSC(2, duracionDia, driveSC, mutexAnimacionesSC, driveDisponibleAnimacionesSC, partesDisponiblesAnimacionesSC, salarioAnimadoresSC);
            animadoresSC[i] = hiloAnimadorSC;
            
        }
        // Actores iniciales
        for (int i = 0; i < Integer.parseInt(infoEstudios[16]); i++) {
            ActorSC hiloActorSC= new ActorSC(3, duracionDia, driveSC, mutexDoblajesSC, driveDisponibleDoblajesSC, partesDisponiblesDoblajesSC, salarioActoresSC);
            actoresSC[i] = hiloActorSC;
            
        }
        // Guionistas de PlotTwists iniciales
        for (int i = 0; i < Integer.parseInt(infoEstudios[17]); i++) {
            PlotTwistSC hiloPlotTwistSC= new PlotTwistSC(4, duracionDia, driveSC, mutexPlotTwistsSC, driveDisponiblePlotTwistsSC, partesDisponiblesPlotTwistsSC, salarioPlotTwistsSC);
            plottwistsSC[i] = hiloPlotTwistSC;
            
        }
        // Ensambladores iniciales
        for (int i = 0; i < Integer.parseInt(infoEstudios[18]); i++) {
            EnsambladorSC hiloEnsambladorSC= new EnsambladorSC(5, duracionDia, driveSC, mutexCapitulosSC, mutexGuionesSC, driveDisponibleGuionesSC, partesDisponiblesGuionesSC, mutexEscenariosSC, driveDisponibleEscenariosSC, partesDisponiblesEscenariosSC, mutexAnimacionesSC, driveDisponibleAnimacionesSC, partesDisponiblesAnimacionesSC, mutexDoblajesSC, driveDisponibleDoblajesSC, partesDisponiblesDoblajesSC, mutexPlotTwistsSC, driveDisponiblePlotTwistsSC, partesDisponiblesPlotTwistsSC, salarioEnsambladoresSC);
            ensambladoresSC[i] = hiloEnsambladorSC;
   
        }
    }
    
    public void agregarTrabajadorCN(JLabel tipoTrabajador, int tipo){
        // Calcular la cantidad de trabajadores concurrentes
        int numTrabajadoresCN = Integer.parseInt(numGuionistasCN.getText()) + 
                                Integer.parseInt(numDisenadoresCN.getText()) + 
                                Integer.parseInt(numAnimadoresCN.getText()) + 
                                Integer.parseInt(numActoresCN.getText()) + 
                                Integer.parseInt(numPlotTwistsCN.getText()) + 
                                Integer.parseInt(numEnsambladoresCN.getText());
        // Validacion para que no exceda el numero de trabajadores permitidos concurrentemente
            if (numTrabajadoresCN == VentanaParametros.maxTrabajadoresCN) {
                JOptionPane.showMessageDialog(null, "El número de trabajadores en Cartoon Network no puede ser mayor a " + "(" + VentanaParametros.maxTrabajadoresCN + ")");
                return;
            }
        // Cambia la cantidad de trabajadores en interfaz
        String numeroTrabajadores = tipoTrabajador.getText();
        int entero= Integer.parseInt(numeroTrabajadores);
        entero++;
        numeroTrabajadores= Integer.toString(entero);
        tipoTrabajador.setText(numeroTrabajadores);
        
        switch (tipo) {
            
            case 0: // Guionista
                GuionistaCN hiloGuionistaCN= new GuionistaCN(0, duracionDia, driveCN, mutexGuionesCN, driveDisponibleGuionesCN, partesDisponiblesGuionesCN, salarioGuionistasCN);
                // Recorre cada posicion del array y pregunta si el elemento de esa posicion es null, 
                // (si hay un algo en una posicion i no sera null, pero si esta vacia es que puede ser 
                // introducido un nuevo elemento, es decir, un nuevo trabajador/hilo)
                for (int i = 0; i < guionistasCN.length; i++) {
                    if (guionistasCN[i] == null) {
                        guionistasCN[i] = hiloGuionistaCN;
                        break;
                    }
                }
                hiloGuionistaCN.start();
                break;
            case 1: // Disenador
                DisenadorCN hiloDisenadorCN= new DisenadorCN(1, duracionDia, driveCN, mutexEscenariosCN, driveDisponibleEscenariosCN, partesDisponiblesEscenariosCN, salarioDisenadoresCN);
                // Recorre cada posicion del array y pregunta si el elemento de esa posicion es null, 
                // (si hay un algo en una posicion i no sera null, pero si esta vacia es que puede ser 
                // introducido un nuevo elemento, es decir, un nuevo trabajador/hilo)
                for (int i = 0; i < disenadoresCN.length; i++) {
                    if (disenadoresCN[i] == null) {
                        disenadoresCN[i] = hiloDisenadorCN;
                        break;
                    }
                }
                hiloDisenadorCN.start();
                break;
            case 2: // Animador
                AnimadorCN hiloAnimadorCN= new AnimadorCN(2, duracionDia, driveCN, mutexAnimacionesCN, driveDisponibleAnimacionesCN, partesDisponiblesAnimacionesCN, salarioAnimadoresCN);
                // Recorre cada posicion del array y pregunta si el elemento de esa posicion es null, 
                // (si hay un algo en una posicion i no sera null, pero si esta vacia es que puede ser 
                // introducido un nuevo elemento, es decir, un nuevo trabajador/hilo)
                for (int i = 0; i < animadoresCN.length; i++) {
                    if (animadoresCN[i] == null) {
                        animadoresCN[i] = hiloAnimadorCN;
                        break;
                    }
                }
                hiloAnimadorCN.start();
                break;
            case 3: // Actor
                ActorCN hiloActorCN= new ActorCN(3, duracionDia, driveCN, mutexDoblajesCN, driveDisponibleDoblajesCN, partesDisponiblesDoblajesCN, salarioActoresCN);
                // Recorre cada posicion del array y pregunta si el elemento de esa posicion es null, 
                // (si hay un algo en una posicion i no sera null, pero si esta vacia es que puede ser 
                // introducido un nuevo elemento, es decir, un nuevo trabajador/hilo)
                for (int i = 0; i < actoresCN.length; i++) {
                    if (actoresCN[i] == null) {
                        actoresCN[i] = hiloActorCN;
                        break;
                    }
                }
                hiloActorCN.start();
                break;
            case 4: // Guionista de Plot Twist
                PlotTwistCN hiloPlotTwistCN= new PlotTwistCN(4, duracionDia, driveCN, mutexPlotTwistsCN, driveDisponiblePlotTwistsCN, partesDisponiblesPlotTwistsCN, salarioPlotTwistsCN);
                // Recorre cada posicion del array y pregunta si el elemento de esa posicion es null, 
                // (si hay un algo en una posicion i no sera null, pero si esta vacia es que puede ser 
                // introducido un nuevo elemento, es decir, un nuevo trabajador/hilo)
                for (int i = 0; i < plottwistsCN.length; i++) {
                    if (plottwistsCN[i] == null) {
                        plottwistsCN[i] = hiloPlotTwistCN;
                        break;
                    }
                }
                hiloPlotTwistCN.start();
                break;
            case 5: // Ensamblador
                EnsambladorCN hiloEnsambladorCN= new EnsambladorCN(5, duracionDia, driveCN, mutexCapitulosCN, mutexGuionesCN, driveDisponibleGuionesCN, partesDisponiblesGuionesCN, mutexEscenariosCN, driveDisponibleEscenariosCN, partesDisponiblesEscenariosCN, mutexAnimacionesCN, driveDisponibleAnimacionesCN, partesDisponiblesAnimacionesCN, mutexDoblajesCN, driveDisponibleDoblajesCN, partesDisponiblesDoblajesCN, mutexPlotTwistsCN, driveDisponiblePlotTwistsCN, partesDisponiblesPlotTwistsCN, salarioEnsambladoresCN);
                // Recorre cada posicion del array y pregunta si el elemento de esa posicion es null, 
                // (si hay un algo en una posicion i no sera null, pero si esta vacia es que puede ser 
                // introducido un nuevo elemento, es decir, un nuevo trabajador/hilo)
                for (int i = 0; i < ensambladoresCN.length; i++) {
                    if (ensambladoresCN[i] == null) {
                        ensambladoresCN[i] = hiloEnsambladorCN;
                        break;
                    }
                }
                hiloEnsambladorCN.start();
                break;
            default:
                break;
        }
    }
    
    public void agregarTrabajadorSC(JLabel tipoTrabajador, int tipo){
        // Calcular la cantidad de trabajadores concurrentes
        int numTrabajadoresSC = Integer.parseInt(numGuionistasSC.getText()) + 
                                Integer.parseInt(numDisenadoresSC.getText()) + 
                                Integer.parseInt(numAnimadoresSC.getText()) + 
                                Integer.parseInt(numActoresSC.getText()) + 
                                Integer.parseInt(numPlotTwistsSC.getText()) + 
                                Integer.parseInt(numEnsambladoresSC.getText());
        // Validacion para que no exceda el numero de trabajadores permitidos concurrentemente
            if (numTrabajadoresSC == VentanaParametros.maxTrabajadoresSC) {
                JOptionPane.showMessageDialog(null, "El número de trabajadores en Star Channel no puede ser mayor a " + "(" + VentanaParametros.maxTrabajadoresSC + ")");
                return;
            }
        // Cambia la cantidad de trabajadores en interfaz
        String numeroTrabajadores = tipoTrabajador.getText();
        int entero= Integer.parseInt(numeroTrabajadores);
        entero++;
        numeroTrabajadores= Integer.toString(entero);
        tipoTrabajador.setText(numeroTrabajadores);
        
        switch (tipo) {
            
            case 0: // Guionista
                GuionistaSC hiloGuionistaSC= new GuionistaSC(0, duracionDia, driveSC, mutexGuionesSC, driveDisponibleGuionesSC, partesDisponiblesGuionesSC, salarioGuionistasSC);
                // Recorre cada posicion del array y pregunta si el elemento de esa posicion es null, 
                // (si hay un algo en una posicion i no sera null, pero si esta vacia es que puede ser 
                // introducido un nuevo elemento, es decir, un nuevo trabajador/hilo)
                for (int i = 0; i < guionistasSC.length; i++) {
                    if (guionistasSC[i] == null) {
                        guionistasSC[i] = hiloGuionistaSC;
                        break;
                    }
                }
                hiloGuionistaSC.start();
                break;
            case 1: // Disenador
                DisenadorSC hiloDisenadorSC= new DisenadorSC(1, duracionDia, driveSC, mutexEscenariosSC, driveDisponibleEscenariosSC, partesDisponiblesEscenariosSC, salarioDisenadoresSC);
                // Recorre cada posicion del array y pregunta si el elemento de esa posicion es null, 
                // (si hay un algo en una posicion i no sera null, pero si esta vacia es que puede ser 
                // introducido un nuevo elemento, es decir, un nuevo trabajador/hilo)
                for (int i = 0; i < disenadoresSC.length; i++) {
                    if (disenadoresSC[i] == null) {
                        disenadoresSC[i] = hiloDisenadorSC;
                        break;
                    }
                }
                hiloDisenadorSC.start();
                break;
            case 2: // Animador
                AnimadorSC hiloAnimadorSC= new AnimadorSC(2, duracionDia, driveSC, mutexAnimacionesSC, driveDisponibleAnimacionesSC, partesDisponiblesAnimacionesSC, salarioAnimadoresSC);
                // Recorre cada posicion del array y pregunta si el elemento de esa posicion es null, 
                // (si hay un algo en una posicion i no sera null, pero si esta vacia es que puede ser 
                // introducido un nuevo elemento, es decir, un nuevo trabajador/hilo)
                for (int i = 0; i < animadoresSC.length; i++) {
                    if (animadoresSC[i] == null) {
                        animadoresSC[i] = hiloAnimadorSC;
                        break;
                    }
                }
                hiloAnimadorSC.start();
                break;
            case 3: // Actor
                ActorSC hiloActorSC= new ActorSC(3, duracionDia, driveSC, mutexDoblajesSC, driveDisponibleDoblajesSC, partesDisponiblesDoblajesSC, salarioActoresSC);
                // Recorre cada posicion del array y pregunta si el elemento de esa posicion es null, 
                // (si hay un algo en una posicion i no sera null, pero si esta vacia es que puede ser 
                // introducido un nuevo elemento, es decir, un nuevo trabajador/hilo)
                for (int i = 0; i < actoresSC.length; i++) {
                    if (actoresSC[i] == null) {
                        actoresSC[i] = hiloActorSC;
                        break;
                    }
                }
                hiloActorSC.start();
                break;
            case 4: // Guionista de Plot Twist
                PlotTwistSC hiloPlotTwistSC= new PlotTwistSC(4, duracionDia, driveSC, mutexPlotTwistsSC, driveDisponiblePlotTwistsSC, partesDisponiblesPlotTwistsSC, salarioPlotTwistsSC);
                // Recorre cada posicion del array y pregunta si el elemento de esa posicion es null, 
                // (si hay un algo en una posicion i no sera null, pero si esta vacia es que puede ser 
                // introducido un nuevo elemento, es decir, un nuevo trabajador/hilo)
                for (int i = 0; i < plottwistsSC.length; i++) {
                    if (plottwistsSC[i] == null) {
                        plottwistsSC[i] = hiloPlotTwistSC;
                        break;
                    }
                }
                hiloPlotTwistSC.start();
                break;
            case 5: // Ensamblador
                EnsambladorSC hiloEnsambladorSC= new EnsambladorSC(5, duracionDia, driveSC, mutexCapitulosSC, mutexGuionesSC, driveDisponibleGuionesSC, partesDisponiblesGuionesSC, mutexEscenariosSC, driveDisponibleEscenariosSC, partesDisponiblesEscenariosSC, mutexAnimacionesSC, driveDisponibleAnimacionesSC, partesDisponiblesAnimacionesSC, mutexDoblajesSC, driveDisponibleDoblajesSC, partesDisponiblesDoblajesSC, mutexPlotTwistsSC, driveDisponiblePlotTwistsSC, partesDisponiblesPlotTwistsSC, salarioEnsambladoresSC);
                // Recorre cada posicion del array y pregunta si el elemento de esa posicion es null, 
                // (si hay un algo en una posicion i no sera null, pero si esta vacia es que puede ser 
                // introducido un nuevo elemento, es decir, un nuevo trabajador/hilo)
                for (int i = 0; i < ensambladoresSC.length; i++) {
                    if (ensambladoresSC[i] == null) {
                        ensambladoresSC[i] = hiloEnsambladorSC;
                        break;
                    }
                }
                hiloEnsambladorSC.start();
                break;
            default:
                break;
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        numAnimadoresCN = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cantidadGuionesSC = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        maxGuionesSC = new javax.swing.JLabel();
        numGuionistasSC = new javax.swing.JLabel();
        agregarGuionistaSC = new javax.swing.JButton();
        eliminarGuionistaSC = new javax.swing.JButton();
        salarioGuionistasSC = new javax.swing.JLabel();
        salarioDisenadoresSC = new javax.swing.JLabel();
        eliminarDisenadorSC = new javax.swing.JButton();
        agregarDisenadorSC = new javax.swing.JButton();
        numDisenadoresSC = new javax.swing.JLabel();
        maxEscenariosSC = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        cantidadEscenariosSC = new javax.swing.JLabel();
        cantidadAnimacionesSC = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        maxAnimacionesSC = new javax.swing.JLabel();
        numAnimadoresSC = new javax.swing.JLabel();
        agregarAnimadorSC = new javax.swing.JButton();
        eliminarAnimadorSC = new javax.swing.JButton();
        salarioAnimadoresSC = new javax.swing.JLabel();
        salarioActoresSC = new javax.swing.JLabel();
        eliminarActorSC = new javax.swing.JButton();
        agregarActorSC = new javax.swing.JButton();
        numActoresSC = new javax.swing.JLabel();
        maxDoblajesSC = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        cantidadDoblajesSC = new javax.swing.JLabel();
        cantidadPlotTwistsSC = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        maxPlotTwistsSC = new javax.swing.JLabel();
        numPlotTwistsSC = new javax.swing.JLabel();
        agregarPlotTwistSC = new javax.swing.JButton();
        eliminarPlotTwistSC = new javax.swing.JButton();
        salarioPlotTwistsSC = new javax.swing.JLabel();
        salarioEnsambladoresSC = new javax.swing.JLabel();
        eliminarEnsambladorSC = new javax.swing.JButton();
        agregarEnsambladorSC = new javax.swing.JButton();
        numEnsambladoresSC = new javax.swing.JLabel();
        eliminarGuionistaCN = new javax.swing.JButton();
        agregarGuionistaCN = new javax.swing.JButton();
        eliminarDisenadorCN = new javax.swing.JButton();
        agregarDisenadorCN = new javax.swing.JButton();
        eliminarAnimadorCN = new javax.swing.JButton();
        agregarAnimadorCN = new javax.swing.JButton();
        eliminarActorCN = new javax.swing.JButton();
        agregarActorCN = new javax.swing.JButton();
        maxEscenariosCN = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        agregarEnsambladorCN = new javax.swing.JButton();
        eliminarEnsambladorCN = new javax.swing.JButton();
        cantidadCapitulosCN = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        faltasPMSC = new javax.swing.JLabel();
        faltasPMCN = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        cantidadCapitulosSC = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        estadoDirectorCN = new javax.swing.JLabel();
        sueldoDCN = new javax.swing.JLabel();
        estadoDirectorSC = new javax.swing.JLabel();
        estadoPMSC = new javax.swing.JLabel();
        iniciarEstudios = new javax.swing.JButton();
        jLabel38 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        numDisenadoresCN = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        numGuionistasCN = new javax.swing.JLabel();
        cantidadEscenariosCN = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        numEnsambladoresCN = new javax.swing.JLabel();
        cantidadGuionesCN = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        maxAnimacionesCN = new javax.swing.JLabel();
        cantidadAnimacionesCN = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        maxDoblajesCN = new javax.swing.JLabel();
        cantidadDoblajesCN = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        estadoPMCN = new javax.swing.JLabel();
        sueldoPMSC = new javax.swing.JLabel();
        maxGuionesCN = new javax.swing.JLabel();
        salarioGuionistasCN = new javax.swing.JLabel();
        salarioDisenadoresCN = new javax.swing.JLabel();
        salarioAnimadoresCN = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        sueldoDSC = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        sueldoPMCN = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        numActoresCN = new javax.swing.JLabel();
        salarioActoresCN = new javax.swing.JLabel();
        salarioEnsambladoresCN = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cantidadPlotTwistsCN = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        maxPlotTwistsCN = new javax.swing.JLabel();
        numPlotTwistsCN = new javax.swing.JLabel();
        agregarPlotTwistCN = new javax.swing.JButton();
        eliminarPlotTwistCN = new javax.swing.JButton();
        salarioPlotTwistsCN = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        irSimulacionButton = new javax.swing.JButton();
        irDashboardButton = new javax.swing.JButton();
        irParametrosButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cantidadCapitulosPTCN = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        netoCN = new javax.swing.JLabel();
        gastosCN = new javax.swing.JLabel();
        gananciasCN = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        netoSC = new javax.swing.JLabel();
        gastosSC = new javax.swing.JLabel();
        gananciasSC = new javax.swing.JLabel();
        reiniciar = new javax.swing.JButton();
        aggDashboard = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        cantidadCapitulosPTSC = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        diasLanzamiento = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Panel.setBackground(new java.awt.Color(204, 204, 204));
        Panel.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        Panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(153, 153, 153));
        jLabel2.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Tipo");
        Panel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, -1, -1));

        jLabel3.setBackground(new java.awt.Color(153, 153, 153));
        jLabel3.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Guionistas");
        Panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, -1, -1));

        jLabel4.setBackground(new java.awt.Color(153, 153, 153));
        jLabel4.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Cartoon Network");
        Panel.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 140, -1, -1));

        numAnimadoresCN.setBackground(new java.awt.Color(153, 153, 153));
        numAnimadoresCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        numAnimadoresCN.setForeground(new java.awt.Color(0, 0, 0));
        numAnimadoresCN.setText("0");
        Panel.add(numAnimadoresCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 370, 10, -1));

        jLabel9.setBackground(new java.awt.Color(153, 153, 153));
        jLabel9.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Capacidad");
        Panel.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 250, 100, -1));

        cantidadGuionesSC.setBackground(new java.awt.Color(153, 153, 153));
        cantidadGuionesSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        cantidadGuionesSC.setForeground(new java.awt.Color(0, 0, 0));
        cantidadGuionesSC.setText("0");
        Panel.add(cantidadGuionesSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 290, 20, -1));

        jLabel35.setBackground(new java.awt.Color(153, 153, 153));
        jLabel35.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 0, 0));
        jLabel35.setText("/");
        Panel.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 290, 20, -1));

        maxGuionesSC.setBackground(new java.awt.Color(153, 153, 153));
        maxGuionesSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        maxGuionesSC.setForeground(new java.awt.Color(0, 0, 0));
        maxGuionesSC.setText("25");
        Panel.add(maxGuionesSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, 20, -1));

        numGuionistasSC.setBackground(new java.awt.Color(153, 153, 153));
        numGuionistasSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        numGuionistasSC.setForeground(new java.awt.Color(0, 0, 0));
        numGuionistasSC.setText("0");
        Panel.add(numGuionistasSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 290, 20, -1));

        agregarGuionistaSC.setBackground(new java.awt.Color(51, 51, 51));
        agregarGuionistaSC.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        agregarGuionistaSC.setForeground(new java.awt.Color(255, 255, 255));
        agregarGuionistaSC.setText("+");
        agregarGuionistaSC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarGuionistaSCActionPerformed(evt);
            }
        });
        Panel.add(agregarGuionistaSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 280, 40, 30));

        eliminarGuionistaSC.setBackground(new java.awt.Color(51, 51, 51));
        eliminarGuionistaSC.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        eliminarGuionistaSC.setForeground(new java.awt.Color(255, 255, 255));
        eliminarGuionistaSC.setText("-");
        eliminarGuionistaSC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarGuionistaSCActionPerformed(evt);
            }
        });
        Panel.add(eliminarGuionistaSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 280, 40, 30));

        salarioGuionistasSC.setBackground(new java.awt.Color(153, 153, 153));
        salarioGuionistasSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        salarioGuionistasSC.setForeground(new java.awt.Color(0, 0, 0));
        salarioGuionistasSC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salarioGuionistasSC.setText("0");
        Panel.add(salarioGuionistasSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 280, 50, 30));

        salarioDisenadoresSC.setBackground(new java.awt.Color(153, 153, 153));
        salarioDisenadoresSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        salarioDisenadoresSC.setForeground(new java.awt.Color(0, 0, 0));
        salarioDisenadoresSC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salarioDisenadoresSC.setText("0");
        Panel.add(salarioDisenadoresSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 320, 50, 30));

        eliminarDisenadorSC.setBackground(new java.awt.Color(51, 51, 51));
        eliminarDisenadorSC.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        eliminarDisenadorSC.setForeground(new java.awt.Color(255, 255, 255));
        eliminarDisenadorSC.setText("-");
        eliminarDisenadorSC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarDisenadorSCActionPerformed(evt);
            }
        });
        Panel.add(eliminarDisenadorSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 320, 40, 30));

        agregarDisenadorSC.setBackground(new java.awt.Color(51, 51, 51));
        agregarDisenadorSC.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        agregarDisenadorSC.setForeground(new java.awt.Color(255, 255, 255));
        agregarDisenadorSC.setText("+");
        agregarDisenadorSC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarDisenadorSCActionPerformed(evt);
            }
        });
        Panel.add(agregarDisenadorSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 320, 40, 30));

        numDisenadoresSC.setBackground(new java.awt.Color(153, 153, 153));
        numDisenadoresSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        numDisenadoresSC.setForeground(new java.awt.Color(0, 0, 0));
        numDisenadoresSC.setText("0");
        Panel.add(numDisenadoresSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 330, -1, -1));

        maxEscenariosSC.setBackground(new java.awt.Color(153, 153, 153));
        maxEscenariosSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        maxEscenariosSC.setForeground(new java.awt.Color(0, 0, 0));
        maxEscenariosSC.setText("20");
        Panel.add(maxEscenariosSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 330, 20, -1));

        jLabel41.setBackground(new java.awt.Color(153, 153, 153));
        jLabel41.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 0, 0));
        jLabel41.setText("/");
        Panel.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 330, 20, -1));

        cantidadEscenariosSC.setBackground(new java.awt.Color(153, 153, 153));
        cantidadEscenariosSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        cantidadEscenariosSC.setForeground(new java.awt.Color(0, 0, 0));
        cantidadEscenariosSC.setText("0");
        Panel.add(cantidadEscenariosSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 330, 20, -1));

        cantidadAnimacionesSC.setBackground(new java.awt.Color(153, 153, 153));
        cantidadAnimacionesSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        cantidadAnimacionesSC.setForeground(new java.awt.Color(0, 0, 0));
        cantidadAnimacionesSC.setText("0");
        Panel.add(cantidadAnimacionesSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 370, 20, -1));

        jLabel42.setBackground(new java.awt.Color(153, 153, 153));
        jLabel42.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 0, 0));
        jLabel42.setText("/");
        Panel.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 370, 20, -1));

        maxAnimacionesSC.setBackground(new java.awt.Color(153, 153, 153));
        maxAnimacionesSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        maxAnimacionesSC.setForeground(new java.awt.Color(0, 0, 0));
        maxAnimacionesSC.setText("55");
        Panel.add(maxAnimacionesSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 20, -1));

        numAnimadoresSC.setBackground(new java.awt.Color(153, 153, 153));
        numAnimadoresSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        numAnimadoresSC.setForeground(new java.awt.Color(0, 0, 0));
        numAnimadoresSC.setText("0");
        Panel.add(numAnimadoresSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 370, 10, -1));

        agregarAnimadorSC.setBackground(new java.awt.Color(51, 51, 51));
        agregarAnimadorSC.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        agregarAnimadorSC.setForeground(new java.awt.Color(255, 255, 255));
        agregarAnimadorSC.setText("+");
        agregarAnimadorSC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarAnimadorSCActionPerformed(evt);
            }
        });
        Panel.add(agregarAnimadorSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 360, 40, 30));

        eliminarAnimadorSC.setBackground(new java.awt.Color(51, 51, 51));
        eliminarAnimadorSC.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        eliminarAnimadorSC.setForeground(new java.awt.Color(255, 255, 255));
        eliminarAnimadorSC.setText("-");
        eliminarAnimadorSC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarAnimadorSCActionPerformed(evt);
            }
        });
        Panel.add(eliminarAnimadorSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 360, 40, 30));

        salarioAnimadoresSC.setBackground(new java.awt.Color(153, 153, 153));
        salarioAnimadoresSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        salarioAnimadoresSC.setForeground(new java.awt.Color(0, 0, 0));
        salarioAnimadoresSC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salarioAnimadoresSC.setText("0");
        Panel.add(salarioAnimadoresSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 360, 50, 30));

        salarioActoresSC.setBackground(new java.awt.Color(153, 153, 153));
        salarioActoresSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        salarioActoresSC.setForeground(new java.awt.Color(0, 0, 0));
        salarioActoresSC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salarioActoresSC.setText("0");
        Panel.add(salarioActoresSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 400, 50, 30));

        eliminarActorSC.setBackground(new java.awt.Color(51, 51, 51));
        eliminarActorSC.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        eliminarActorSC.setForeground(new java.awt.Color(255, 255, 255));
        eliminarActorSC.setText("-");
        eliminarActorSC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActorSCActionPerformed(evt);
            }
        });
        Panel.add(eliminarActorSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 400, 40, 30));

        agregarActorSC.setBackground(new java.awt.Color(51, 51, 51));
        agregarActorSC.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        agregarActorSC.setForeground(new java.awt.Color(255, 255, 255));
        agregarActorSC.setText("+");
        agregarActorSC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActorSCActionPerformed(evt);
            }
        });
        Panel.add(agregarActorSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 400, 40, 30));

        numActoresSC.setBackground(new java.awt.Color(153, 153, 153));
        numActoresSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        numActoresSC.setForeground(new java.awt.Color(0, 0, 0));
        numActoresSC.setText("0");
        Panel.add(numActoresSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 410, -1, -1));

        maxDoblajesSC.setBackground(new java.awt.Color(153, 153, 153));
        maxDoblajesSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        maxDoblajesSC.setForeground(new java.awt.Color(0, 0, 0));
        maxDoblajesSC.setText("35");
        Panel.add(maxDoblajesSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 410, 20, -1));

        jLabel45.setBackground(new java.awt.Color(153, 153, 153));
        jLabel45.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setText("/");
        Panel.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 410, 20, -1));

        cantidadDoblajesSC.setBackground(new java.awt.Color(153, 153, 153));
        cantidadDoblajesSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        cantidadDoblajesSC.setForeground(new java.awt.Color(0, 0, 0));
        cantidadDoblajesSC.setText("0");
        Panel.add(cantidadDoblajesSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, 20, -1));

        cantidadPlotTwistsSC.setBackground(new java.awt.Color(153, 153, 153));
        cantidadPlotTwistsSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        cantidadPlotTwistsSC.setForeground(new java.awt.Color(0, 0, 0));
        cantidadPlotTwistsSC.setText("0");
        Panel.add(cantidadPlotTwistsSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 450, 20, -1));

        jLabel46.setBackground(new java.awt.Color(153, 153, 153));
        jLabel46.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("/");
        Panel.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 450, 20, -1));

        maxPlotTwistsSC.setBackground(new java.awt.Color(153, 153, 153));
        maxPlotTwistsSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        maxPlotTwistsSC.setForeground(new java.awt.Color(0, 0, 0));
        maxPlotTwistsSC.setText("10");
        Panel.add(maxPlotTwistsSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 450, 20, -1));

        numPlotTwistsSC.setBackground(new java.awt.Color(153, 153, 153));
        numPlotTwistsSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        numPlotTwistsSC.setForeground(new java.awt.Color(0, 0, 0));
        numPlotTwistsSC.setText("0");
        Panel.add(numPlotTwistsSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 450, -1, -1));

        agregarPlotTwistSC.setBackground(new java.awt.Color(51, 51, 51));
        agregarPlotTwistSC.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        agregarPlotTwistSC.setForeground(new java.awt.Color(255, 255, 255));
        agregarPlotTwistSC.setText("+");
        agregarPlotTwistSC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPlotTwistSCActionPerformed(evt);
            }
        });
        Panel.add(agregarPlotTwistSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 440, 40, 30));

        eliminarPlotTwistSC.setBackground(new java.awt.Color(51, 51, 51));
        eliminarPlotTwistSC.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        eliminarPlotTwistSC.setForeground(new java.awt.Color(255, 255, 255));
        eliminarPlotTwistSC.setText("-");
        eliminarPlotTwistSC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarPlotTwistSCActionPerformed(evt);
            }
        });
        Panel.add(eliminarPlotTwistSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 440, 40, 30));

        salarioPlotTwistsSC.setBackground(new java.awt.Color(153, 153, 153));
        salarioPlotTwistsSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        salarioPlotTwistsSC.setForeground(new java.awt.Color(0, 0, 0));
        salarioPlotTwistsSC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salarioPlotTwistsSC.setText("0");
        Panel.add(salarioPlotTwistsSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 440, 50, 30));

        salarioEnsambladoresSC.setBackground(new java.awt.Color(153, 153, 153));
        salarioEnsambladoresSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        salarioEnsambladoresSC.setForeground(new java.awt.Color(0, 0, 0));
        salarioEnsambladoresSC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salarioEnsambladoresSC.setText("0");
        Panel.add(salarioEnsambladoresSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 500, 50, 30));

        eliminarEnsambladorSC.setBackground(new java.awt.Color(51, 51, 51));
        eliminarEnsambladorSC.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        eliminarEnsambladorSC.setForeground(new java.awt.Color(255, 255, 255));
        eliminarEnsambladorSC.setText("-");
        eliminarEnsambladorSC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarEnsambladorSCActionPerformed(evt);
            }
        });
        Panel.add(eliminarEnsambladorSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 500, 40, 30));

        agregarEnsambladorSC.setBackground(new java.awt.Color(51, 51, 51));
        agregarEnsambladorSC.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        agregarEnsambladorSC.setForeground(new java.awt.Color(255, 255, 255));
        agregarEnsambladorSC.setText("+");
        agregarEnsambladorSC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarEnsambladorSCActionPerformed(evt);
            }
        });
        Panel.add(agregarEnsambladorSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 500, 40, 30));

        numEnsambladoresSC.setBackground(new java.awt.Color(153, 153, 153));
        numEnsambladoresSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        numEnsambladoresSC.setForeground(new java.awt.Color(0, 0, 0));
        numEnsambladoresSC.setText("0");
        Panel.add(numEnsambladoresSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 510, 20, -1));

        eliminarGuionistaCN.setBackground(new java.awt.Color(51, 51, 51));
        eliminarGuionistaCN.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        eliminarGuionistaCN.setForeground(new java.awt.Color(255, 255, 255));
        eliminarGuionistaCN.setText("-");
        eliminarGuionistaCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarGuionistaCNActionPerformed(evt);
            }
        });
        Panel.add(eliminarGuionistaCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 280, 40, 30));

        agregarGuionistaCN.setBackground(new java.awt.Color(51, 51, 51));
        agregarGuionistaCN.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        agregarGuionistaCN.setForeground(new java.awt.Color(255, 255, 255));
        agregarGuionistaCN.setText("+");
        agregarGuionistaCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarGuionistaCNActionPerformed(evt);
            }
        });
        Panel.add(agregarGuionistaCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 280, 40, 30));

        eliminarDisenadorCN.setBackground(new java.awt.Color(51, 51, 51));
        eliminarDisenadorCN.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        eliminarDisenadorCN.setForeground(new java.awt.Color(255, 255, 255));
        eliminarDisenadorCN.setText("-");
        eliminarDisenadorCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarDisenadorCNActionPerformed(evt);
            }
        });
        Panel.add(eliminarDisenadorCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 320, 40, 30));

        agregarDisenadorCN.setBackground(new java.awt.Color(51, 51, 51));
        agregarDisenadorCN.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        agregarDisenadorCN.setForeground(new java.awt.Color(255, 255, 255));
        agregarDisenadorCN.setText("+");
        agregarDisenadorCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarDisenadorCNActionPerformed(evt);
            }
        });
        Panel.add(agregarDisenadorCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 320, 40, 30));

        eliminarAnimadorCN.setBackground(new java.awt.Color(51, 51, 51));
        eliminarAnimadorCN.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        eliminarAnimadorCN.setForeground(new java.awt.Color(255, 255, 255));
        eliminarAnimadorCN.setText("-");
        eliminarAnimadorCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarAnimadorCNActionPerformed(evt);
            }
        });
        Panel.add(eliminarAnimadorCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 360, 40, 30));

        agregarAnimadorCN.setBackground(new java.awt.Color(51, 51, 51));
        agregarAnimadorCN.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        agregarAnimadorCN.setForeground(new java.awt.Color(255, 255, 255));
        agregarAnimadorCN.setText("+");
        agregarAnimadorCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarAnimadorCNActionPerformed(evt);
            }
        });
        Panel.add(agregarAnimadorCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 360, 40, 30));

        eliminarActorCN.setBackground(new java.awt.Color(51, 51, 51));
        eliminarActorCN.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        eliminarActorCN.setForeground(new java.awt.Color(255, 255, 255));
        eliminarActorCN.setText("-");
        eliminarActorCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActorCNActionPerformed(evt);
            }
        });
        Panel.add(eliminarActorCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 400, 40, 30));

        agregarActorCN.setBackground(new java.awt.Color(51, 51, 51));
        agregarActorCN.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        agregarActorCN.setForeground(new java.awt.Color(255, 255, 255));
        agregarActorCN.setText("+");
        agregarActorCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActorCNActionPerformed(evt);
            }
        });
        Panel.add(agregarActorCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 400, 40, 30));

        maxEscenariosCN.setBackground(new java.awt.Color(153, 153, 153));
        maxEscenariosCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        maxEscenariosCN.setForeground(new java.awt.Color(0, 0, 0));
        maxEscenariosCN.setText("20");
        Panel.add(maxEscenariosCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 330, 20, -1));

        jLabel10.setBackground(new java.awt.Color(153, 153, 153));
        jLabel10.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Ensambladores");
        Panel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 480, -1, -1));

        agregarEnsambladorCN.setBackground(new java.awt.Color(51, 51, 51));
        agregarEnsambladorCN.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        agregarEnsambladorCN.setForeground(new java.awt.Color(255, 255, 255));
        agregarEnsambladorCN.setText("+");
        agregarEnsambladorCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarEnsambladorCNActionPerformed(evt);
            }
        });
        Panel.add(agregarEnsambladorCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 500, 40, 30));

        eliminarEnsambladorCN.setBackground(new java.awt.Color(51, 51, 51));
        eliminarEnsambladorCN.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        eliminarEnsambladorCN.setForeground(new java.awt.Color(255, 255, 255));
        eliminarEnsambladorCN.setText("-");
        eliminarEnsambladorCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarEnsambladorCNActionPerformed(evt);
            }
        });
        Panel.add(eliminarEnsambladorCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 500, 40, 30));

        cantidadCapitulosCN.setBackground(new java.awt.Color(153, 153, 153));
        cantidadCapitulosCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        cantidadCapitulosCN.setForeground(new java.awt.Color(0, 0, 0));
        cantidadCapitulosCN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantidadCapitulosCN.setText("0");
        Panel.add(cantidadCapitulosCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 190, 50, 20));

        jLabel26.setBackground(new java.awt.Color(153, 153, 153));
        jLabel26.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Cartoon Network");
        Panel.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 156, 170, 30));

        jLabel24.setBackground(new java.awt.Color(153, 153, 153));
        jLabel24.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Trabajadores");
        Panel.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 250, -1, -1));

        jLabel32.setBackground(new java.awt.Color(153, 153, 153));
        jLabel32.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(0, 0, 0));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText("Faltas");
        Panel.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 60, -1));

        faltasPMSC.setBackground(new java.awt.Color(153, 153, 153));
        faltasPMSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        faltasPMSC.setForeground(new java.awt.Color(0, 0, 0));
        faltasPMSC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        faltasPMSC.setText("0");
        Panel.add(faltasPMSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, 60, 20));

        faltasPMCN.setBackground(new java.awt.Color(153, 153, 153));
        faltasPMCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        faltasPMCN.setForeground(new java.awt.Color(0, 0, 0));
        faltasPMCN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        faltasPMCN.setText("0");
        Panel.add(faltasPMCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 60, 20));
        Panel.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 310, 20, -1));

        cantidadCapitulosSC.setBackground(new java.awt.Color(153, 153, 153));
        cantidadCapitulosSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        cantidadCapitulosSC.setForeground(new java.awt.Color(0, 0, 0));
        cantidadCapitulosSC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantidadCapitulosSC.setText("0");
        Panel.add(cantidadCapitulosSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 390, 50, 30));

        jLabel48.setBackground(new java.awt.Color(153, 153, 153));
        jLabel48.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(0, 0, 0));
        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setText("Star Channel");
        Panel.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 366, 170, 20));

        jLabel49.setBackground(new java.awt.Color(153, 153, 153));
        jLabel49.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Director esta...");
        Panel.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 196, 120, 30));

        jLabel50.setBackground(new java.awt.Color(153, 153, 153));
        jLabel50.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("PM esta... ");
        Panel.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, -1, 20));

        jLabel51.setBackground(new java.awt.Color(153, 153, 153));
        jLabel51.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Star Channel");
        Panel.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, -1, -1));

        estadoDirectorCN.setBackground(new java.awt.Color(153, 153, 153));
        estadoDirectorCN.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        estadoDirectorCN.setForeground(new java.awt.Color(0, 0, 0));
        estadoDirectorCN.setText("null");
        Panel.add(estadoDirectorCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 200, 110, 20));

        sueldoDCN.setBackground(new java.awt.Color(153, 153, 153));
        sueldoDCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        sueldoDCN.setForeground(new java.awt.Color(0, 0, 0));
        sueldoDCN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sueldoDCN.setText("0");
        Panel.add(sueldoDCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 200, 50, 20));

        estadoDirectorSC.setBackground(new java.awt.Color(153, 153, 153));
        estadoDirectorSC.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        estadoDirectorSC.setForeground(new java.awt.Color(0, 0, 0));
        estadoDirectorSC.setText("null");
        Panel.add(estadoDirectorSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 200, 100, 20));

        estadoPMSC.setBackground(new java.awt.Color(153, 153, 153));
        estadoPMSC.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        estadoPMSC.setForeground(new java.awt.Color(0, 0, 0));
        estadoPMSC.setText("null");
        Panel.add(estadoPMSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, 100, 20));

        iniciarEstudios.setBackground(new java.awt.Color(51, 51, 51));
        iniciarEstudios.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        iniciarEstudios.setForeground(new java.awt.Color(255, 255, 255));
        iniciarEstudios.setText("Iniciar Simulacion");
        iniciarEstudios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarEstudiosActionPerformed(evt);
            }
        });
        Panel.add(iniciarEstudios, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 160, 40));

        jLabel38.setBackground(new java.awt.Color(153, 153, 153));
        jLabel38.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("Trabajadores");
        Panel.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 250, -1, -1));

        jLabel58.setBackground(new java.awt.Color(153, 153, 153));
        jLabel58.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(0, 0, 0));
        jLabel58.setText("Diseñadores");
        Panel.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, -1, -1));

        numDisenadoresCN.setBackground(new java.awt.Color(153, 153, 153));
        numDisenadoresCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        numDisenadoresCN.setForeground(new java.awt.Color(0, 0, 0));
        numDisenadoresCN.setText("0");
        Panel.add(numDisenadoresCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 330, -1, -1));

        jLabel14.setBackground(new java.awt.Color(153, 153, 153));
        jLabel14.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("Animadores");
        Panel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 370, 120, -1));

        jLabel15.setBackground(new java.awt.Color(153, 153, 153));
        jLabel15.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Actores");
        Panel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 410, -1, -1));

        numGuionistasCN.setBackground(new java.awt.Color(153, 153, 153));
        numGuionistasCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        numGuionistasCN.setForeground(new java.awt.Color(0, 0, 0));
        numGuionistasCN.setText("0");
        Panel.add(numGuionistasCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 290, 20, -1));

        cantidadEscenariosCN.setBackground(new java.awt.Color(153, 153, 153));
        cantidadEscenariosCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        cantidadEscenariosCN.setForeground(new java.awt.Color(0, 0, 0));
        cantidadEscenariosCN.setText("0");
        Panel.add(cantidadEscenariosCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 330, 20, -1));

        jLabel22.setBackground(new java.awt.Color(153, 153, 153));
        jLabel22.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("/");
        Panel.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 330, 20, -1));

        numEnsambladoresCN.setBackground(new java.awt.Color(153, 153, 153));
        numEnsambladoresCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        numEnsambladoresCN.setForeground(new java.awt.Color(0, 0, 0));
        numEnsambladoresCN.setText("0");
        Panel.add(numEnsambladoresCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 510, 20, -1));

        cantidadGuionesCN.setBackground(new java.awt.Color(153, 153, 153));
        cantidadGuionesCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        cantidadGuionesCN.setForeground(new java.awt.Color(0, 0, 0));
        cantidadGuionesCN.setText("0");
        Panel.add(cantidadGuionesCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 20, -1));

        jLabel29.setBackground(new java.awt.Color(153, 153, 153));
        jLabel29.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("/");
        Panel.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 20, -1));

        maxAnimacionesCN.setBackground(new java.awt.Color(153, 153, 153));
        maxAnimacionesCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        maxAnimacionesCN.setForeground(new java.awt.Color(0, 0, 0));
        maxAnimacionesCN.setText("55");
        Panel.add(maxAnimacionesCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 370, 20, -1));

        cantidadAnimacionesCN.setBackground(new java.awt.Color(153, 153, 153));
        cantidadAnimacionesCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        cantidadAnimacionesCN.setForeground(new java.awt.Color(0, 0, 0));
        cantidadAnimacionesCN.setText("0");
        Panel.add(cantidadAnimacionesCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 370, 20, -1));

        jLabel40.setBackground(new java.awt.Color(153, 153, 153));
        jLabel40.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(0, 0, 0));
        jLabel40.setText("/");
        Panel.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 20, -1));

        maxDoblajesCN.setBackground(new java.awt.Color(153, 153, 153));
        maxDoblajesCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        maxDoblajesCN.setForeground(new java.awt.Color(0, 0, 0));
        maxDoblajesCN.setText("35");
        Panel.add(maxDoblajesCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 410, 20, -1));

        cantidadDoblajesCN.setBackground(new java.awt.Color(153, 153, 153));
        cantidadDoblajesCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        cantidadDoblajesCN.setForeground(new java.awt.Color(0, 0, 0));
        cantidadDoblajesCN.setText("0");
        Panel.add(cantidadDoblajesCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 410, 20, -1));

        jLabel43.setBackground(new java.awt.Color(153, 153, 153));
        jLabel43.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("/");
        Panel.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 410, 20, -1));

        estadoPMCN.setBackground(new java.awt.Color(153, 153, 153));
        estadoPMCN.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        estadoPMCN.setForeground(new java.awt.Color(0, 0, 0));
        estadoPMCN.setText("null");
        Panel.add(estadoPMCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 170, 110, 20));

        sueldoPMSC.setBackground(new java.awt.Color(153, 153, 153));
        sueldoPMSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        sueldoPMSC.setForeground(new java.awt.Color(0, 0, 0));
        sueldoPMSC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sueldoPMSC.setText("0");
        Panel.add(sueldoPMSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 170, 50, 20));

        maxGuionesCN.setBackground(new java.awt.Color(153, 153, 153));
        maxGuionesCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        maxGuionesCN.setForeground(new java.awt.Color(0, 0, 0));
        maxGuionesCN.setText("25");
        Panel.add(maxGuionesCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 290, 20, -1));

        salarioGuionistasCN.setBackground(new java.awt.Color(153, 153, 153));
        salarioGuionistasCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        salarioGuionistasCN.setForeground(new java.awt.Color(0, 0, 0));
        salarioGuionistasCN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salarioGuionistasCN.setText("0");
        Panel.add(salarioGuionistasCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 280, 50, 30));

        salarioDisenadoresCN.setBackground(new java.awt.Color(153, 153, 153));
        salarioDisenadoresCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        salarioDisenadoresCN.setForeground(new java.awt.Color(0, 0, 0));
        salarioDisenadoresCN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salarioDisenadoresCN.setText("0");
        Panel.add(salarioDisenadoresCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 320, 50, 30));

        salarioAnimadoresCN.setBackground(new java.awt.Color(153, 153, 153));
        salarioAnimadoresCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        salarioAnimadoresCN.setForeground(new java.awt.Color(0, 0, 0));
        salarioAnimadoresCN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salarioAnimadoresCN.setText("0");
        Panel.add(salarioAnimadoresCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 360, 50, 30));

        jLabel12.setBackground(new java.awt.Color(153, 153, 153));
        jLabel12.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Capacidad");
        Panel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 250, 90, -1));

        jLabel60.setBackground(new java.awt.Color(153, 153, 153));
        jLabel60.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 0, 0));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText("Faltas");
        Panel.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 140, 60, -1));

        sueldoDSC.setBackground(new java.awt.Color(153, 153, 153));
        sueldoDSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        sueldoDSC.setForeground(new java.awt.Color(0, 0, 0));
        sueldoDSC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sueldoDSC.setText("0");
        Panel.add(sueldoDSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 200, 50, 20));

        jLabel33.setBackground(new java.awt.Color(153, 153, 153));
        jLabel33.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 0, 0));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText("Sueldo");
        Panel.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, 60, -1));

        sueldoPMCN.setBackground(new java.awt.Color(153, 153, 153));
        sueldoPMCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        sueldoPMCN.setForeground(new java.awt.Color(0, 0, 0));
        sueldoPMCN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        sueldoPMCN.setText("0");
        Panel.add(sueldoPMCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 170, 50, 20));

        jLabel61.setBackground(new java.awt.Color(153, 153, 153));
        jLabel61.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(0, 0, 0));
        jLabel61.setText("Sueldo");
        Panel.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 140, 80, -1));

        jLabel16.setBackground(new java.awt.Color(153, 153, 153));
        jLabel16.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Ensambladores");
        Panel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 480, -1, -1));

        jLabel47.setBackground(new java.awt.Color(153, 153, 153));
        jLabel47.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setText("Capitulos PT");
        Panel.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 410, 90, 40));

        numActoresCN.setBackground(new java.awt.Color(153, 153, 153));
        numActoresCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        numActoresCN.setForeground(new java.awt.Color(0, 0, 0));
        numActoresCN.setText("0");
        Panel.add(numActoresCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 410, -1, -1));

        salarioActoresCN.setBackground(new java.awt.Color(153, 153, 153));
        salarioActoresCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        salarioActoresCN.setForeground(new java.awt.Color(0, 0, 0));
        salarioActoresCN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salarioActoresCN.setText("0");
        Panel.add(salarioActoresCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 400, 50, 30));

        salarioEnsambladoresCN.setBackground(new java.awt.Color(153, 153, 153));
        salarioEnsambladoresCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        salarioEnsambladoresCN.setForeground(new java.awt.Color(0, 0, 0));
        salarioEnsambladoresCN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salarioEnsambladoresCN.setText("0");
        Panel.add(salarioEnsambladoresCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 500, 50, 30));

        jLabel6.setBackground(new java.awt.Color(153, 153, 153));
        jLabel6.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Estudio 2");
        Panel.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 346, 170, 20));

        jLabel13.setBackground(new java.awt.Color(153, 153, 153));
        jLabel13.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Estudio 1");
        Panel.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 130, 170, 40));

        jLabel19.setBackground(new java.awt.Color(153, 153, 153));
        jLabel19.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Plot Twist");
        Panel.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 450, -1, -1));

        cantidadPlotTwistsCN.setBackground(new java.awt.Color(153, 153, 153));
        cantidadPlotTwistsCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        cantidadPlotTwistsCN.setForeground(new java.awt.Color(0, 0, 0));
        cantidadPlotTwistsCN.setText("0");
        Panel.add(cantidadPlotTwistsCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 450, 20, -1));

        jLabel36.setBackground(new java.awt.Color(153, 153, 153));
        jLabel36.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 0, 0));
        jLabel36.setText("Capitulos PT");
        Panel.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 210, 90, 30));

        jLabel44.setBackground(new java.awt.Color(153, 153, 153));
        jLabel44.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("/");
        Panel.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 450, 20, -1));

        maxPlotTwistsCN.setBackground(new java.awt.Color(153, 153, 153));
        maxPlotTwistsCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        maxPlotTwistsCN.setForeground(new java.awt.Color(0, 0, 0));
        maxPlotTwistsCN.setText("10");
        Panel.add(maxPlotTwistsCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 450, 20, -1));

        numPlotTwistsCN.setBackground(new java.awt.Color(153, 153, 153));
        numPlotTwistsCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        numPlotTwistsCN.setForeground(new java.awt.Color(0, 0, 0));
        numPlotTwistsCN.setText("0");
        Panel.add(numPlotTwistsCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 450, -1, -1));

        agregarPlotTwistCN.setBackground(new java.awt.Color(51, 51, 51));
        agregarPlotTwistCN.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        agregarPlotTwistCN.setForeground(new java.awt.Color(255, 255, 255));
        agregarPlotTwistCN.setText("+");
        agregarPlotTwistCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarPlotTwistCNActionPerformed(evt);
            }
        });
        Panel.add(agregarPlotTwistCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 440, 40, 30));

        eliminarPlotTwistCN.setBackground(new java.awt.Color(51, 51, 51));
        eliminarPlotTwistCN.setFont(new java.awt.Font("NSimSun", 0, 12)); // NOI18N
        eliminarPlotTwistCN.setForeground(new java.awt.Color(255, 255, 255));
        eliminarPlotTwistCN.setText("-");
        eliminarPlotTwistCN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarPlotTwistCNActionPerformed(evt);
            }
        });
        Panel.add(eliminarPlotTwistCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 440, 40, 30));

        salarioPlotTwistsCN.setBackground(new java.awt.Color(153, 153, 153));
        salarioPlotTwistsCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        salarioPlotTwistsCN.setForeground(new java.awt.Color(0, 0, 0));
        salarioPlotTwistsCN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salarioPlotTwistsCN.setText("0");
        Panel.add(salarioPlotTwistsCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 440, 50, 30));

        jPanel1.setBackground(new java.awt.Color(102, 0, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Poor Richard", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("ESTUDIOS");
        jLabel17.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 40));

        Panel.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 40));

        jPanel3.setBackground(new java.awt.Color(51, 0, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        irSimulacionButton.setBackground(new java.awt.Color(0, 0, 0));
        irSimulacionButton.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        irSimulacionButton.setForeground(new java.awt.Color(204, 204, 204));
        irSimulacionButton.setText("Simulacion");
        irSimulacionButton.setBorder(null);
        irSimulacionButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        irSimulacionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irSimulacionButtonActionPerformed(evt);
            }
        });
        jPanel3.add(irSimulacionButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 40, 130, 40));

        irDashboardButton.setBackground(new java.awt.Color(0, 0, 0));
        irDashboardButton.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        irDashboardButton.setForeground(new java.awt.Color(204, 204, 204));
        irDashboardButton.setText("Dashboard");
        irDashboardButton.setBorder(null);
        irDashboardButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        irDashboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irDashboardButtonActionPerformed(evt);
            }
        });
        jPanel3.add(irDashboardButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 90, 130, 40));

        irParametrosButton.setBackground(new java.awt.Color(0, 0, 0));
        irParametrosButton.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        irParametrosButton.setForeground(new java.awt.Color(204, 204, 204));
        irParametrosButton.setText("Parametros");
        irParametrosButton.setBorder(null);
        irParametrosButton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        irParametrosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irParametrosButtonActionPerformed(evt);
            }
        });
        jPanel3.add(irParametrosButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 140, 130, 40));

        Panel.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 110, 500));

        jLabel5.setBackground(new java.awt.Color(153, 153, 153));
        jLabel5.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Neto");
        Panel.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 300, 50, 20));

        jLabel7.setBackground(new java.awt.Color(153, 153, 153));
        jLabel7.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Capitulos");
        Panel.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 390, 80, 30));

        cantidadCapitulosPTCN.setBackground(new java.awt.Color(153, 153, 153));
        cantidadCapitulosPTCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        cantidadCapitulosPTCN.setForeground(new java.awt.Color(0, 0, 0));
        cantidadCapitulosPTCN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantidadCapitulosPTCN.setText("0");
        Panel.add(cantidadCapitulosPTCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 210, 50, 30));

        jLabel18.setBackground(new java.awt.Color(153, 153, 153));
        jLabel18.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("Ganancia");
        Panel.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 270, 80, 20));

        netoCN.setBackground(new java.awt.Color(153, 153, 153));
        netoCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        netoCN.setForeground(new java.awt.Color(0, 0, 0));
        netoCN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        netoCN.setText("0");
        Panel.add(netoCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 300, 50, 20));

        gastosCN.setBackground(new java.awt.Color(153, 153, 153));
        gastosCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        gastosCN.setForeground(new java.awt.Color(0, 0, 0));
        gastosCN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gastosCN.setText("0");
        Panel.add(gastosCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 240, 50, 20));

        gananciasCN.setBackground(new java.awt.Color(153, 153, 153));
        gananciasCN.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        gananciasCN.setForeground(new java.awt.Color(0, 0, 0));
        gananciasCN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gananciasCN.setText("0");
        Panel.add(gananciasCN, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 270, 50, 20));

        jLabel23.setBackground(new java.awt.Color(153, 153, 153));
        jLabel23.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Neto");
        Panel.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 510, 50, 20));

        jLabel25.setBackground(new java.awt.Color(153, 153, 153));
        jLabel25.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Gastos");
        Panel.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 450, 50, 20));

        jLabel27.setBackground(new java.awt.Color(153, 153, 153));
        jLabel27.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 0, 0));
        jLabel27.setText("Ganancia");
        Panel.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 480, 80, 20));

        netoSC.setBackground(new java.awt.Color(153, 153, 153));
        netoSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        netoSC.setForeground(new java.awt.Color(0, 0, 0));
        netoSC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        netoSC.setText("0");
        Panel.add(netoSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 510, 50, 20));

        gastosSC.setBackground(new java.awt.Color(153, 153, 153));
        gastosSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        gastosSC.setForeground(new java.awt.Color(0, 0, 0));
        gastosSC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gastosSC.setText("0");
        Panel.add(gastosSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 450, 50, 20));

        gananciasSC.setBackground(new java.awt.Color(153, 153, 153));
        gananciasSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        gananciasSC.setForeground(new java.awt.Color(0, 0, 0));
        gananciasSC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gananciasSC.setText("0");
        Panel.add(gananciasSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 480, 50, 20));

        reiniciar.setBackground(new java.awt.Color(51, 51, 51));
        reiniciar.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        reiniciar.setForeground(new java.awt.Color(255, 255, 255));
        reiniciar.setText("Reiniciar");
        reiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reiniciarActionPerformed(evt);
            }
        });
        Panel.add(reiniciar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, -1, 40));

        aggDashboard.setBackground(new java.awt.Color(51, 51, 51));
        aggDashboard.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        aggDashboard.setForeground(new java.awt.Color(255, 255, 255));
        aggDashboard.setText("Guardar Corrida");
        aggDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aggDashboardActionPerformed(evt);
            }
        });
        Panel.add(aggDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 140, 40));

        jLabel1.setBackground(new java.awt.Color(102, 102, 102));
        jLabel1.setFont(new java.awt.Font("Poor Richard", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 102));
        jLabel1.setText("SIMULACION");
        Panel.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 50, 770, 20));
        Panel.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 770, 10));

        jLabel20.setBackground(new java.awt.Color(153, 153, 153));
        jLabel20.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Gastos");
        Panel.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 240, 50, 20));

        jLabel21.setBackground(new java.awt.Color(153, 153, 153));
        jLabel21.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Capitulos");
        Panel.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 190, 80, 20));

        jLabel28.setBackground(new java.awt.Color(153, 153, 153));
        jLabel28.setToolTipText("");
        jLabel28.setOpaque(true);
        Panel.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, 590, 410));

        cantidadCapitulosPTSC.setBackground(new java.awt.Color(153, 153, 153));
        cantidadCapitulosPTSC.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        cantidadCapitulosPTSC.setForeground(new java.awt.Color(0, 0, 0));
        cantidadCapitulosPTSC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantidadCapitulosPTSC.setText("0");
        Panel.add(cantidadCapitulosPTSC, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 410, 50, 40));

        jLabel30.setBackground(new java.awt.Color(153, 153, 153));
        jLabel30.setToolTipText("");
        jLabel30.setOpaque(true);
        Panel.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 340, 170, 200));

        jLabel31.setBackground(new java.awt.Color(153, 153, 153));
        jLabel31.setToolTipText("");
        jLabel31.setOpaque(true);
        Panel.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 130, 170, 200));

        jLabel37.setBackground(new java.awt.Color(153, 153, 153));
        jLabel37.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(0, 0, 0));
        jLabel37.setText("Dias restantes para lanzamiento:");
        Panel.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, 230, 60));

        diasLanzamiento.setBackground(new java.awt.Color(153, 153, 153));
        diasLanzamiento.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        diasLanzamiento.setForeground(new java.awt.Color(0, 0, 0));
        diasLanzamiento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        diasLanzamiento.setText("0");
        Panel.add(diasLanzamiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 80, 90, 40));

        jLabel34.setBackground(new java.awt.Color(153, 153, 153));
        jLabel34.setFont(new java.awt.Font("NSimSun", 0, 14)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 0, 0));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Faltas");
        Panel.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 140, 60, -1));

        getContentPane().add(Panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 550));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void eliminarGuionistaCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarGuionistaCNActionPerformed
    }//GEN-LAST:event_eliminarGuionistaCNActionPerformed

    private void agregarGuionistaCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarGuionistaCNActionPerformed
        agregarTrabajadorCN(numGuionistasCN,0);
    }//GEN-LAST:event_agregarGuionistaCNActionPerformed

    private void eliminarDisenadorCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarDisenadorCNActionPerformed
    }//GEN-LAST:event_eliminarDisenadorCNActionPerformed

    private void agregarDisenadorCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarDisenadorCNActionPerformed
        agregarTrabajadorCN(numDisenadoresCN,1);

    }//GEN-LAST:event_agregarDisenadorCNActionPerformed

    private void eliminarAnimadorCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarAnimadorCNActionPerformed
    }//GEN-LAST:event_eliminarAnimadorCNActionPerformed

    private void agregarAnimadorCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarAnimadorCNActionPerformed
        agregarTrabajadorCN(numAnimadoresCN,2);
    }//GEN-LAST:event_agregarAnimadorCNActionPerformed

    private void eliminarActorCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActorCNActionPerformed
    }//GEN-LAST:event_eliminarActorCNActionPerformed

    private void agregarActorCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActorCNActionPerformed
        agregarTrabajadorCN(numActoresCN,3);
    }//GEN-LAST:event_agregarActorCNActionPerformed

    private void agregarEnsambladorCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarEnsambladorCNActionPerformed
        agregarTrabajadorCN(numEnsambladoresCN,5);
    }//GEN-LAST:event_agregarEnsambladorCNActionPerformed

    private void eliminarEnsambladorCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarEnsambladorCNActionPerformed

    }//GEN-LAST:event_eliminarEnsambladorCNActionPerformed

    private void iniciarEstudiosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarEstudiosActionPerformed
        
        // Se habilitan los botones de ambos estudios 
        agregarGuionistaCN.setEnabled(true);
        agregarDisenadorCN.setEnabled(true);
        agregarAnimadorCN.setEnabled(true);
        agregarActorCN.setEnabled(true);
        agregarPlotTwistCN.setEnabled(true);
        agregarEnsambladorCN.setEnabled(true);
        
        eliminarGuionistaCN.setEnabled(true);
        eliminarDisenadorCN.setEnabled(true);
        eliminarAnimadorCN.setEnabled(true);
        eliminarActorCN.setEnabled(true);
        eliminarPlotTwistCN.setEnabled(true);
        eliminarEnsambladorCN.setEnabled(true);
        
        agregarGuionistaSC.setEnabled(true);
        agregarDisenadorSC.setEnabled(true);
        agregarAnimadorSC.setEnabled(true);
        agregarActorSC.setEnabled(true);
        agregarPlotTwistSC.setEnabled(true);
        agregarEnsambladorSC.setEnabled(true);
        
        eliminarGuionistaSC.setEnabled(true);
        eliminarDisenadorSC.setEnabled(true);
        eliminarAnimadorSC.setEnabled(true);
        eliminarActorSC.setEnabled(true);
        eliminarPlotTwistSC.setEnabled(true);
        eliminarEnsambladorSC.setEnabled(true);
        
        
        // Se inician los hilos de los arrays de trabajadores/ensambladores, PM y director 
        // Cartoon Network
        // Inician los guionistas
        for (int i = 0; i < guionistasCN.length; i++) {
            // Va recorriendo el array y si la posicion i no esta vacia es que tiene un hilo y lo inicia
            if (guionistasCN[i] != null) {
                guionistasCN[i].start();
            }
        }
        
        // Inician los disenadores
        for (int i = 0; i < disenadoresCN.length; i++) {
            if (disenadoresCN[i] != null) {
                disenadoresCN[i].start();
            }
        }
        
        // Inician los animadores
        for (int i = 0; i < animadoresCN.length; i++) {
            if (animadoresCN[i] != null) {
                animadoresCN[i].start();
            }
        }
        
        // Inician los actores
        for (int i = 0; i < actoresCN.length; i++) {
            if (actoresCN[i] != null) {
                actoresCN[i].start();
            }
        }
        // Inician los guionistas de Plot Twists
        for (int i = 0; i < plottwistsCN.length; i++) {
            if (plottwistsCN[i] != null) {
                plottwistsCN[i].start();
            }
        }
        // Inician los ensambladores
        for (int i = 0; i < ensambladoresCN.length; i++) {
            if (ensambladoresCN[i] != null) {
                ensambladoresCN[i].start();
            }
        }
        
        // Star Channel
        // Inician los guionistas
        for (int i = 0; i < guionistasSC.length; i++) {
            if (guionistasSC[i] != null) {
                guionistasSC[i].start();
            }
        }
        
        // Inician los disenadores
        for (int i = 0; i < disenadoresSC.length; i++) {
            if (disenadoresSC[i] != null) {
                disenadoresSC[i].start();
            }
        }
        
        // Inician los animadores
        for (int i = 0; i < animadoresSC.length; i++) {
            if (animadoresSC[i] != null) {
                animadoresSC[i].start();
            }
        }
        
        // Inician los actores
        for (int i = 0; i < actoresSC.length; i++) {
            if (actoresSC[i] != null) {
                actoresSC[i].start();
            }
        }
        // Inician los guionistas de Plot Twists
        for (int i = 0; i < plottwistsSC.length; i++) {
            if (plottwistsSC[i] != null) {
                plottwistsSC[i].start();
            }
        }
        // Inician los ensambladores
        for (int i = 0; i < ensambladoresSC.length; i++) {
            if (ensambladoresSC[i] != null) {
                ensambladoresSC[i].start();
            }
        }
       
        
        
    }//GEN-LAST:event_iniciarEstudiosActionPerformed

    private void agregarPlotTwistCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPlotTwistCNActionPerformed
        agregarTrabajadorCN(numPlotTwistsCN,4);
    }//GEN-LAST:event_agregarPlotTwistCNActionPerformed

    private void eliminarPlotTwistCNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarPlotTwistCNActionPerformed
    }//GEN-LAST:event_eliminarPlotTwistCNActionPerformed

    private void irSimulacionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irSimulacionButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_irSimulacionButtonActionPerformed

    private void irDashboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irDashboardButtonActionPerformed

        this.dispose();
        VentanaDashboard dashboard = new VentanaDashboard();
        dashboard.setVisible(true);
    }//GEN-LAST:event_irDashboardButtonActionPerformed

    private void irParametrosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irParametrosButtonActionPerformed
        this.dispose();
        VentanaParametros parametros = new VentanaParametros();
        parametros.setVisible(true);
    }//GEN-LAST:event_irParametrosButtonActionPerformed

    private void reiniciarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reiniciarActionPerformed
   
    }//GEN-LAST:event_reiniciarActionPerformed

    private void aggDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aggDashboardActionPerformed
        JOptionPane.showMessageDialog(rootPane, duracionDia);
    }//GEN-LAST:event_aggDashboardActionPerformed

    private void agregarGuionistaSCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarGuionistaSCActionPerformed
        agregarTrabajadorSC(numGuionistasSC, 0);
    }//GEN-LAST:event_agregarGuionistaSCActionPerformed

    private void eliminarGuionistaSCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarGuionistaSCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarGuionistaSCActionPerformed

    private void eliminarDisenadorSCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarDisenadorSCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarDisenadorSCActionPerformed

    private void agregarDisenadorSCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarDisenadorSCActionPerformed
        agregarTrabajadorSC(numDisenadoresSC, 1);
    }//GEN-LAST:event_agregarDisenadorSCActionPerformed

    private void agregarAnimadorSCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarAnimadorSCActionPerformed
        agregarTrabajadorSC(numAnimadoresSC, 2);
    }//GEN-LAST:event_agregarAnimadorSCActionPerformed

    private void eliminarAnimadorSCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarAnimadorSCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarAnimadorSCActionPerformed

    private void eliminarActorSCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActorSCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarActorSCActionPerformed

    private void agregarActorSCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActorSCActionPerformed
        agregarTrabajadorSC(numActoresSC, 3);
    }//GEN-LAST:event_agregarActorSCActionPerformed

    private void agregarPlotTwistSCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarPlotTwistSCActionPerformed
        agregarTrabajadorSC(numPlotTwistsSC, 4);
    }//GEN-LAST:event_agregarPlotTwistSCActionPerformed

    private void eliminarPlotTwistSCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarPlotTwistSCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarPlotTwistSCActionPerformed

    private void eliminarEnsambladorSCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarEnsambladorSCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eliminarEnsambladorSCActionPerformed

    private void agregarEnsambladorSCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarEnsambladorSCActionPerformed
        agregarTrabajadorSC(numEnsambladoresSC, 5);
    }//GEN-LAST:event_agregarEnsambladorSCActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaSimulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaSimulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaSimulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaSimulacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaSimulacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel;
    private javax.swing.JButton aggDashboard;
    private javax.swing.JButton agregarActorCN;
    private javax.swing.JButton agregarActorSC;
    private javax.swing.JButton agregarAnimadorCN;
    private javax.swing.JButton agregarAnimadorSC;
    private javax.swing.JButton agregarDisenadorCN;
    private javax.swing.JButton agregarDisenadorSC;
    private javax.swing.JButton agregarEnsambladorCN;
    private javax.swing.JButton agregarEnsambladorSC;
    private javax.swing.JButton agregarGuionistaCN;
    private javax.swing.JButton agregarGuionistaSC;
    private javax.swing.JButton agregarPlotTwistCN;
    private javax.swing.JButton agregarPlotTwistSC;
    public static javax.swing.JLabel cantidadAnimacionesCN;
    public static javax.swing.JLabel cantidadAnimacionesSC;
    public static javax.swing.JLabel cantidadCapitulosCN;
    public static javax.swing.JLabel cantidadCapitulosPTCN;
    public static javax.swing.JLabel cantidadCapitulosPTSC;
    public static javax.swing.JLabel cantidadCapitulosSC;
    public static javax.swing.JLabel cantidadDoblajesCN;
    public static javax.swing.JLabel cantidadDoblajesSC;
    public static javax.swing.JLabel cantidadEscenariosCN;
    public static javax.swing.JLabel cantidadEscenariosSC;
    public static javax.swing.JLabel cantidadGuionesCN;
    public static javax.swing.JLabel cantidadGuionesSC;
    public static javax.swing.JLabel cantidadPlotTwistsCN;
    public static javax.swing.JLabel cantidadPlotTwistsSC;
    private javax.swing.JLabel diasLanzamiento;
    private javax.swing.JButton eliminarActorCN;
    private javax.swing.JButton eliminarActorSC;
    private javax.swing.JButton eliminarAnimadorCN;
    private javax.swing.JButton eliminarAnimadorSC;
    private javax.swing.JButton eliminarDisenadorCN;
    private javax.swing.JButton eliminarDisenadorSC;
    private javax.swing.JButton eliminarEnsambladorCN;
    private javax.swing.JButton eliminarEnsambladorSC;
    private javax.swing.JButton eliminarGuionistaCN;
    private javax.swing.JButton eliminarGuionistaSC;
    private javax.swing.JButton eliminarPlotTwistCN;
    private javax.swing.JButton eliminarPlotTwistSC;
    private javax.swing.JLabel estadoDirectorCN;
    private javax.swing.JLabel estadoDirectorSC;
    private javax.swing.JLabel estadoPMCN;
    private javax.swing.JLabel estadoPMSC;
    private javax.swing.JLabel faltasPMCN;
    private javax.swing.JLabel faltasPMSC;
    private javax.swing.JLabel gananciasCN;
    private javax.swing.JLabel gananciasSC;
    private javax.swing.JLabel gastosCN;
    private javax.swing.JLabel gastosSC;
    private javax.swing.JButton iniciarEstudios;
    private javax.swing.JButton irDashboardButton;
    private javax.swing.JButton irParametrosButton;
    private javax.swing.JButton irSimulacionButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel maxAnimacionesCN;
    private javax.swing.JLabel maxAnimacionesSC;
    private javax.swing.JLabel maxDoblajesCN;
    private javax.swing.JLabel maxDoblajesSC;
    private javax.swing.JLabel maxEscenariosCN;
    private javax.swing.JLabel maxEscenariosSC;
    private javax.swing.JLabel maxGuionesCN;
    private javax.swing.JLabel maxGuionesSC;
    private javax.swing.JLabel maxPlotTwistsCN;
    private javax.swing.JLabel maxPlotTwistsSC;
    private javax.swing.JLabel netoCN;
    private javax.swing.JLabel netoSC;
    private javax.swing.JLabel numActoresCN;
    private javax.swing.JLabel numActoresSC;
    private javax.swing.JLabel numAnimadoresCN;
    private javax.swing.JLabel numAnimadoresSC;
    private javax.swing.JLabel numDisenadoresCN;
    private javax.swing.JLabel numDisenadoresSC;
    private javax.swing.JLabel numEnsambladoresCN;
    private javax.swing.JLabel numEnsambladoresSC;
    public static javax.swing.JLabel numGuionistasCN;
    private javax.swing.JLabel numGuionistasSC;
    private javax.swing.JLabel numPlotTwistsCN;
    private javax.swing.JLabel numPlotTwistsSC;
    private javax.swing.JButton reiniciar;
    public static javax.swing.JLabel salarioActoresCN;
    public static javax.swing.JLabel salarioActoresSC;
    public static javax.swing.JLabel salarioAnimadoresCN;
    public static javax.swing.JLabel salarioAnimadoresSC;
    public static javax.swing.JLabel salarioDisenadoresCN;
    public static javax.swing.JLabel salarioDisenadoresSC;
    public static javax.swing.JLabel salarioEnsambladoresCN;
    public static javax.swing.JLabel salarioEnsambladoresSC;
    public static javax.swing.JLabel salarioGuionistasCN;
    public static javax.swing.JLabel salarioGuionistasSC;
    public static javax.swing.JLabel salarioPlotTwistsCN;
    public static javax.swing.JLabel salarioPlotTwistsSC;
    private javax.swing.JLabel sueldoDCN;
    private javax.swing.JLabel sueldoDSC;
    private javax.swing.JLabel sueldoPMCN;
    private javax.swing.JLabel sueldoPMSC;
    // End of variables declaration//GEN-END:variables
}
