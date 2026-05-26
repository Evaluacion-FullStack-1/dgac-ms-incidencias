package cl.dgac.incidencias.repository;

import cl.dgac.incidencias.model.Incidencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {

    Optional<Incidencia> findByCodigoIncidencia(String codigoIncidencia);

    List<Incidencia> findByEstado(String estado);

    List<Incidencia> findByNivelGravedad(String nivelGravedad);

    List<Incidencia> findByPlanVueloId(Long planVueloId);

    @Query("SELECT i FROM Incidencia i WHERE LOWER(i.tipoIncidencia) LIKE LOWER(CONCAT('%', :tipoIncidencia, '%'))")
    List<Incidencia> buscarPorTipoIncidencia(String tipoIncidencia);
}