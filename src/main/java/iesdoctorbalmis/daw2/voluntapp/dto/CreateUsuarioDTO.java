package iesdoctorbalmis.daw2.voluntapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUsuarioDTO {
    
    private String nombre;
    private String apellidos;
    private String dni;
    private String direccion;
    private String email;
    private String contraseña;
    private String fotoPerfil;
    private String rol;
    private long eventoId;
}
