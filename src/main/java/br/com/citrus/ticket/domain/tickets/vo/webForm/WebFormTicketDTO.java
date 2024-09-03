package br.com.citrus.ticket.domain.tickets.vo.webForm;

import br.com.citrus.ticket.infraestructure.web.ticket.dto.FileUploadDTO;
import br.com.citrus.ticket.shared.constants.Constantes;
import br.com.citrus.ticket.shared.utils.Helper;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WebFormTicketDTO {

    private static final String IDENTIFIER = "CO";


    String identify;
    String name;
    String code;
    String sfaCode;
    String email;
    String phone;
    String message;
    List<FileUploadDTO> attachment;

    private Map<String, Object> additionalFields = new HashMap<>();

    @JsonAnySetter
    public void setAdditionalField(String key, Object value) {
        this.additionalFields.put(key, value);
    }

    public String getSfaCode() {
        if (Objects.isNull(this.sfaCode)) {
            this.sfaCode = IDENTIFIER + "-" + Helper.generatesNumber(Constantes.TAMANHO_CODIGO);
        }
        return this.sfaCode;
    }
}
