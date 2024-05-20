package iesdoctorbalmis.daw2.voluntapp.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import iesdoctorbalmis.daw2.voluntapp.modelos.Enlaces;

public interface EnlacesRepository extends JpaRepository<Enlaces, Long>{
    Optional<Enlaces> findByOriginal(String original);
    Optional<Enlaces> findByAcortado(String acortado);
}