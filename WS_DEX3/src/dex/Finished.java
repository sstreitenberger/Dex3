package dex;
// annotations

import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import dex.ConnectionDB;
import dex.Orden;

@Path("/Finalizados.json")
public class Finished {
	
	
	private ConnectionDB con;
	private ArrayList<Orden> orden;

	@GET
	@Produces("application/json;charset=UTF-8")
	public String finishedJSON() throws Exception {
		con = new ConnectionDB();
		String respuesta = new String();	
		orden = new ArrayList<Orden>();
		orden = con.GetOrderFinal();
	
		for (int i = 0; i < orden.size(); i++)
		{
			if (i == 0)
			{
				respuesta = "[{\"Nombre\":\""+orden.get(i).getname()+"\",\"Pedido\":\""+orden.get(i).getChk()+"\"},";	
			}
			else if (i == orden.size()-1)
			{
				respuesta = respuesta.concat("{\"Nombre\":\""+orden.get(i).getname()+"\",\"Pedido\":\""+orden.get(i).getChk()+"\"}]");
			}
			else 
			{	
				respuesta = respuesta.concat("{\"Nombre\":\""+orden.get(i).getname()+"\",\"Pedido\":\""+orden.get(i).getChk()+"\"},");
			}
		}
		return respuesta;
	}
	
}