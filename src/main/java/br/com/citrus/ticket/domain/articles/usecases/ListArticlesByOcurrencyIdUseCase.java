package br.com.citrus.ticket.domain.articles.usecases;

import java.util.List;

import br.com.citrus.ticket.domain.articles.models.Article;

public interface ListArticlesByOcurrencyIdUseCase {
    List<Article> execute(String occurrencyId);
}
