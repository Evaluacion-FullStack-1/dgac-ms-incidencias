package cl.dgac.incidencias.service;

import cl.dgac.incidencias.dto.IncidenciaRequestDTO;
import cl.dgac.incidencias.dto.IncidenciaResponseDTO;
import cl.dgac.incidencias.exception.ResourceNotFoundException;
import cl.dgac.incidencias.mapper.IncidenciaMapper;
import cl.dgac.incidencias.model.Incidencia;
import cl.dgac.incidencias.repository.IncidenciaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class IncidenciaService {

    private final IncidenciaRepository incidenciaRepository;
    private final IncidenciaMapper incidenciaMapper;
    
    // Inyectamos el WebClient configurado directamente
    private final WebClient webClientPlanesVuelo;

    public IncidenciaService(IncidenciaRepository incidenciaRepository,
                             IncidenciaMapper incidenciaMapper,
                             WebClient webClientPlanesVuelo) {
        this.incidenciaRepository = incidenciaRepository;
        this.incidenciaMapper = incidenciaMapper;
        this.webClientPlanesVuelo = webClientPlanesVuelo;
    }

    public List<IncidenciaResponseDTO> listarIncidencias() {
        return incidenciaRepository.findAll()
                .stream()
                .map(incidenciaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public IncidenciaResponseDTO buscarPorId(Long id) {
        Incidencia incidencia = incidenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incidencia no encontrada con ID: " + id));

        return incidenciaMapper.toDTO(incidencia);
    }

    public IncidenciaResponseDTO crearIncidencia(IncidenciaRequestDTO dto) {
        Incidencia incidencia = incidenciaMapper.toEntity(dto);

        incidencia.setCodigoIncidencia("INC-" + System.currentTimeMillis());
        incidencia.setEstado("ABIERTA");

        Incidencia incidenciaGuardada = incidenciaRepository.save(incidencia);

        return incidenciaMapper.toDTO(incidenciaGuardada);
    }

    public IncidenciaResponseDTO actualizarIncidencia(Long id, IncidenciaRequestDTO dto) {
        Incidencia incidencia = incidenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incidencia no encontrada con ID: " + id));

        incidenciaMapper.updateEntity(incidencia, dto);
        Incidencia incidenciaActualizada = incidenciaRepository.save(incidencia);

        return incidenciaMapper.toDTO(incidenciaActualizada);
    }

    public void eliminarIncidencia(Long id) {
        Incidencia incidencia = incidenciaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incidencia no encontrada con ID: " + id));

        incidenciaRepository.delete(incidencia);
    }

    public IncidenciaResponseDTO buscarPorCodigoIncidencia(String codigoIncidencia) {
        Incidencia incidencia = incidenciaRepository.findByCodigoIncidencia(codigoIncidencia)
                .orElseThrow(() -> new ResourceNotFoundException("Incidencia no encontrada con código: " + codigoIncidencia));

        return incidenciaMapper.toDTO(incidencia);
    }

    public List<IncidenciaResponseDTO> listarPorEstado(String estado) {
        return incidenciaRepository.findByEstado(estado)
                .stream()
                .map(incidenciaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<IncidenciaResponseDTO> listarPorGravedad(String nivelGravedad) {
        return incidenciaRepository.findByNivelGravedad(nivelGravedad)
                .stream()
                .map(incidenciaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<IncidenciaResponseDTO> listarPorPlanVuelo(Long planVueloId) {
        return incidenciaRepository.findByPlanVueloId(planVueloId)
                .stream()
                .map(incidenciaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<IncidenciaResponseDTO> buscarPorTipoIncidencia(String tipoIncidencia) {
        return incidenciaRepository.buscarPorTipoIncidencia(tipoIncidencia)
                .stream()
                .map(incidenciaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public String consultarMicroservicioPlanesVuelo() {
        // Utilizamos el WebClient con la ruta relativa, sin localhost
        return webClientPlanesVuelo
                .get()
                .uri("/api/planes-vuelo")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}