package iesdoctorbalmis.daw2.voluntapp.servicios;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import iesdoctorbalmis.daw2.voluntapp.dto.NoticiasDTO;
import iesdoctorbalmis.daw2.voluntapp.excepciones.AzureBlobStorageException;
import iesdoctorbalmis.daw2.voluntapp.modelos.Noticias;
import iesdoctorbalmis.daw2.voluntapp.repositorios.NoticiasRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticiasService {

    private final NoticiasRepository noticiasRepository;
    private final AzureBlobStorageService azureBlobStorageService;

    public Noticias guardar(Noticias noticia) {
        return noticiasRepository.save(noticia);
    }

    public List<Noticias> obtenerTodos() {
        return noticiasRepository.findAll();
    }

    // obtener todos los usuarios pageable
    public Page<Noticias> ObtenerTodosPageable(Pageable pageable) {
        return noticiasRepository.findAll(pageable);
    }

    // crear noticia
    public Noticias crearNoticias(NoticiasDTO noticiasDTO) throws AzureBlobStorageException {

        String ubicacionImagenAzure = "https://voluntapp.blob.core.windows.net/images/"
                + azureBlobStorageService.uploadFile("noticias", UUID.randomUUID().toString(), noticiasDTO.getImagen());

        Noticias noticias = Noticias.builder()
                .titulo(noticiasDTO.getTitulo())
                .contenido(noticiasDTO.getContenido())
                .fecha(noticiasDTO.getFecha())
                .autor(noticiasDTO.getAutor())
                .imagen(ubicacionImagenAzure)
                .build();
        return noticiasRepository.save(noticias);
    }

    public Noticias obtenerNoticiaPorTitulo(String titulo) {
        return noticiasRepository.findByTitulo(titulo);
    }

    public void eliminarNoticiasPorId(Long id) {
        noticiasRepository.deleteById(id);
    }

    public void eliminarNoticias(Long id) {
        noticiasRepository.deleteById(id);
    }

    public Noticias editarNoticias(Long id, NoticiasDTO noticiasDTO) {
        Noticias noticias = noticiasRepository.findById(id).orElseThrow();
        noticias.setTitulo(noticiasDTO.getTitulo());
        noticias.setContenido(noticiasDTO.getContenido());
        noticias.setFecha(noticiasDTO.getFecha());
        noticias.setAutor(noticiasDTO.getAutor());
        noticias.setImagen(noticiasDTO.getImagen());
        return noticiasRepository.save(noticias);
    }
}