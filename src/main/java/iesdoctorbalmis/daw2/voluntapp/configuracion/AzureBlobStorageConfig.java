package iesdoctorbalmis.daw2.voluntapp.configuracion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

@Configuration
public class AzureBlobStorageConfig {
    @Value("${}")
    private String nombreContenedor;
    
    @Value("${}")
    private String cadenaConexion;
    
    @Bean
    public BlobServiceClient getBlobServiceClient() {
        BlobServiceClient serviceClient = new BlobServiceClientBuilder().connectionString(cadenaConexion).buildClient();
        return serviceClient;
    }

    @Bean
    public BlobContainerClient getBlobContainerClient() {
        BlobContainerClient containerClient = getBlobServiceClient().getBlobContainerClient(nombreContenedor);
        return containerClient;
    }
}
