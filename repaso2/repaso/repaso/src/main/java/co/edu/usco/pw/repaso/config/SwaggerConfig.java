package co.edu.usco.pw.repaso.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI colegioOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API del Sistema Escolar")
                        .version("1.0")
                        .description("Documentación de los endpoints del sistema escolar (Rector, Docentes, Asignaturas, etc.)"));
    }
}
