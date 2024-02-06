package iesdoctorbalmis.daw2.voluntapp.controladores;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import iesdoctorbalmis.daw2.voluntapp.dto.EventosDTO;
import iesdoctorbalmis.daw2.voluntapp.dto.IdEventoUsuarioDTO;
import iesdoctorbalmis.daw2.voluntapp.dto.converter.EventoDTOConverter;
import iesdoctorbalmis.daw2.voluntapp.dto.converter.UsuarioDTOConverter;
import iesdoctorbalmis.daw2.voluntapp.dto.create.CreateEventoDTO;
import iesdoctorbalmis.daw2.voluntapp.error.eventos.EventosNotFoundException;
import iesdoctorbalmis.daw2.voluntapp.modelos.Eventos;
import iesdoctorbalmis.daw2.voluntapp.modelos.Instituciones;
import iesdoctorbalmis.daw2.voluntapp.modelos.Usuarios;
import iesdoctorbalmis.daw2.voluntapp.servicios.EventosService;
import iesdoctorbalmis.daw2.voluntapp.servicios.InstitucionesService;
import iesdoctorbalmis.daw2.voluntapp.servicios.UsuariosService;
import iesdoctorbalmis.daw2.voluntapp.util.pagination.PaginationLinksUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    // Utils
    private final EventoDTOConverter eventoDTOConverter;
    private final UsuarioDTOConverter usuarioDTOConverter;
    private final PaginationLinksUtils paginationLinksUtils;


    // Obtencion de todos los Eventos
    @GetMapping("/eventos") 
    public ResponseEntity<?> todosLosEventos(@PageableDefault(size = 10, page = 0) Pageable pageable, HttpServletRequest request) {
        Page<Eventos> listaEventos = eventosService.ObtenerTodosPageable(pageable);

        if (listaEventos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay Eventos registrados");
        } else {
            Page<EventosDTO> dtoList = listaEventos.map(eventoDTOConverter::convertToDto);

            UriComponentsBuilder uriBuilder =
					UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
			
			return ResponseEntity.ok().header("link", 
					paginationLinksUtils.createLinkHeader(dtoList, uriBuilder)).body(dtoList);
            
            // return ResponseEntity.ok(listaEventos);
        }
    }


    // Encontrar al Eventos por la ID (con DTO)
    @GetMapping("/eventos/{id}")
    public EventosDTO obtenerUno(@PathVariable Long id) {

        Eventos eventos = eventosService.buscarPorId(id)
            .orElseThrow(() -> 
            new EventosNotFoundException(id));

        return eventoDTOConverter.convertToDto(eventos);
    }

    // Devolver booleano en caso de que el usuario logueado este en el apuntado en el evento o no
    @PostMapping("/eventos/isUserInEvento")
    public Boolean isUserInEvento(@RequestBody IdEventoUsuarioDTO idEventoUsuarioDTO) {

        Optional<Eventos> evento = eventosService.buscarPorId(idEventoUsuarioDTO.getId_evento());
        Optional<Usuarios> usuarios = usuariosService.buscarPorId(idEventoUsuarioDTO.getId_usuario());

        if (evento.isPresent()) {

            for ( Usuarios usu : evento.get().getUsuarios()) {

                if (usu.equals(usuarios.get())) {
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

    // Desapuntar usuario del evento
    @PostMapping("/eventos/desapuntar-usuario")
    public ResponseEntity<?> desapuntarUsuarioEvento(@RequestBody IdEventoUsuarioDTO idEventoUsuarioDTO ) {

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


    // Filtrado por el nombre del evento ( NO FUNCIONA )
    // @CrossOrigin(origins = "http://localhost:9000")
    // @GetMapping(value="/Eventos", params = "nombre")
    //     public ResponseEntity<?> buscarEventosPorNombre(@RequestParam("nombre") String txt, Pageable pageable, HttpServletRequest request) {
    //     Page<Eventos> listaEventos = eventosService.buscarPorNombre(txt, pageable);

    //     if (listaEventos.isEmpty()) {
    //         throw new SearchEventosNoResultException(txt);
    //     } else {
    //         Page<EventosDTO> dtoList = listaEventos.map(eventoDTOConverter::convertToDto);
    //         return ResponseEntity.ok(dtoList);
    //     }
    // }


    // Añadir Eventos a la base de datos
    @PostMapping("/eventos")
    public ResponseEntity<Eventos> nuevoEvento(@RequestBody CreateEventoDTO nuevo) {

        Instituciones creadoPorInstituciones = institucionesService.buscarPorNombre(nuevo.getCreadoPorUsuario());
        Usuarios creadoPorUsuarios = usuariosService.buscarPorUsername(nuevo.getCreadoPorUsuario()).orElse(null);

        Eventos eventoNuevo =  Eventos.builder()
                                    .titulo(nuevo.getTitulo())
                                    .imagen(nuevo.getImagen())
                                    .descripcion(nuevo.getDescripcion())
                                    .ubicacion(nuevo.getUbicacion())
                                    .fInicio(nuevo.getFInicio())
                                    .fFin(nuevo.getFFin())
                                    .creadoPorInstituciones(creadoPorInstituciones)
                                    .creadoPorUsuarios(creadoPorUsuarios)
                                    .estado("Revisión")
                                    .maxVoluntarios(nuevo.getMaxVoluntarios())
                                    .build();

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
    @DeleteMapping("/Eventos/{id}")
    public ResponseEntity<?> eliminarevento(@PathVariable Long id) {

        Eventos evento = eventosService.buscarPorId(id)
                            .orElseThrow(() -> new EventosNotFoundException(id));
        
        eventosService.eliminar(evento);
        return ResponseEntity.noContent().build();
    }
    
}
