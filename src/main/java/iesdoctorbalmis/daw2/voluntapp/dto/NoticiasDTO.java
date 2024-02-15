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
public class NoticiasDTO {
    
    private long id;

    private String titulo;

    private String contenido;

    private Date fecha;

    private String autor;

    private String imagen;
}