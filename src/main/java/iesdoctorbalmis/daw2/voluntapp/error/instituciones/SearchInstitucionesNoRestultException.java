package iesdoctorbalmis.daw2.voluntapp.error.instituciones;

public class SearchInstitucionesNoRestultException extends RuntimeException {
    
    private static final long serialVersionUID = 4254760268350693633L;

    public SearchInstitucionesNoRestultException() {
        super("La búsqueda de instituciones no produjo resultados");
    }

    public SearchInstitucionesNoRestultException(String txt) {
        super("El término de la búsquda " + txt + " no produjo resultados");
    }
    
}
