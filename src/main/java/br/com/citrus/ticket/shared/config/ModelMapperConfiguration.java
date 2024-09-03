package br.com.citrus.ticket.shared.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {
    
    @Bean
    ModelMapper mapper(){
        return new ModelMapper();
    }

}
