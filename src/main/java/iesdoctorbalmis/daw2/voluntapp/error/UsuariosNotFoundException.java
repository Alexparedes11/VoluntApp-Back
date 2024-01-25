package iesdoctorbalmis.daw2.voluntapp.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuariosNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UsuariosNotFoundException(Long id) {
        super("No se puede encontrar el usuarios con la ID: " + id);
    }

    @ExceptionHandler(UsuariosNotFoundException.class)
    public ResponseEntity<ApiError> handleUsuarioNoEncontrado(UsuariosNotFoundException ex) {
        ApiError apiError = new ApiError();
        apiError.setEstado(HttpStatus.NOT_FOUND);
        apiError.setFecha(LocalDateTime.now());
        apiError.setMensaje(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
    
}
