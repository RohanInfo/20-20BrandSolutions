package com.infocepts.casino.swaggerconfig;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
@EnableAutoConfiguration
public class SwaggerConfig {

    /** The title for the spring boot controller to be displayed on swagger UI. */
    @Value("${swagger.title}")
    private String title;

    /** The description of the spring boot controller. */
    @Value("${swagger.description}")
    private String description;

    /** The version of the controller. */
    @Value("${swagger.version}")
    private String version;

    /** The terms of controller url for the controller if applicable. */
    @Value("${swagger.termsOfServiceUrl}")
    private String termsOfServiceUrl;

    /** The contact name for the controller. */
    @Value("${swagger.contact.name}")
    private String contactName;

    /** The contact url for the controller. */
    @Value("${swagger.contact.url}")
    private String contactURL;

    /** The contact email for the controller. */
    @Value("${swagger.contact.email}")
    private String contactEmail;

    /** The license for the controller if applicable. */
    @Value("${swagger.license}")
    private String license;

    /** The license url for the controller if applicable. */
    @Value("${swagger.licenseUrl}")
    private String licenseURL;

    /**
     * This method will return the Docket API object to swagger which will in turn display the information on the swagger UI.
     *
     * Refer the URL http://{ip-address or host-name}:{controller.port}/{server.contextPath}/swagger-ui.html
     *
     * controller.port and server.contextPath are provided in application.properties.
     * If these properties are not defined in the application.properties then the URL for swagger will be
     * http://{ip-address or host-name}:8080/swagger-ui.html
     *
     * @return the docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }

    /**
     * This method will return the API info object to swagger which will in turn display the information on the swagger UI.
     *
     * @return the API information
     */
    private ApiInfo apiInfo() {
        return new ApiInfo(title, description, version, termsOfServiceUrl, new Contact(contactName, contactURL, contactEmail), license, licenseURL);
    }
}
