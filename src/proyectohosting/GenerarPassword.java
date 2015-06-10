/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectohosting;

/**
 *
 * @author Zarza
 */
public class GenerarPassword {
   
 
	public static String numeros = "0123456789"; 
	public static String mayusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
	public static String minusculas = "abcdefghijklmnopqrstuvwxyz";
        
 	public static String getPassword(int length) {
		return getPassword(numeros + mayusculas + minusculas, length);
	}
 
	public static String getPassword(String key, int length) {
		String password = "";
 
		for (int i = 0; i < length; i++) {
			password+=(key.charAt((int)(Math.random() * key.length())));
		}
 
		return password;
	}
}

