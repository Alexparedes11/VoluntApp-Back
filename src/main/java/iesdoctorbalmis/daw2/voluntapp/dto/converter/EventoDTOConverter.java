package iesdoctorbalmis.daw2.voluntapp.dto.converter;

import org.springframework.stereotype.Component;

import iesdoctorbalmis.daw2.voluntapp.dto.EventosDTO;
import iesdoctorbalmis.daw2.voluntapp.modelos.Eventos;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EventoDTOConverter {
    
    public EventosDTO convertToDto(Eventos eventos) {
        
        int usuarariosNumber = eventos.usuariosSize();
                
        return EventosDTO.builder()
            .fInicio(eventos.getFInicio())
            .fFin(eventos.getFFin())
            .fotoEvento(eventos.getFotoEvento())
            .creadoPorUsuario(eventos.getUsuarios().toString())
            .creadoPorInstitucion(eventos.getCreadoPorInstituciones().toString())
            .descripcion(eventos.getDescripcion())
            .maxVoluntarios(eventos.getMaxVoluntarios())
            .numUsuarios(usuarariosNumber)
            .build();
    }
}
