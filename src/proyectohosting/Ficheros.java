/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectohosting;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author Zarza
 */
public class Ficheros {
    PrintWriter fich;
    public void escribir(String nombreFich, ArrayList <String> datos) {
        int nLinea = 0;
        
        try{
            fich = new PrintWriter(new File(nombreFich));
        
        
        
        //for(int i=0;i<palabras.size();i++)
            for (String dato : datos) {
               
                fich.println(dato);
            } 
        }catch (IOException ex){
            System.err.println("Error de escritura "+ex);
        }finally{
                fich.close();
                }
        
    }
}
