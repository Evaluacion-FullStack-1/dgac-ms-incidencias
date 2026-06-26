package cl.dgac.incidencias.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Modelo de respuesta con la información detallada de una incidencia de vuelo")
public class IncidenciaResponseDTO {

    @Schema(description = "Identificador interno de la incidencia en el sistema", example = "1")
    private Long id;

    @Schema(description = "Código único oficial generado automáticamente para el seguimiento del reporte", example = "INC-2026-001")
    private String codigoIncidencia;

    @Schema(description = "Clasificación general del incidente", example = "INFRACCION_ESPACIO_AEREO")
    private String tipoIncidencia;

    @Schema(description = "Detalle completo de los hechos ocurridos", example = "El drone sobrepasó el límite de altura permitido (130m) y perdió señal de control temporalmente cerca de un área restringida.")
    private String descripcion;

    @Schema(description = "Fecha exacta en la que ocurrió el incidente", example = "2026-06-25")
    private LocalDate fechaIncidencia;

    @Schema(description = "Estado actual del proceso del reporte (ej. EN_INVESTIGACION, RESUELTA)", example = "EN_INVESTIGACION")
    private String estado;

    @Schema(description = "Clasificación del impacto o severidad", example = "GRAVE")
    private String nivelGravedad;

    @Schema(description = "Identificador único del plan de vuelo asociado", example = "502")
    private Long planVueloId;

    @Schema(description = "Identificador único del drone involucrado", example = "105")
    private Long droneId;

    @Schema(description = "Identificador único del piloto al mando", example = "88")
    private Long pilotoId;
}