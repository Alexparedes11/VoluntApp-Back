package iesdoctorbalmis.daw2.voluntapp.servicios;

import java.io.ByteArrayInputStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import iesdoctorbalmis.daw2.voluntapp.excepciones.AzureBlobStorageException;
import iesdoctorbalmis.daw2.voluntapp.modelos.Storage;

@Service
public class AzureBlobStorageService {
    @Autowired
    AzureBlobStorageImpl storageImpl;

    private Storage getStorage(String path, String fileName, String data) {
        Storage storage = new Storage();
        storage.setPath(path);
        storage.setFileName(fileName);
        if (StringUtils.isNotBlank(data)) {
            storage.setInputStream(new ByteArrayInputStream(data.getBytes()));
        }

        return storage;
    }

    public void writeBlob() throws AzureBlobStorageException {
        Storage storage = getStorage("events", "prueba.webp", "prueba");
        storageImpl.write(storage);
    }

}
