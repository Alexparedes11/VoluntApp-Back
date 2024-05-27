package iesdoctorbalmis.daw2.voluntapp.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import iesdoctorbalmis.daw2.voluntapp.modelos.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{
    Optional<Tag> findByNombre(String nombre);
}
