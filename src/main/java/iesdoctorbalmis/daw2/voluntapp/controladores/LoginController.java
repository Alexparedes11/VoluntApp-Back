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
import iesdoctorbalmis.daw2.voluntapp.modelos.Instituciones;
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
    public  ResponseEntity<?> login(@Valid @RequestBody LoginRequest  loginRequest) {
        //TODO: process POST request
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = null;

        if (authentication != null) {
            Object principal = authentication.getPrincipal();

            if (principal instanceof Usuarios) {
                // El principal autenticado es una instancia de Usuarios
                Usuarios usuario = (Usuarios) authentication.getPrincipal();
                jwt = tokenProvider.generateUsuariosToken(authentication);
                System.out.println("El principal autenticado no es una instancia de Usuarios");
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(convertUsuariosAndTokenToJwtUserResponse(usuario, jwt)); 
            } else if (principal instanceof Instituciones) {
                // El principal autenticado es una instancia de Instituciones
                Instituciones institucion = (Instituciones) authentication.getPrincipal();
                jwt = tokenProvider.generateInstitucionesToken(authentication);
                System.out.println("El principal autenticado no es una instancia de Instituciones");
                return ResponseEntity.status(HttpStatus.CREATED)
                        .body(convertInstitucionAndTokenToJwtUserResponse(institucion, jwt)); 
            }
        }
        
        return null;
           
    }
    
    private JwtUserResponse convertUsuariosAndTokenToJwtUserResponse(Usuarios usuario, String jwt) {
        return JwtUserResponse.jwtUserResponseBuilder()
            .nombre(usuario.getNombre())
            .email(usuario.getUsername())
            .password(usuario.getPassword())
            .token(jwt)
            .build();
    }

    private JwtUserResponse convertInstitucionAndTokenToJwtUserResponse(Instituciones institucion, String jwt) {
        return JwtUserResponse.jwtUserResponseBuilder()
            .nombre(institucion.getNombre())
            .email(institucion.getUsername())
            .password(institucion.getPassword())
            .token(jwt)
            .build();
    }
    
}
