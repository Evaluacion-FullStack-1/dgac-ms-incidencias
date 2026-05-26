package cl.dgac.incidencias.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class IncidenciaRequestDTO {

    @NotBlank(message = "El código de incidencia es obligatorio")
    private String codigoIncidencia;

    @NotBlank(message = "El tipo de incidencia es obligatorio")
    private String tipoIncidencia;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotNull(message = "La fecha de incidencia es obligatoria")
    private LocalDate fechaIncidencia;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    @NotBlank(message = "El nivel de gravedad es obligatorio")
    private String nivelGravedad;

    @NotNull(message = "El ID del plan de vuelo es obligatorio")
    private Long planVueloId;

    @NotNull(message = "El ID del drone es obligatorio")
    private Long droneId;

    @NotNull(message = "El ID del piloto es obligatorio")
    private Long pilotoId;
}