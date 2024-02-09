package iesdoctorbalmis.daw2.voluntapp.servicios;

import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import iesdoctorbalmis.daw2.voluntapp.modelos.Instituciones;
import iesdoctorbalmis.daw2.voluntapp.modelos.Usuarios;
import lombok.RequiredArgsConstructor;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UsuariosDetailsService implements UserDetailsService{

    private final UsuariosService userService;
	private final InstitucionesService institucionesService;

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Usuarios> usuarioExistente = userService.buscarPorUsername(username);
		Optional<Instituciones> institucionExistente = institucionesService.buscarPorUsername(username);

		if (usuarioExistente.isPresent()){
				return usuarioExistente.get();
		} else if (institucionExistente.isPresent()){
				return institucionExistente.get();
		} else {
			throw new UsernameNotFoundException("Usuario con email: " + username + " no encontrado");
		}
	}
	
	public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
		return userService.buscarPorId(id)
					.orElseThrow(() -> new UsernameNotFoundException("Usuario con ID: " + id + " no encontrado") );
	}

	public UserDetails loadInstitucionById(Long id) throws UsernameNotFoundException {
		return institucionesService.buscarPorId(id)
					.orElseThrow(() -> new UsernameNotFoundException("Institucion con ID: " + id + " no encontrado") );
	}
}
