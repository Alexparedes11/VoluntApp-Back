package iesdoctorbalmis.daw2.voluntapp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import iesdoctorbalmis.daw2.voluntapp.modelos.Categorias;

public interface CategoriasRepository extends JpaRepository<Categorias, Long>{
    
}
