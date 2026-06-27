package cl.dgac.incidencias;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DgacMsIncidenciasApplication {

    public static void main(String[] args) {
        SpringApplication.run(DgacMsIncidenciasApplication.class, args);
    }

}