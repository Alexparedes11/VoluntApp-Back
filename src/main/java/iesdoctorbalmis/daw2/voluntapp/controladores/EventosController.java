package iesdoctorbalmis.daw2.voluntapp.controladores;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import iesdoctorbalmis.daw2.voluntapp.dto.EventosDTO;
import iesdoctorbalmis.daw2.voluntapp.dto.IdEventoInstitucionDTO;
import iesdoctorbalmis.daw2.voluntapp.dto.IdEventoUsuarioDTO;
import iesdoctorbalmis.daw2.voluntapp.dto.NumeroDeEventosDTO;
import iesdoctorbalmis.daw2.voluntapp.dto.converter.EventoDTOConverter;
import iesdoctorbalmis.daw2.voluntapp.dto.converter.InstitucionDTOConverter;
import iesdoctorbalmis.daw2.voluntapp.dto.converter.UsuarioDTOConverter;
import iesdoctorbalmis.daw2.voluntapp.dto.create.CreateEventoDTO;
import iesdoctorbalmis.daw2.voluntapp.error.eventos.EventosNotFoundException;
import iesdoctorbalmis.daw2.voluntapp.error.usuarios.UsuariosNotFoundException;
import iesdoctorbalmis.daw2.voluntapp.excepciones.AzureBlobStorageException;
import iesdoctorbalmis.daw2.voluntapp.modelos.Eventos;
import iesdoctorbalmis.daw2.voluntapp.modelos.Instituciones;
import iesdoctorbalmis.daw2.voluntapp.modelos.Ubicacion;
import iesdoctorbalmis.daw2.voluntapp.modelos.Usuarios;
import iesdoctorbalmis.daw2.voluntapp.servicios.AzureBlobStorageService;
import iesdoctorbalmis.daw2.voluntapp.servicios.EventosService;
import iesdoctorbalmis.daw2.voluntapp.servicios.InstitucionesService;
import iesdoctorbalmis.daw2.voluntapp.servicios.UbicacionService;
import iesdoctorbalmis.daw2.voluntapp.servicios.UsuariosService;
import iesdoctorbalmis.daw2.voluntapp.util.pagination.PaginationLinksUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequiredArgsConstructor
public class EventosController {

    // Servicios
    private final EventosService eventosService;
    private final UsuariosService usuariosService;
    private final InstitucionesService institucionesService;
    private final UbicacionService ubicacionService;
    private final AzureBlobStorageService azureBlobStorageService;

    // Utils
    private final EventoDTOConverter eventoDTOConverter;
    private final UsuarioDTOConverter usuarioDTOConverter;
    private final InstitucionDTOConverter institucionesDTOConverter;
    private final PaginationLinksUtils paginationLinksUtils;

    // Obtencion de todos los Eventos
    @GetMapping("/eventos")
    public ResponseEntity<?> todosLosEventos(@PageableDefault(size = 9, page = 0) Pageable pageable,
            HttpServletRequest request) {
        Page<Eventos> listaEventos = eventosService.ObtenerTodosPageable(pageable);

        if (listaEventos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay Eventos registrados");
        } else {
            Page<EventosDTO> dtoList = listaEventos.map(eventoDTOConverter::convertToDto);

            UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

            return ResponseEntity.ok().header("link",
                    paginationLinksUtils.createLinkHeader(dtoList, uriBuilder)).body(dtoList);

            // return ResponseEntity.ok(listaEventos);
        }
    }

    // Encontrar al Eventos por la ID
    @GetMapping("/eventos/{id}")
    public Eventos obtenerUno(@PathVariable Long id) {

        Eventos eventos = eventosService.buscarPorId(id)
                .orElseThrow(() -> new EventosNotFoundException(id));

        return eventos;
    }

    // Encontrar al Eventos por la ID (con DTO)
    @GetMapping("/eventosDTO/{id}")
    public EventosDTO obtenerUnoDTO(@PathVariable Long id) {

        Eventos eventos = eventosService.buscarPorId(id)
                .orElseThrow(() -> new EventosNotFoundException(id));

        return eventoDTOConverter.convertToDto(eventos);
    }

