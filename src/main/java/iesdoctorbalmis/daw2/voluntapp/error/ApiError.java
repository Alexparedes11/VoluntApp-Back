package iesdoctorbalmis.daw2.voluntapp.error;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter @Setter
@RequiredArgsConstructor @NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    @NonNull
    private HttpStatus estado;

    @JsonFormat(shape = Shape.STRING, pattern="dd/MM/yyyy hh:mm:ss")
    private LocalDateTime fecha;

    @NonNull
    private String mensaje;

}
