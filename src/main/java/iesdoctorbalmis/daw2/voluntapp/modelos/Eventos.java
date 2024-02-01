package iesdoctorbalmis.daw2.voluntapp.modelos;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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

    private Date fInicio;

    private Date fFin;

    private String nombre;

    private String descripcion;

    private String ubicacion;
    
    private int maxVoluntarios;

    private String fotoEvento;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuarios creadoPorUsuarios;

    @ManyToOne
    @JoinColumn(name = "intitucion_id")
    private Instituciones creadoPorInstituciones;

    @Builder.Default
    @JsonBackReference
    @ManyToMany(mappedBy = "eventos")
    private Set<Usuarios> usuarios = new HashSet<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "eventos")
    @Builder.Default
    private Set<Instituciones> instituciones = new HashSet<>();

    @JsonBackReference
    @ManyToMany(mappedBy = "eventos")
    @Builder.Default
    private Set<Categorias> categorias = new HashSet<>();


    // Funciones de Eventos
    public int usuariosSize() {

        return usuarios.size();

    }
}
