package br.com.citrus.ticket.infraestructure.web.ticket.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadDTO {
    private String base64Content;
    private String fileName;
}