    // Devuelve la cantidad de eventos a los que esta creado, apuntado y realizado
    // el usuario
    @GetMapping("/eventos/profile/{id}")
    public NumeroDeEventosDTO obtenerEventosPerfil(@PathVariable Long id) {

        Optional<Usuarios> usu = usuariosService.buscarPorId(id);
        List<Eventos> realizado = eventosService.buscarPorEstadoYUsuario("finalizado", usu.get());
        List<Eventos> disponible = eventosService.buscarPorEstadoYUsuario("disponible", usu.get());
        List<Eventos> creados = eventosService.findByCreadoPorUsuariosId(id);

        NumeroDeEventosDTO numero = new NumeroDeEventosDTO(creados.size(), disponible.size(), realizado.size());

        return numero;

    }

    // Devolver booleano en caso de que el usuario logueado este en el apuntado en
    // el evento o no
    @PostMapping("/eventos/isUserInEvento")
    public Boolean isUserInEvento(@RequestBody IdEventoUsuarioDTO idEventoUsuarioDTO) {

        Optional<Eventos> evento = eventosService.buscarPorId(idEventoUsuarioDTO.getId_evento());
        Optional<Usuarios> usuarios = usuariosService.buscarPorId(idEventoUsuarioDTO.getId_usuario());

        if (evento.isPresent()) {

            for (Usuarios usu : evento.get().getUsuarios()) {

                if (usu.equals(usuarios.get())) {
                    return true;
                }
            }
        }

        return false;

    }

    // Devolver booleano en caso de que la institucion este logueada y este apoyando
    // el evento o no
    @PostMapping("/eventos/isInstitutionInEvento")
    public Boolean isInstitutionInEvento(@RequestBody IdEventoInstitucionDTO idEventoInstitucionDTO){

        Optional<Eventos> evento = eventosService.buscarPorId(idEventoInstitucionDTO.getId_evento());
        Optional<Instituciones> instituciones = institucionesService.buscarPorId(idEventoInstitucionDTO.getId_institucion());

        if (evento.isPresent()) {
            for (Instituciones ins : evento.get().getInstituciones()) {
                if (ins.equals(instituciones.get())) {
                    return true;
                }
            }
        }

        return false;
    }

    // Añadir usuario al evento
    @PostMapping("/eventos/apuntar-usuario")
    public ResponseEntity<?> apuntarUsuarioEvento(@RequestBody IdEventoUsuarioDTO idEventoUsuarioDTO) {

        Optional<Eventos> evento = eventosService.buscarPorId(idEventoUsuarioDTO.getId_evento());
        Optional<Usuarios> usuarios = usuariosService.buscarPorId(idEventoUsuarioDTO.getId_usuario());

        if (evento.isPresent()) {

            usuarios.get().addEventos(evento.get());
            usuariosService.editar(usuarios.get());

            return ResponseEntity.ok(usuarioDTOConverter.convertToDto(usuarios.get()));

        } else {
            throw new EventosNotFoundException(idEventoUsuarioDTO.getId_evento());
        }
    }

    // Apoyar evento
    @PostMapping("/eventos/apuntar-institucion")
    public ResponseEntity<?> apuntarInstitucionEvento(@RequestBody IdEventoInstitucionDTO idEventoInstitucionDTO) {

        Optional<Eventos> evento = eventosService.buscarPorId(idEventoInstitucionDTO.getId_evento());
        Optional<Instituciones> instituciones = institucionesService.buscarPorId(idEventoInstitucionDTO.getId_institucion());

        if (evento.isPresent()) {

            instituciones.get().addEventos(evento.get());
            institucionesService.editar(instituciones.get());

            return ResponseEntity.ok(institucionesDTOConverter.convertToDto(instituciones.get()));

        } else {
            throw new EventosNotFoundException(idEventoInstitucionDTO.getId_evento());
        }
    }

    // Desapuntar usuario del evento
    @PostMapping("/eventos/desapuntar-usuario")
    public ResponseEntity<?> desapuntarUsuarioEvento(@RequestBody IdEventoUsuarioDTO idEventoUsuarioDTO) {

        Optional<Eventos> evento = eventosService.buscarPorId(idEventoUsuarioDTO.getId_evento());
        Optional<Usuarios> usuarios = usuariosService.buscarPorId(idEventoUsuarioDTO.getId_usuario());

        if (evento.isPresent()) {

            usuarios.get().deleteEventos(evento.get());
            usuariosService.editar(usuarios.get());

            return ResponseEntity.ok(usuarioDTOConverter.convertToDto(usuarios.get()));

        } else {
            throw new EventosNotFoundException(idEventoUsuarioDTO.getId_evento());
        }

    }

