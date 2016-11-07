package fiap.scj.agendamento.ws;

import javax.inject.Inject;
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
@Path("/comparecimento")
public class ComparecimentoSC {
	
	@Inject
	private AgendamentoRepositorio repositorio;
	
	@POST
	@Produces("application/json")
	public Response registrarComparecimento(@QueryParam("protocolo") String protocolo) {
		AgendamentoTO to;
		try {
			to = repositorio.registrarComparecimento(protocolo);
		} catch (NaoEncontradoException e) {
			return Response.status(Status.NOT_FOUND).entity(e.getMessage()).build();
		}
		return Response.ok(to).build();
	}
}