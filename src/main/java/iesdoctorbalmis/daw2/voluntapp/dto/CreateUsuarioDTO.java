package iesdoctorbalmis.daw2.voluntapp.dto;

import java.util.Set;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateUsuarioDTO {
    
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

    private Long eventosId;
}
