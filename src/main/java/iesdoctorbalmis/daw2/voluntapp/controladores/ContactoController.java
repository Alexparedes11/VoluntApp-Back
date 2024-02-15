package iesdoctorbalmis.daw2.voluntapp.controladores;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iesdoctorbalmis.daw2.voluntapp.modelos.Contacto;
import iesdoctorbalmis.daw2.voluntapp.servicios.ContactoService;

@RestController
@RequestMapping("/contacto")
public class ContactoController {
    @Autowired
    private ContactoService enviarCorreo;

    @PostMapping("/enviarCorreo")
    public ResponseEntity<Map<String, String>> enviarCorreo(@RequestBody Contacto contacto) {
    try {
        enviarCorreo.enviarCorreo(contacto);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Correo enviado");
        return ResponseEntity.ok(response);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);}
    }

    @PostMapping("/enviarSolicitud")
    public ResponseEntity<Map<String, String>> enviarSolicitud(@RequestBody Contacto contacto) {
        try {
            enviarCorreo.enviarSolicitud(contacto);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Correo enviado");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
}

@PostMapping("/enviarRespuestaDenegada")
    public ResponseEntity<Map<String, String>> enviarRespuestaDenegada(@RequestBody Contacto contacto) {
        try {
            enviarCorreo.enviarRespuestaDenegada(contacto);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Correo enviado");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
}



}