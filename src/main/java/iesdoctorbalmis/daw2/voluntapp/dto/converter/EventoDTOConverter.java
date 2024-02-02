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
        String usuario;
        String institucion;

        if (eventos.getCreadoPorInstituciones() != null) {
            usuario = null;
            institucion = eventos.getCreadoPorInstituciones().getNombre();
        } else {
            usuario = eventos.getCreadoPorUsuarios().getNombre();
            institucion = null;
        }

        System.out.println("                Descripcion del evento: " + eventos.getDescripcion());
                
        return EventosDTO.builder()
            .fInicio(eventos.getFInicio())
            .fFin(eventos.getFFin())
            .imagen(eventos.getImagen())
            .creadoPorUsuario(usuario)
            .creadoPorInstitucion(institucion)
            .descripcion(eventos.getDescripcion())
            .maxVoluntarios(eventos.getMaxVoluntarios())
            .numVoluntarios(usuarariosNumber)
            .titulo(eventos.getTitulo())
            .id(eventos.getId())
            .ubicacion(eventos.getUbicacion())
            .build();
    }
}
