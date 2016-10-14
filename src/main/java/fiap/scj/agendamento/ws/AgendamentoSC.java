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

import fiap.scj.agendamento.resources.HorariosResource;
import fiap.scj.agendamento.to.AgendamentoTO;

@Component
@Path("/agendamento")
public class AgendamentoSC {
	
	@Inject
	private HorariosResource horarioResource;
	
	@GET
	@Produces("application/json")
	public Response listarAgendamentos() {
		Collection<AgendamentoTO> horariosLivres = horarioResource.listarAgendamento();
		return Response.ok(horariosLivres).build();
	}
	
	@POST
	public Response agendar(@QueryParam("horario") String horario, @QueryParam("nomeCidadao") String nomeCidadao) {
		horarioResource.agendar(horario, nomeCidadao);
		return Response.noContent().build();
	}
	
	@DELETE
	public Response cancelarAgendamento(@QueryParam("horario") String horario, @QueryParam("nomeCidadao") String nomeCidadao) {
		horarioResource.cancelarAgendamento(horario, nomeCidadao);
		return Response.noContent().build();
	}
}