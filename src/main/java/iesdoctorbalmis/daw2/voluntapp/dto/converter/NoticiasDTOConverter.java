package iesdoctorbalmis.daw2.voluntapp.dto.converter;

import org.springframework.stereotype.Component;

import iesdoctorbalmis.daw2.voluntapp.dto.NoticiasDTO;
import iesdoctorbalmis.daw2.voluntapp.modelos.Noticias;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NoticiasDTOConverter {
    public NoticiasDTO convertToDto(Noticias noticias){
    
        return NoticiasDTO.builder()
            .id(noticias.getId())
            .titulo(noticias.getTitulo())
            .contenido(noticias.getContenido())
            .fecha(noticias.getFecha())
            .autor(noticias.getAutor())
            .imagen(noticias.getImagen())
            .build();
    }
}