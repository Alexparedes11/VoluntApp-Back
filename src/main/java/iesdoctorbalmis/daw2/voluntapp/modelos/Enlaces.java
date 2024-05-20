package iesdoctorbalmis.daw2.voluntapp.modelos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor @AllArgsConstructor
@Builder @Entity
public class Enlaces {
    
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String original;

    private String acortado;

}
