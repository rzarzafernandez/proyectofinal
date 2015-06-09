package proyectohosting;

import java.sql.*;
/**
 *
 * @author Zarza
 */
public class Conectar {
    Connection conect = null;
    
//Método que crea la conexión con la DB    
    public Connection conexSQL()
    {
       try {

         Class.forName("com.mysql.jdbc.Driver");

         conect = DriverManager.getConnection("jdbc:mysql://localhost/proyecto","root","root"); //getConnection(url,usuario,contraseña)
       } catch (SQLException | ClassNotFoundException ex) {
         System.out.println("Error: " + ex);
       }

       return conect;
    }
}