package iesdoctorbalmis.daw2.voluntapp.controladores;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import iesdoctorbalmis.daw2.voluntapp.modelos.Enlaces;
import iesdoctorbalmis.daw2.voluntapp.servicios.EnlacesService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class EnlacesController {
    private final EnlacesService enlacesService;

    @PostMapping("/enlaces/acortar")
    public ResponseEntity<Map<String, String>> acortarUrl(@RequestBody Map<String, String> request) {
        String longUrl = request.get("url");
        String shortUrl = enlacesService.acortarUrl(longUrl);

        Map<String, String> response = new HashMap<>();
        response.put("shortUrl", "http://localhost:9000/" + shortUrl);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shortUrl}")
    public void resolverUrl(@PathVariable String shortUrl, HttpServletResponse response) throws IOException {
        Optional<Enlaces> enlace = enlacesService.resolverUrl(shortUrl);
        if (enlace.isPresent()) {
            String originalUrl = enlace.get().getOriginal();
            response.setStatus(HttpStatus.SEE_OTHER.value());
            response.setHeader("Location", originalUrl);
            response.sendRedirect(originalUrl);
        } else {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            response.sendRedirect("/404");
        }
    }
}