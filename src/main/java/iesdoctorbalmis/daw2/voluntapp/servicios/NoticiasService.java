package iesdoctorbalmis.daw2.voluntapp.servicios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import iesdoctorbalmis.daw2.voluntapp.modelos.Noticias;
import iesdoctorbalmis.daw2.voluntapp.repositorios.NoticiasRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticiasService {
    
    private final NoticiasRepository noticiasRepository;
    

    public List<Noticias> obtenerTodos() {
        return noticiasRepository.findAll();
    }

    // obtener todos los usuarios pageable
    public Page<Noticias> ObtenerTodosPageable(Pageable pageable) {
        return noticiasRepository.findAll(pageable);
    }
}