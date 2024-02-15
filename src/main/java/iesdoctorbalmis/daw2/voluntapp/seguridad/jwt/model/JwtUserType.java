package iesdoctorbalmis.daw2.voluntapp.seguridad.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class JwtUserType {
        Long id;
        String type;
}
