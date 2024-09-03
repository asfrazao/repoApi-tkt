package br.com.citrus.ticket.domain.procedures.usecases;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.citrus.ticket.domain.articles.gateways.ArticleGateway;
import br.com.citrus.ticket.domain.articles.models.Article;
import br.com.citrus.ticket.domain.articles.usecases.ListArticlesByOcurrencyIdUseCase;
import br.com.citrus.ticket.domain.articles.usecases.ListArticlesByOcurrencyIdUseCaseImpl;

//@SpringBootTest
class ListArticlesByOcurrencyIdUseCaseTest {

	@Mock
	private ArticleGateway gateway;

	@InjectMocks
	private ListArticlesByOcurrencyIdUseCase listArticlesByOcurrencyIdUseCase = new ListArticlesByOcurrencyIdUseCaseImpl();

//	@Test
//	@DisplayName("Given an Ocurrency id should return a list of Article")
	void withValidOcurrencyId() {
		String ocurrencyId = "907f3eb2-15d8-b419-f617-90668f246023";
		List<Article> articlesFounded = listArticlesByOcurrencyIdUseCase.execute(ocurrencyId);

		Article mock = new Article();
		Mockito.when(gateway.listArticlesByOccurrencyId(ocurrencyId))
				.thenReturn(Arrays.asList(mock, mock));

		Assertions.assertInstanceOf(List.class, articlesFounded);
	}
}
