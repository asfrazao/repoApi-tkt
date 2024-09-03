package br.com.citrus.ticket.domain.tickets.enums;
import lombok.Getter;

public enum TicketStatus {
    APROVED("APROVADOR"), 
    OPEN("ABERTO"),
    WAIT_AUTH_FINISH("AGUARDANDO_AUTORIZACAO_FINALIZAR"),
    WAIT_RETURN("AGUARDANDO_RETORNO"),
    CANCEL("CANCELADO"),
    IN_PROGRESS("EM_ATENDIMENTO"),
    FINISHED("FINALIZADO"),
    UNFOUNDED("IMPROCEDENTE"),
    RESOLVED("RESOLVIDO"),
    CLOSED("FECHADO");

    @Getter
    private String label;

    TicketStatus(String label){
        this.label = label;
    }
}
