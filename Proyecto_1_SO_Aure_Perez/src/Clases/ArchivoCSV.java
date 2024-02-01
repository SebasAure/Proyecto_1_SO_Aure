/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JOptionPane;
import com.csvreader.CsvWriter;

/**
 *
 * @author sebas
 */
public class ArchivoCSV {
    public String[] leerCSV() {
        String line;
        String text = "";
        File file = new File("test\\datos.csv");
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                while ((line = br.readLine()) != null) {
                    if (!line.isEmpty()) {
                        text += line + "\n";
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "ERROR AL LEER");
        }
        String[] data = separarData(text.split("\n"));
        return data;
    }
    
    public String[] separarData(String[] text) {
        if (text.length != 1) {
            String[] data = new String[text.length - 4];
            int j = 0;
            for (int i = 0; i < text.length; i++) {
                String[] line = text[i].split(",");
                if (line.length != 1) {
                    data[j] = line[1];
                    j++;
                }
            }
            return data;
        }
        return text;
    }
    
    public String[] generarData(String duracionDia,
            String guionesDrive, String escenariosDrive, String animacionesDrive, String doblajesDrive, String plotTwitsDrive,
            String guionistasCN, String disenadoresCN, String animadoresCN, String actoresCN, String plotTwistsCN, String ensambladoresCN,
            String guionistasSC, String disenadoresSC, String animadoresSC, String actoresSC, String plotTwistsSC, String ensambladoresSC) {
        
        String[] data = {
            "--TIEMPO--",
            "duracionDia, " + duracionDia,
            "--DRIVE--",
            "guionesDrive, " + guionesDrive,
            "escenariosDrive, " + escenariosDrive,
            "animacionesDrive, " + animacionesDrive,
            "doblajesDrive, " + doblajesDrive,
            "plotTwitsDrive, " + plotTwitsDrive,
            "--PRODUCTORES CN--",
            "guionistasCN, " + guionistasCN,
            "disenadoresCN, " + disenadoresCN,
            "animadoresCN, " + animadoresCN,
            "actoresCN, " + actoresCN,
            "plotTwitsCN, " + plotTwistsCN,
            "ensambladoresCN, " + ensambladoresCN,
            "--PRODUCTORES SC--",
            "guionistasSC, " + guionistasSC,
            "disenadoresSC, " + disenadoresSC,
            "animadoresSC, " + animadoresSC,
            "actoresSC, " + actoresSC,
            "plotTwitsSC, " + plotTwistsSC,
            "ensambladoresSC, " + ensambladoresSC,
        };
        
        return data;
    }
    
    public void GuardarCSVgenerarData(String duracionDia,
            String guionesDrive, String escenariosDrive, String animacionesDrive, String doblajesDrive, String plotTwitsDrive,
            String guionistasCN, String disenadoresCN, String animadoresCN, String actoresCN, String plotTwistsCN, String ensambladoresCN,
            String guionistasSC, String disenadoresSC, String animadoresSC, String actoresSC, String plotTwistsSC, String ensambladoresSC) {
        
        String[] data = generarData( duracionDia,
             guionesDrive, escenariosDrive, animacionesDrive, doblajesDrive, plotTwitsDrive,
             guionistasCN, disenadoresCN, animadoresCN, actoresCN, plotTwistsCN, ensambladoresCN,
             guionistasSC, disenadoresSC, animadoresSC, actoresSC, plotTwistsSC, ensambladoresSC);
        
        try {
            File file = new File("test\\datos.csv");
            if (!file.exists()) {
                file.createNewFile();
            } else {
                CsvWriter csvwriter = new CsvWriter("test\\datos.csv");
                for (int i = 0; i < data.length; i++) {
                    String [] line = data[i].split(",");
                    csvwriter.writeRecord(line);
                }
                csvwriter.close();
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "ERROR AL LEER");
        }
    }
    
    public static int contar(Object array[]){
        int contador=0;
        for (int i = 0; i < array.length; i++) {
            if (array[i]!=null) {
                contador++;
            }
        }
        return contador;
    }
}
