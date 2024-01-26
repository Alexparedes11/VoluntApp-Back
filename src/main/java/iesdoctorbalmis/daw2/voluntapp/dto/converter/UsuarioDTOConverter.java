package iesdoctorbalmis.daw2.voluntapp.dto.converter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import iesdoctorbalmis.daw2.voluntapp.dto.UsuariosDTO;
import iesdoctorbalmis.daw2.voluntapp.modelos.Eventos;
import iesdoctorbalmis.daw2.voluntapp.modelos.Usuarios;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioDTOConverter {
    

    public UsuariosDTO convertToDto(Usuarios usu) {
        
        Iterator<Eventos> iterador = usu.getEventos().iterator();
        Set<String> eventos = new HashSet<>();

        while (iterador.hasNext()) {
            Eventos elemento = iterador.next();
            String eventosNombre = elemento.getNombre();
            eventos.add(eventosNombre);
        }
                
        return UsuariosDTO.builder()
            .nombre(usu.getNombre())
            .apellidos(usu.getApellidos())
            .dni(usu.getDni())
            .email(usu.getEmail())
            .rol(usu.getRol())
            .eventosNombre(eventos)
            .build();
    }
}