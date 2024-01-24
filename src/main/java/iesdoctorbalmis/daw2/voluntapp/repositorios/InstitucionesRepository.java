package iesdoctorbalmis.daw2.voluntapp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import iesdoctorbalmis.daw2.voluntapp.modelos.Instituciones;

public interface InstitucionesRepository extends JpaRepository<Instituciones, Long> {
    
}
