package iesdoctorbalmis.daw2.voluntapp.controladores;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import iesdoctorbalmis.daw2.voluntapp.dto.EventosDTO;
import iesdoctorbalmis.daw2.voluntapp.dto.converter.EventoDTOConverter;
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
import java.util.Set;


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
    private final PaginationLinksUtils paginationLinksUtils;


    // Obtencion de todos los Eventos
    @CrossOrigin(origins = "http://localhost:9000")
    @GetMapping("/eventos") 
    public ResponseEntity<?> todosLosEventos(@PageableDefault(size = 10, page = 0) Pageable pageable, HttpServletRequest request) {
        Page<Eventos> listaEventos = eventosService.ObtenerTodosPageable(pageable);

        System.out.println("                       La lista es: "+listaEventos);
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
    @CrossOrigin(origins = "http://localhost:9000")
    @GetMapping("/eventos/{id}")
    public EventosDTO obtenerUno(@PathVariable Long id) {

        Eventos eventos = eventosService.buscarPorId(id)
            .orElseThrow(() -> 
            new EventosNotFoundException(id));

        return eventoDTOConverter.convertToDto(eventos);
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
    @CrossOrigin(origins = "http://localhost:9000")
    @PostMapping("/eventos")
    public ResponseEntity<Eventos> nuevoEvento(@RequestBody CreateEventoDTO nuevo) {

        Instituciones creadoPorInstituciones = institucionesService.buscarPorNombre(nuevo.getCreadoPorUsuario());
        Usuarios creadoPorUsuarios = usuariosService.buscarPorNombre(nuevo.getCreadoPorUsuario());

        Eventos eventoNuevo =  Eventos.builder()
                                    .nombre(nuevo.getNombre())
                                    .fotoEvento(nuevo.getFotoEvento())
                                    .descripcion(nuevo.getDescripcion())
                                    .ubicacion(nuevo.getUbicacion())
                                    .fInicio(nuevo.getFInicio())
                                    .fFin(nuevo.getFFin())
                                    .creadoPorInstituciones(creadoPorInstituciones)
                                    .creadoPorUsuarios(creadoPorUsuarios)
                                    .maxVoluntarios(nuevo.getMaxVoluntarios())
                                    .build();

        Eventos nuevoevento = eventosService.guardar(eventoNuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoevento);

    }

    // Editar evento de la base de datos
    @CrossOrigin(origins = "http://localhost:9000")
    @PutMapping("/eventos/{id}")
    public ResponseEntity<Eventos> editarevento(@RequestBody CreateEventoDTO editarevento, @PathVariable Long id) {

        return eventosService.buscarPorId(id).map(p -> {
            
            return ResponseEntity.ok(eventosService.editar(p));
        }).orElseThrow(() -> new EventosNotFoundException(id));
    }

    // Eliminar evento de la base de datos
    @CrossOrigin(origins = "http://localhost:9000")
    @DeleteMapping("/Eventos/{id}")
    public ResponseEntity<?> eliminarevento(@PathVariable Long id) {

        Eventos evento = eventosService.buscarPorId(id)
                            .orElseThrow(() -> new EventosNotFoundException(id));
        
        eventosService.eliminar(evento);
        return ResponseEntity.noContent().build();
    }
    
}
