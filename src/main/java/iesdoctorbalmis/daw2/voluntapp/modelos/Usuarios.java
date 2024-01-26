package iesdoctorbalmis.daw2.voluntapp.modelos;

import java.util.HashSet;
import java.util.Set;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
public class Usuarios {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nombre;

    @NonNull
    private String apellidos;

    @Column(unique = true)
    @NonNull
    private String dni;

    private String direccion;

    @Column(unique = true)
    @NonNull
    private String email;

    private String contraseña;

    private String fotoPerfil;

    @NonNull
    private String rol;

    @Builder.Default
    @ManyToMany(mappedBy = "usuarios", fetch = FetchType.EAGER)
    private Set<Eventos> eventos = new HashSet<>();

}
