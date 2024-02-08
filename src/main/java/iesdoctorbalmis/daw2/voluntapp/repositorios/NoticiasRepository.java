package iesdoctorbalmis.daw2.voluntapp.repositorios;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import iesdoctorbalmis.daw2.voluntapp.modelos.Noticias;

public interface NoticiasRepository extends JpaRepository<Noticias, Long>{
    Page<Noticias> findByTituloContainsIgnoreCase(String txt, Pageable pageable);
    
    Optional<Noticias> findById(Long id);
}