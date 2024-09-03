package br.com.citrus.ticket.infraestructure.web.procedures.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.citrus.ticket.domain.articles.models.Article;
import br.com.citrus.ticket.domain.articles.usecases.ListArticlesByOcurrencyIdUseCase;
import br.com.citrus.ticket.infraestructure.web.procedures.dto.ArticleResponseDTO;
import br.com.citrus.ticket.shared.constants.RestConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = RestConstants.API_BASE_URI
        + RestConstants.TICKET_MODULE
        + RestConstants.ARTICLE_ENDPOINT, 
        produces = MediaType.APPLICATION_JSON_VALUE)
public class ArticleController {

    @Autowired
    private ListArticlesByOcurrencyIdUseCase listArticlesByOcurrencyIdUseCase;

    @Autowired
    private ModelMapper articleMapper;

    @Operation(summary = "Get articles by an ocurrency id", tags="Article")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Found a list of articles", 
                    content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ArticleResponseDTO.class))}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
                    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
                })
    @GetMapping("/{ocurrencyId}")
    public List<ArticleResponseDTO> listArticlesByOcurrencyId(@PathVariable String ocurrencyId) {
        List<Article> procedures = new ArrayList<Article>();
        procedures = listArticlesByOcurrencyIdUseCase.execute(ocurrencyId);
        List<ArticleResponseDTO> dto = procedures
                .stream()
                .map(procedure -> articleMapper.map(procedure, ArticleResponseDTO.class))
                .collect(Collectors.toList());

        return dto;
    }
}
