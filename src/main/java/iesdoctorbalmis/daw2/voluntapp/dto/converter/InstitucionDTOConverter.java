package iesdoctorbalmis.daw2.voluntapp.dto.converter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.stereotype.Component;

import iesdoctorbalmis.daw2.voluntapp.dto.InstitucionesDTO;
import iesdoctorbalmis.daw2.voluntapp.modelos.Eventos;
import iesdoctorbalmis.daw2.voluntapp.modelos.Instituciones;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InstitucionDTOConverter {
    
    public InstitucionesDTO convertToDto(Instituciones instituciones) {
        
        Iterator<Eventos> iterador = instituciones.getEventos().iterator();
        Set<String> eventos = new HashSet<>();

        while (iterador.hasNext()) {
            Eventos elemento = iterador.next();
            String eventosNombre = elemento.getTitulo();
            eventos.add(eventosNombre);
        }

                
        return InstitucionesDTO.builder()
            .nombre(instituciones.getNombre())
            .nombreLegal(instituciones.getNombreLegal())
            .telefono(instituciones.getTelefono())
            .email(instituciones.getUsername())
            .cif(instituciones.getCif())
            .personaCargo(instituciones.getPersonaCargo())
            .eventosNombre(eventos)
            .build();
    }

}
