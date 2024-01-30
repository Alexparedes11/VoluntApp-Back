package iesdoctorbalmis.daw2.voluntapp.controladores;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import iesdoctorbalmis.daw2.voluntapp.dto.InstitucionesDTO;
import iesdoctorbalmis.daw2.voluntapp.dto.converter.InstitucionDTOConverter;
import iesdoctorbalmis.daw2.voluntapp.error.usuarios.SearchUsuarioNoResultException;
import iesdoctorbalmis.daw2.voluntapp.error.usuarios.UsuariosNotFoundException;
import iesdoctorbalmis.daw2.voluntapp.modelos.Eventos;
import iesdoctorbalmis.daw2.voluntapp.modelos.Instituciones;
import iesdoctorbalmis.daw2.voluntapp.servicios.InstitucionesService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InstitucionesController {
    
    private final InstitucionesService institucionesService;
    private final InstitucionDTOConverter institucionDTOConverter;

    // Obtencion de todas las instituciones
    @CrossOrigin(origins = "http://localhost:9000")
    @GetMapping("/instituciones") 
    public ResponseEntity<?> TodosLosUsuarios(@PageableDefault(size = 10, page = 0) Pageable pageable, HttpServletRequest request) {
        Page<Instituciones> listaInstituciones = institucionesService.ObtenerTodosPageable(pageable);

        if (listaInstituciones.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay usuarios registrados");
        } else {
            Page<InstitucionesDTO> dtoList = listaInstituciones.map(institucionDTOConverter::convertToDto);
            return ResponseEntity.ok(dtoList);
        }
    }


    // Encontrar al usuarios por la ID (Sin DTO)
    @CrossOrigin(origins = "http://localhost:9000")
    @GetMapping("/instituciones/{id}")
    public Instituciones obtenerUno(@PathVariable Long id) {
        return institucionesService.buscarPorId(id)
            .orElseThrow(() -> // No lanza la excepción
             new UsuariosNotFoundException(id));
    }


    // Filtrado por el nombre de la institución
    @CrossOrigin(origins = "http://localhost:9000")
    @GetMapping(value="/instituciones", params = "nombre")
        public ResponseEntity<?> buscarInstitucionPorNombre(@RequestParam("nombre") String txt, Pageable pageable, HttpServletRequest request) {
        Page<Instituciones> listaInstituciones = institucionesService.buscarPorNombre(txt, pageable);

        if (listaInstituciones.isEmpty()) {
            throw new SearchUsuarioNoResultException(txt);
        } else {
            Page<InstitucionesDTO> dtoList = listaInstituciones.map(institucionDTOConverter::convertToDto);
            return ResponseEntity.ok(dtoList);
        }
    }

    // Añadir institución a la base de datos (Sin DTO)
    @CrossOrigin(origins = "http://localhost:9000")
    @PostMapping("/instituciones")
    public ResponseEntity<Instituciones> nuevaInstitucion(@RequestBody Instituciones nuevo) {

        System.out.println(nuevo);
        Instituciones nuevaInstitucion = institucionesService.guardar(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaInstitucion);

    }

    // Editar institución de la base de datos
    @CrossOrigin(origins = "http://localhost:9000")
    @PutMapping("/instituciones/{id}")
    public ResponseEntity<Instituciones> editarInstitucion(@RequestBody Instituciones editarInstituciones, @PathVariable Long id) {

        return institucionesService.buscarPorId(id).map(p -> {
            p.setNombre(editarInstituciones.getNombre());
            p.setCif(editarInstituciones.getCif());
            p.setPersonaCargo(editarInstituciones.getPersonaCargo());
            p.setFotoInstitucion(editarInstituciones.getFotoInstitucion());
            p.setEventos(editarInstituciones.getEventos());
            return ResponseEntity.ok(institucionesService.editar(p));
        }).orElseThrow(() -> new UsuariosNotFoundException(id));
    }

    // Eliminar institución de la base de datos
    @CrossOrigin(origins = "http://localhost:9000")
    @DeleteMapping("/instituciones/{id}")
    public ResponseEntity<?> eliminarInstitucion(@PathVariable Long id) {

        Instituciones instituciones = institucionesService.buscarPorId(id)
                            .orElseThrow(() -> new UsuariosNotFoundException(id));
        
        institucionesService.eliminar(instituciones);
        return ResponseEntity.noContent().build();
    }

    // Añadir evento a una Institución
    @CrossOrigin(origins = "http://localhost:9000")
    @PostMapping("/instituciones/añadir-evento")
    public ResponseEntity<Instituciones> añadirEvento(@RequestBody Eventos evento, @RequestBody Instituciones instituciones, @PathVariable Long id) {
        
        Set<Eventos> eventoAñadir = instituciones.getEventos();
        eventoAñadir.add(evento);

        return institucionesService.buscarPorId(id).map(p -> {
            p.setEventos(eventoAñadir);
            return ResponseEntity.ok(institucionesService.editar(instituciones));
        }).orElseThrow(() -> new UsuariosNotFoundException(id));
    }

}