    // Desapuntar institución del evento
    @PostMapping("/eventos/desapuntar-institucion")
    public ResponseEntity<?> desapuntarInstitucionEvento(@RequestBody IdEventoInstitucionDTO idEventoInstitucionDTO) {

        Optional<Eventos> evento = eventosService.buscarPorId(idEventoInstitucionDTO.getId_evento());
        Optional<Instituciones> instituciones = institucionesService.buscarPorId(idEventoInstitucionDTO.getId_institucion());

        if (evento.isPresent()) {

            instituciones.get().deleteEventos(evento.get());
            institucionesService.editar(instituciones.get());

            return ResponseEntity.ok(institucionesDTOConverter.convertToDto(instituciones.get()));

        } else {
            throw new EventosNotFoundException(idEventoInstitucionDTO.getId_evento());
        }

    }

    // Añadir Eventos a la base de datos

    @PostMapping("/eventos")
    public ResponseEntity<Eventos> nuevoEvento(@RequestBody CreateEventoDTO nuevo) throws AzureBlobStorageException {

        Instituciones creadoPorInstituciones = null;
        Usuarios creadoPorUsuarios = null;

        if (nuevo.getInstitucionNombre() != null) {
            creadoPorInstituciones = institucionesService.buscarPorId(nuevo.getUsuarioId()).orElse(null);
        }else {
            creadoPorUsuarios = usuariosService.buscarPorId(nuevo.getUsuarioId()).orElse(null);
        }

        Ubicacion u = Ubicacion.builder()
                .id(null)
                .nombre(nuevo.getNombreUbicacion())
                .lat(nuevo.getLat())
                .lon(nuevo.getLon())
                .build();

        String ubicacionImagenAzure = "https://voluntapp.blob.core.windows.net/images/"
                + azureBlobStorageService.uploadFile("eventos", UUID.randomUUID().toString(), nuevo.getImagen());

        Eventos eventoNuevo = Eventos.builder()
                .titulo(nuevo.getTitulo())
                .imagen(ubicacionImagenAzure)
                .descripcion(nuevo.getDescripcion())
                .ubicacion(u)
                .fInicio(nuevo.getFInicio())
                .fFin(nuevo.getFFin())
                .creadoPorInstituciones(creadoPorInstituciones)
                .creadoPorUsuarios(creadoPorUsuarios)
                .estado("revision")
                .maxVoluntarios(nuevo.getMaxVoluntarios())
                .build();

        ubicacionService.guardar(u);
        Eventos nuevoevento = eventosService.guardar(eventoNuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoevento);

    }

    // Editar evento de la base de datos
    @PutMapping("/eventos/{id}")
    public ResponseEntity<Eventos> editarevento(@RequestBody CreateEventoDTO editarevento, @PathVariable Long id) {

        return eventosService.buscarPorId(id).map(p -> {

            return ResponseEntity.ok(eventosService.editar(p));
        }).orElseThrow(() -> new EventosNotFoundException(id));
    }

    // Eliminar evento de la base de datos
    @DeleteMapping("/eventos/{id}")
    public ResponseEntity<?> eliminarevento(@PathVariable Long id) {

        Eventos evento = eventosService.buscarPorId(id)
                .orElseThrow(() -> new EventosNotFoundException(id));

        eventosService.eliminar(evento);
        return ResponseEntity.noContent().build();
    }

