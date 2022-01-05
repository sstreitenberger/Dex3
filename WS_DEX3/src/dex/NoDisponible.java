package dex;
import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import dex.SKUND;

@Path("/nodisponibles.json")
public class NoDisponible {
	
	@GET
	@Produces("application/json;charset=UTF-8")
	public String preparationJSON() throws SQLException, Exception {
		
		SKUND nd = new SKUND();
		String respuesta = nd.obtenerNoDisponibles();
		return respuesta;
	}
	
	
}
