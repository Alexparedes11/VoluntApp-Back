package iesdoctorbalmis.daw2.voluntapp.modelos;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
    private Long id;

    private Date fInicio;

    private Date fFin;

    private String nombre;

    private float rating;

    private String comentarios;

    private String fotoEvento;

    @Builder.Default
    @ManyToMany
    @JoinTable(
        name = "evento_voluntarios",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name="eventos_id")
    )
    private Set<Usuarios> usuarios = new HashSet<>();

    @ManyToMany
    @Builder.Default
    @JoinTable(
        name = "evento_instituciones",
        joinColumns = @JoinColumn(name = "institucion_id"),
        inverseJoinColumns = @JoinColumn(name = "eventos_id")
    )
    private Set<Instituciones> instituciones = new HashSet<>();

}
