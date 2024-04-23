package iesdoctorbalmis.daw2.voluntapp.servicios;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import iesdoctorbalmis.daw2.voluntapp.modelos.Eventos;
import iesdoctorbalmis.daw2.voluntapp.modelos.Usuarios;
import iesdoctorbalmis.daw2.voluntapp.repositorios.EventosRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EventosService {

    private final EventosRepository eventosRepository;

    // guardar usuarios
    public Eventos guardar(Eventos usu) {
        return eventosRepository.save(usu);
    }

    // editar usuarios
    public Eventos editar(Eventos usu) {
        return eventosRepository.save(usu);
    }

    // eliminar usuarios
    public void eliminar(Eventos usu) {
        eventosRepository.delete(usu);
    }

    // obtener todos los usuarios
    public List<Eventos> obtenerTodos() {
        return eventosRepository.findAll();
    }

    // obtener un usuario por id
    public Optional<Eventos> buscarPorId(Long id) {
        return eventosRepository.findById(id);
    }

    public Page<Eventos> buscarPorTituloAndEstadoDisponible(String txt, Pageable pageable) {
        return eventosRepository.findByTituloContainsIgnoreCaseAndEstado(txt, "disponible", pageable);
    }

    // obtener todos los usuarios pageable
    public Page<Eventos> ObtenerTodosPageable(Pageable pageable) {
        return eventosRepository.findAll(pageable);
    }

    // obtener Eventos por estado
    public Page<Eventos> findByEstado(String estado, Pageable pageable) {
        return eventosRepository.findByEstado(estado, pageable);
    }

    public Optional<Eventos> findByIdConUsuarios(Long id) {
        return eventosRepository.findByIdJoinFetch(id);
    }

    public List<Eventos> findByCreadoPorUsuariosIdYEstado(Long id, String estado) {
        return eventosRepository.findByCreadoPorUsuariosIdAndEstado(id, estado);
    }

    public List<Eventos> findByCreadoPorUsuariosId(Long id) {
        return eventosRepository.findByCreadoPorUsuariosId(id);
    }

    public List<Eventos> findByCreadoPorInstitucionesId(Long id) {
        return eventosRepository.findByCreadoPorInstitucionesId(id);
    }

    public Page<Eventos> findByFechaInicioBetween(LocalDateTime fInicio, LocalDateTime fFin, Pageable pageable) {
        return eventosRepository.findByFechaInicioBetween(fInicio, fFin, pageable);
    }

    public List<Eventos> buscarPorEstadoYUsuario(String estado, Usuarios usu) {
        return eventosRepository.findByEstadoAndUsuarios(estado, usu);
    }

    public Page<Eventos> findAllByOrderByUsuariosDesc(Pageable pageable) {
        return eventosRepository.findAllByOrderByUsuariosDesc(pageable);
    }

    public Page<Eventos> obtenerEventosOrdenadosPorFechaProxima(Pageable pageable) {
        return eventosRepository.findAllByOrderByFechaInicioAsc(pageable);
    }

    public Page<Eventos> obtenerEventosOrdenadosPorFechaLejana(Pageable pageable) {
        return eventosRepository.findAllByOrderByFechaInicioDesc(pageable);
    }

    // Filtrado de eventos
    public Page<Eventos> filtrarEventos(LocalDateTime finicio, LocalDateTime ffin, String ubicacionNombre,
            List<String> tags, Pageable pageable) {
        if (finicio != null && ffin != null && ubicacionNombre != null && tags != null) {
            return eventosRepository.findByFInicioBetweenAndFFinBetweenAndUbicacionNombreContainsAndTagsNombreIn(
                    finicio, ffin, ubicacionNombre, tags, pageable);
        } else if (finicio != null && ffin != null && ubicacionNombre != null) {
            return eventosRepository.findByFInicioBetweenAndFFinBetweenAndUbicacionNombreContains(finicio, ffin,
                    ubicacionNombre, pageable);
        } else if (finicio != null && ffin != null && tags != null) {
            return eventosRepository.findByFInicioBetweenAndUbicacionNombreContainsAndTagsNombreIn(finicio, ffin,
                    ubicacionNombre, tags, pageable);
        } else if (finicio != null && ffin != null) {
            return eventosRepository.findByFInicioBetweenAndFFinBetween(finicio, ffin, pageable);
        } else if (finicio != null && ubicacionNombre != null && tags != null) {
            return eventosRepository.findByFInicioAndUbicacionNombreContainsAndTagsNombreIn(finicio, ubicacionNombre,
                    tags, pageable);
        } else if (finicio != null && ubicacionNombre != null) {
            return eventosRepository.findByFInicioAndUbicacionNombreContains(finicio, ubicacionNombre, pageable);
        } else if (finicio != null && tags != null) {
            return eventosRepository.findByFInicioAndTagsNombreIn(finicio, tags, pageable);
        } else if (ffin != null && ubicacionNombre != null && tags != null) {
            return eventosRepository.findByFFinAndUbicacionNombreContainsAndTagsNombreIn(ffin, ubicacionNombre, tags,
                    pageable);
        } else if (ffin != null && ubicacionNombre != null) {
            return eventosRepository.findByFFinAndUbicacionNombreContains(ffin, ubicacionNombre, pageable);
        } else if (ffin != null && tags != null) {
            return eventosRepository.findByFFinAndTagsNombreIn(ffin, tags, pageable);
        } else if (ubicacionNombre != null && tags != null) {
            return eventosRepository.findByUbicacionNombreContainsAndTagsNombreIn(ubicacionNombre, tags, pageable);
        } else if (finicio != null) {
            return eventosRepository.findByFInicio(finicio, finicio.plusDays(1), pageable);
        } else if (ffin != null) {
            return eventosRepository.findByFFin(ffin, ffin.plusDays(1), pageable);
        } else if (ubicacionNombre != null) {
            return eventosRepository.findByUbicacionNombreContains(ubicacionNombre, pageable);
        } else if (tags != null) {
            return eventosRepository.findByTagsNombreIn(tags, pageable);
        } else {
            // Manejo de caso cuando no se proporcionan filtros
            return eventosRepository.findByEstado("disponible", pageable);
        }
    }
}
