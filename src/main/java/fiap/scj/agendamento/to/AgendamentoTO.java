package fiap.scj.agendamento.to;

public class AgendamentoTO {

	private String horario;
	private String nomeCidadao;
	private Boolean compareceu;
	private String protocolo;
	
	private AgendamentoTO() {
		super();
		compareceu = false;
	}
	
	public AgendamentoTO(String horario, String nomeCidadao, String protocolo) {
		this();
		this.horario = horario;
		this.nomeCidadao = nomeCidadao;
		this.protocolo = protocolo;
	}
	
	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getNomeCidadao() {
		return nomeCidadao;
	}

	public void setNomeCidadao(String nomeCidadao) {
		this.nomeCidadao = nomeCidadao;
	}

	public Boolean getCompareceu() {
		return compareceu;
	}

	public void setCompareceu(Boolean compareceu) {
		this.compareceu = compareceu;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}
}
