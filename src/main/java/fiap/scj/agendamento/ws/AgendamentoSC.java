package fiap.scj.agendamento.ws;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import fiap.scj.agendamento.repositorios.HorariosRepositorio;
import fiap.scj.agendamento.to.AgendamentoTO;

@Component
@Path("/agendamento")
public class AgendamentoSC {
	
	@Inject
	private HorariosRepositorio horarioRepositorio;
	
	@GET
	@Produces("application/json")
	public Response listarTodosAgendamentos() {
		Collection<AgendamentoTO> horariosLivres = horarioRepositorio.listarAgendamento();
		return Response.ok(horariosLivres).build();
	}
	
	@POST
	public Response agendar(@QueryParam("horario") String horario, @QueryParam("nomeCidadao") String nomeCidadao) {
		String numeroProtocolo = horarioRepositorio.agendar(horario, nomeCidadao);
		return Response.ok(numeroProtocolo).build();
	}
	
	@DELETE
	public Response cancelarAgendamento(@QueryParam("horario") String horario, @QueryParam("nomeCidadao") String nomeCidadao) {
		horarioRepositorio.cancelarAgendamento(horario, nomeCidadao);
		return Response.noContent().build();
	}
}