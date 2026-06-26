package cl.dgac.incidencias.controller;

import cl.dgac.incidencias.dto.IncidenciaRequestDTO;
import cl.dgac.incidencias.dto.IncidenciaResponseDTO;
import cl.dgac.incidencias.service.IncidenciaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidencias")
@Tag(name = "Incidencias", description = "Operaciones para el registro y gestión de anomalías, accidentes e infracciones normativas asociadas a vuelos de drones")
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    public IncidenciaController(IncidenciaService incidenciaService) {
        this.incidenciaService = incidenciaService;
    }

    @Operation(summary = "Listar todas las incidencias", description = "Obtiene un registro completo de todos los incidentes y reportes de vuelo ingresados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Lista de incidencias obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<IncidenciaResponseDTO>> listarIncidencias() {
        return ResponseEntity.ok(incidenciaService.listarIncidencias());
    }

    @Operation(summary = "Buscar incidencia por ID", description = "Obtiene los detalles de un incidente específico mediante su identificador único interno.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incidencia encontrada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Incidencia no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<IncidenciaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(incidenciaService.buscarPorId(id));
    }

    @Operation(summary = "Registrar nueva incidencia", description = "Ingresa un nuevo reporte de accidente, infracción o anomalía ocurrida durante una operación de vuelo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Incidencia registrada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos (ej. código duplicado)")
    })
    @PostMapping
    public ResponseEntity<IncidenciaResponseDTO> crearIncidencia(
            @Valid @RequestBody IncidenciaRequestDTO dto) {

        IncidenciaResponseDTO incidenciaCreada = incidenciaService.crearIncidencia(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(incidenciaCreada);
    }

    @Operation(summary = "Actualizar estado o detalles de la incidencia", description = "Modifica la información de un reporte existente, como actualizar su estado de investigación.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incidencia actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Incidencia no encontrada"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PutMapping("/{id}")
    public ResponseEntity<IncidenciaResponseDTO> actualizarIncidencia(
            @PathVariable Long id,
            @Valid @RequestBody IncidenciaRequestDTO dto) {

        return ResponseEntity.ok(incidenciaService.actualizarIncidencia(id, dto));
    }

    @Operation(summary = "Eliminar incidencia", description = "Elimina un reporte de incidencia del sistema mediante su ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Incidencia eliminada exitosamente (Sin contenido)"),
            @ApiResponse(responseCode = "404", description = "Incidencia no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarIncidencia(@PathVariable Long id) {
        incidenciaService.eliminarIncidencia(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar por código de incidencia", description = "Busca el registro exacto utilizando el código oficial del reporte generado (ej. INC-2026-001).")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incidencia encontrada"),
            @ApiResponse(responseCode = "404", description = "Código no registrado")
    })
    @GetMapping("/buscar-codigo")
    public ResponseEntity<IncidenciaResponseDTO> buscarPorCodigoIncidencia(
            @RequestParam String codigoIncidencia) {

        return ResponseEntity.ok(incidenciaService.buscarPorCodigoIncidencia(codigoIncidencia));
    }

    @Operation(summary = "Filtrar por estado del reporte", description = "Obtiene una lista de incidencias según su estado actual (ej. EN_INVESTIGACION, RESUELTA, DESCARTADA).")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/estado")
    public ResponseEntity<List<IncidenciaResponseDTO>> listarPorEstado(
            @RequestParam String estado) {

        return ResponseEntity.ok(incidenciaService.listarPorEstado(estado));
    }

    @Operation(summary = "Filtrar por nivel de gravedad", description = "Obtiene una lista de incidentes categorizados por su impacto (ej. LEVE, GRAVE, CATASTROFICO).")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/gravedad")
    public ResponseEntity<List<IncidenciaResponseDTO>> listarPorGravedad(
            @RequestParam String nivelGravedad) {

        return ResponseEntity.ok(incidenciaService.listarPorGravedad(nivelGravedad));
    }

    @Operation(summary = "Listar incidencias por Plan de Vuelo", description = "Obtiene todos los incidentes asociados al ID de un plan de vuelo específico autorizado por la DGAC.")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/plan-vuelo/{planVueloId}")
    public ResponseEntity<List<IncidenciaResponseDTO>> listarPorPlanVuelo(
            @PathVariable Long planVueloId) {

        return ResponseEntity.ok(incidenciaService.listarPorPlanVuelo(planVueloId));
    }

    @Operation(summary = "Buscar por tipo de incidencia", description = "Busca coincidencias parciales según la clasificación del evento (ej. INFRACCION_ESPACIO_AEREO, FALLA_TECNICA).")
    @ApiResponse(responseCode = "200", description = "Búsqueda realizada exitosamente")
    @GetMapping("/tipo")
    public ResponseEntity<List<IncidenciaResponseDTO>> buscarPorTipoIncidencia(
            @RequestParam String tipoIncidencia) {

        return ResponseEntity.ok(incidenciaService.buscarPorTipoIncidencia(tipoIncidencia));
    }

    @Operation(summary = "Consultar estado del servicio de Planes de Vuelo (WebClient)", description = "Endpoint de integración para verificar la disponibilidad del microservicio de Planes de Vuelo.")
    @ApiResponse(responseCode = "200", description = "Comunicación exitosa con el microservicio de Planes de Vuelo")
    @GetMapping("/planes-vuelo")
    public ResponseEntity<String> consultarPlanesVuelo() {
        return ResponseEntity.ok(incidenciaService.consultarMicroservicioPlanesVuelo());
    }
}