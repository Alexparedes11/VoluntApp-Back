package iesdoctorbalmis.daw2.voluntapp.error.eventos;

public class EventosCreateException extends RuntimeException {
    
    private static final long serialVersionUID = 213123L;

    public EventosCreateException() {
        super("Error al crear un nuevo Usuario");
    }

}
