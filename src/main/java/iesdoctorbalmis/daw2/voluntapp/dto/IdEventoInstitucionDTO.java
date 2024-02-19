package iesdoctorbalmis.daw2.voluntapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor @AllArgsConstructor
@Data
public class IdEventoInstitucionDTO {
    
    private Long id_institucion;

    private Long id_evento;
}
