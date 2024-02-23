package iesdoctorbalmis.daw2.voluntapp;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
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

	@Value("${COMMANDLINERUNNER_FLAG:false}")
	boolean runCommandLineRunner;

	@Bean
	@Transactional
	CommandLineRunner init(UsuariosService usuariosService, InstitucionesService institucionesService,
			EventosService eventosService, UbicacionService ubicacionService,
			NoticiasService noticiasService) {
		return (args) -> {
			System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
			System.out.println(runCommandLineRunner);
			System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
			if (runCommandLineRunner) {

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

				LocalDateTime fechaInicioEvento7 = LocalDateTime.of(2024, 4, 1, 10, 0);
				LocalDateTime fechaFinEvento7 = LocalDateTime.of(2024, 4, 3, 18, 0);

				LocalDateTime fechaInicioEvento8 = LocalDateTime.of(2024, 5, 10, 9, 0);
				LocalDateTime fechaFinEvento8 = LocalDateTime.of(2024, 5, 12, 17, 30);

				LocalDateTime fechaInicioEvento9 = LocalDateTime.of(2024, 6, 5, 8, 30);
				LocalDateTime fechaFinEvento9 = LocalDateTime.of(2024, 6, 7, 16, 45);

				LocalDateTime fechaInicioEvento10 = LocalDateTime.of(2024, 7, 20, 11, 0);
				LocalDateTime fechaFinEvento10 = LocalDateTime.of(2024, 7, 22, 20, 15);

				LocalDateTime fechaInicioEvento11 = LocalDateTime.of(2024, 8, 15, 9, 15);
				LocalDateTime fechaFinEvento11 = LocalDateTime.of(2024, 8, 17, 15, 45);

				LocalDateTime fechaInicioEvento12 = LocalDateTime.of(2024, 9, 3, 8, 0);
				LocalDateTime fechaFinEvento12 = LocalDateTime.of(2024, 9, 5, 19, 30);

				LocalDateTime fechaInicioEvento13 = LocalDateTime.of(2024, 10, 12, 10, 30);
				LocalDateTime fechaFinEvento13 = LocalDateTime.of(2024, 10, 14, 18, 45);

				LocalDateTime fechaInicioEvento14 = LocalDateTime.of(2024, 11, 8, 9, 30);
				LocalDateTime fechaFinEvento14 = LocalDateTime.of(2024, 11, 10, 17, 0);

				Ubicacion ubicacion = new Ubicacion(null, "Calle De San Olegario, Madrid",
						-3.7602838, 40.3699524);
				ubicacionService.guardar(ubicacion); //

				Ubicacion ubicacion2 = new Ubicacion(null, "C. Escultor Bañuls 7, Alicante",
						-0.4912633978169558, 38.35726809850449);
				ubicacionService.guardar(ubicacion2); //

				Ubicacion ubicacion3 = new Ubicacion(null, "Playa Postiguet, Alicante",
						-0.47876710565513714, 38.3444807533824);
				ubicacionService.guardar(ubicacion3); //

				Ubicacion ubicacion4 = new Ubicacion(null, "02696 Hoya-Gonzalo, Albacete",
						-1.5938187933444825, 39.00886407161091); //
				ubicacionService.guardar(ubicacion4);

				Ubicacion ubicacion5 = new Ubicacion(null, "Omet Carrer 17, 46229 Picassent, Valencia",
						-0.4715994343625013, 39.353950780131534);
				ubicacionService.guardar(ubicacion5); //

				Ubicacion ubicacion6 = new Ubicacion(null, "Ctra. Villalpando, Km 1.5, 49136 Villafáfila, Zamora",
						-5.590871042847749, 41.85154420630644);
				ubicacionService.guardar(ubicacion6); //

				Ubicacion ubicacion7 = new Ubicacion(null, "C. 19 de Abril 34, San Miguel de Salinas, Alicante",
						-0.7887108508683476, 37.97922685104109);
				ubicacionService.guardar(ubicacion7); //

				Ubicacion ubicacion8 = new Ubicacion(null, "N-340, Pk 131, Alcoi, Alicante",
						-0.47140612489073314, 38.683488155501855);
				ubicacionService.guardar(ubicacion8); //

				Ubicacion ubicacion9 = new Ubicacion(null, "Pr. Maior, 1, 27001 Lugo",
						-7.555675689392136, 43.009993935609494);
				ubicacionService.guardar(ubicacion9); //

				Ubicacion ubicacion10 = new Ubicacion(null, "Playa de Riazor (La Coruña)",
						-8.41043255583519, 43.36879639197843);
				ubicacionService.guardar(ubicacion10); //

				Ubicacion ubicacion11 = new Ubicacion(null, "Av. de las Ciencias, s/n, 41020 Sevilla",
						-5.926158165625714, 37.40044051828174);
				ubicacionService.guardar(ubicacion11); //

				Ubicacion ubicacion12 = new Ubicacion(null,
						"Carrer de Josep Torras I Bages, 50, 08401 Granollers, Barcelona",
						2.2868041855279686, 41.61087620523382);
				ubicacionService.guardar(ubicacion12); //

				Ubicacion ubicacion13 = new Ubicacion(null, "Pintor Baeza 11, Alicante",
						-0.48590116945300316, 38.36322105571466);
				ubicacionService.guardar(ubicacion13); //

				Ubicacion ubicacion14 = new Ubicacion(null, "Pl. Redona, Ciutat Vella, 46001 València, Valencia",
						-0.37661300992725444, 39.47356680517395);
				ubicacionService.guardar(ubicacion14); //

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
						"https://voluntapp.blob.core.windows.net/images/banners/default.webp", "Manolito", "Unicef",
						"123",
						"https://voluntapp.blob.core.windows.net/images/perfiles/default.webp",
						listaEventosInstituciones);
				institucionesService.guardar(instituciones);
				listaInstituciones.add(instituciones);

				Instituciones instituciones2 = new Instituciones(null, "cruz@example.com",
						"12345678B", "Cruz ORG", "616 616 616",
						"https://voluntapp.blob.core.windows.net/images/banners/default.webp", "Manolito", "Cruz Roja",
						"123",
						"https://voluntapp.blob.core.windows.net/images/perfiles/default.webp",
						listaEventosInstituciones2);
				institucionesService.guardar(instituciones2);
				listaInstituciones.add(instituciones2);

				Usuarios usuario = new Usuarios(null, "Manuel", "manuel@example.com",
						"Rodriguez Martinez", "12345678A", "Calle Alfonso el sabio 1", "123",
						"https://voluntapp.blob.core.windows.net/images/perfiles/voluntario1.webp",
						"https://voluntapp.blob.core.windows.net/images/banners/default.webp", "612 612 612", "Usuario",
						listaEventosUsuarios);
				usuariosService.guardar(usuario);
				listaUsuarios.add(usuario);

				Usuarios usuario2 = new Usuarios(null, "Jose Miguel", "jose@example.com",
						"Muñoz Vazquez", "21436587B", "Calle Teulada 10", "123",
						"https://voluntapp.blob.core.windows.net/images/perfiles/voluntario2.webp",
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
						.titulo("Voluntarios con colador: cientos de personas se lanzan a las playas gallegas para luchar contra la marea de ‘pellets’ plásticos")
						.contenido(
								"La Xunta y el Gobierno se enfrentan entre sí, mientras los particulares reclaman coordinación y medios para limpiar los millones de bolitas de un compuesto todavía desconocido extendido por la costa")
						.imagen("https://voluntapp.blob.core.windows.net/images/noticias/news1.webp")
						.fecha(Date.valueOf(LocalDateTime.now().toLocalDate()))
						.autor("Silvia R. Pontevedra / La Voz de Galicia")
						.build();
				noticiasService.guardar(noticias1);

				Noticias noticias5 = Noticias.builder()
						.titulo("UNICEF se Une a VoluntApp: Transformando Vidas Juntos")
						.contenido(
								"Estamos emocionados de dar la bienvenida a UNICEF a VoluntApp. Su registro fortalece nuestra misión compartida de impactar positivamente en la vida de los niños en todo el mundo. Gracias a UNICEF por unirse a esta comunidad de voluntarios comprometidos. Juntos, estamos haciendo una diferencia significativa.")
						.imagen("https://voluntapp.blob.core.windows.net/images/noticias/news5.webp")
						.fecha(Date.valueOf(LocalDateTime.now().toLocalDate()))
						.autor("VoluntApp")
						.build();
				noticiasService.guardar(noticias5);

				Noticias noticias2 = Noticias.builder()
						.titulo("Voluntariado: ¿qué aporta al desarrollo de los niños y niñas?")
						.contenido(
								"Aunque no hay una edad determinada para implicarse en labores solidarias hacerlo desde edades tempranas promueve habilidades como la autonomía, la comunicación o la resiliencia. Además, fomenta los sentimientos de compromiso, el pensamiento crítico y el espíritu ciudadano")
						.imagen("https://voluntapp.blob.core.windows.net/images/noticias/news2.webp")
						.fecha(Date.valueOf(LocalDateTime.now().toLocalDate()))
						.autor("Ana Camarero / El País")
						.build();
				noticiasService.guardar(noticias2);

				Noticias noticias3 = Noticias.builder()
						.titulo("Ir como voluntario a África, abrazar niños y colgar la foto en Instagram. No, gracias")
						.contenido(
								"Las ONG aumentan los filtros y profesionalizan el altruismo para que sea efectivo y para limitar el turismo solidario")
						.imagen("https://voluntapp.blob.core.windows.net/images/noticias/news3.webp")
						.fecha(Date.valueOf(LocalDateTime.now().toLocalDate()))
						.autor("Beatriz Lecumberri / El País")
						.build();
				noticiasService.guardar(noticias3);

				Noticias noticias4 = Noticias.builder()
						.titulo("Jesús, exgerente comercial que vive en la calle: “No sabes adónde ir ni qué hacer” ")
						.contenido(
								"Una noche con las personas sin hogar y los voluntarios de la Fundación Conciénciate en el barrio Carrús de Elche, uno de los más pobres de España")
						.imagen("https://voluntapp.blob.core.windows.net/images/noticias/news4.webp")
						.fecha(Date.valueOf(LocalDateTime.now().toLocalDate()))
						.autor("Mario Abril / El País")
						.build();
				noticiasService.guardar(noticias4);

				Noticias noticias6 = Noticias.builder()
						.titulo("Cruz Roja Se Suma a VoluntApp: Solidaridad en Acción")
						.contenido(
								"Es un honor anunciar que Cruz Roja se ha unido a VoluntApp, nuestra plataforma dedicada a impulsar la solidaridad a través del voluntariado. Su participación fortalece nuestra red y amplía el alcance de nuestra labor humanitaria. Agradecemos a Cruz Roja por unirse a esta causa común de ayudar a quienes más lo necesitan. Juntos, llevaremos esperanza y asistencia a comunidades vulnerables en todo el mundo.")
						.imagen("https://voluntapp.blob.core.windows.net/images/noticias/news6.webp")
						.fecha(Date.valueOf(LocalDateTime.now().toLocalDate()))
						.autor("VoluntApp")
						.build();
				noticiasService.guardar(noticias6);

				Eventos eventos1 = Eventos.builder()
						.fInicio(fechaInicioEvento1)
						.fFin(fechaFinEvento1)
						.titulo("Taller de primeros auxilios")
						.imagen("https://voluntapp.blob.core.windows.net/images/eventos/event1.webp")
						.descripcion(
								"¡Atención, adolescentes! ¿Estás listo para ser un héroe en situaciones de emergencia? Únete a nosotros en nuestro evento especial de primeros auxilios diseñado exclusivamente para jóvenes como tú. En este emocionante taller, aprenderás habilidades vitales que podrían salvar vidas en momentos críticos.")
						.ubicacion(ubicacion)
						.estado("disponible")
						.categorias(listaCategorias)
						.creadoPorInstituciones(instituciones2)
						.maxVoluntarios(20)
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
						.descripcion(
								"¡Únete a nosotros en un acto de solidaridad y generosidad mientras nos unimos para ayudar a aquellos que más lo necesitan en nuestra comunidad! Nuestro evento de recogida de alimentos es una oportunidad para marcar la diferencia y apoyar a las personas que enfrentan la inseguridad alimentaria.")
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
						.titulo("Recogida de basura de la playa")
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
						.titulo("Recogida de basura en el campo")
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
						.titulo("Amor en Cuatro Patas: Adopción de Perros")
						.imagen("https://voluntapp.blob.core.windows.net/images/eventos/event5.webp")
						.descripcion(
								"¡Únete a nosotros en un evento lleno de amor y compañerismo mientras ayudamos a perros necesitados a encontrar sus hogares para siempre! En \"Amor en Cuatro Patas\", te invitamos a considerar la adopción como la mejor opción al momento de agregar un nuevo miembro a tu familia.")
						.ubicacion(ubicacion5)
						.estado("revision")
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
						.titulo("Recogida de cristales en el campo")
						.imagen("https://voluntapp.blob.core.windows.net/images/eventos/event6.webp")
						.descripcion(
								"¡Únete a nosotros en una jornada de acción comunitaria para limpiar y preservar la belleza natural de nuestro campo mientras recogemos cristales tipo basura! En este evento especial, te invitamos a ser parte del cambio y ayudar a devolver la pureza a nuestros paisajes naturales.")
						.ubicacion(ubicacion6)
						.estado("disponible")
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

				Eventos eventos7 = Eventos.builder()
						.fInicio(fechaInicioEvento7)
						.fFin(fechaFinEvento7)
						.titulo("Ayuda humanitaria por la inundaciones")
						.imagen("https://voluntapp.blob.core.windows.net/images/eventos/event7.webp")
						.descripcion(
								"En momentos de crisis, la solidaridad y el apoyo comunitario son más importantes que nunca. Únete a nosotros en un esfuerzo conjunto para brindar ayuda y esperanza a las personas afectadas por las devastadoras inundaciones que han golpeado nuestra región.")
						.ubicacion(ubicacion7)
						.estado("disponible")
						.categorias(listaCategorias)
						.creadoPorUsuarios(usuario2)
						.maxVoluntarios(50)
						.build();
				eventosService.guardar(eventos7);
				evento = eventosService.buscarPorId(eventos7.getId());
				usuarios = usuariosService.buscarPorId(usuario2.getId());
				if (evento.isPresent()) {
					usuarios.get().addEventos(evento.get());
					usuariosService.editar(usuarios.get());
				}

				Eventos eventos8 = Eventos.builder()
						.fInicio(fechaInicioEvento8)
						.fFin(fechaFinEvento8)
						.titulo("Limpieza del bosque de alcoi")
						.imagen("https://voluntapp.blob.core.windows.net/images/eventos/event8.webp")
						.descripcion(
								"Únete a nosotros en una jornada dedicada a la preservación y protección de nuestro preciado bosque. En este evento de limpieza ambiental, nos uniremos como comunidad para devolverle su esplendor natural a este ecosistema vital.")
						.ubicacion(ubicacion8)
						.estado("disponible")
						.categorias(listaCategorias)
						.creadoPorUsuarios(usuario2)
						.maxVoluntarios(50)
						.build();
				eventosService.guardar(eventos8);
				evento = eventosService.buscarPorId(eventos8.getId());
				usuarios = usuariosService.buscarPorId(usuario2.getId());
				if (evento.isPresent()) {
					usuarios.get().addEventos(evento.get());
					usuariosService.editar(usuarios.get());
				}

				Eventos eventos9 = Eventos.builder()
						.fInicio(fechaInicioEvento9)
						.fFin(fechaFinEvento9)
						.titulo("Taller de informatica para mayores")
						.imagen("https://voluntapp.blob.core.windows.net/images/eventos/event9.webp")
						.descripcion(
								"¡Únete a nosotros en un taller de informática para mayores! En este evento especial, te invitamos a compartir tus habilidades tecnológicas con nuestros mayores, ayudándoles a navegar por el mundo digital y a conectarse con sus seres queridos.")
						.ubicacion(ubicacion9)
						.estado("disponible")
						.categorias(listaCategorias)
						.creadoPorUsuarios(usuario2)
						.maxVoluntarios(50)
						.build();
				eventosService.guardar(eventos9);
				evento = eventosService.buscarPorId(eventos9.getId());
				usuarios = usuariosService.buscarPorId(usuario2.getId());
				if (evento.isPresent()) {
					usuarios.get().addEventos(evento.get());
					usuariosService.editar(usuarios.get());
				}

				Eventos eventos10 = Eventos.builder()
						.fInicio(fechaInicioEvento10)
						.fFin(fechaFinEvento10)
						.titulo("Limpieza de pellets en la playa de riazor")
						.imagen("https://voluntapp.blob.core.windows.net/images/eventos/event10.webp")
						.descripcion(
								"¡Únete a nosotros en un evento de limpieza de pellets en la playa de Riazor! En este esfuerzo comunitario, nos uniremos para limpiar y preservar la belleza natural de nuestra costa, ayudando a devolverle su esplendor a este ecosistema vital.")
						.ubicacion(ubicacion10)
						.estado("disponible")
						.categorias(listaCategorias)
						.creadoPorUsuarios(usuario2)
						.maxVoluntarios(50)
						.build();
				eventosService.guardar(eventos10);
				evento = eventosService.buscarPorId(eventos10.getId());
				usuarios = usuariosService.buscarPorId(usuario2.getId());
				if (evento.isPresent()) {
					usuarios.get().addEventos(evento.get());
					usuariosService.editar(usuarios.get());
				}

				Eventos eventos11 = Eventos.builder()
						.fInicio(fechaInicioEvento11)
						.fFin(fechaFinEvento11)
						.titulo("Taller de movilidad para mayores")
						.imagen("https://voluntapp.blob.core.windows.net/images/eventos/event11.webp")
						.descripcion(
								"¡Únete a nosotros en un taller de movilidad para mayores! En este evento especial, te invitamos a compartir tus habilidades de movilidad con nuestros mayores, ayudándoles a mantenerse activos y saludables.")
						.ubicacion(ubicacion11)
						.estado("disponible")
						.categorias(listaCategorias)
						.creadoPorUsuarios(usuario2)
						.maxVoluntarios(50)
						.build();
				eventosService.guardar(eventos11);
				evento = eventosService.buscarPorId(eventos11.getId());
				usuarios = usuariosService.buscarPorId(usuario2.getId());
				if (evento.isPresent()) {
					usuarios.get().addEventos(evento.get());
					usuariosService.editar(usuarios.get());
				}

				Eventos eventos12 = Eventos.builder()
						.fInicio(fechaInicioEvento12)
						.fFin(fechaFinEvento12)
						.titulo("Recogida de alimentos")
						.imagen("https://voluntapp.blob.core.windows.net/images/eventos/event12.webp")
						.descripcion(
								"¡Únete a nosotros en un acto de solidaridad y generosidad mientras nos unimos para ayudar a aquellos que más lo necesitan en nuestra comunidad! Nuestro evento de recogida de alimentos es una oportunidad para marcar la diferencia y apoyar a las personas que enfrentan la inseguridad alimentaria.")
						.ubicacion(ubicacion12)
						.estado("disponible")
						.categorias(listaCategorias)
						.creadoPorUsuarios(usuario2)
						.maxVoluntarios(50)
						.build();
				eventosService.guardar(eventos12);
				evento = eventosService.buscarPorId(eventos12.getId());
				usuarios = usuariosService.buscarPorId(usuario2.getId());
				if (evento.isPresent()) {
					usuarios.get().addEventos(evento.get());
					usuariosService.editar(usuarios.get());
				}

				Eventos eventos13 = Eventos.builder()
						.fInicio(fechaInicioEvento13)
						.fFin(fechaFinEvento13)
						.titulo("Sonrisas para los peques")
						.imagen("https://voluntapp.blob.core.windows.net/images/eventos/event13.webp")
						.descripcion(
								"¡Únete a nosotros en un evento especial para llevar sonrisas a los más pequeños! En este evento, te invitamos a compartir tu tiempo y tu alegría con los niños, ayudándoles a disfrutar de un día lleno de diversión y entretenimiento.")
						.ubicacion(ubicacion13)
						.estado("revision")
						.categorias(listaCategorias)
						.creadoPorUsuarios(usuario2)
						.maxVoluntarios(50)
						.build();
				eventosService.guardar(eventos13);
				evento = eventosService.buscarPorId(eventos13.getId());
				usuarios = usuariosService.buscarPorId(usuario2.getId());
				if (evento.isPresent()) {
					usuarios.get().addEventos(evento.get());
					usuariosService.editar(usuarios.get());
				}

				Eventos eventos14 = Eventos.builder()
						.fInicio(fechaInicioEvento14)
						.fFin(fechaFinEvento14)
						.titulo("Voluntarios para la gran maratón")
						.imagen("https://voluntapp.blob.core.windows.net/images/eventos/event14.webp")
						.descripcion(
								"¡Únete a nosotros en un evento especial para llevar sonrisas a los más pequeños! En este evento, te invitamos a compartir tu tiempo y tu alegría con los niños, ayudándoles a disfrutar de un día lleno de diversión y entretenimiento.")
						.ubicacion(ubicacion14)
						.estado("denegado")
						.categorias(listaCategorias)
						.creadoPorUsuarios(usuario2)
						.maxVoluntarios(50)
						.build();
				eventosService.guardar(eventos14);
				evento = eventosService.buscarPorId(eventos14.getId());
				usuarios = usuariosService.buscarPorId(usuario2.getId());
				if (evento.isPresent()) {
					usuarios.get().addEventos(evento.get());
					usuariosService.editar(usuarios.get());
				}
			}
		};
	}
}