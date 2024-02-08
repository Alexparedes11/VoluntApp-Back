package iesdoctorbalmis.daw2.voluntapp.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import iesdoctorbalmis.daw2.voluntapp.modelos.Contacto;

@Service
public class ContactoService {
   @Autowired
   private JavaMailSender mailSender;

    public void enviarCorreo(Contacto contacto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("voluntapp.management@gmail.com");
        message.setTo("voluntapp.management@gmail.com");
        message.setSubject(contacto.getAsunto());
        message.setText("Email: " + contacto.getEmail() + "\n"+"Consulta: " + contacto.getMensaje());
        mailSender.send(message);
    }
}