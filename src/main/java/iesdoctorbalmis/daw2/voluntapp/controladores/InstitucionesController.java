package iesdoctorbalmis.daw2.voluntapp.controladores;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import iesdoctorbalmis.daw2.voluntapp.dto.InstitucionesDTO;
import iesdoctorbalmis.daw2.voluntapp.dto.converter.InstitucionDTOConverter;
import iesdoctorbalmis.daw2.voluntapp.dto.create.CreateInstitucionDTO;
import iesdoctorbalmis.daw2.voluntapp.error.instituciones.InstitucionesNotFoundException;
import iesdoctorbalmis.daw2.voluntapp.error.instituciones.SearchInstitucionesNoRestultException;
import iesdoctorbalmis.daw2.voluntapp.modelos.Eventos;
import iesdoctorbalmis.daw2.voluntapp.modelos.Instituciones;
import iesdoctorbalmis.daw2.voluntapp.servicios.InstitucionesService;
import iesdoctorbalmis.daw2.voluntapp.util.pagination.PaginationLinksUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InstitucionesController {
    
    private final InstitucionesService institucionesService;
    private final InstitucionDTOConverter institucionDTOConverter;
    private final PaginationLinksUtils paginationLinksUtils;


    // Obtención de todos los Instituciones
    @GetMapping("/instituciones") 
    public ResponseEntity<?> todasLasInstituciones(@PageableDefault(size = 10, page = 0) Pageable pageable, HttpServletRequest request) {
        Page<Instituciones> listaInstituciones = institucionesService.ObtenerTodosPageable(pageable);
        if (listaInstituciones.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay instituciones registradas");
        } else {
            Page<InstitucionesDTO> dtoList = listaInstituciones.map(institucionDTOConverter::convertToDto);

            UriComponentsBuilder uriBuilder =
					UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
			
			return ResponseEntity.ok().header("link", 
					paginationLinksUtils.createLinkHeader(dtoList, uriBuilder)).body(dtoList);
            
        }
    }


    // Encontrar al Instituciones por la ID (con DTO)
    @GetMapping("/instituciones/{id}")
    public InstitucionesDTO obtenerUno(@PathVariable Long id) {

        Instituciones instituciones = institucionesService.buscarPorId(id)
            .orElseThrow(() -> // No lanza la excepción
            new InstitucionesNotFoundException(id));

        return institucionDTOConverter.convertToDto(instituciones);
    }


    // Filtrado por el nombre de institución ( NO FUNCIONA )
    @GetMapping(value="/instituciones", params = "nombre")
        public ResponseEntity<?> buscarInstitucionesPorNombre(@RequestParam("nombre") String txt, Pageable pageable, HttpServletRequest request) {
        Page<Instituciones> listaInstituciones = institucionesService.buscarPorNombre(txt, pageable);

        if (listaInstituciones.isEmpty()) {
            throw new SearchInstitucionesNoRestultException(txt);
        } else {
            Page<InstitucionesDTO> dtoList = listaInstituciones.map(institucionDTOConverter::convertToDto);
            return ResponseEntity.ok(dtoList);
        }
    }


    // Añadir Instituciones a la base de datos
    @PostMapping("/instituciones")
    public ResponseEntity<Instituciones> nuevoUsuario(@RequestBody CreateInstitucionDTO nuevo) {

        Instituciones institucionesNuevo =  Instituciones.builder()
                                    .nombre(nuevo.getNombre())
                                    .cif(nuevo.getCif())
                                    .personaCargo(nuevo.getPersonaCargo())
                                    .fotoInstitucion(nuevo.getFotoInstitucion())
                                    .username(nuevo.getEmail())
                                    .password(nuevo.getPassword())
                                    .telefono(nuevo.getTelefono())
                                    .nombreLegal(nuevo.getNombreLegal())
                                    .build();

        Instituciones nuevaInstitucion = institucionesService.guardar(institucionesNuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaInstitucion);

    }

    // Editar instituciones de la base de datos
    @PutMapping("/instituciones/{id}")
    public ResponseEntity<Instituciones> editaInstitucion(@RequestBody CreateInstitucionDTO editaInstitucion, @PathVariable Long id) {

        return institucionesService.buscarPorId(id).map(p -> {
            p.setNombre(editaInstitucion.getNombre());
            p.setNombreLegal(editaInstitucion.getNombreLegal());
            p.setTelefono(editaInstitucion.getTelefono());
            p.setUsername(editaInstitucion.getEmail());
            p.setPassword(editaInstitucion.getPassword());
            p.setCif(editaInstitucion.getCif());
            p.setFotoInstitucion(editaInstitucion.getFotoInstitucion());
            p.setPersonaCargo(editaInstitucion.getPersonaCargo());
            p.setEventos(p.getEventos());

            return ResponseEntity.ok(institucionesService.editar(p));
        }).orElseThrow(() -> new InstitucionesNotFoundException(id));
    }

    // Eliminar institución de la base de datos
    @DeleteMapping("/instituciones/{id}")
    public ResponseEntity<?> eliminarInstitucion(@PathVariable Long id) {

        Instituciones instituciones = institucionesService.buscarPorId(id)
                            .orElseThrow(() -> new InstitucionesNotFoundException(id));
        
        institucionesService.eliminar(instituciones);
        return ResponseEntity.noContent().build();
    }

    // Añadir evento a una Institución ( not works )
    @PostMapping("/instituciones/añadir-evento")
    public ResponseEntity<Instituciones> añadirEvento(@RequestBody Eventos eventos, @RequestBody Instituciones instituciones) {
        
        Set<Eventos> eventoAñadir = instituciones.getEventos();
        eventoAñadir.add(eventos);

        return institucionesService.buscarPorId(instituciones.getId()).map(p -> {
            p.setEventos(eventoAñadir);
            return ResponseEntity.ok(institucionesService.editar(instituciones));
        }).orElseThrow(() -> new InstitucionesNotFoundException(instituciones.getId()));
    }

}
