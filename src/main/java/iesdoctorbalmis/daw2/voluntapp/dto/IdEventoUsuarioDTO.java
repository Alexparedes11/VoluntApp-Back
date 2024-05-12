package iesdoctorbalmis.daw2.voluntapp.dto;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor @AllArgsConstructor
@Data
@Table(name = "usuario_eventos")
public class IdEventoUsuarioDTO {
    
    private Long id_usuario;

    private Long id_evento;
    
}
