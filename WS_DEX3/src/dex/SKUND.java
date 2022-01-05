package dex;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import dex.ConnectionDB;
//import com.google.gson.Gson;
public class SKUND

{
	
	static File archivo = null;
    static FileReader fr = null;
    static BufferedReader br = null;
	public static String respuesta;


	 public String obtenerNoDisponibles() throws SQLException, Exception
	 {
		 ArrayList<String> ND =  ConnectionDB.GetProductND();
	     
	     if(ND.size() > 0)
		 {
	    	 for (int i = 0; i < ND.size(); i++)
	    	 {	
	    		 if (i == 0)
	    		 {
	    			 if(ND.size() > 1)
	    			 {
	    				 respuesta = "[{ \"SKU\": "+ND.get(i)+"}, ";	
	    			 }
	    			 else 
	    			 {
	    				 respuesta = "[{ \"SKU\": "+ND.get(i)+"}]";	
	    			 }
				    			
	    		 }
	    		 else if (i == ND.size()-1)
	    		 {
	    			 respuesta = respuesta.concat("{ \"SKU\": "+ND.get(i)+"}]");
	    		 }
	    		 else
	    		 {
	    			 respuesta = respuesta.concat("{ \"SKU\": "+ND.get(i)+"}, ");
	    		 } 				
	    	 }
		 }
	     else
	     {
	    	 respuesta = "{}";		
	     }
	    // Gson json = new Gson();
	     //String var = json.toJson(respuesta);
		 return respuesta; 
	 }
	
	
}
