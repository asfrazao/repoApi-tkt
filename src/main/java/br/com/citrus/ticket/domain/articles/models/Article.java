package br.com.citrus.ticket.domain.articles.models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    
    private UUID id;
    private String name;
    private String note;
    private String description;
    private String idCategory;
    private boolean isVisibleToClientUser;
    private Integer status;
    
}
