package iesdoctorbalmis.daw2.voluntapp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import iesdoctorbalmis.daw2.voluntapp.modelos.Ubicacion;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {
    
}
