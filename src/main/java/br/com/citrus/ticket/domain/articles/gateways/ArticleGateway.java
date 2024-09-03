package br.com.citrus.ticket.domain.articles.gateways;

import java.util.List;

import br.com.citrus.ticket.domain.articles.models.Article;

public interface ArticleGateway {
    List<Article> listArticlesByOccurrencyId(String occurrencyId);
}
