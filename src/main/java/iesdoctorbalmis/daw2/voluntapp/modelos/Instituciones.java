package iesdoctorbalmis.daw2.voluntapp.modelos;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

    @Builder.Default
    @ManyToMany(mappedBy="instituciones", fetch = FetchType.EAGER)
    private Set<Eventos> eventos = new HashSet<>();
}
