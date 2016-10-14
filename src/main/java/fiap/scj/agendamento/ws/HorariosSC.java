package fiap.scj.agendamento.ws;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import fiap.scj.agendamento.resources.HorariosResource;

@Component
@Path("/horarios")
public class HorariosSC {
	
	@Inject
	private HorariosResource horarioResource;
	
	@GET
	@Produces("application/json")
	public Response listarHorariosLivres() {
		Collection<String> horariosLivres = horarioResource.listarHorariosLivres();
		return Response.ok(horariosLivres).build();
	}
}