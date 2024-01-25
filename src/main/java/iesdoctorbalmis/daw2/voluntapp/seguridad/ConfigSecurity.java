package iesdoctorbalmis.daw2.voluntapp.seguridad;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration @EnableWebSecurity
public class ConfigSecurity {

    @Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http 
			.authorizeHttpRequests(auth -> auth
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