    // Obtener un eventos de un usuario
    @GetMapping("/eventos/usuario/{id}")
    public ResponseEntity<?> obtenerEventos(@PathVariable Long id) {
        Usuarios usuario = usuariosService.buscarPorId(id)
                .orElseThrow(() -> new UsuariosNotFoundException(id));
        List<EventosDTO> eventosDTOList = usuario.getEventos().stream()
                .map(eventoDTOConverter::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(eventosDTOList);
    }

    // Obtener eventos creados por un usuario
    @GetMapping("/eventos/creadoPorUsuario/{usuarioId}")
    public ResponseEntity<?> obtenerEventosCreadosPorUsuario(@PathVariable Long usuarioId) {
        Usuarios usuario = usuariosService.buscarPorId(usuarioId)
                .orElseThrow(() -> new UsuariosNotFoundException(usuarioId));
        List<Eventos> eventos = eventosService.findByCreadoPorUsuariosId(usuarioId);
        List<EventosDTO> eventosDTOList = eventos.stream()
                .map(eventoDTOConverter::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(eventosDTOList);
    }

    // Obtener eventos con un estado concreto
    @GetMapping("/eventos/buscaporestado/{estado}")
    public ResponseEntity<?> obtenerEventosEnRevision(@PathVariable String estado, Pageable pageable) {
        Page<Eventos> eventos = eventosService.findByEstado(estado, pageable);
        Page<EventosDTO> eventosDTOPage = eventos.map(eventoDTOConverter::convertToDto);
        return ResponseEntity.ok(eventosDTOPage);
    }

    // Obtener booleano para ver si un usuario es creador de un evento
    @GetMapping("/eventos/esCreador/{usuarioId}/{eventoId}")
    public boolean esCreadorEvento(@PathVariable Long usuarioId, @PathVariable Long eventoId) {
        Eventos evento = eventosService.buscarPorId(eventoId)
                .orElseThrow(() -> new EventosNotFoundException(eventoId));
        return evento.getCreadoPorUsuarios().getId().equals(usuarioId);
    }

    // Obtener eventos filtrados por búsqueda de texto
    @GetMapping("/eventos/buscar/{texto}")
    public ResponseEntity<?> buscarEventosPorTexto(@PathVariable String texto, Pageable pageable) {
        Page<Eventos> eventos = eventosService.buscarPorTitulo(texto, pageable);
        Page<EventosDTO> eventosDTOPage = eventos.map(eventoDTOConverter::convertToDto);
        return ResponseEntity.ok(eventosDTOPage);
    }

    // Actualizar estado del evento
    @PutMapping("/eventos/{id}/estado")
    public ResponseEntity<Eventos> actualizarEstadoEvento(@PathVariable Long id, @RequestBody String estado) throws AzureBlobStorageException {
        Eventos evento = eventosService.buscarPorId(id)
                .orElseThrow(() -> new EventosNotFoundException(id));
        evento.setEstado(estado);
        return ResponseEntity.ok(eventosService.editar(evento));
    }

    // Obtener los eventos entre dos fechas

    @GetMapping("/eventos/disponibles-entre-fechas/{fInicio}/{fFin}")
    public ResponseEntity<?> obtenerEventosDisponiblesEntreFechas(@PathVariable LocalDateTime fInicio,
            @PathVariable LocalDateTime fFin, Pageable pageable) {
        Page<Eventos> eventos = eventosService.findByEstadoAndFechaInicioBetween("disponible", fInicio, fFin, pageable);
        Page<EventosDTO> eventosDTOPage = eventos.map(eventoDTOConverter::convertToDto);
        return ResponseEntity.ok(eventosDTOPage);
    }

    // Obtener eventos por ubicacion con estado disponible

    @GetMapping("/eventos/ubicacion/disponibles/{nombreUbicacion}")
    public ResponseEntity<?> obtenerEventosDisponiblesPorUbicacion(@PathVariable String nombreUbicacion,
            Pageable pageable) {
        Page<Eventos> eventos = eventosService.findByEstadoAndUbicacionDisponible(nombreUbicacion.toLowerCase(),
                pageable);
        Page<EventosDTO> eventosDTOPage = eventos.map(eventoDTOConverter::convertToDto);
        return ResponseEntity.ok(eventosDTOPage);
    }

    // Obtener eventos filtrados por ubicacion y entre dos fechas
    @GetMapping("eventos/disponibles-entre-fechas-y-ubicacion/{fInicio}/{fFin}/{nombreUbicacion}")
    public ResponseEntity<Page<EventosDTO>> obtenerEventosEntreFechasYUbicacion(@PathVariable LocalDateTime fInicio,
            @PathVariable LocalDateTime fFin, @PathVariable String nombreUbicacion, Pageable pageable) {

        Page<Eventos> eventos = eventosService.findByFechaInicioBetweenAndUbicacionAndEstado(
                fInicio, fFin, nombreUbicacion.toLowerCase(), pageable);

        Page<EventosDTO> eventosDTOPage = eventos.map(eventoDTOConverter::convertToDto);
        return ResponseEntity.ok(eventosDTOPage);
    }

    // Obtener eventos ordenados por fecha de inicio
    // @GetMapping("/eventos/ordenarporfecha")
    // public ResponseEntity<?> obtenerEventosOrdenadosPorFechaInicio(Pageable pageable) {
    //     Page<Eventos> eventos = eventosService.ObtenerTodosOrdenadosPorFechaInicioDesc(pageable);
    //     Page<EventosDTO> eventosDTOPage = eventos.map(eventoDTOConverter::convertToDto);
    //     return ResponseEntity.ok(eventosDTOPage);
    // }

}
