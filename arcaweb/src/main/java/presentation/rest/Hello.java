package presentation.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class Hello {

	@GET
	@Path("/ciao")
	@Produces(MediaType.TEXT_PLAIN)
	public String direBonjour() {
		return "Ciao, sono un test di prova";
	}
}