package iesdoctorbalmis.daw2.voluntapp.error.usuarios;

public class UsuarioCreateException extends RuntimeException {
    
    private static final long serialVersionUID = 213L;

    public UsuarioCreateException() {
        super("Error al crear un nuevo Usuario");
    }
}
