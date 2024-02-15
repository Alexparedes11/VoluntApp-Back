package iesdoctorbalmis.daw2.voluntapp.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import iesdoctorbalmis.daw2.voluntapp.modelos.Instituciones;
import iesdoctorbalmis.daw2.voluntapp.modelos.Usuarios;
import iesdoctorbalmis.daw2.voluntapp.repositorios.InstitucionesRepository;
import io.jsonwebtoken.security.Password;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstitucionesService {
    
    private final InstitucionesRepository institucionesRepository;
    private final PasswordEncoder passwordEncoder;

    // guardar usuarios
    public Instituciones guardar(Instituciones usu) {
        usu.setPassword(passwordEncoder.encode(usu.getPassword()));
        return institucionesRepository.save(usu);
    }

    // editar usuarios
    public Instituciones editar(Instituciones insti) {
        return institucionesRepository.save(insti);
    }

    // eliminar usuarios
    public void eliminar(Instituciones insti) {
        institucionesRepository.delete(insti);
    }

    // obtener todos los usuarios
    public List<Instituciones> obtenerTodos() {
        return institucionesRepository.findAll();
    }

    // obtener un usuario por id
    public Optional<Instituciones> buscarPorId(Long id) {
        return institucionesRepository.findById(id);
    }

    // buscar usuarios por nombre
    public Page<Instituciones> buscarPorNombre(String txt, Pageable pageable) {
        return institucionesRepository.findByNombreContainsIgnoreCase(txt, pageable);
    }

    // obtener todos los usuarios pageable
    public Page<Instituciones> ObtenerTodosPageable(Pageable pageable) {
        return institucionesRepository.findAll(pageable);
    }

    public Instituciones buscarPorNombre(String nombre) {
        return institucionesRepository.findByNombre(nombre);
    }

    public Optional<Instituciones> buscarPorUsername(String email) {
        return institucionesRepository.findByUsername(email);
    }
}