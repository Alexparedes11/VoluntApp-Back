package iesdoctorbalmis.daw2.voluntapp.dto;

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
public class EventosDTO {

    private Date fInicio;

    private Date fFin;

    private String nombre;

    private String descripcion;

    private String ubicacion;

    private float rating;

    private String fotoEvento;

    private String creadoPorUsuario;

    private String creadoPorInstitucion;

    private int numUsuarios;

    private int maxVoluntarios;

}
