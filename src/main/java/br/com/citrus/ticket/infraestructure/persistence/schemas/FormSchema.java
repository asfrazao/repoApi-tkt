package br.com.citrus.ticket.infraestructure.persistence.schemas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "formulario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormSchema {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "nome")
    private String name;

    @Column(name = "descricao")
    private String description;

    @Column(name = "informacoes_cliente")
    private String clientInformation;

    @Column(name = "enviar_cliente")
    private boolean sendClient;

    @ManyToOne
    @JoinColumn(name = "grupo_campo_extra_id")
    private ExtraFieldGroupSchema extraFieldGroup;

    @Column(name = "tipo_formulario")
    private String formType;

    @Column(name = "imagem_cabecalho")
    private String headerImage;

    @Column(name = "mensagem_pesquisa")
    private String surveyMessage;

    @Column(name = "imagem_rodape")
    private String footerImage;

    @Column(name = "imagem_email")
    private String emailImage;
}