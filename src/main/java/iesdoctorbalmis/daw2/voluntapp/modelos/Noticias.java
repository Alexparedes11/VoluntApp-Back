package iesdoctorbalmis.daw2.voluntapp.modelos;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Noticias {
    
    @Id @GeneratedValue
    private long id;

    private String titulo;

    @Column(length = 1000000)
    private String contenido;

    private Date fecha;

    // Se debe de cambiar
    private String autor;

    private String imagen;
}
