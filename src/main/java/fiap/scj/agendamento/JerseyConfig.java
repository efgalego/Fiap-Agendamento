package fiap.scj.agendamento;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import fiap.scj.agendamento.ws.AgendamentoSC;
import fiap.scj.agendamento.ws.ComparecimentoSC;
import fiap.scj.agendamento.ws.HorariosSC;
import fiap.scj.agendamento.ws.ServicoSC;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(HorariosSC.class);
        register(AgendamentoSC.class);
        register(ComparecimentoSC.class);
        register(ServicoSC.class);
    }
}