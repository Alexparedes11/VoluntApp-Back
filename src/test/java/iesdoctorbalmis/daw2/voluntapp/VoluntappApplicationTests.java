package iesdoctorbalmis.daw2.voluntapp;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import iesdoctorbalmis.daw2.voluntapp.excepciones.AzureBlobStorageException;
import iesdoctorbalmis.daw2.voluntapp.modelos.Storage;
import iesdoctorbalmis.daw2.voluntapp.servicios.AzureBlobStorageImpl;

@SpringBootTest
class VoluntappApplicationTests {

	@Autowired
	AzureBlobStorageImpl storageImpl;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	public void createContainer() throws AzureBlobStorageException {
		storageImpl.createContainer();
	}

	@AfterEach
	public void deleteContainer() throws AzureBlobStorageException {
		storageImpl.deleteContainer();
	}

	@Test
	public void writeBlob() throws AzureBlobStorageException {
		Storage storage = getStorage("images", "cust1.txt", "Hello World");
		String writeStr = storageImpl.write(storage);
	}

	private Storage getStorage(String path, String fileName, String data) {
		Storage storage = new Storage();
		storage.setPath(path);
		storage.setFileName(fileName);
		if (StringUtils.isNotBlank(data)) {
			storage.setInputStream(new ByteArrayInputStream(data.getBytes()));
		}

		return storage;
	}

}
