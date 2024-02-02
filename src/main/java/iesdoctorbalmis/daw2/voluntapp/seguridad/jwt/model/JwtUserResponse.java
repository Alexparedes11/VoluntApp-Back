package iesdoctorbalmis.daw2.voluntapp.seguridad.jwt.model;

import java.util.Set;

import iesdoctorbalmis.daw2.voluntapp.dto.LoginDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class JwtUserResponse extends LoginDTO {

    private String token;

    @Builder(builderMethodName = "jwtUserResponseBuilder")
    public JwtUserResponse(String nombre, String email, String rol, String token) {
        super(nombre, email, rol);
        this.token = token;
    }
    
}
