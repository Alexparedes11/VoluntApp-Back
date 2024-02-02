package iesdoctorbalmis.daw2.voluntapp.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import iesdoctorbalmis.daw2.voluntapp.dto.converter.UsuarioDTOConverter;
import iesdoctorbalmis.daw2.voluntapp.modelos.Usuarios;
import iesdoctorbalmis.daw2.voluntapp.seguridad.jwt.JwtProvider;
import iesdoctorbalmis.daw2.voluntapp.seguridad.jwt.model.JwtUserResponse;
import iesdoctorbalmis.daw2.voluntapp.seguridad.jwt.model.LoginRequest;
import iesdoctorbalmis.daw2.voluntapp.servicios.UsuariosService;
import iesdoctorbalmis.daw2.voluntapp.util.pagination.PaginationLinksUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {
    
    private final UsuariosService usuariosService;
    private final UsuarioDTOConverter usuarioDTOConverter;
    private final PaginationLinksUtils paginationLinksUtils;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider tokenProvider;

     @PostMapping("/login")
    public  ResponseEntity<JwtUserResponse> login(@Valid @RequestBody LoginRequest  loginRequest) {
        //TODO: process POST request
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Usuarios usuario = (Usuarios) authentication.getPrincipal();
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(convertUsuariosAndTokenToJwtUserResponse(usuario, jwt));    
    }
    
    private JwtUserResponse convertUsuariosAndTokenToJwtUserResponse(Usuarios usuario, String jwt) {
        return JwtUserResponse.jwtUserResponseBuilder()
            .nombre(usuario.getNombre())
            .email(usuario.getUsername())
            .password(usuario.getPassword())
            .token(jwt)
            .build();
    }
    
}
