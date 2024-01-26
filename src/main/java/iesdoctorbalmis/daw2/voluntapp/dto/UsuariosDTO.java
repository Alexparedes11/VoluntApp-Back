package iesdoctorbalmis.daw2.voluntapp.dto;


import java.util.Set;

import iesdoctorbalmis.daw2.voluntapp.modelos.Eventos;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
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

    private Set<String> eventosNombre;

}
