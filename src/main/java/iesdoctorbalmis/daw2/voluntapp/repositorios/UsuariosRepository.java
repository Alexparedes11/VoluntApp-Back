package iesdoctorbalmis.daw2.voluntapp.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import iesdoctorbalmis.daw2.voluntapp.modelos.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

    
}