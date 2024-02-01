package iesdoctorbalmis.daw2.voluntapp.error.instituciones;

public class InstitucionesCreateException extends RuntimeException {
    
    private static final long serialVersionUID = 123321L;

    public InstitucionesCreateException() {
        super("Error al crear un nuevo Usuario");
    }

}
