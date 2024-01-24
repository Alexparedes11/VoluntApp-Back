package iesdoctorbalmis.daw2.voluntapp.modelos;

import java.sql.Date;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Eventos {
    
    @Id @GeneratedValue
    private long id;

    private Date fInicio;

    private Date fFin;

    private String nombre;

    private float rating;

    private String comentarios;

    private String fotoEvento;

    @ManyToMany
    @JoinTable(
        name = "evento_voluntarios",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name="eventos_id")
    )
    private Set<Usuarios> usuarios;

    @ManyToMany
    @JoinTable(
        name = "evento_instituciones",
        joinColumns = @JoinColumn(name = "institucion_id"),
        inverseJoinColumns = @JoinColumn(name = "eventos_id")
    )
    private Set<Instituciones> instituciones;

}
