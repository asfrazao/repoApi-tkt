package br.com.citrus.ticket.domain.extraFields.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraFieldGroup {

    private UUID id;

    private String name;

    private String type;

    private List<FieldExtraField> extraFields;
}
