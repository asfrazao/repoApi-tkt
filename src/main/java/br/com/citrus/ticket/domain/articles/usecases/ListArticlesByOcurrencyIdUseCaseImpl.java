package br.com.citrus.ticket.domain.articles.usecases;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.citrus.ticket.domain.articles.gateways.ArticleGateway;
import br.com.citrus.ticket.domain.articles.models.Article;
import br.com.citrus.ticket.shared.exception.InvalidUUIDFormatException;
import br.com.citrus.ticket.shared.utils.Helper;

@Service
public class ListArticlesByOcurrencyIdUseCaseImpl implements ListArticlesByOcurrencyIdUseCase {
    private static Logger log = LoggerFactory.getLogger(ListArticlesByOcurrencyIdUseCaseImpl.class);
    @Autowired
    private ArticleGateway gateway;

    @Override
    public List<Article> execute (String ocurrencyId) {
        if(!Helper.isUUIDValid(ocurrencyId)){
           InvalidUUIDFormatException error = new InvalidUUIDFormatException();
           log.error(error.getMessage());
           throw error;
        }

        return gateway.listArticlesByOccurrencyId(ocurrencyId);
    }
    
}
