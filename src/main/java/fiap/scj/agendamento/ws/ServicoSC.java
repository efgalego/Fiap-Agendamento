package fiap.scj.agendamento.ws;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Component;

import fiap.scj.agendamento.to.ServicoCidadaoTO;

@Component
@Path("/servico")
public class ServicoSC {

	@GET
	@Produces("application/json")
	public Response permiteServicoAoCidadao(
			@QueryParam("servico") String servico,
			@QueryParam("nomeCidadao") String nomeCidadao, 
			@QueryParam("idadeCidadao") Integer idadeCidadao) {
		try {
			// Carrega a base de conhecimento:
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("ksession-rules");

			// Adiciona um novo fato à base de conhecimento:
			ServicoCidadaoTO to = new ServicoCidadaoTO();
			to.setIdadeCidadao(idadeCidadao);
			to.setNomeCidadao(nomeCidadao);
			to.setNomeServico(servico);
			kSession.insert(to);

			// Executa todas as regras:
			kSession.fireAllRules();

		} catch (Throwable t) {
			t.printStackTrace();
			return Response.serverError().entity(t).build();
		}
		return Response.noContent().build();
	}

}
