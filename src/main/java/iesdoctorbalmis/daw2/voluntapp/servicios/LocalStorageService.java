package iesdoctorbalmis.daw2.voluntapp.servicios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LocalStorageService {
    @Value("${storage.local.directory}")
    private String storageDirectory;

    public String uploadFile(String folder, String base64Image) throws IOException {
        // Eliminar el prefijo "data:image/jpeg;base64," si está presente
        if (base64Image.contains(",")) {
            base64Image = base64Image.split(",")[1];
        }

        // Decodifica la imagen base64
        byte[] decodedBytes = Base64.getDecoder().decode(base64Image);

        // Genera un nombre único para el archivo
        String filename = UUID.randomUUID().toString() + ".webp"; // Puedes ajustar la extensión según el tipo de imagen

        // Crea el directorio si no existe
        Path folderPath = Paths.get(storageDirectory, folder);
        Files.createDirectories(folderPath);

        // Define la ruta completa del archivo
        Path filePath = folderPath.resolve(filename);

        // Escribe los bytes decodificados en el archivo
        Files.write(filePath, decodedBytes);

        // Retorna la ruta del archivo como una cadena
        return filePath.toString();
    }
}
