package iesdoctorbalmis.daw2.voluntapp;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import iesdoctorbalmis.daw2.voluntapp.modelos.Categorias;
import iesdoctorbalmis.daw2.voluntapp.modelos.Eventos;
import iesdoctorbalmis.daw2.voluntapp.modelos.Instituciones;
import iesdoctorbalmis.daw2.voluntapp.modelos.Usuarios;
import iesdoctorbalmis.daw2.voluntapp.servicios.EventosService;
import iesdoctorbalmis.daw2.voluntapp.servicios.UsuariosService;

@SpringBootApplication
public class VoluntappApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoluntappApplication.class, args);
	}

	// @Bean
	// CommandLineRunner init(UsuariosService usuariosService, EventosService eventosService) {
	// 	return (args) -> {
			
	// 		Set<Usuarios> listaUsuarios = new HashSet<>();
	// 		Set<Instituciones> listaInstituciones = new HashSet<>();
	// 		Set<Categorias> listaCategorias = new HashSet<>();

	// 		Set<Eventos> listaEventosUsuarios = new HashSet<>();

	
	// 		Usuarios usuario2 = new Usuarios(null, "Josemiguel","asd@example.com", "Muñoz", "DNI12", "ejemplo-direccion", "123", "/assets/imagen", "123213", "Admin", listaEventosUsuarios);
	// 		usuariosService.guardar(usuario2);

	// 		Eventos eventos2 = Eventos.builder()
	// 			.fInicio(new Date(0))
	// 			.fFin(new Date(0))
	// 			.nombre("Evento de ejemplo 2")
	// 			.fotoEvento("/assets/ejemplo")
	// 			.usuarios(listaUsuarios)
	// 			.descripcion("Evento Buenardo")
	// 			.ubicacion("Barcelona")
	// 			.instituciones(listaInstituciones)
	// 			.categorias(listaCategorias)
	// 			.creadoPorUsuarios(usuario2)
	// 			.maxVoluntarios(30)
	// 			.build();
	// 		eventosService.guardar(eventos2);
	// 		listaEventosUsuarios.add(eventos2);

	// 		Eventos eventos1 = Eventos.builder()
	// 			.fInicio(new Date(0))
	// 			.fFin(new Date(0))
	// 			.nombre("Evento de ejemplo")
	// 			.fotoEvento("/assets/ejemplo")
	// 			.descripcion("Evento pocho")
	// 			.ubicacion("Castilla la Mancha")
	// 			.usuarios(listaUsuarios)
	// 			.instituciones(listaInstituciones)
	// 			.categorias(listaCategorias)
	// 			.creadoPorUsuarios(usuario2)
	// 			.maxVoluntarios(50)
	// 			.build();
	// 		eventosService.guardar(eventos1);
	// 		listaEventosUsuarios.add(eventos1);

	// 		System.out.println(listaEventosUsuarios);

	// 		Usuarios usuario = new Usuarios(null, "Iván", "ivanarias@example.com", "Arias", "DNI123", "ejemplo-direccion", "123", "/assets/imagen","123213", "Admin", listaEventosUsuarios);
	// 		usuariosService.guardar(usuario);


	// 	};
	// }
}
