package iesdoctorbalmis.daw2.voluntapp.servicios;

import java.io.ByteArrayInputStream;
import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iesdoctorbalmis.daw2.voluntapp.excepciones.AzureBlobStorageException;
import iesdoctorbalmis.daw2.voluntapp.modelos.Storage;

@Service
public class AzureBlobStorageService {
    @Autowired
    AzureBlobStorageImpl storageImpl;

    private Storage getStorage(String path, String fileName, byte[] bytes) {
        Storage storage = new Storage();
        storage.setPath(path);
        storage.setFileName(fileName);
        storage.setInputStream(new ByteArrayInputStream(bytes));

        return storage;
    }

    public String uploadFile(String path, String fileName, String base64Data) throws AzureBlobStorageException {
        byte[] decodedBytes = Base64.getDecoder().decode(base64Data.split(",")[1]); // Decodificar los datos directamente sin convertirlos a String
        Storage storage = getStorage(path, fileName, decodedBytes);
        return storageImpl.write(storage);
    }
}
