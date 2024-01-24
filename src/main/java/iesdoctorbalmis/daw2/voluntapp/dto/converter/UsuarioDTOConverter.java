package iesdoctorbalmis.daw2.voluntapp.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import iesdoctorbalmis.daw2.voluntapp.dto.UsuariosDTO;
import iesdoctorbalmis.daw2.voluntapp.modelos.Usuarios;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioDTOConverter {
    
    private final ModelMapper modelMapper;

    public UsuariosDTO convertToDto(Usuarios usu) {
        return modelMapper.map(usu, UsuariosDTO.class);
    }
}
