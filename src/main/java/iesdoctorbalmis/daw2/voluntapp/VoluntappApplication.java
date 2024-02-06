package iesdoctorbalmis.daw2.voluntapp;

import java.sql.Date;
import java.time.LocalDateTime;
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
			
	// 		LocalDateTime fechaInicio = LocalDateTime.of(2024, 2, 6, 15, 30);
	// 		LocalDateTime fechaFin = LocalDateTime.of(2024, 2, 10, 18, 0);
	// 		Set<Usuarios> listaUsuarios = new HashSet<>();
	// 		Set<Instituciones> listaInstituciones = new HashSet<>();
	// 		Set<Categorias> listaCategorias = new HashSet<>();

	// 		Set<Eventos> listaEventosUsuarios = new HashSet<>();
	// 		Set<Eventos> listaEventosUsuarios2 = new HashSet<>();

	
	// 		Usuarios usuario2 = new Usuarios(null, "Josemiguel","asd@example.com", "Muñoz", "DNI12", "ejemplo-direccion", "123", "/assets/imagen", "123213", "Admin", listaEventosUsuarios);
	// 		usuariosService.guardar(usuario2);

	// 		Eventos eventos2 = Eventos.builder()
	// 			.fInicio(fechaInicio)
	// 			.fFin(fechaFin)
	// 			.titulo("Evento de ejemplo 2")
	// 			.imagen("/assets/images/montana.jpg")
	// 			.usuarios(listaUsuarios)
	// 			.descripcion("Evento Buenardo")
	// 			.ubicacion("Barcelona")
	// 			.estado("Disponible")
	// 			.instituciones(listaInstituciones)
	// 			.categorias(listaCategorias)
	// 			.creadoPorUsuarios(usuario2)
	// 			.maxVoluntarios(30)
	// 			.build();
	// 		eventosService.guardar(eventos2);
	// 		listaEventosUsuarios.add(eventos2);
	// 		listaEventosUsuarios2.add(eventos2);

	// 		Eventos eventos1 = Eventos.builder()
	// 			.fInicio(fechaInicio)
	// 			.fFin(fechaFin)
	// 			.titulo("Evento de ejemplo")
	// 			.imagen("/assets/images/cascada.jpg")
	// 			.descripcion("Evento pocho")
	// 			.ubicacion("Castilla la Mancha")
	// 			.estado("Finalizado")
	// 			.usuarios(listaUsuarios)
	// 			.instituciones(listaInstituciones)
	// 			.categorias(listaCategorias)
	// 			.creadoPorUsuarios(usuario2)
	// 			.maxVoluntarios(50)
	// 			.build();
	// 		eventosService.guardar(eventos1);
	// 		listaEventosUsuarios.add(eventos1);

	// 		Eventos eventos3 = Eventos.builder()
	// 			.fInicio(fechaInicio)
	// 			.fFin(fechaFin)
	// 			.titulo("Evento de ejemplo 3")
	// 			.imagen("/assets/images/rio.jpg")
	// 			.descripcion("Evento pocho")
	// 			.ubicacion("Castilla la Mancha")
	// 			.estado("Revisión")
	// 			.usuarios(listaUsuarios)
	// 			.instituciones(listaInstituciones)
	// 			.categorias(listaCategorias)
	// 			.creadoPorUsuarios(usuario2)
	// 			.maxVoluntarios(50)
	// 			.build();
	// 		eventosService.guardar(eventos3);
	// 		listaEventosUsuarios.add(eventos3);
	// 		listaEventosUsuarios2.add(eventos3);

	// 		Eventos eventos4 = Eventos.builder()
	// 			.fInicio(fechaInicio)
	// 			.fFin(fechaFin)
	// 			.titulo("Evento de ejemplo 4")
	// 			.imagen("/assets/images/rio.jpg")
	// 			.descripcion("Evento pocho")
	// 			.ubicacion("Castilla la Mancha")
	// 			.usuarios(listaUsuarios)
	// 			.estado("Disponible")
	// 			.instituciones(listaInstituciones)
	// 			.categorias(listaCategorias)
	// 			.creadoPorUsuarios(usuario2)
	// 			.maxVoluntarios(50)
	// 			.build();
	// 		eventosService.guardar(eventos4);
	// 		listaEventosUsuarios.add(eventos4);

	// 		Eventos eventos5 = Eventos.builder()
	// 			.fInicio(fechaInicio)
	// 			.fFin(fechaFin)
	// 			.titulo("Evento de ejemplo 5")
	// 			.imagen("/assets/images/cascada.jpg")
	// 			.descripcion("Evento pocho")
	// 			.ubicacion("Castilla la Mancha")
	// 			.estado("En curso")
	// 			.usuarios(listaUsuarios)
	// 			.instituciones(listaInstituciones)
	// 			.categorias(listaCategorias)
	// 			.creadoPorUsuarios(usuario2)
	// 			.maxVoluntarios(50)
	// 			.build();
	// 		eventosService.guardar(eventos5);
	// 		listaEventosUsuarios2.add(eventos5);

	// 		Eventos eventos6 = Eventos.builder()
	// 			.fInicio(fechaInicio)
	// 			.fFin(fechaFin)
	// 			.titulo("Evento de ejemplo 6")
	// 			.imagen("/assets/images/montana.jpg")
	// 			.descripcion("Evento pocho")
	// 			.ubicacion("Castilla la Mancha")
	// 			.estado("Denegado")
	// 			.usuarios(listaUsuarios)
	// 			.instituciones(listaInstituciones)
	// 			.categorias(listaCategorias)
	// 			.creadoPorUsuarios(usuario2)
	// 			.maxVoluntarios(50)
	// 			.build();
	// 		eventosService.guardar(eventos6);

	// 		Eventos eventos7 = Eventos.builder()
	// 			.fInicio(fechaInicio)
	// 			.fFin(fechaFin)
	// 			.titulo("Evento de ejemplo 7")
	// 			.imagen("/assets/images/pellets.jpg")
	// 			.descripcion("Evento pocho")
	// 			.ubicacion("Castilla la Mancha")
	// 			.estado("Disponible")
	// 			.usuarios(listaUsuarios)
	// 			.instituciones(listaInstituciones)
	// 			.categorias(listaCategorias)
	// 			.creadoPorUsuarios(usuario2)
	// 			.maxVoluntarios(50)
	// 			.build();
	// 		eventosService.guardar(eventos7);
	// 		listaEventosUsuarios.add(eventos7);
	// 		listaEventosUsuarios2.add(eventos7);

	// 		Usuarios usuario = new Usuarios(null, "Iván", "ivanarias@example.com", "Arias", "DNI123", "ejemplo-direccion", "123", "/assets/imagen","123213", "Admin", listaEventosUsuarios);
	// 		usuariosService.guardar(usuario);

	// 		Usuarios usuario3 = new Usuarios(null, "Manin", "manin@example.com", "Brothel", "D123NI123", "ejemplo-direccion", "123", "/assets/imagen","123213", "Usuario", listaEventosUsuarios2);
	// 		usuariosService.guardar(usuario3);

	// 	};
	// }
}
