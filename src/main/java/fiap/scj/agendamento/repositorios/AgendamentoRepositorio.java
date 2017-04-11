package fiap.scj.agendamento.repositorios;

import java.util.ArrayList;
import java.util.Collection;

import javax.inject.Named;

import fiap.scj.agendamento.NaoEncontradoException;
import fiap.scj.agendamento.to.AgendamentoTO;

/**
 * Classe responsável por armazenar os horários livres e agendamentos efetuados.
 * @author Eduardo Galego
 */
@Named
public class AgendamentoRepositorio {
	
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
	 * @throws NaoEncontradoException 
	 */
	public String agendar(String horario, String nomeCidadao) throws NaoEncontradoException {
		if (!listaHorariosLivres.remove(horario)) {
			throw new NaoEncontradoException("Hor�rio");
		}
		
		String numeroProtocolo = String.format("%07d", proxProtocolo++);
		listaAgendamentos.add(new AgendamentoTO(horario, nomeCidadao, numeroProtocolo));			
		return numeroProtocolo;
	}
	
	/**
	 * Cancela um agendamento já efetuado.
	 * @param horario Horário do agendamento.
	 * @throws NaoEncontradoException Exceção lançada caso não encontrada a agenda com o número de protocolo informado.
	 */
	public void cancelarAgendamento(String protocolo) throws NaoEncontradoException {
		AgendamentoTO remover = buscarAgendaPorProtocolo(protocolo);
		
		if (remover == null) {
			throw new NaoEncontradoException("Protocolo");
		}
		
		if (remover != null) {
			listaAgendamentos.remove(remover);
			listaHorariosLivres.add(remover.getHorario());
		}
	}
	
	private AgendamentoTO buscarAgendaPorProtocolo(String protocolo) {
		AgendamentoTO agenda = null;
		for (AgendamentoTO to : listaAgendamentos) {
			if (protocolo.equals(to.getProtocolo())) {
				agenda = to;
				break;
			}
		}
		return agenda;
	}
	
	/**
	 * Registra o comparecimento de um cidadão.
	 * @param protocolo Número do protocolo.
	 * @return Dados do agendamento.
	 * @throws NaoEncontradoException Exceção lançada caso não encontrada a agenda com o número de protocolo informado.
	 */
	public AgendamentoTO registrarComparecimento(String protocolo) throws NaoEncontradoException {
		AgendamentoTO agendaParaRegistro = buscarAgendaPorProtocolo(protocolo);
		
		if (agendaParaRegistro == null) {
			throw new NaoEncontradoException("Protocolo");
		}
		
		agendaParaRegistro.setCompareceu(true);
		return agendaParaRegistro;
	}
}
