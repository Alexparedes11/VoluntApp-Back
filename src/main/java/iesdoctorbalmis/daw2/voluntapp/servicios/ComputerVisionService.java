package iesdoctorbalmis.daw2.voluntapp.servicios;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.ClientResponse;

@Service
public class ComputerVisionService {
    private final WebClient webClient;

    private final String subscriptionKey = "f94f764270aa4a2a906913a85f2b0a2d"; // Reemplaza con tu clave de suscripción

    public ComputerVisionService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://voluntapp.cognitiveservices.azure.com").build();
    }

    public boolean isImageAppropriate(String imageUrl) {
        String endpoint = "/vision/v3.2/analyze";
        String visualFeatures = "Adult";
        String language = "es";
        String modelVersion = "latest";

        ClientResponse response = webClient.post()
                .uri(uriBuilder -> uriBuilder.path(endpoint)
                        .queryParam("visualFeatures", visualFeatures)
                        .queryParam("language", language)
                        .queryParam("model-version", modelVersion)
                        .build())
                .header("Content-Type", "application/json")
                .header("Ocp-Apim-Subscription-Key", subscriptionKey)
                .body(BodyInserters.fromValue("{\"url\":\"" + imageUrl + "\"}"))
                .exchange()
                .block();

        if (response != null && response.statusCode().is2xxSuccessful()) {
            String responseBody = response.bodyToMono(String.class).block();
            if (responseBody != null) {
                // Analizar la respuesta
                boolean isAppropiate = responseBody.contains("\"isAdultContent\":false")
                        && responseBody.contains("\"isRacyContent\":false")
                        && responseBody.contains("\"isGoryContent\":false");
                return isAppropiate;

            }
        }

        // Si la respuesta es nula o no es exitosa, consideramos la imagen como no
        // apropiada por precaución
        return false;
    }
}
