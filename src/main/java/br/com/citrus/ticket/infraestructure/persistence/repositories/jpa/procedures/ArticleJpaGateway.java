package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.procedures;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.citrus.ticket.domain.articles.gateways.ArticleGateway;
import br.com.citrus.ticket.domain.articles.models.Article;
import br.com.citrus.ticket.infraestructure.persistence.schemas.ArticleSchema;

@Component
public class ArticleJpaGateway implements ArticleGateway {

    @Autowired
    private ArticleJpaRepository jpaRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<Article> listArticlesByOccurrencyId(String occurrencyId) {
       List<ArticleSchema> schemas = jpaRepository.findArticlesByOcurrencyId(UUID.fromString(occurrencyId));

       List<Article> procedures = schemas
       .stream()
       .map(schema -> mapper.map(schema,  Article.class))
       .collect(Collectors.toList());

       return procedures;
    }
    
}