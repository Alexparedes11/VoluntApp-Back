package iesdoctorbalmis.daw2.voluntapp.modelos;

import java.util.Set;

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
public class Usuarios {
    
    @Id @GeneratedValue
    private long id;

    private String nombre;

    private String apellidos;

    @Column(unique = true)
    private String dni;

    private String direccion;

    @Column(unique = true)
    private String email;

    private String contraseña;

    private String fotoPerfil;

    @Column(nullable = false)
    private String rol;

    @ManyToMany(mappedBy = "usuarios")
    private Set<Eventos> eventos;

}
