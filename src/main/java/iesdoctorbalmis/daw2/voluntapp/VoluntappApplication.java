package iesdoctorbalmis.daw2.voluntapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VoluntappApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoluntappApplication.class, args);
	}

	@Bean
	CommandLineRunner init(Usuarios) {
		return (args) -> {
			Set<Usuarios> listaUsuarios = new HashSet<>();
			Set<Instituciones> listaInstituciones = new HashSet<>();
			Set<Eventos> listaEventosUsuarios = new HashSet<>();
			Set<Eventos> listaEventosInstituciones = new HashSet<>();

			Usuarios usuario = new Usuarios("Iván", "Arias", "DNI123", "ejemplo-direccion", "ivanarias@example.com", "123", "/assets/imagen", "Admin", listaEventosUsuarios);
			usuario = new Usuarios("Belen", "Rodriguez", "123DNI", "ejemplo-direccion", "belenrodriguez@example.com", "123", "/assets/imagen", "User", listaEventosUsuarios);



		}
	}
}
