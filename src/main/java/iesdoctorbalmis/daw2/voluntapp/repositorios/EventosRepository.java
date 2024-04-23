package iesdoctorbalmis.daw2.voluntapp.repositorios;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import iesdoctorbalmis.daw2.voluntapp.modelos.Eventos;
import iesdoctorbalmis.daw2.voluntapp.modelos.Ubicacion;
import iesdoctorbalmis.daw2.voluntapp.modelos.Usuarios;

public interface EventosRepository extends JpaRepository<Eventos, Long> {

        Page<Eventos> findByTituloContainsIgnoreCase(String txt, Pageable pageable);

        Page<Eventos> findByTituloContainsIgnoreCaseAndEstado(String txt, String estado, Pageable pageable);

        @Query("select p from Eventos p LEFT JOIN FETCH p.usuarios WHERE p.id = :id")
        Optional<Eventos> findByIdJoinFetch(Long id);

        List<Eventos> findByCreadoPorUsuariosId(Long id);

        List<Eventos> findByCreadoPorUsuariosIdAndEstado(Long id, String estado);

        List<Eventos> findByCreadoPorInstitucionesId(Long id);

        Page<Eventos> findByEstado(String estado, Pageable pageable);

        @Query("SELECT e FROM Eventos e WHERE e.fInicio BETWEEN :fechaInicio AND :fechaFin")
        Page<Eventos> findByFechaInicioBetween(
                        @Param("fechaInicio") LocalDateTime fechaInicio,
                        @Param("fechaFin") LocalDateTime fechaFin,
                        Pageable pageable);

        @Query("SELECT e FROM Eventos e WHERE e.estado = :estado AND e.fInicio BETWEEN :fechaInicio AND :fechaFin")
        Page<Eventos> findByEstadoAndFechaInicioBetween(
                        @Param("estado") String estado,
                        @Param("fechaInicio") LocalDateTime fechaInicio,
                        @Param("fechaFin") LocalDateTime fechaFin,
                        Pageable pageable);

        @Query("SELECT e FROM Eventos e JOIN e.ubicacion u WHERE e.estado = 'disponible' AND LOWER(u.nombre) LIKE %:ubicacion%")
        Page<Eventos> findByEstadoAndUbicacionDisponible(
                        @Param("ubicacion") String nombreUbicacion,
                        Pageable pageable);

        @Query("SELECT e FROM Eventos e JOIN e.ubicacion u WHERE e.estado = 'disponible' AND e.fInicio BETWEEN :fInicio AND :fFin AND LOWER(u.nombre) LIKE %:nombreUbicacion%")
        Page<Eventos> findByFechaInicioBetweenAndUbicacionAndEstado(
                        @Param("fInicio") LocalDateTime fInicio,
                        @Param("fFin") LocalDateTime fFin,
                        @Param("nombreUbicacion") String nombreUbicacion,
                        Pageable pageable);

        List<Eventos> findByEstadoAndUsuarios(String estado, Usuarios usu);

        // coger los eventos ordenados por numero de voluntarios apuntados:
        @Query("SELECT e FROM Eventos e JOIN e.usuarios u WHERE e.estado = 'disponible' GROUP BY e.id ORDER BY COUNT(u.id) DESC, e.id")
        Page<Eventos> findAllByOrderByUsuariosDesc(Pageable pageable);

        // coger los eventos ordenados por fecha de inicio:
        @Query("SELECT e FROM Eventos e WHERE e.estado = 'disponible' ORDER BY e.fInicio ASC")
        Page<Eventos> findAllByOrderByFechaInicioAsc(Pageable pageable);

        // coger los eventos ordenados por fecha de inicio tardia:
        @Query("SELECT e FROM Eventos e WHERE e.estado = 'disponible' ORDER BY e.fInicio DESC")
        Page<Eventos> findAllByOrderByFechaInicioDesc(Pageable pageable);

        // filtrado de eventos
        @Query("SELECT e FROM Eventos e JOIN e.tags t WHERE e.fInicio BETWEEN :finicio AND :ffin AND e.ubicacion.nombre LIKE %:ubicacionNombre% AND t.nombre IN :tags")
        Page<Eventos> findByFInicioBetweenAndFFinBetweenAndUbicacionNombreContainsAndTagsNombreIn(
                        LocalDateTime finicio, LocalDateTime ffin, String ubicacionNombre, List<String> tags,
                        Pageable pageable);

