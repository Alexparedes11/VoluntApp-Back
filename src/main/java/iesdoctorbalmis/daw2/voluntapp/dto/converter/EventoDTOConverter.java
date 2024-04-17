package iesdoctorbalmis.daw2.voluntapp.dto.converter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.stereotype.Component;

import iesdoctorbalmis.daw2.voluntapp.dto.EventosDTO;
import iesdoctorbalmis.daw2.voluntapp.modelos.Eventos;
import iesdoctorbalmis.daw2.voluntapp.modelos.Instituciones;
import iesdoctorbalmis.daw2.voluntapp.modelos.Tag;
import io.micrometer.core.instrument.Tags;
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

        Iterator<Instituciones> iterador = eventos.getInstituciones().iterator();
        Set<String> instituciones = new HashSet<>();

        while (iterador.hasNext()) {
            Instituciones elemento = iterador.next();
            String eventosNombre = elemento.getNombre();
            instituciones.add(eventosNombre);
        }

        Iterator<Tag> iteradorTags = eventos.getTags().iterator();
        Set<String> tags = new HashSet<>();

        while (iteradorTags.hasNext()) {
            Tag elemento = iteradorTags.next();
            String eventosNombre = elemento.getNombre();
            tags.add(eventosNombre);
        }


        System.out.println("                Descripcion del evento: " + instituciones);
                
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
            .estado(eventos.getEstado())
            .ubicacion(eventos.getUbicacion())
            .nombreInstituciones(instituciones)
            .tags(tags)
            .build();
    }
}
