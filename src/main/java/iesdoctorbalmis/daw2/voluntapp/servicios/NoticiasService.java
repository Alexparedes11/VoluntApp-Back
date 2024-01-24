package iesdoctorbalmis.daw2.voluntapp.servicios;

import org.springframework.stereotype.Service;

import iesdoctorbalmis.daw2.voluntapp.repositorios.NoticiasRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticiasService {
    
    private final NoticiasRepository noticiasRepository;

}
