package iesdoctorbalmis.daw2.voluntapp.servicios;

import org.springframework.stereotype.Service;

import iesdoctorbalmis.daw2.voluntapp.repositorios.UsuariosRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuariosService {
    
    private final UsuariosRepository usuariosRepository;
}
