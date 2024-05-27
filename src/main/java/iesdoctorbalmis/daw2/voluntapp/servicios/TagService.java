package iesdoctorbalmis.daw2.voluntapp.servicios;

import java.util.Optional;

import org.springframework.stereotype.Service;

import iesdoctorbalmis.daw2.voluntapp.modelos.Tag;
import iesdoctorbalmis.daw2.voluntapp.repositorios.TagRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService {

    private final TagRepository tagRepository;

    // guardar tag
    public Tag guardar(Tag tag) {
        return tagRepository.save(tag);
    }

    // Buscar un tag por su nombre
    public Optional<Tag> buscarPorNombre(String nombre) {
        return tagRepository.findByNombre(nombre);
    }
}
