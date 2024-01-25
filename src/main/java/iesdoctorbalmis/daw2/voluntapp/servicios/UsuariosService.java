package iesdoctorbalmis.daw2.voluntapp.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import iesdoctorbalmis.daw2.voluntapp.modelos.Usuarios;
import iesdoctorbalmis.daw2.voluntapp.repositorios.UsuariosRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuariosService {
    
    private final UsuariosRepository usuariosRepository;

    // guardar usuarios
    public Usuarios guardar(Usuarios usu) {
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

}
