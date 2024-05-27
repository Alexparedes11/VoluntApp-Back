package iesdoctorbalmis.daw2.voluntapp.servicios;

import java.security.SecureRandom;
import java.util.Optional;

import org.springframework.stereotype.Service;

import iesdoctorbalmis.daw2.voluntapp.modelos.Enlaces;
import iesdoctorbalmis.daw2.voluntapp.repositorios.EnlacesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EnlacesService {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SHORT_URL_LENGTH = 4;
    private static final SecureRandom RANDOM = new SecureRandom();

    private final EnlacesRepository enlacesRepository;

    public String acortarUrl(String longUrl) {
        Optional<Enlaces> existingEnlace = enlacesRepository.findByOriginal(longUrl);

        if (existingEnlace.isPresent()) {
            return existingEnlace.get().getAcortado();
        } else {
            String shortUrl = generarShortUrl();

            Enlaces enlace = Enlaces.builder()
                    .original(longUrl)
                    .acortado(shortUrl)
                    .build();

            enlacesRepository.save(enlace);

            return shortUrl;
        }
    }

    private String generarShortUrl() {
        StringBuilder sb = new StringBuilder(SHORT_URL_LENGTH);
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            sb.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }

    public Optional<Enlaces> resolverUrl(String shortUrl) {
        return enlacesRepository.findByAcortado(shortUrl);
    }
}
