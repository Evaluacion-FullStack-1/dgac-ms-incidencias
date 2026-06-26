package cl.dgac.incidencias.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI dgacIncidenciasOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API DGAC - Microservicio de Incidencias")
                        .description("Documentación oficial de los endpoints para el registro, gestión y seguimiento de anomalías, accidentes e infracciones normativas asociadas a vuelos de drones en el ecosistema DGAC.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo DGAC")
                                .email("soporte@dgac.cl")
                                .url("https://www.dgac.cl")));
    }
}