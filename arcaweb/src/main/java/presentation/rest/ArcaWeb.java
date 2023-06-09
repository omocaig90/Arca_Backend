package presentation.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.*;
import presentation.pojo.Animale;
import business.Arca;
import integration.enumeratori.EnumSpecie;
import integration.pojo.AnimaleDto;

@Path("/animale")
public class ArcaWeb {

	@DELETE
	@Path("/sbarca")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sbarca(Animale animale) {
		Arca arca = new Arca();

		if (animale.getId() == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		if (arca.sbarca(animale.getId())) {
			return Response.ok(animale).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	@POST
	@Path("/imbarca")
	@Produces(MediaType.APPLICATION_JSON)
	public Response imbarca(Animale animale) {
		Arca arca = new Arca();
		AnimaleDto animaleDto = new AnimaleDto();

		if (animale.getId() == null || animale.getPeso() == null || animale.getSpecie() == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		animaleDto.setId(animale.getId());
		animaleDto.setPeso(animale.getPeso());
		animaleDto.setSpecie(animale.getSpecie());
		if (arca.imbarca(animaleDto)) {
			return Response.ok(animaleDto).build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	@GET
	@Path("/lista")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getLista() {
		Arca arca = new Arca();
		List<AnimaleDto> animaliImbarcati = new ArrayList<>();
		animaliImbarcati = arca.getAnimaliImbarcati();

		return Response.ok(animaliImbarcati).build();
	}
	
	@GET
	@Path("/speci")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSpeci() {
		Arca arca = new Arca();
		List<EnumSpecie> speci = new ArrayList<>();
		speci = arca.getSpeci();

		return Response.ok(speci).build();
	}
	
	@GET
	@Path("/animalesingolo")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAnimale(Animale animale) {
		Arca arca = new Arca();
		AnimaleDto animaleImbarcato;
		if (animale.getId() == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		animaleImbarcato=arca.getAnimaleImbarcato(animale.getId());
			return Response.ok(animaleImbarcato).build();
		
		
	}
	
	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAnimale(Animale animale) {
		Arca arca = new Arca();
		if (animale.getId() == null || animale.getPeso() == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		

		if(arca.updateAnimale(animale.getId(),animale.getPeso())) {
			return Response.ok(animale).build();
			
		};
		return Response.status(Status.BAD_REQUEST).build();
		
		
	}
}
