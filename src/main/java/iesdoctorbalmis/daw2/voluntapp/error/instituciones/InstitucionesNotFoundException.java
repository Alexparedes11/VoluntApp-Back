package iesdoctorbalmis.daw2.voluntapp.error.instituciones;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import iesdoctorbalmis.daw2.voluntapp.error.ApiError;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InstitucionesNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InstitucionesNotFoundException(Long id) {
        super("No se puede encontrar la institución con la ID: " + id);
    }

    @ExceptionHandler(InstitucionesNotFoundException.class)
    public ResponseEntity<ApiError> handleInstitucionesNoEncontrado(InstitucionesNotFoundException ex) {
        ApiError apiError = new ApiError();
        apiError.setEstado(HttpStatus.NOT_FOUND);
        apiError.setFecha(LocalDateTime.now());
        apiError.setMensaje(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
}
