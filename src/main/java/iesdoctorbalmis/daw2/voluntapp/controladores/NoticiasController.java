package iesdoctorbalmis.daw2.voluntapp.controladores;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import iesdoctorbalmis.daw2.voluntapp.servicios.NoticiasService;
import iesdoctorbalmis.daw2.voluntapp.util.pagination.PaginationLinksUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import iesdoctorbalmis.daw2.voluntapp.modelos.Noticias;
import iesdoctorbalmis.daw2.voluntapp.dto.NoticiasDTO;
import iesdoctorbalmis.daw2.voluntapp.dto.converter.NoticiasDTOConverter;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class NoticiasController {
    private final NoticiasService noticiasService;
    private final PaginationLinksUtils paginationLinksUtils;
    private final NoticiasDTOConverter noticiasDTOConverter;

    // Obtencion de todas las Noticias
    @GetMapping("/noticias")
    public ResponseEntity<?> todasLasNoticias(@PageableDefault(size = 10, page = 0) Pageable pageable, HttpServletRequest request) {
                Page<Noticias> listaNoticias = noticiasService.ObtenerTodosPageable(pageable);

                if (listaNoticias.isEmpty()) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay Noticias registradas");
                } else {
                    Page<NoticiasDTO> dtoList = listaNoticias.map(noticiasDTOConverter::convertToDto);

                    UriComponentsBuilder uriBuilder =
                        UriComponentsBuilder.fromHttpUrl(request.getRequestURL().toString());

                    return ResponseEntity.ok()
                        .header("link", paginationLinksUtils.createLinkHeader(dtoList, uriBuilder))
                        .body(dtoList);
                }
    }

    
    // Crear Noticias
    @PostMapping("/noticias/crearNoticia")
    public ResponseEntity<NoticiasDTO> crearNoticias(@RequestBody NoticiasDTO noticiasDTO) {
        Noticias noticias = noticiasService.crearNoticias(noticiasDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(noticiasDTOConverter.convertToDto(noticias));
        
    }

    //Eliminar noticia
     @DeleteMapping("/noticias/eliminarNoticia/{id}")
    public ResponseEntity<Void> eliminarNoticia(@PathVariable Long id) {
        noticiasService.eliminarNoticias(id);
        return ResponseEntity.noContent().build();
    }

    //Editar noticia
    @PutMapping("/noticias/editarNoticia/{id}")
    public ResponseEntity<NoticiasDTO> editarNoticia(@PathVariable Long id, @RequestBody NoticiasDTO noticiasDTO) {
        Noticias noticias = noticiasService.editarNoticias(id, noticiasDTO);
        return ResponseEntity.ok(noticiasDTOConverter.convertToDto(noticias));
    }
    
}