package iesdoctorbalmis.daw2.voluntapp.error.usuarios;

public class SearchUsuarioNoResultException extends RuntimeException{
    
    private static final long serialVersionUID = 4254760268350693633L;

    public SearchUsuarioNoResultException() {
        super("La búsqueda de usuarios no produjo resultados");
    }

    public SearchUsuarioNoResultException(String txt) {
        super("El término de la búsquda " + txt + " no produjo resultados");
    }
}
