package iesdoctorbalmis.daw2.voluntapp.dto;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.web.bind.annotation.RequestParam;

import iesdoctorbalmis.daw2.voluntapp.modelos.Eventos;
import iesdoctorbalmis.daw2.voluntapp.modelos.Instituciones;
import iesdoctorbalmis.daw2.voluntapp.modelos.Ubicacion;
import iesdoctorbalmis.daw2.voluntapp.modelos.Usuarios;
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

    private Long id;

    private LocalDateTime fInicio;

    private LocalDateTime fFin;

    private String titulo;

    private String descripcion;

    private Ubicacion ubicacion;

    private String imagen;

    private String estado;

    private String ods;

    private String creadoPorUsuario;

    private String creadoPorInstitucion;

    private int numVoluntarios;

    private int maxVoluntarios;

    private Set<String> nombreInstituciones;

}
