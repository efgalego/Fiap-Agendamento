package fiap.scj.agendamento;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import fiap.scj.agendamento.ws.AgendamentoSC;
import fiap.scj.agendamento.ws.HorariosSC;

@Configuration
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        register(HorariosSC.class);
        register(AgendamentoSC.class);
    }
}