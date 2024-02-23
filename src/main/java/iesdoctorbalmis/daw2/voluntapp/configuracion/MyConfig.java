package iesdoctorbalmis.daw2.voluntapp.configuracion;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig {

        @Bean
        protected ModelMapper modelMapper() {
                return new ModelMapper();
        }

        @Bean
        protected WebMvcConfigurer corsConfigurer() {
                return new WebMvcConfigurer() {

                        @Override
                        public void addCorsMappings(CorsRegistry registry) {

                                registry.addMapping("/usuarios/**")
                                                .allowedOrigins("*")
                                                .allowedMethods("GET", "POST", "PUT", "DELETE")
                                                .maxAge(3600);

                                registry.addMapping("/instituciones/**")
                                                .allowedOrigins("*")
                                                .allowedMethods("GET", "POST", "PUT", "DELETE")
                                                .maxAge(3600);

                                registry.addMapping("/eventos/**")
                                                .allowedOrigins("*")
                                                .allowedMethods("GET", "POST", "PUT", "DELETE")
                                                .maxAge(3600);

                                registry.addMapping("/eventosDTO/**")
                                                .allowedOrigins("*")
                                                .allowedMethods("GET", "POST", "PUT", "DELETE")
                                                .maxAge(3600);

                                registry.addMapping("/login/**")
                                                .allowedOrigins("*")
                                                .allowedMethods("GET", "POST", "PUT", "DELETE")
                                                .maxAge(3600);

                                registry.addMapping("/noticias/**")
                                                .allowedOrigins("*")
                                                .allowedMethods("GET", "POST", "PUT", "DELETE")
                                                .maxAge(3600);

                                registry.addMapping("/contacto/**")
                                                .allowedOrigins("*")
                                                .allowedMethods("POST")
                                                .maxAge(3600);
                        }

                };
        }

}
