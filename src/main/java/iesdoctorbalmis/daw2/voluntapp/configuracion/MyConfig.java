package iesdoctorbalmis.daw2.voluntapp.configuracion;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {
    
    @Bean
    protected ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
