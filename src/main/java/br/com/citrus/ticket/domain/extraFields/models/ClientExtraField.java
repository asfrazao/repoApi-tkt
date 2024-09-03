package br.com.citrus.ticket.domain.extraFields.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientExtraField {
    private UUID id;
    private String value;
    private String note;
    private String optionLabel;
    private UUID extraFieldId;
    private UUID clientId;

    public ClientExtraField toModel() {
        return new ClientExtraField(
                this.id,
                this.value,
                this.note,
                this.optionLabel,
                this.extraFieldId,
                this.clientId
        );
    }
}
