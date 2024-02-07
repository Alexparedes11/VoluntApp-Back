package iesdoctorbalmis.daw2.voluntapp.dto.create;

import java.sql.Date;
import java.time.LocalDateTime;

import iesdoctorbalmis.daw2.voluntapp.modelos.Ubicacion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateEventoDTO {
    
    private LocalDateTime fInicio;

    private LocalDateTime fFin;

    private String titulo;

    private String descripcion;

    private Ubicacion ubicacion;

    private String imagen;

    private String estado;

    private String creadoPorUsuario;

    private String creadoPorIntitucion;

    private int maxVoluntarios;

}
