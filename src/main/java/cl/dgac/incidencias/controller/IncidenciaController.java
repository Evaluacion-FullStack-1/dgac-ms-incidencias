package cl.dgac.incidencias.controller;

import cl.dgac.incidencias.dto.IncidenciaRequestDTO;
import cl.dgac.incidencias.dto.IncidenciaResponseDTO;
import cl.dgac.incidencias.service.IncidenciaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaController {

    private final IncidenciaService incidenciaService;

    public IncidenciaController(IncidenciaService incidenciaService) {
        this.incidenciaService = incidenciaService;
    }

    @GetMapping
    public ResponseEntity<List<IncidenciaResponseDTO>> listarIncidencias() {
        return ResponseEntity.ok(incidenciaService.listarIncidencias());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidenciaResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(incidenciaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<IncidenciaResponseDTO> crearIncidencia(
            @Valid @RequestBody IncidenciaRequestDTO dto) {

        IncidenciaResponseDTO incidenciaCreada = incidenciaService.crearIncidencia(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(incidenciaCreada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IncidenciaResponseDTO> actualizarIncidencia(
            @PathVariable Long id,
            @Valid @RequestBody IncidenciaRequestDTO dto) {

        return ResponseEntity.ok(incidenciaService.actualizarIncidencia(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarIncidencia(@PathVariable Long id) {
        incidenciaService.eliminarIncidencia(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar-codigo")
    public ResponseEntity<IncidenciaResponseDTO> buscarPorCodigoIncidencia(
            @RequestParam String codigoIncidencia) {

        return ResponseEntity.ok(incidenciaService.buscarPorCodigoIncidencia(codigoIncidencia));
    }

    @GetMapping("/estado")
    public ResponseEntity<List<IncidenciaResponseDTO>> listarPorEstado(
            @RequestParam String estado) {

        return ResponseEntity.ok(incidenciaService.listarPorEstado(estado));
    }

    @GetMapping("/gravedad")
    public ResponseEntity<List<IncidenciaResponseDTO>> listarPorGravedad(
            @RequestParam String nivelGravedad) {

        return ResponseEntity.ok(incidenciaService.listarPorGravedad(nivelGravedad));
    }

    @GetMapping("/plan-vuelo/{planVueloId}")
    public ResponseEntity<List<IncidenciaResponseDTO>> listarPorPlanVuelo(
            @PathVariable Long planVueloId) {

        return ResponseEntity.ok(incidenciaService.listarPorPlanVuelo(planVueloId));
    }

    @GetMapping("/tipo")
    public ResponseEntity<List<IncidenciaResponseDTO>> buscarPorTipoIncidencia(
            @RequestParam String tipoIncidencia) {

        return ResponseEntity.ok(incidenciaService.buscarPorTipoIncidencia(tipoIncidencia));
    }

    @GetMapping("/planes-vuelo")
    public ResponseEntity<String> consultarPlanesVuelo() {
        return ResponseEntity.ok(incidenciaService.consultarMicroservicioPlanesVuelo());
    }
}