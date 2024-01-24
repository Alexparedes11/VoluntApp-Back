package iesdoctorbalmis.daw2.voluntapp.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data @Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuariosDTO {

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

}
