package br.com.citrus.ticket.domain.extraFields.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FieldExtraField {

    private UUID id;

    private int position;

    private String label;

    private String tip;

    private boolean optional;

    private boolean required;

    private boolean modifySLATicket;

    private String fieldType;

    private String nameField;

    private boolean allowed;

    private boolean excluded;

    private ExtraFieldGroup extraFieldGroup;

    private List<TicketExtraField> ticketExtraFields = new ArrayList<>();
}