package iesdoctorbalmis.daw2.voluntapp.modelos;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Instituciones {
    
    @Id @GeneratedValue
    private long id;

    @Column(unique = true)
    private String cif;

    private String personaCargo;

    private String nombre;

    @Column(nullable = false)
    private String fotoInstitucion;

    @JsonBackReference
    @ManyToMany(mappedBy="instituciones")
    private Set<Eventos> eventos;
}
