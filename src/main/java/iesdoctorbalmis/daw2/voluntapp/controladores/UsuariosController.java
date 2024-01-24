package iesdoctorbalmis.daw2.voluntapp.controladores;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import iesdoctorbalmis.daw2.voluntapp.dto.UsuariosDTO;
import iesdoctorbalmis.daw2.voluntapp.dto.converter.UsuarioDTOConverter;
import iesdoctorbalmis.daw2.voluntapp.modelos.Usuarios;
import iesdoctorbalmis.daw2.voluntapp.servicios.UsuariosService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequiredArgsConstructor
public class UsuariosController {

    private final UsuariosService usuariosService;
    private final UsuarioDTOConverter usuarioDTOConverter = new UsuarioDTOConverter(null);


    @CrossOrigin(origins = "http://localhost:9000")
    @GetMapping("/usuarios") 
    public ResponseEntity<?> TodosLosUsuarios() {
        List<Usuarios> listaUsuarios = usuariosService.obtenerTodos();

        if (listaUsuarios.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay usuarios registrados");
        } else {
            // Falta la transformación a DTO (En caso de que sea necesaria)
            return ResponseEntity.ok(listaUsuarios);
        }
    }

    @CrossOrigin(origins = "http://localhost:9000")
    @GetMapping("/usuarios/{id}")
    public Usuarios obtenerUno(@PathVariable Long id) {
        return usuariosService.buscarPorId(id).orElse(null);
    }
    
    
    
}
