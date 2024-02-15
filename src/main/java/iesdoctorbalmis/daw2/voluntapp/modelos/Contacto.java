package iesdoctorbalmis.daw2.voluntapp.modelos;

public class Contacto {
    private String email;
    private String asunto;
    private String mensaje;

    public Contacto() {
    }

    public Contacto(String email, String asunto, String mensaje) {
        this.email = email;
        this.asunto = asunto;
        this.mensaje = mensaje;
    }

    public String getEmail() {
        return this.email;
    }

    public String getAsunto() {
        return this.asunto;
    }

    public String getMensaje() {
        return this.mensaje;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


}