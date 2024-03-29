package iesdoctorbalmis.daw2.voluntapp.controladores;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import iesdoctorbalmis.daw2.voluntapp.error.ApiError;
import iesdoctorbalmis.daw2.voluntapp.error.eventos.EventosCreateException;
import iesdoctorbalmis.daw2.voluntapp.error.eventos.EventosNotFoundException;
import iesdoctorbalmis.daw2.voluntapp.error.instituciones.InstitucionesCreateException;
import iesdoctorbalmis.daw2.voluntapp.error.instituciones.InstitucionesNotFoundException;
import iesdoctorbalmis.daw2.voluntapp.error.instituciones.SearchInstitucionesNoRestultException;
import iesdoctorbalmis.daw2.voluntapp.error.usuarios.SearchUsuarioNoResultException;
import iesdoctorbalmis.daw2.voluntapp.error.usuarios.UsuarioCreateException;
import iesdoctorbalmis.daw2.voluntapp.error.usuarios.UsuariosNotFoundException;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler({UsuariosNotFoundException.class, SearchUsuarioNoResultException.class, UsuarioCreateException.class})
    public ResponseEntity<ApiError> handleUsuarioNoEncontrado(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, LocalDateTime.now(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler({InstitucionesNotFoundException.class, InstitucionesCreateException.class, SearchInstitucionesNoRestultException.class})
    public ResponseEntity<ApiError> handleInstitucionesNoEncontrado(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, LocalDateTime.now(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler({EventosCreateException.class, EventosNotFoundException.class})
    public ResponseEntity<ApiError> handleEventosNoEncontrado(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, LocalDateTime.now(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

}
