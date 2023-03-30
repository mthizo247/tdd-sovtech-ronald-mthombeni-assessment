package com.sovtech.tddsovtechassessment;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TddSovtechAssessmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(TddSovtechAssessmentApplication.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        String docUrl = "https://sovtech.atlassian.net/wiki/spaces/PG/pages/tdd-sovtech-ronald-mthombeni-assessment";
        String docDescription = "Confluence documentation";


        return new OpenAPI()

                .components(new Components())
                .addServersItem(new Server().url("/"))
                .info(new Info().title("TDD Sovtech Ronald Mthombeni Assessment").version("1.0")).
                        externalDocs(new ExternalDocumentation().url(docUrl).description(docDescription));
    }
}
