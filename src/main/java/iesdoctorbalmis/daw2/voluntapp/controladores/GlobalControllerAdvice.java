package iesdoctorbalmis.daw2.voluntapp.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import iesdoctorbalmis.daw2.voluntapp.error.ApiError;
import iesdoctorbalmis.daw2.voluntapp.error.SearchUsuarioNoResultException;
import iesdoctorbalmis.daw2.voluntapp.error.UsuariosNotFoundException;

@RestControllerAdvice
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler{
    
    @ExceptionHandler({UsuariosNotFoundException.class, SearchUsuarioNoResultException.class})
    public ResponseEntity<ApiError> handleUsuarioNoEncontrado(Exception ex) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }


}
