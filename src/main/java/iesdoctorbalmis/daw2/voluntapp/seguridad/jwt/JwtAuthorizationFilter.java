package iesdoctorbalmis.daw2.voluntapp.seguridad.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import iesdoctorbalmis.daw2.voluntapp.modelos.Usuarios;
import iesdoctorbalmis.daw2.voluntapp.servicios.UsuariosDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;

@Log
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
	
	private final JwtProvider tokenProvider;
	private final UsuariosDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			
			String token = getJwtFromRequest(request);
			
			if(StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
				
				Long userId = tokenProvider.getUserIdFromJWT(token);
				Usuarios user = (Usuarios) userDetailsService.loadUserById(userId);
				
				UsernamePasswordAuthenticationToken authentication =
						new UsernamePasswordAuthenticationToken(user, user.getRol(), user.getAuthorities());
				
				// Establece detalles adicional de autenticación si los hubiera (no en nuestr caso)
				authentication.setDetails(new WebAuthenticationDetails(request));	
				
				// Guardamos la autenticación en el contexto de seguridad
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
			
		} catch(Exception ex) {
			log.info("No se ha podido establecer la autenticación en el contexto de seguridad");
		}
		
		// Necesario para que la petición continua la cadena de filtros
		filterChain.doFilter(request, response);
	}
	
	private String getJwtFromRequest(HttpServletRequest request) {
		//Obtenemos el atriburo Authorization de la cabecera HTTP
		String bearerToken = request.getHeader(JwtProvider.TOKEN_HEADER);
		
		// Si la autenticacion es de tipo JWT
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(JwtProvider.TOKEN_PREFIX)) {
			//Extraemos del encabezado el lo que necesitamos, quitamos el Bearer
			return bearerToken.substring(JwtProvider.TOKEN_PREFIX.length(), bearerToken.length());
		}
		return null;
	}

}
