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
	// 		Set<Eventos> listaEventosUsuarios2 = new HashSet<>();
	// 		Set<Eventos> listaEventosUsuarios3 = new HashSet<>();
	// 		Set<Eventos> listaEventosInstituciones = new HashSet<>();
	// 		Set<Eventos> listaEventosCategorias = new HashSet<>();

	// 		Eventos eventos1 = Eventos.builder()
	// 			.fInicio(new Date(0))
	// 			.fFin(new Date(0))
	// 			.nombre("Evento de ejemplo")
	// 			.rating(4.5f)
	// 			.comentarios("Excelente evento")
	// 			.fotoEvento("/assets/ejemplo")
	// 			.usuarios(listaUsuarios)
	// 			.instituciones(listaInstituciones)
	// 			.categorias(listaCategorias)
	// 			.build();
	// 		eventosService.guardar(eventos1);
	// 		listaEventosUsuarios.add(eventos1);
	
	// 		Usuarios usuario2 = new Usuarios(null, "Belen", "Arias", "DNI12", "ejemplo-direccion", "asd@example.com", "123", "/assets/imagen", "Admin", listaEventosUsuarios);
	// 		usuariosService.guardar(usuario2);

	// 		Eventos eventos2 = Eventos.builder()
	// 			.fInicio(new Date(0))
	// 			.fFin(new Date(0))
	// 			.nombre("Evento de ejemplo 2")
	// 			.rating(4.5f)
	// 			.comentarios("Excelente evento")
	// 			.fotoEvento("/assets/ejemplo")
	// 			.usuarios(listaUsuarios)
	// 			.instituciones(listaInstituciones)
	// 			.categorias(listaCategorias)
	// 			.creadoPorUsuarios(usuario2)
	// 			.build();
	// 		eventosService.guardar(eventos2);
	// 		listaEventosUsuarios.add(eventos2);

	// 		System.out.println(listaEventosUsuarios);

	// 		Usuarios usuario = new Usuarios(null, "Iván", "Arias", "DNI123", "ejemplo-direccion", "ivanarias@example.com", "123", "/assets/imagen", "Admin", listaEventosUsuarios);
	// 		usuariosService.guardar(usuario);


	// 	};
	// }
}
