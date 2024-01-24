package iesdoctorbalmis.daw2.voluntapp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import iesdoctorbalmis.daw2.voluntapp.modelos.Eventos;

public interface EventosRepository extends JpaRepository<Eventos, Long> {
    
}
