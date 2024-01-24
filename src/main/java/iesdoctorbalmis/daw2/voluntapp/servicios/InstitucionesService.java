package iesdoctorbalmis.daw2.voluntapp.servicios;

import org.springframework.stereotype.Service;

import iesdoctorbalmis.daw2.voluntapp.repositorios.InstitucionesRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InstitucionesService {
    
    private final InstitucionesRepository institucionesRepository;
}
