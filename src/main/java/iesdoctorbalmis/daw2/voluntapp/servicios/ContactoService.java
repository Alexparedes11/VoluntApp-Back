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
        message.setText("Email: " + contacto.getEmail() + "\n"+ contacto.getMensaje());
        mailSender.send(message);
    }
    
    public void enviarSolicitud(Contacto contacto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("voluntapp.management@gmail.com");
        message.setTo("voluntapp.management@gmail.com");
        message.setSubject(contacto.getEmail());
        message.setText(contacto.getAsunto() +contacto.getMensaje());
        mailSender.send(message);
    }
    public void enviarRespuestaDenegada(Contacto contacto) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("voluntapp.management@gmail.com");
            message.setTo(contacto.getEmail());
            message.setSubject(contacto.getAsunto());
            message.setText(contacto.getMensaje());
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes registrar la excepción o lanzarla para obtener más detalles en la consola
            throw e;
        }
    }

    //correo de registro completado
    public void enviarRegistro(Contacto contacto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("voluntapp.management@gmail.com");
        message.setTo(contacto.getEmail());
        message.setSubject(contacto.getAsunto());
        message.setText(contacto.getMensaje());
        mailSender.send(message);
    }
}