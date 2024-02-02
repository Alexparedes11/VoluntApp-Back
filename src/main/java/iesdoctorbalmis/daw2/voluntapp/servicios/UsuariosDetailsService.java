package iesdoctorbalmis.daw2.voluntapp.servicios;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service("userDetailsService")
@RequiredArgsConstructor
public class UsuariosDetailsService implements UserDetailsService{

    private final UsuariosService userService;

    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		return userService.buscarPorUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(username + "no encontrado"));
	}
	
	public UserDetails loadUserById(Long id) throws UsernameNotFoundException {
		return userService.buscarPorId(id)
					.orElseThrow(() -> new UsernameNotFoundException("Usuario con ID: " + id + " no encontrado") );
	}
}
