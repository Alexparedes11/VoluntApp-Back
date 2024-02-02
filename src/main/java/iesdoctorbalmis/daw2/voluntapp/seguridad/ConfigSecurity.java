package iesdoctorbalmis.daw2.voluntapp.seguridad;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration @EnableWebSecurity @RequiredArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class ConfigSecurity {

	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	private final UserDetailsService userDetailsService;
	private final PasswordEncoder passwordEncoder;

	@Bean
    protected AuthenticationManager authenticationManager(UserDetailsService userDetailsService){
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder);
        authProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(authProvider);
    }
	

    @Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http 
			.exceptionHandling(ex -> ex.authenticationEntryPoint(jwtAuthenticationEntryPoint))
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth -> auth
					.requestMatchers(HttpMethod.POST, "/login").permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/webjars/**")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/css/**")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/js/**")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/**")).permitAll()
					.requestMatchers(AntPathRequestMatcher.antMatcher("/usuarios/**")).permitAll()
					.anyRequest().authenticated()
					);
			
			// .formLogin(login -> login
			// 		.loginPage("/login")
			// 		.permitAll()
			// 		.defaultSuccessUrl("/web", true)
			// 		.loginProcessingUrl("/login-post")
			// 		)
		
			// .logout(logout -> logout
			// 			.logoutUrl("/logout")
			// 			.logoutSuccessUrl("/login")
			// 		);
		
		http.csrf(csrf -> csrf.disable());
		http.headers(headers -> headers.frameOptions( frame -> frame.disable()));
		return http.build();
			
	}
}