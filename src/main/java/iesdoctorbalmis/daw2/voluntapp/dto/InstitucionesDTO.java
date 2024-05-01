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
public class InstitucionesDTO {


    private Long id;
    @Column(unique = true)
    @NonNull
    private String cif;

    @Column(unique = true)
    @NonNull
    private String email;

    private String personaCargo;

    private String telefono;

    private String nombreLegal;

    private String nombre;

    private Set<String> eventosNombre;

    private String fotoPerfil;

    private String fotoBanner;

    private String estado;

}
