package fiap.scj.agendamento.repositorios;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Named;

import fiap.scj.agendamento.to.AgendamentoTO;

/**
 * Classe responsável por armazenar os horários livres e agendamentos efetuados.
 * @author Eduardo Galego
 */
@Named
public class HorariosRepositorio {
	
	private static Collection<String> listaHorariosLivres;
	private static Collection<AgendamentoTO> listaAgendamentos;
	private static Integer proxProtocolo = 1000;

	static {
		listaHorariosLivres = new ArrayList<String>();
		for (int i = 8; i < 19; i++) {
			for (int j = 0; j < 4; j++) {
				listaHorariosLivres.add(String.format("%02d:%02d", i, 15*j));
			}
		}
		listaAgendamentos = new ArrayList<AgendamentoTO>();
	}
	
	/**
	 * Lista os horários livres.
	 * @return Lista de horários livres.
	 */
	public Collection<String> listarHorariosLivres() {
		return listaHorariosLivres;
	}
	
	/**
	 * Retorna a lista de agendamentos efetuados.
	 * @return Lista de agendamentos efetuados.
	 */
	public Collection<AgendamentoTO> listarAgendamento() {
		return listaAgendamentos;
	}
	
	/**
	 * Efetua um novo agendamento.
	 * @param horario Horário.
	 * @param nomeCidadao Nome do cidadão.
	 */
	public String agendar(String horario, String nomeCidadao) {
		String numeroProtocolo = String.format("%07d", proxProtocolo++);		
		listaHorariosLivres.remove(horario);
		listaAgendamentos.add(new AgendamentoTO(horario, nomeCidadao, numeroProtocolo));
		return numeroProtocolo;
	}
	
	/**
	 * Cancela um agendamento já efetuado.
	 * @param horario Horário do agendamento.
	 * @param nomeCidadao Nome do cidadão.
	 */
	public void cancelarAgendamento(String horario, String nomeCidadao) {
		AgendamentoTO remover = null;
		for (AgendamentoTO to : listaAgendamentos) {
			if (horario.equals(to.getHorario())) {
				remover = to;
				break;
			}
		}
		if (remover != null) {
			listaAgendamentos.remove(remover);
			listaHorariosLivres.add(horario);
		}
	}
}
