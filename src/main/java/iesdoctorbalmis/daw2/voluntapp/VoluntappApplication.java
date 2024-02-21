package iesdoctorbalmis.daw2.voluntapp;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
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
import iesdoctorbalmis.daw2.voluntapp.servicios.EventosService;
import iesdoctorbalmis.daw2.voluntapp.servicios.InstitucionesService;
import iesdoctorbalmis.daw2.voluntapp.servicios.NoticiasService;
import iesdoctorbalmis.daw2.voluntapp.servicios.UbicacionService;
import iesdoctorbalmis.daw2.voluntapp.servicios.UsuariosService;
import jakarta.transaction.Transactional;

@SpringBootApplication
public class VoluntappApplication {

	public static void main(String[] args) {
		SpringApplication.run(VoluntappApplication.class, args);
	}

	@Bean
	@Transactional
	CommandLineRunner init(UsuariosService usuariosService, InstitucionesService institucionesService,
			EventosService eventosService, UbicacionService ubicacionService,
			NoticiasService noticiasService) {
		return (args) -> {

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
			Set<Categorias> listaCategorias = new HashSet<>();
			Set<Eventos> listaEventosUsuarios = new HashSet<>();
			Set<Eventos> listaEventosUsuarios2 = new HashSet<>();
			Set<Eventos> listaEventosUsuarios3 = new HashSet<>();
			Set<Instituciones> listaInstituciones = new HashSet<>();
			Set<Eventos> listaEventosInstituciones = new HashSet<>();
			Set<Eventos> listaEventosInstituciones2 = new HashSet<>();
			Optional<Instituciones> institucion;
			Optional<Eventos> evento;
			Optional<Usuarios> usuarios;

			Instituciones instituciones = new Instituciones(null, "unicef@example.com",
					"12345678A", "Unicef ORG", "616 616 616",
					"https://voluntapp.blob.core.windows.net/images/banners/default.webp", "Manolito", "Unicef", "123",
					"https://voluntapp.blob.core.windows.net/images/perfiles/default.webp", listaEventosInstituciones);
			institucionesService.guardar(instituciones);
			listaInstituciones.add(instituciones);

			Instituciones instituciones2 = new Instituciones(null, "cruz@example.com",
					"12345678B", "Cruz ORG", "616 616 616",
					"https://voluntapp.blob.core.windows.net/images/banners/default.webp", "Manolito", "Cruz Roja",
					"123",
					"https://voluntapp.blob.core.windows.net/images/perfiles/default.webp", listaEventosInstituciones2);
			institucionesService.guardar(instituciones2);
			listaInstituciones.add(instituciones2);

			Usuarios usuario = new Usuarios(null, "Manuel", "manuel@example.com",
					"Rodriguez Martinez", "12345678A", "Calle Alfonso el sabio 1", "123",
					"https://voluntapp.blob.core.windows.net/images/perfiles/default.webp",
					"https://voluntapp.blob.core.windows.net/images/banners/default.webp", "612 612 612", "Usuario",
					listaEventosUsuarios);
			usuariosService.guardar(usuario);
			listaUsuarios.add(usuario);

			Usuarios usuario2 = new Usuarios(null, "Jose Miguel", "jose@example.com",
					"Muñoz Vazquez", "21436587B", "Calle Teulada 10", "123",
					"https://voluntapp.blob.core.windows.net/images/perfiles/default.webp",
					"https://voluntapp.blob.core.windows.net/images/banners/default.webp",
					"613 613 613", "Usuario", listaEventosUsuarios2);
			usuariosService.guardar(usuario2);
			listaUsuarios.add(usuario2);

			Usuarios usuario3 = new Usuarios(null, "Administrador", "admin@example.com",
					"Admin", "Inventado", "", "123",
					"https://voluntapp.blob.core.windows.net/images/perfiles/default.webp",
					"https://voluntapp.blob.core.windows.net/images/banners/default.webp", "", "Admin",
					listaEventosUsuarios3);
			usuariosService.guardar(usuario3);
			listaUsuarios.add(usuario3);

			Noticias noticias1 = Noticias.builder()
					.titulo("Limpieza masiva en el bosque de Alcoy")
					.contenido(
							"¡Únete a nosotros para una limpieza masiva en el bosque de Alcoy! ¡Ayúdanos a mantener nuestro bosque limpio y seguro!")
					.imagen("https://voluntapp.blob.core.windows.net/images/noticias/new1.webp")
					.fecha(Date.valueOf(LocalDateTime.now().toLocalDate()))
					.autor("VoluntApp")
					.build();
			noticiasService.guardar(noticias1);

			Noticias noticias2 = Noticias.builder()
					.titulo("Inauguración del nuevo centro comunitario en Valencia")
					.contenido(
							"¡Ven y únete a la celebración de la inauguración del nuevo centro comunitario en Valencia! Habrá música en vivo, comida deliciosa y actividades para toda la familia.")
					.imagen("https://voluntapp.blob.core.windows.net/images/noticias/new2.webp")
					.fecha(Date.valueOf(LocalDateTime.now().toLocalDate()))
					.autor("Ayuntamiento de Valencia")
					.build();
			noticiasService.guardar(noticias2);

			Noticias noticias3 = Noticias.builder()
					.titulo("Conferencia sobre conservación marina en Alicante")
					.contenido(
							"No te pierdas nuestra conferencia sobre la importancia de la conservación marina en Alicante. Expertos en el campo compartirán sus conocimientos y experiencias.")
					.imagen("https://voluntapp.blob.core.windows.net/images/noticias/new3.webp")
					.fecha(Date.valueOf(LocalDateTime.now().toLocalDate()))
					.autor("Asociación de Conservación Marina")
					.build();
			noticiasService.guardar(noticias3);

			Noticias noticias4 = Noticias.builder()
					.titulo("Torneo benéfico de fútbol en Castellón")
					.contenido(
							"Participa en nuestro torneo benéfico de fútbol en Castellón para recaudar fondos para niños necesitados. ¡Únete a nosotros para una jornada llena de diversión y solidaridad!")
					.imagen("https://voluntapp.blob.core.windows.net/images/noticias/new4.webp")
					.fecha(Date.valueOf(LocalDateTime.now().toLocalDate()))
					.autor("Fundación Niños Felices")
					.build();
			noticiasService.guardar(noticias4);

			Eventos eventos1 = Eventos.builder()
					.fInicio(fechaInicioEvento1)
					.fFin(fechaFinEvento1)
					.titulo("Taller para mayores")
					.imagen("https://voluntapp.blob.core.windows.net/images/eventos/event1.webp")
					.descripcion(
							"¡Descubre nuevas experiencias y conexiones en nuestro evento de talleres para personas mayores!")
					.ubicacion(ubicacion)
					.estado("disponible")
					.categorias(listaCategorias)
					.creadoPorUsuarios(usuario2)
					.maxVoluntarios(10)
					.build();

			eventosService.guardar(eventos1);
			usuario.addEventos(eventos1);
			usuariosService.editar(usuario);
			instituciones.addEventos(eventos1);
			institucionesService.editar(instituciones);
			evento = eventosService.buscarPorId(eventos1.getId());
			usuarios = usuariosService.buscarPorId(usuario2.getId());
			if (evento.isPresent()) {
				usuarios.get().addEventos(evento.get());
				usuariosService.editar(usuarios.get());
			}

			Eventos eventos2 = Eventos.builder()
					.fInicio(fechaInicioEvento2)
					.fFin(fechaFinEvento2)
					.titulo("Recogida de alimentos")
					.imagen("https://voluntapp.blob.core.windows.net/images/eventos/event2.webp")
					.descripcion("¡Únete a nosotros en nuestra recogida de alimentos!")
					.ubicacion(ubicacion2)
					.estado("disponible")
					.categorias(listaCategorias)
					.creadoPorUsuarios(usuario)
					.maxVoluntarios(100)
					.build();
			eventosService.guardar(eventos2);
			usuario2.addEventos(eventos2);
			usuariosService.editar(usuario2);
			instituciones2.addEventos(eventos2);
			institucionesService.editar(instituciones2);
			evento = eventosService.buscarPorId(eventos2.getId());
			usuarios = usuariosService.buscarPorId(usuario.getId());
			if (evento.isPresent()) {
				usuarios.get().addEventos(evento.get());
				usuariosService.editar(usuarios.get());
			}

			Eventos eventos3 = Eventos.builder()
					.fInicio(fechaInicioEvento3)
					.fFin(fechaFinEvento3)
					.titulo("Limpiar pellets de la playa")
					.imagen("https://voluntapp.blob.core.windows.net/images/eventos/event3.webp")
					.descripcion("¡Únete a nosotros en la playa Postiguet para un evento de recogida de pellets!")
					.ubicacion(ubicacion3)
					.estado("disponible")
					.categorias(listaCategorias)
					.creadoPorUsuarios(usuario2)
					.maxVoluntarios(50)
					.build();
			eventosService.guardar(eventos3);
			evento = eventosService.buscarPorId(eventos3.getId());
			usuarios = usuariosService.buscarPorId(usuario2.getId());
			if (evento.isPresent()) {
				usuarios.get().addEventos(evento.get());
				usuariosService.editar(usuarios.get());
			}

			Eventos eventos4 = Eventos.builder()
					.fInicio(fechaInicioEvento4)
					.fFin(fechaFinEvento4)
					.titulo("Recogida de basura")
					.imagen("https://voluntapp.blob.core.windows.net/images/eventos/event4.webp")
					.descripcion("Te invitamos a unirte a nosotros en un esfuerzo comunitario para recoger basura")
					.ubicacion(ubicacion4)
					.estado("disponible")
					.categorias(listaCategorias)
					.creadoPorUsuarios(usuario2)
					.maxVoluntarios(50)
					.build();
			eventosService.guardar(eventos4);
			evento = eventosService.buscarPorId(eventos4.getId());
			usuarios = usuariosService.buscarPorId(usuario2.getId());
			if (evento.isPresent()) {
				usuarios.get().addEventos(evento.get());
				usuariosService.editar(usuarios.get());
			}
			evento = eventosService.buscarPorId(eventos4.getId());
			institucion = institucionesService.buscarPorId(instituciones.getId());
			if (evento.isPresent()) {
				institucion.get().addEventos(evento.get());
				institucionesService.editar(institucion.get());
			}
			evento = eventosService.buscarPorId(eventos4.getId());
			institucion = institucionesService.buscarPorId(instituciones2.getId());
			if (evento.isPresent()) {
				institucion.get().addEventos(evento.get());
				institucionesService.editar(institucion.get());
			}

			Eventos eventos5 = Eventos.builder()
					.fInicio(fechaInicioEvento5)
					.fFin(fechaFinEvento5)
					.titulo("Sonrisas para los pequeños")
					.imagen("https://voluntapp.blob.core.windows.net/images/eventos/event5.webp")
					.descripcion("¡Ven y únete a nosotros para un día lleno de risas y diversión!")
					.ubicacion(ubicacion)
					.estado("finalizado")
					.categorias(listaCategorias)
					.creadoPorUsuarios(usuario)
					.maxVoluntarios(30)
					.build();
			eventosService.guardar(eventos5);
			evento = eventosService.buscarPorId(eventos5.getId());
			usuarios = usuariosService.buscarPorId(usuario.getId());
			if (evento.isPresent()) {
				usuarios.get().addEventos(evento.get());
				usuariosService.editar(usuarios.get());
			}

			Eventos eventos6 = Eventos.builder()
					.fInicio(fechaInicioEvento6)
					.fFin(fechaFinEvento6)
					.titulo("Ayuda para limpiar las inundaciones")
					.imagen("https://voluntapp.blob.core.windows.net/images/eventos/event6.webp")
					.descripcion(
							"Únete a nosotros para un evento comunitario de apoyo y solidaridad frente a las inundaciones")
					.ubicacion(ubicacion)
					.estado("denegado")
					.categorias(listaCategorias)
					.creadoPorUsuarios(usuario2)
					.maxVoluntarios(50)
					.build();
			eventosService.guardar(eventos6);
			evento = eventosService.buscarPorId(eventos6.getId());
			usuarios = usuariosService.buscarPorId(usuario2.getId());
			if (evento.isPresent()) {
				usuarios.get().addEventos(evento.get());
				usuariosService.editar(usuarios.get());
			}

		};
	}
}