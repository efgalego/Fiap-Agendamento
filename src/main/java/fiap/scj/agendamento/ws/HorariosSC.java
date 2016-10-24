package fiap.scj.agendamento.ws;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import fiap.scj.agendamento.repositorios.HorariosRepositorio;

@Component
@Path("/horarios")
public class HorariosSC {
	
	@Inject
	private HorariosRepositorio horarioRepositorio;
	
	@GET
	@Produces("application/json")
	public Response listarHorariosLivres() {
		Collection<String> horariosLivres = horarioRepositorio.listarHorariosLivres();
		return Response.ok(horariosLivres).build();
	}
}