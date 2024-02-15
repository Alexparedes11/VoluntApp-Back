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
import iesdoctorbalmis.daw2.voluntapp.modelos.Noticias;
import iesdoctorbalmis.daw2.voluntapp.modelos.Ubicacion;
import iesdoctorbalmis.daw2.voluntapp.modelos.Usuarios;
import iesdoctorbalmis.daw2.voluntapp.servicios.AzureBlobStorageService;
import iesdoctorbalmis.daw2.voluntapp.servicios.EventosService;
import iesdoctorbalmis.daw2.voluntapp.servicios.InstitucionesService;
import iesdoctorbalmis.daw2.voluntapp.servicios.NoticiasService;
import iesdoctorbalmis.daw2.voluntapp.servicios.UbicacionService;
import iesdoctorbalmis.daw2.voluntapp.servicios.UsuariosService;

@SpringBootApplication
public class VoluntappApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoluntappApplication.class, args);
	}

	// ESTO ES PARA PROBAR AZURE
	// @Bean
	// CommandLineRunner init(AzureBlobStorageService azureBlobStorageService) {
	// return (args) -> {
	// azureBlobStorageService.uploadFile("/events", "test.jpg", "");
	// };
	// }

	@Bean
	CommandLineRunner init(UsuariosService usuariosService, InstitucionesService institucionesService,
			EventosService eventosService, UbicacionService ubicacionService,
			NoticiasService noticiasService) {
		return (args) -> {

			Instituciones instituciones = new Instituciones(null, "Unicef@example.com",
					"12345678A", "Unicef ORG", "616 616 616", "Manolito", "Unicef", "123",
					"/assets/imagen", null);
			institucionesService.guardar(instituciones);

			LocalDateTime fechaInicioEvento1 = LocalDateTime.of(2024, 2, 17, 9, 30);
			LocalDateTime fechaFinEvento1 = LocalDateTime.of(2024, 2, 17, 14, 0);

			LocalDateTime fechaInicioEvento2 = LocalDateTime.of(2024, 3, 8, 8, 0);
			LocalDateTime fechaFinEvento2 = LocalDateTime.of(2024, 3, 10, 20, 0);

			LocalDateTime fechaInicioEvento3 = LocalDateTime.of(2024, 4, 15, 9, 0);
			LocalDateTime fechaFinEvento3 = LocalDateTime.of(2024, 4, 18, 14, 0);

			LocalDateTime fechaInicioEvento4 = LocalDateTime.of(2024, 3, 8, 8, 0);
			LocalDateTime fechaFinEvento4 = LocalDateTime.of(2024, 3, 8, 20, 0);

			LocalDateTime fechaInicioEvento5 = LocalDateTime.of(2024, 2, 20, 9, 30);
			LocalDateTime fechaFinEvento5 = LocalDateTime.of(2024, 2, 22, 19, 0);

			LocalDateTime fechaInicioEvento6 = LocalDateTime.of(2024, 3, 1, 9, 30);
			LocalDateTime fechaFinEvento6 = LocalDateTime.of(2024, 3, 3, 22, 0);

			Ubicacion ubicacion = new Ubicacion(null, "Calle De San Olegario, Madrid",
					-3.7602838, 40.3699524);
			ubicacionService.guardar(ubicacion);

			Ubicacion ubicacion2 = new Ubicacion(null, "N-340, Pk 131, Alcoi, Alicante",
					-0.47140612489073314, 38.683488155501855);
			ubicacionService.guardar(ubicacion2);

			Ubicacion ubicacion3 = new Ubicacion(null, "Playa Postiguet, Alicante",
					-0.47876710565513714, 38.3444807533824);
			ubicacionService.guardar(ubicacion3);

			Ubicacion ubicacion4 = new Ubicacion(null, "C. Escultor Bañuls 7, Alicante",
					-0.4912633978169558, 38.35726809850449);
			ubicacionService.guardar(ubicacion4);

			Ubicacion ubicacion5 = new Ubicacion(null, "Pintor Baeza 11, Alicante",
					-0.48590116945300316, 38.36322105571466);
			ubicacionService.guardar(ubicacion5);

			Ubicacion ubicacion6 = new Ubicacion(null, "C. 19 de Abril 34, San Miguel de Salinas, Alicante",
					-0.7887108508683476, 37.97922685104109);
			ubicacionService.guardar(ubicacion6);

			Set<Usuarios> listaUsuarios = new HashSet<>();

			Usuarios usuario = new Usuarios(null, "Manuel", "manuel@example.com",
					"Rodriguez Martinez", "12345678A", "Calle Alfonso el sabio 1", "123",
					"/assets/imagen", "612 612 612", "Usuario", null);
			usuariosService.guardar(usuario);
			listaUsuarios.add(usuario);

			Usuarios usuario2 = new Usuarios(null, "Jose Miguel", "jose@example.com",
					"Muñoz Vazquez", "21436587B", "Calle Teulada 10", "123", "/assets/imagen",
					"613 613 613", "Usuario", null);
			usuariosService.guardar(usuario2);
			listaUsuarios.add(usuario2);

			Usuarios usuario3 = new Usuarios(null, "Administrador", "admin@example.com",
					"Admin", "Inventado", "", "123", "/assets/imagen", "", "Admin", null);
			usuariosService.guardar(usuario3);
			listaUsuarios.add(usuario3);

			Set<Instituciones> listaInstituciones = new HashSet<>();
			Set<Categorias> listaCategorias = new HashSet<>();

			Set<Eventos> listaEventosUsuarios = new HashSet<>();

			Noticias noticias1 = Noticias.builder()
					.titulo("Limpieza masiva en el bosque de Alcoy")
					.contenido(
							"¡Únete a nosotros para una limpieza masiva en el bosque de Alcoy! ¡Ayúdanos a mantener nuestro bosque limpio y seguro!")
					.imagen("/aseets/images/bosque.jpg")
					.fecha(Date.valueOf(LocalDateTime.now().toLocalDate()))
					.autor("VoluntApp")
					.build();
			noticiasService.guardar(noticias1);

			Eventos eventos1 = Eventos.builder()
					.fInicio(fechaInicioEvento1)
					.fFin(fechaFinEvento1)
					.titulo("Taller para mayores")
					.imagen("/assets/images/mayores.jpg")
					.usuarios(listaUsuarios)
					.descripcion(
							"¡Descubre nuevas experiencias y conexiones en nuestro evento de talleres para personas mayores!")
					.ubicacion(ubicacion)
					.estado("disponible")
					.instituciones(listaInstituciones)
					.categorias(listaCategorias)
					.creadoPorUsuarios(usuario2)
					.maxVoluntarios(10)
					.build();
			eventosService.guardar(eventos1);
			listaEventosUsuarios.add(eventos1);

			Eventos eventos2 = Eventos.builder()
					.fInicio(fechaInicioEvento2)
					.fFin(fechaFinEvento2)
					.titulo("Recogida de basura")
					.imagen("/assets/images/limpiarbosque.jpg")
					.usuarios(listaUsuarios)
					.descripcion("Te invitamos a unirte a nosotros en un esfuerzo comunitario para recoger basura")
					.ubicacion(ubicacion2)
					.estado("disponible")
					.instituciones(listaInstituciones)
					.categorias(listaCategorias)
					.creadoPorUsuarios(usuario)
					.maxVoluntarios(100)
					.build();
			eventosService.guardar(eventos2);
			listaEventosUsuarios.add(eventos2);

			Eventos eventos3 = Eventos.builder()
					.fInicio(fechaInicioEvento3)
					.fFin(fechaFinEvento3)
					.titulo("Limpiar pellets de la playa")
					.imagen("/assets/images/pellets.jpg")
					.descripcion("¡Únete a nosotros en la playa Postiguet para un evento de recogida de pellets")
					.ubicacion(ubicacion3)
					.estado("disponible")
					.usuarios(listaUsuarios)
					.instituciones(listaInstituciones)
					.categorias(listaCategorias)
					.creadoPorUsuarios(usuario2)
					.maxVoluntarios(50)
					.build();
			eventosService.guardar(eventos3);
			listaEventosUsuarios.add(eventos3);

			Eventos eventos4 = Eventos.builder()
					.fInicio(fechaInicioEvento4)
					.fFin(fechaFinEvento4)
					.titulo("Recogida de alimentos")
					.imagen("/assets/images/recogidaalimentos.jpg")
					.descripcion("¡Únete a nosotros en nuestra recogida de alimentos para apoyar")
					.ubicacion(ubicacion4)
					.usuarios(listaUsuarios)
					.estado("disponible")
					.instituciones(listaInstituciones)
					.categorias(listaCategorias)
					.creadoPorUsuarios(usuario2)
					.maxVoluntarios(50)
					.build();
			eventosService.guardar(eventos4);
			listaEventosUsuarios.add(eventos4);

			Eventos eventos5 = Eventos.builder()
					.fInicio(fechaInicioEvento5)
					.fFin(fechaFinEvento5)
					.titulo("Sonrisas para los pequeños")
					.imagen("/assets/images/sonrisas.jpg")
					.descripcion("¡Ven y únete a nosotros para un día lleno de risas y diversión")
					.ubicacion(ubicacion)
					.estado("finalizado")
					.usuarios(listaUsuarios)
					.instituciones(listaInstituciones)
					.categorias(listaCategorias)
					.creadoPorUsuarios(usuario)
					.maxVoluntarios(30)
					.build();
			eventosService.guardar(eventos5);
			listaEventosUsuarios.add(eventos5);

			Eventos eventos6 = Eventos.builder()
					.fInicio(fechaInicioEvento6)
					.fFin(fechaFinEvento6)
					.titulo("Ayuda para limpiar las inundaciones")
					.imagen("/assets/images/inundacion.jpg")
					.descripcion(
							"Únete a nosotros para un evento comunitario de apoyo y solidaridad frente a las inundaciones")
					.ubicacion(ubicacion)
					.estado("denegado")
					.usuarios(listaUsuarios)
					.instituciones(listaInstituciones)
					.categorias(listaCategorias)
					.creadoPorUsuarios(usuario2)
					.maxVoluntarios(50)
					.build();
			eventosService.guardar(eventos6);
			listaEventosUsuarios.add(eventos6);
		};
	}
}