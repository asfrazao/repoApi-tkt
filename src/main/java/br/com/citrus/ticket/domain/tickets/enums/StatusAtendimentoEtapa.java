package br.com.citrus.ticket.domain.tickets.enums;

import lombok.Getter;

public enum StatusAtendimentoEtapa {
	
	PENDENTE("enum.status.atendimento.pendente"),
	CONFIRMADA_EM_ANDAMENTO("enum.status.atendimento.confirma.em.andamento"),
	FINALIZADA("enum.status.atendimento.finalizada"),
	VOLTAR_ETAPA("enum_status_atendimento_voltar_etapa"),
	CANCELADA("enum.status.atendimento.cancelada");

	@Getter
	private String label;
	
	private StatusAtendimentoEtapa(String label) {
		this.label = label;
	}

	public static String[] getStatusPendente() {
		return new String[]{PENDENTE.name(), CONFIRMADA_EM_ANDAMENTO.name()};
	}
	
}
