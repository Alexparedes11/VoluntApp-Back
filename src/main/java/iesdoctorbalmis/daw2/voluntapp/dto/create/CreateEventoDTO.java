package iesdoctorbalmis.daw2.voluntapp.dto.create;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

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

    private String descripcionResumida;

    //private Ubicacion ubicacion;

    private String nombreUbicacion;

    private double lat;

    private double lon;

    private String imagen;

    private String estado;

    private String usuarioNombre;

    private Long usuarioId;

    private String institucionNombre;

    private int maxVoluntarios;

    private Set<String> tags;

}
