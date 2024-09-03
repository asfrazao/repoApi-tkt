package br.com.citrus.ticket.domain.articles.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum ArticleStatusType {

    DRAFT(0, "em rascunho"),
	APPROVED(1, "aprovado"),
	IN_VALIDATION(2, "em validacao");

    @Getter
    private Integer order;

    @Getter
    private String label;

    public static ArticleStatusType getByOrder(Integer order) {
    for(ArticleStatusType status : values()) {
       if(status.getOrder() == order){
            return status;
       }
    }

    return null;
}
}
