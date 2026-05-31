package cl.dgac.incidencias.mapper;

import cl.dgac.incidencias.dto.IncidenciaRequestDTO;
import cl.dgac.incidencias.dto.IncidenciaResponseDTO;
import cl.dgac.incidencias.model.Incidencia;
import org.springframework.stereotype.Component;

@Component
public class IncidenciaMapper {

    public Incidencia toEntity(IncidenciaRequestDTO dto) {
        Incidencia incidencia = new Incidencia();

        incidencia.setTipoIncidencia(dto.getTipoIncidencia());
        incidencia.setDescripcion(dto.getDescripcion());
        incidencia.setFechaIncidencia(dto.getFechaIncidencia());
        incidencia.setNivelGravedad(dto.getNivelGravedad());
        incidencia.setPlanVueloId(dto.getPlanVueloId());
        incidencia.setDroneId(dto.getDroneId());
        incidencia.setPilotoId(dto.getPilotoId());

        return incidencia;
    }

    public IncidenciaResponseDTO toDTO(Incidencia incidencia) {
        IncidenciaResponseDTO dto = new IncidenciaResponseDTO();

        dto.setId(incidencia.getId());
        dto.setCodigoIncidencia(incidencia.getCodigoIncidencia());
        dto.setTipoIncidencia(incidencia.getTipoIncidencia());
        dto.setDescripcion(incidencia.getDescripcion());
        dto.setFechaIncidencia(incidencia.getFechaIncidencia());
        dto.setEstado(incidencia.getEstado());
        dto.setNivelGravedad(incidencia.getNivelGravedad());
        dto.setPlanVueloId(incidencia.getPlanVueloId());
        dto.setDroneId(incidencia.getDroneId());
        dto.setPilotoId(incidencia.getPilotoId());

        return dto;
    }

    public void updateEntity(Incidencia incidencia, IncidenciaRequestDTO dto) {
        incidencia.setTipoIncidencia(dto.getTipoIncidencia());
        incidencia.setDescripcion(dto.getDescripcion());
        incidencia.setFechaIncidencia(dto.getFechaIncidencia());
        incidencia.setNivelGravedad(dto.getNivelGravedad());
        incidencia.setPlanVueloId(dto.getPlanVueloId());
        incidencia.setDroneId(dto.getDroneId());
        incidencia.setPilotoId(dto.getPilotoId());
    }
}