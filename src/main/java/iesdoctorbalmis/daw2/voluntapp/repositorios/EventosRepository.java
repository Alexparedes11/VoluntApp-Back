package iesdoctorbalmis.daw2.voluntapp.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import iesdoctorbalmis.daw2.voluntapp.modelos.Eventos;

public interface EventosRepository extends JpaRepository<Eventos, Long> {
    
        Page<Eventos> findByTituloContainsIgnoreCase(String txt, Pageable pageable);

        @Query("select p from Eventos p LEFT JOIN FETCH p.usuarios WHERE p.id = :id")
	Optional<Eventos> findByIdJoinFetch(Long id);

        List <Eventos> findByCreadoPorUsuariosId(Long id);

        Page<Eventos> findByEstado(String estado, Pageable pageable);

}