        @Query("SELECT e FROM Eventos e WHERE e.fInicio BETWEEN :finicio AND :ffin AND e.ubicacion.nombre LIKE %:ubicacionNombre%")
        Page<Eventos> findByFInicioBetweenAndFFinBetweenAndUbicacionNombreContains(
                        LocalDateTime finicio, LocalDateTime ffin, String ubicacionNombre, Pageable pageable);

        @Query("SELECT e FROM Eventos e JOIN e.tags t WHERE e.fInicio BETWEEN :finicio AND :ffin AND e.ubicacion.nombre LIKE %:ubicacionNombre% AND t.nombre IN :tags")
        Page<Eventos> findByFInicioBetweenAndUbicacionNombreContainsAndTagsNombreIn(
                        LocalDateTime finicio, LocalDateTime ffin, String ubicacionNombre, List<String> tags,
                        Pageable pageable);

        @Query("SELECT e FROM Eventos e WHERE e.fInicio BETWEEN :finicio AND :ffin")
        Page<Eventos> findByFInicioBetweenAndFFinBetween(LocalDateTime finicio, LocalDateTime ffin, Pageable pageable);

        @Query("SELECT e FROM Eventos e JOIN e.tags t WHERE e.fInicio = :finicio AND e.ubicacion.nombre LIKE %:ubicacionNombre% AND t.nombre IN :tags")
        Page<Eventos> findByFInicioAndUbicacionNombreContainsAndTagsNombreIn(
                        LocalDateTime finicio, String ubicacionNombre, List<String> tags, Pageable pageable);

        @Query("SELECT e FROM Eventos e WHERE e.fInicio = :finicio AND e.ubicacion.nombre LIKE %:ubicacionNombre%")
        Page<Eventos> findByFInicioAndUbicacionNombreContains(LocalDateTime finicio, String ubicacionNombre,
                        Pageable pageable);

        @Query("SELECT e FROM Eventos e JOIN e.tags t WHERE e.fInicio = :finicio AND t.nombre IN :tags")
        Page<Eventos> findByFInicioAndTagsNombreIn(LocalDateTime finicio, List<String> tags, Pageable pageable);

        @Query("SELECT e FROM Eventos e JOIN e.tags t WHERE e.fFin = :ffin AND e.ubicacion.nombre LIKE %:ubicacionNombre% AND t.nombre IN :tags")
        Page<Eventos> findByFFinAndUbicacionNombreContainsAndTagsNombreIn(
                        LocalDateTime ffin, String ubicacionNombre, List<String> tags, Pageable pageable);

        @Query("SELECT e FROM Eventos e WHERE e.fFin = :ffin AND e.ubicacion.nombre LIKE %:ubicacionNombre%")
        Page<Eventos> findByFFinAndUbicacionNombreContains(LocalDateTime ffin, String ubicacionNombre,
                        Pageable pageable);

        @Query("SELECT e FROM Eventos e JOIN e.tags t WHERE e.fFin = :ffin AND t.nombre IN :tags")
        Page<Eventos> findByFFinAndTagsNombreIn(LocalDateTime ffin, List<String> tags, Pageable pageable);

        @Query("SELECT e FROM Eventos e JOIN e.tags t WHERE e.ubicacion.nombre LIKE %:ubicacionNombre% AND t.nombre IN :tags")
        Page<Eventos> findByUbicacionNombreContainsAndTagsNombreIn(String ubicacionNombre, List<String> tags,
                        Pageable pageable);

        @Query("SELECT e FROM Eventos e WHERE e.fInicio >= :finicio AND e.fInicio < :finicioPlusOneDay")
        Page<Eventos> findByFInicio(LocalDateTime finicio, LocalDateTime finicioPlusOneDay, Pageable pageable);

        @Query("SELECT e FROM Eventos e WHERE e.fFin >= :ffin AND e.fFin < :ffinPlusOneDay")
        Page<Eventos> findByFFin(LocalDateTime ffin, LocalDateTime ffinPlusOneDay, Pageable pageable);

        @Query("SELECT e FROM Eventos e WHERE e.ubicacion.nombre LIKE %:ubicacionNombre%")
        Page<Eventos> findByUbicacionNombreContains(String ubicacionNombre, Pageable pageable);

        @Query("SELECT e FROM Eventos e JOIN e.tags t WHERE t.nombre IN :tags")
        Page<Eventos> findByTagsNombreIn(List<String> tags, Pageable pageable);
}
