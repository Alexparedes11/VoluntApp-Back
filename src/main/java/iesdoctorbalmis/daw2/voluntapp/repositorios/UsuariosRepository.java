package iesdoctorbalmis.daw2.voluntapp.repositorios;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import iesdoctorbalmis.daw2.voluntapp.modelos.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long>, JpaSpecificationExecutor<Usuarios> {

    Page<Usuarios> findByNombreContainsIgnoreCase(String txt, Pageable pageable);

    Optional<Usuarios> findByUsername(String email);

}