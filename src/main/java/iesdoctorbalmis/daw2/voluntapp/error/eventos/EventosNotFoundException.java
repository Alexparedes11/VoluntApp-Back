package iesdoctorbalmis.daw2.voluntapp.error.eventos;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import iesdoctorbalmis.daw2.voluntapp.error.ApiError;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EventosNotFoundException extends RuntimeException{
    
    private static final long serialVersionUID = 3L;

    public EventosNotFoundException(Long id) {
        super("No se puede encontrar el evento con la ID: " + id);
    }

    @ExceptionHandler(EventosNotFoundException.class)
    public ResponseEntity<ApiError> handleEventoNoEncontrado(EventosNotFoundException ex) {
        ApiError apiError = new ApiError();
        apiError.setEstado(HttpStatus.NOT_FOUND);
        apiError.setFecha(LocalDateTime.now());
        apiError.setMensaje(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
}
