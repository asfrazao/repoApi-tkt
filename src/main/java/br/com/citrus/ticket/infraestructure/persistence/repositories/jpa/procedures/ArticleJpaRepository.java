package br.com.citrus.ticket.infraestructure.persistence.repositories.jpa.procedures;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.citrus.ticket.infraestructure.persistence.schemas.ArticleSchema;

public interface ArticleJpaRepository extends JpaRepository<ArticleSchema, String> {

    @Query("SELECT p FROM ArticleSchema p join p.ocurrencySchema o where o.id = :ocurrencyId")
    List<ArticleSchema> findArticlesByOcurrencyId(@Param("ocurrencyId") UUID ocurrencyId);
    
}
