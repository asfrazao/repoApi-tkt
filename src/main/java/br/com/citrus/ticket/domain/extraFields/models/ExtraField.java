package br.com.citrus.ticket.domain.extraFields.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraField {
    private UUID id;
    private UUID ticketId;
    private String name;
    private String value;
}
