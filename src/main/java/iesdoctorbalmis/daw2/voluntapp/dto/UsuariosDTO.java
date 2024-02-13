package iesdoctorbalmis.daw2.voluntapp.dto;


import java.util.Set;

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
public class UsuariosDTO {

    private Long id;

    @NonNull
    private String nombre;

    @NonNull
    private String apellidos;

    @Column(unique = true)
    @NonNull
    private String dni;

    @Column(unique = true)
    @NonNull
    private String email;

    @NonNull
    private String rol;

    @NonNull
    private String telefono;

    @NonNull
    private String direccion;

    @NonNull
    private String contraseña;

    private Set<String> eventosNombre;

}
