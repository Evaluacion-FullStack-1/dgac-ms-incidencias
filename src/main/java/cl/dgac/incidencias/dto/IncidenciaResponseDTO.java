package cl.dgac.incidencias.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class IncidenciaResponseDTO {

    private Long id;
    private String codigoIncidencia;
    private String tipoIncidencia;
    private String descripcion;
    private LocalDate fechaIncidencia;
    private String estado;
    private String nivelGravedad;
    private Long planVueloId;
    private Long droneId;
    private Long pilotoId;
}