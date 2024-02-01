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
                        .allowedOrigins("http://localhost:9000")
                        .allowedOrigins("http://10.100.21.1:4200")
                        .allowedOrigins("http://10.100.22.1:4200")
                        .allowedOrigins("http://10.100.23.1:4200")
                        .allowedOrigins("http://10.100.24.1:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .maxAge(3600);

                registry.addMapping("/instituciones/**")
                        .allowedOrigins("http://localhost:9000")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .maxAge(3600);
            }

        };
    }
    
}
