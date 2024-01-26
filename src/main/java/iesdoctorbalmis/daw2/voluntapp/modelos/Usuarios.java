package iesdoctorbalmis.daw2.voluntapp.modelos;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
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
public class Usuarios {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar en blanco")
    private String nombre;

    @NotBlank(message = "Los apellidos no pueden estar en blanco")
    private String apellidos;

    @NotBlank(message = "El DNI no puede estar en blanco")
    @Size(min = 9, max = 9, message = "El DNI debe tener 9 caracteres")
    private String dni;

    private String direccion;

    @NotBlank(message = "El email no puede estar en blanco")
    @Email(message = "El formato del email no es válido")
    private String email;

    private String contraseña;

    private String fotoPerfil;

    @NotBlank(message = "El rol no puede estar en blanco")
    private String rol;

    @JsonBackReference
    @ManyToMany(mappedBy = "usuarios")
    private Set<Eventos> eventos;

}
