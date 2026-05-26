package cl.dgac.incidencias.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "incidencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Incidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigoIncidencia;

    @Column(nullable = false)
    private String tipoIncidencia;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fechaIncidencia;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false)
    private String nivelGravedad;

    @Column(nullable = false)
    private Long planVueloId;

    @Column(nullable = false)
    private Long droneId;

    @Column(nullable = false)
    private Long pilotoId;
}