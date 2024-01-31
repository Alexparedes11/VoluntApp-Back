package iesdoctorbalmis.daw2.voluntapp.dto.create;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Data @Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUsuarioDTO {
    
    @NonNull
    private String nombre;

    @NonNull
    private String apellidos;

    @NonNull
    private String contraseña;

    @Column(unique = true)
    @NonNull
    private String dni;

    @NonNull
    private String direccion;

    @NonNull
    private String fotoPerfil;

    @Column(unique = true)
    @NonNull
    private String email;

    @NonNull
    private String rol;
}
