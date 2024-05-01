package iesdoctorbalmis.daw2.voluntapp.repositorios;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import iesdoctorbalmis.daw2.voluntapp.modelos.Instituciones;

public interface InstitucionesRepository extends JpaRepository<Instituciones, Long> {
    
    Page<Instituciones> findByNombreContainsIgnoreCase(String txt, Pageable pageable);

    Instituciones findByNombre(String nombre);

    Optional<Instituciones> findByUsername(String email);


    Page<Instituciones> findByEstado(String estado, Pageable pageable);
}
