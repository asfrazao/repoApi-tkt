package br.com.citrus.ticket.infraestructure.persistence.dao;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExtraFieldDAO {
    private String id;
    private String ticketId;
    private String name;
    private String value;

    public ExtraFieldDAO(UUID id, UUID ticketId, String nameField, String value){
        this.id = id.toString();
        this.ticketId = ticketId.toString();
        this.name = nameField;
        this.value = value;
    }
}
