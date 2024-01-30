package iesdoctorbalmis.daw2.voluntapp.modelos;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Eventos {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fInicio;

    private Date fFin;

    private String nombre;

    private float rating;

    private String comentarios;

    private String fotoEvento;

    @Builder.Default
    @ManyToMany(mappedBy = "eventos")
    private Set<Usuarios> usuarios = new HashSet<>();

    @ManyToMany(mappedBy = "eventos")
    @Builder.Default
    private Set<Instituciones> instituciones = new HashSet<>();

    @ManyToMany(mappedBy = "eventos")
    @Builder.Default
    private Set<Categorias> categorias = new HashSet<>();
}
