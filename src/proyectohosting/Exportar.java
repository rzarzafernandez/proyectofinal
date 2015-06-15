/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectohosting;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Zarza
 */
public class Exportar {
    




public final void exportarClientes(String valor){
        
        String sql;
        ArrayList<String> datos = new ArrayList<>();
        
        Conectar mysql = new Conectar();
        Connection cn = mysql.conexSQL();
        sql = "SELECT nombre, apellidos, correo, DNI, fechaAlta FROM clientes "
                + "WHERE CONCAT (nombre, ' ', apellidos, ' ', correo, ' ', DNI, ' ', fechaAlta) LIKE '%"+valor+"%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                datos.add(rs.getString("nombre"));
                datos.add(rs.getString("apellidos"));
                datos.add(rs.getString("correo"));
                datos.add(rs.getString("DNI"));
                datos.add(rs.getString("fechaAlta"));
                               
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        Ficheros fich = new Ficheros();
        fich.escribir("clientes.txt", datos);
    }

public final void exportarDominios(String valor){
        
        String sql;
        ArrayList<String> datos = new ArrayList<>();
        
        Conectar mysql = new Conectar();
        Connection cn = mysql.conexSQL();
        sql = "SELECT propietario, dominio, fechaAlta, fechaBaja FROM dominios "
                + "WHERE CONCAT (dominio, ' ', fechaAlta, ' ', fechaBaja) LIKE '%"+valor+"%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                String dominio = rs.getString("dominio");
            String extension = dominio.substring(dominio.length()-3);
            String  extensionPrecio="";
            switch (extension){
                case ".es": extensionPrecio="7";
                    break;
                case "com": extensionPrecio="9";
                    break;
                case "org": extensionPrecio="11";
                    break;
                case "net": extensionPrecio="13";
                    break;                    
            }
                datos.add(rs.getString("dominio"));
                datos.add(extensionPrecio);
                datos.add(rs.getString("fechaAlta"));
                datos.add(rs.getString("fechaBaja"));
                datos.add(rs.getString("propietario"));
                               
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        Ficheros fich = new Ficheros();
        fich.escribir("dominios.txt", datos);
    }
public final void exportarHostings(String valor){
        
        String sql;
        ArrayList<String> datos = new ArrayList<>();
        
        Conectar mysql = new Conectar();
        Connection cn = mysql.conexSQL();
        sql = "SELECT propietario, dominioPrincipal, talla, fechaAlta, fechaBaja FROM hostings "
                + "WHERE CONCAT (propietario, ' ', dominioPrincipal, ' ', talla, ' ', fechaAlta, ' ', fechaBaja) LIKE '%"+valor+"%'";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
            String talla = rs.getString("talla");
            String tallaPrecio="0";
            
            switch (talla){
                case "S": tallaPrecio="25";
                    break;
                case "M": tallaPrecio="60";
                    break;
                case "L": tallaPrecio="110";
                    break;                                 
            }
                datos.add(rs.getString("dominioPrincipal"));
                datos.add(rs.getString("talla"));
                datos.add(tallaPrecio);
                datos.add(rs.getString("fechaAlta"));
                datos.add(rs.getString("fechaBaja"));
                datos.add(rs.getString("propietario"));
                               
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        Ficheros fich = new Ficheros();
        fich.escribir("hostings.txt", datos);
    }
}