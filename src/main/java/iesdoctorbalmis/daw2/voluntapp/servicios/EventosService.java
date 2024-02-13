package iesdoctorbalmis.daw2.voluntapp.servicios;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import iesdoctorbalmis.daw2.voluntapp.modelos.Eventos;
import iesdoctorbalmis.daw2.voluntapp.repositorios.EventosRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
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

    // buscar usuarios por nombre
    public Page<Eventos> buscarPorNombre(String txt, Pageable pageable) {
        return eventosRepository.findByTituloContainsIgnoreCase(txt, pageable);
    }

    // obtener todos los usuarios pageable
    public Page<Eventos> ObtenerTodosPageable(Pageable pageable) {
        return eventosRepository.findAll(pageable);
    }
    //obtenerEventos por estado
    public Page<Eventos> findByEstado(String estado, Pageable pageable) {
        return eventosRepository.findByEstado(estado, pageable);
    }

    public Optional<Eventos> findByIdConUsuarios(Long id) {
        return eventosRepository.findByIdJoinFetch(id);
    }
    
    public List<Eventos> findByCreadoPorUsuariosId(Long id) {
        return eventosRepository.findByCreadoPorUsuariosId(id);
    }

    public Page<Eventos> findByFechaInicioBetween(LocalDateTime fInicio, LocalDateTime fFin, Pageable pageable) {
        return eventosRepository.findByFechaInicioBetween(fInicio, fFin, pageable);
    }

    public Page<Eventos> findByEstadoAndFechaInicioBetween (String estado, LocalDateTime fInicio, LocalDateTime fFin, Pageable pageable) {
        return eventosRepository.findByEstadoAndFechaInicioBetween(estado, fInicio, fFin, pageable);
    }

    //obtener eventos por ubicacion
    public Page<Eventos> findByUbicacionContainsIgnoreCase(String txt, Pageable pageable) {
        return eventosRepository.findByUbicacionContainsIgnoreCase(txt, pageable);
    }
}
