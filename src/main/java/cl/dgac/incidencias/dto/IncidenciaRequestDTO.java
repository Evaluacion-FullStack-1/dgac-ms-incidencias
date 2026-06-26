package cl.dgac.incidencias.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Modelo de petición para registrar o actualizar un reporte de incidencia de vuelo")
public class IncidenciaRequestDTO {

    @Schema(description = "Clasificación general del incidente (ej. INFRACCION_ESPACIO_AEREO, FALLA_TECNICA, ACCIDENTE)", example = "INFRACCION_ESPACIO_AEREO")
    @NotBlank(message = "El tipo de incidencia es obligatorio")
    private String tipoIncidencia;

    @Schema(description = "Detalle completo de los hechos ocurridos durante la operación", example = "El drone sobrepasó el límite de altura permitido (130m) y perdió señal de control temporalmente cerca de un área restringida.")
    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @Schema(description = "Fecha exacta en la que ocurrió el incidente", example = "2026-06-25")
    @NotNull(message = "La fecha de incidencia es obligatoria")
    private LocalDate fechaIncidencia;

    @Schema(description = "Clasificación del impacto o severidad del incidente (ej. LEVE, GRAVE, CATASTROFICO)", example = "GRAVE")
    @NotBlank(message = "El nivel de gravedad es obligatorio")
    private String nivelGravedad;

    @Schema(description = "Identificador único del plan de vuelo asociado a la operación", example = "502")
    @NotNull(message = "El ID del plan de vuelo es obligatorio")
    private Long planVueloId;

    @Schema(description = "Identificador único del drone involucrado", example = "105")
    @NotNull(message = "El ID del drone es obligatorio")
    private Long droneId;

    @Schema(description = "Identificador único del piloto al mando durante el incidente", example = "88")
    @NotNull(message = "El ID del piloto es obligatorio")
    private Long pilotoId;
}