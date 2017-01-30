package com.example;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.data.rest.webmvc.spi.BackendIdConverter;

@SpringBootApplication
public class MouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(MouseApplication.class, args);
    }

    @Configuration
    static class MouseRestConfiguration extends RepositoryRestMvcConfiguration {

        @Override
        protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
            config
                    .setReturnBodyOnCreate(true)
                    .setReturnBodyOnUpdate(true)
                    .exposeIdsFor(Mouse.class)
                    .withEntityLookup()
                    .forRepository(MouseRepository.class, Mouse::getName, MouseRepository::findLatestMouseByName);
        }

        @Bean
        public UUIDBackendIdConverter mouseBackendIdConverter() {
            return new UUIDBackendIdConverter() {
                @Override
                public boolean supports(Class<?> delimiter) {
                    return delimiter.isAssignableFrom(Mouse.class);
                }
            };
        }
    }
}

abstract class UUIDBackendIdConverter implements BackendIdConverter {

    @Override
    public Serializable fromRequestId(String id, Class<?> aClass) {

        if (supports(aClass)) {
            return id == null ? null : UUID.fromString(id);
        } else {
            return id;
        }
    }

    @Override
    public String toRequestId(Serializable id, Class<?> aClass) {
        return id.toString();
    }

}



