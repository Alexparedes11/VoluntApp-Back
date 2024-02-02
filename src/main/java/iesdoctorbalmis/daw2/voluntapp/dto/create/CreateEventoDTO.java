package iesdoctorbalmis.daw2.voluntapp.dto.create;

import java.sql.Date;

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
    
    private Date fInicio;

    private Date fFin;

    private String titulo;

    private String descripcion;

    private String ubicacion;

    private String imagen;

    private String creadoPorUsuario;

    private String creadoPorIntitucion;

    private int maxVoluntarios;

}
