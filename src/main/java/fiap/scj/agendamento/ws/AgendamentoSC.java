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
import javax.ws.rs.core.Response.Status;

import org.springframework.stereotype.Component;

import fiap.scj.agendamento.NaoEncontradoException;
import fiap.scj.agendamento.repositorios.AgendamentoRepositorio;
import fiap.scj.agendamento.to.AgendamentoTO;

@Component
@Path("/agendamento")
public class AgendamentoSC {
	
	@Inject
	private AgendamentoRepositorio repositorio;
	
	@GET
	@Produces("application/json")
	public Response listarTodosAgendamentos() {
		Collection<AgendamentoTO> horariosLivres = repositorio.listarAgendamento();
		return Response.ok(horariosLivres).build();
	}
	
	@POST
	public Response agendar(@QueryParam("horario") String horario, @QueryParam("nomeCidadao") String nomeCidadao) {
		String numeroProtocolo = null;
		try {
			numeroProtocolo = repositorio.agendar(horario, nomeCidadao);
		} catch (NaoEncontradoException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
		return Response.ok(numeroProtocolo).build();
	}
	
	@DELETE
	public Response cancelarAgendamento(@QueryParam("protocolo") String protocolo, @QueryParam("nomeCidadao") String nomeCidadao) {
		try {
			repositorio.cancelarAgendamento(protocolo, nomeCidadao);
		} catch (NaoEncontradoException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
		return Response.noContent().build();
	}
}