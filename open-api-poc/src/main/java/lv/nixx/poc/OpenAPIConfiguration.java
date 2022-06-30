package lv.nixx.poc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Value("${app.version}")
    private String version;

    @Bean
    public OpenAPI customOpenAPI() {
        OpenAPI openAPI = new OpenAPI();
        return openAPI.info(apiInfo());
    }

    @Bean
    public GroupedOpenApi publicOpenApi() {
        String[] paths = {"/public/**"};
        return GroupedOpenApi.builder().group("public").pathsToMatch(paths).build();
    }

    @Bean
    public GroupedOpenApi hiddenOpenApi() {
        String[] paths = {"/hidden/**"};
        return GroupedOpenApi.builder().group("hidden").pathsToMatch(paths).build();
    }

    private Info apiInfo() {
        return new Info()
                .title("PoC project for OpenAPI")
                .description("Simple API to show how OpenAPI works")
                .version(version)
                .contact(apiContact())
                .license(apiLicence());
    }

    private License apiLicence() {
        return new License()
                .name("MIT Licence")
                .url("https://opensource.org/licenses/mit-license.php");
    }

    private Contact apiContact() {
        return new Contact()
                .name("NiXX")
                .email("nixx78 gmail")
                .url("My URL");
    }
}