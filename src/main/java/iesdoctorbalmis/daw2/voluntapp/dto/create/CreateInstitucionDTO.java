package iesdoctorbalmis.daw2.voluntapp.dto.create;


import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter @Setter
public class CreateInstitucionDTO {
    
    @Column(unique = true)
    @NonNull
    private String cif;

    private String personaCargo;

    private String nombre;

    private Long eventosId;

}
