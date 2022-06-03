    package com.nust.concrete.strength.api.config;

    import io.swagger.v3.oas.models.OpenAPI;
    import io.swagger.v3.oas.models.info.Info;
    import io.swagger.v3.oas.models.info.License;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import springfox.documentation.builders.ApiInfoBuilder;
    import springfox.documentation.builders.RequestHandlerSelectors;
    import springfox.documentation.service.*;
    import springfox.documentation.spi.DocumentationType;
    import springfox.documentation.spring.web.plugins.Docket;
    import springfox.documentation.swagger2.annotations.EnableSwagger2;

    import static springfox.documentation.builders.PathSelectors.regex;

    @Slf4j
    @EnableSwagger2
    @Configuration
    public class SwaggerConfig {


        private Contact contact = new Contact(
                "Prince Kudzai Maposa",
                "https://developerprince.co.zw",
                "n01416165h@student.nust.com");
        @Bean
        public Docket userEndpoint(){
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.nust.concrete.strength.api"))
                    .paths(regex("/.*"))
                    .build()
                    .apiInfo(metaInfo());
        }

        private ApiInfo metaInfo(){
            return new ApiInfoBuilder()
                    .title("Concrete Compressive Strength Predictor")
                    .description( "Abstract: Concrete is the most important material in civil engineering. The \n" +
                            "concrete compressive strength is a highly nonlinear function of age and \n" +
                            "ingredients. These ingredients include cement, blast furnace slag, fly ash, \n" +
                            "water, superplasticizer, coarse aggregate, and fine aggregate.")
                    .version("0.0.1")
                    .contact(contact)
                    .licenseUrl("https://opensource.org/licenses/Apache-2.0")
                    .license("Apache Licence Version 2.0")
                    .build();
        }

        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .info(getInfo());
        }

        private Info getInfo() {
            return new Info()
                    .title("Concrete Compressive Strength Predictor")
                    .description("Abstract: Concrete is the most important material in civil engineering. The \n" +
                            "concrete compressive strength is a highly nonlinear function of age and \n" +
                            "ingredients. These ingredients include cement, blast furnace slag, fly ash, \n" +
                            "water, superplasticizer, coarse aggregate, and fine aggregate.")
                    .version("0.0.1")
                    .license(getLicense());
        }

        private License getLicense() {
            return new License()
                    .name("Apache Licence Version 2.0")
                    .url("https://opensource.org/licenses/Apache-2.0");
        }






    }