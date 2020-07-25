package br.com.globallabs.rest.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // Propriedades não mapeadas não quebram
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Falha se alguma propriedade estiver vazia
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        // Serve para compatibilidade de arrays quando tem um array com um item, caso não tenha essa config ele se perde
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        // Serelize datas
        mapper.registerModule(new JavaTimeModule());

        return mapper;
    }

}
