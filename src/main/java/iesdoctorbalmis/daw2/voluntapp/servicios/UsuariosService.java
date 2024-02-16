package iesdoctorbalmis.daw2.voluntapp.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import iesdoctorbalmis.daw2.voluntapp.modelos.Usuarios;
import iesdoctorbalmis.daw2.voluntapp.repositorios.UsuariosRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuariosService {
    
    private final PasswordEncoder passwordEncoder;
    private final UsuariosRepository usuariosRepository;

    // public Usuarios nuevoUsuario(CreateUsuarioDTO nuevoUsuario) {

    //     Usuarios l = Usuarios.builder()
    //         .nombre(nuevoUsuario.getNombre())
    //         .apellidos(nuevoUsuario.getApellidos())
    //         .dni(nuevoUsuario.getDni())
    //         .email(nuevoUsuario.getEmail())
    //         .rol(nuevoUsuario.getRol())
    //         .build();
        
    //         nuevoUsuario.getEventosId().stream()
    //             .map(id -> {
    //                 return eventosService.findByIdConUsuarios(id).orElseThrow(() -> new UsuarioCreateException());
    //             })
    //             .forEach(l::addEventos);
    //         return guardar(l);
    // }
    
    // guardar usuarios
    public Usuarios guardar(Usuarios usu) {
        usu.setPassword(passwordEncoder.encode(usu.getPassword()));
        return usuariosRepository.save(usu);
    }

    // editar usuarios
    public Usuarios editar(Usuarios usu) {
        return usuariosRepository.save(usu);
    }

    // eliminar usuarios
    public void eliminar(Usuarios usu) {
        usuariosRepository.delete(usu);
    }

    // obtener todos los usuarios
    public List<Usuarios> obtenerTodos() {
        return usuariosRepository.findAll();
    }

    // obtener un usuario por id
    public Optional<Usuarios> buscarPorId(Long id) {
        return usuariosRepository.findById(id);
    }

    // buscar usuarios por nombre
    public Page<Usuarios> buscarPorNombre(String txt, Pageable pageable) {
        return usuariosRepository.findByNombreContainsIgnoreCase(txt, pageable);
    }

    // obtener todos los usuarios pageable
    public Page<Usuarios> ObtenerTodosPageable(Pageable pageable) {
        return usuariosRepository.findAll(pageable);
    }

    public Optional<Usuarios> buscarPorUsername(String email) {
        return usuariosRepository.findByUsername(email);
    }

}
