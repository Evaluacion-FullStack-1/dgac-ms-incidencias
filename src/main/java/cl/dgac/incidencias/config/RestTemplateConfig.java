package cl.dgac.incidencias.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    @LoadBalanced // <--- Fundamental para que Eureka resuelva "http://DGAC-MS-PLANES-VUELO"
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}