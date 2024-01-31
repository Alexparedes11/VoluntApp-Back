package iesdoctorbalmis.daw2.voluntapp.controladores;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import iesdoctorbalmis.daw2.voluntapp.dto.UsuariosDTO;
import iesdoctorbalmis.daw2.voluntapp.dto.converter.UsuarioDTOConverter;
import iesdoctorbalmis.daw2.voluntapp.dto.create.CreateUsuarioDTO;
import iesdoctorbalmis.daw2.voluntapp.error.usuarios.SearchUsuarioNoResultException;
import iesdoctorbalmis.daw2.voluntapp.error.usuarios.UsuariosNotFoundException;
import iesdoctorbalmis.daw2.voluntapp.modelos.Eventos;
import iesdoctorbalmis.daw2.voluntapp.modelos.Usuarios;
import iesdoctorbalmis.daw2.voluntapp.servicios.UsuariosService;
import iesdoctorbalmis.daw2.voluntapp.util.pagination.PaginationLinksUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

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
public class UsuariosController {

    private final UsuariosService usuariosService;
    private final UsuarioDTOConverter usuarioDTOConverter;
    private final PaginationLinksUtils paginationLinksUtils;


    // Obtencion de todos los usuarios
    @CrossOrigin(origins = "http://localhost:9000")
    @GetMapping("/usuarios") 
    public ResponseEntity<?> todosLosUsuarios(@PageableDefault(size = 10, page = 0) Pageable pageable, HttpServletRequest request) {
        Page<Usuarios> listaUsuarios = usuariosService.ObtenerTodosPageable(pageable);
        if (listaUsuarios.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay usuarios registrados");
        } else {
            Page<UsuariosDTO> dtoList = listaUsuarios.map(usuarioDTOConverter::convertToDto);

            UriComponentsBuilder uriBuilder =
					UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());
			
			return ResponseEntity.ok().header("link", 
					paginationLinksUtils.createLinkHeader(dtoList, uriBuilder)).body(dtoList);
            
            // return ResponseEntity.ok(listaUsuarios);
        }
    }


    // Encontrar al usuarios por la ID (con DTO)
    @CrossOrigin(origins = "http://localhost:9000")
    @GetMapping("/usuarios/{id}")
    public UsuariosDTO obtenerUno(@PathVariable Long id) {

        Usuarios usuarios = usuariosService.buscarPorId(id)
            .orElseThrow(() -> // No lanza la excepción
            new UsuariosNotFoundException(id));

        return usuarioDTOConverter.convertToDto(usuarios);
    }


    // Filtrado por el nombre del usuario ( NO FUNCIONA )
    @CrossOrigin(origins = "http://localhost:9000")
    @GetMapping(value="/usuarios", params = "nombre")
        public ResponseEntity<?> buscarUsuariosPorNombre(@RequestParam("nombre") String txt, Pageable pageable, HttpServletRequest request) {
        Page<Usuarios> listaUsuarios = usuariosService.buscarPorNombre(txt, pageable);

        if (listaUsuarios.isEmpty()) {
            throw new SearchUsuarioNoResultException(txt);
        } else {
            Page<UsuariosDTO> dtoList = listaUsuarios.map(usuarioDTOConverter::convertToDto);
            return ResponseEntity.ok(dtoList);
        }
    }


    // Añadir usuarios a la base de datos
    @CrossOrigin(origins = "http://localhost:9000")
    @PostMapping("/usuarios")
    public ResponseEntity<Usuarios> nuevoUsuario(@RequestBody CreateUsuarioDTO nuevo) {

        Usuarios usuarioNuevo =  Usuarios.builder()
                                    .apellidos(nuevo.getApellidos())
                                    .contraseña(nuevo.getContraseña())
                                    .direccion(nuevo.getDireccion())
                                    .dni(nuevo.getDni())
                                    .nombre(nuevo.getNombre())
                                    .email(nuevo.getEmail())
                                    .rol(nuevo.getRol())
                                    .fotoPerfil(nuevo.getFotoPerfil())
                                    .build();

        Usuarios nuevoUsuario = usuariosService.guardar(usuarioNuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);

    }

    // Editar usuario de la base de datos
    @CrossOrigin(origins = "http://localhost:9000")
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuarios> editarUsuario(@RequestBody CreateUsuarioDTO editarUsuario, @PathVariable Long id) {

        return usuariosService.buscarPorId(id).map(p -> {
            p.setNombre(editarUsuario.getNombre());
            p.setApellidos(editarUsuario.getApellidos());
            p.setContraseña(editarUsuario.getContraseña());
            p.setDireccion(editarUsuario.getDireccion());
            p.setDni(editarUsuario.getDni());
            p.setEmail(editarUsuario.getEmail());
            p.setFotoPerfil(editarUsuario.getFotoPerfil());
            p.setEventos(p.getEventos());
            p.setRol(editarUsuario.getRol());
            return ResponseEntity.ok(usuariosService.editar(p));
        }).orElseThrow(() -> new UsuariosNotFoundException(id));
    }

    // Eliminar usuario de la base de datos
    @CrossOrigin(origins = "http://localhost:9000")
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {

        Usuarios usuario = usuariosService.buscarPorId(id)
                            .orElseThrow(() -> new UsuariosNotFoundException(id));
        
        usuariosService.eliminar(usuario);
        return ResponseEntity.noContent().build();
    }

    // Añadir evento a un Usuario ( not works )
    @CrossOrigin(origins = "http://localhost:9000")
    @PostMapping("/usuarios/añadir-evento")
    public ResponseEntity<Usuarios> añadirEvento(@RequestBody Eventos eventos, @RequestBody Usuarios usuario) {
        
        Set<Eventos> eventoAñadir = usuario.getEventos();
        eventoAñadir.add(eventos);

        return usuariosService.buscarPorId(usuario.getId()).map(p -> {
            p.setEventos(eventoAñadir);
            return ResponseEntity.ok(usuariosService.editar(usuario));
        }).orElseThrow(() -> new UsuariosNotFoundException(usuario.getId()));
    }
    
}
