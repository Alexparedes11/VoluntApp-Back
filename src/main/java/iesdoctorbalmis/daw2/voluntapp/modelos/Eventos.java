package iesdoctorbalmis.daw2.voluntapp.modelos;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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

    private LocalDateTime fInicio;

    private LocalDateTime fFin;

    private String titulo;

    @Column(length = 1000000)
    private String descripcion;

    private String descripcionResumida;

    @JdbcTypeCode(SqlTypes.JSON)
    @ManyToOne
    private Ubicacion ubicacion;
    
    private int maxVoluntarios;

    private String estado;

    @Lob
    @Column(length = 1000000)
    private String imagen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuarios creadoPorUsuarios;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "institucion_id")
    private Instituciones creadoPorInstituciones;

    @Builder.Default
    @JsonIgnore
    @ManyToMany(mappedBy = "eventos", fetch = FetchType.EAGER)
    private Set<Usuarios> usuarios = new HashSet<>();

    @JsonBackReference
    @JsonIgnore
    @ManyToMany(mappedBy = "eventos", fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Instituciones> instituciones = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "evento_tags",
            joinColumns = @JoinColumn(name = "evento_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    // Funciones de Eventos
    public int usuariosSize() {

        return usuarios.size();

    }
    
    
}
