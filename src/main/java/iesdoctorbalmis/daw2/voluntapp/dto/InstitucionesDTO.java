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
    

    @Column(unique = true)
    @NonNull
    private String cif;

    private String personaCargo;

    private String nombre;

    private Set<String> eventosNombre;

}
