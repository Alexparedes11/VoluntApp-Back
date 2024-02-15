package iesdoctorbalmis.daw2.voluntapp.dto;

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
public class NumeroDeEventosDTO {
    
    private Integer creados;

    private Integer apuntados;

    private Integer realizados;
}
