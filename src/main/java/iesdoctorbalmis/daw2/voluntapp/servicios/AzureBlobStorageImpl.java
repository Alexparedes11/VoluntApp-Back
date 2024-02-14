package iesdoctorbalmis.daw2.voluntapp.servicios;

import com.azure.core.http.rest.PagedIterable;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobItem;
import com.azure.storage.blob.models.BlobStorageException;

import iesdoctorbalmis.daw2.voluntapp.excepciones.AzureBlobStorageException;
import iesdoctorbalmis.daw2.voluntapp.modelos.Storage;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AzureBlobStorageImpl implements IAzureBlobStorage {
    @Autowired
    BlobServiceClient blobServiceClient;

    @Autowired
    BlobContainerClient blobContainerClient;

    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;

    @Override
    public String write(Storage storage) throws AzureBlobStorageException {
        try {
            String path = getPath(storage);
            BlobClient blob = blobContainerClient.getBlobClient(path);
            blob.upload(storage.getInputStream(), false);
            return path;
        } catch (BlobStorageException e) {
            throw new AzureBlobStorageException(e.getServiceMessage());
        } catch (RuntimeException e) {
            throw new AzureBlobStorageException(e.getMessage());
        } catch (Exception e) {
            throw new AzureBlobStorageException(e.getMessage());
        }
    }

    @Override
    public String update(Storage storage) throws AzureBlobStorageException {
        try {
            String path = getPath(storage);
            BlobClient client = blobContainerClient.getBlobClient(path);
            client.upload(storage.getInputStream(), true);
            return path;
        } catch (BlobStorageException e) {
            throw new AzureBlobStorageException(e.getServiceMessage());
        } catch (RuntimeException e) {
            throw new AzureBlobStorageException(e.getMessage());
        } catch (Exception e) {
            throw new AzureBlobStorageException(e.getMessage());
        }
    }

    @Override
    public byte[] read(Storage storage) throws AzureBlobStorageException {
        try {
            String path = getPath(storage);
            BlobClient client = blobContainerClient.getBlobClient(path);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            client.download(outputStream);
            final byte[] bytes = outputStream.toByteArray();
            return bytes;
        } catch (BlobStorageException e) {
            throw new AzureBlobStorageException(e.getServiceMessage());
        } catch (RuntimeException e) {
            throw new AzureBlobStorageException(e.getMessage());
        } catch (Exception e) {
            throw new AzureBlobStorageException(e.getMessage());
        }
    }

    @Override
    public List<String> listFiles(Storage storage) throws AzureBlobStorageException {
        try {
            PagedIterable<BlobItem> blobList = blobContainerClient.listBlobsByHierarchy(storage.getPath() + "/");
            List<String> blobNamesList = new ArrayList<>();
            for (BlobItem blob : blobList) {
                blobNamesList.add(blob.getName());
            }
            return blobNamesList;
        } catch (BlobStorageException e) {
            throw new AzureBlobStorageException(e.getServiceMessage());
        } catch (RuntimeException e) {
            throw new AzureBlobStorageException(e.getMessage());
        } catch (Exception e) {
            throw new AzureBlobStorageException(e.getMessage());
        }
    }

    @Override
    public void delete(Storage storage) throws AzureBlobStorageException {
        try {
            String path = getPath(storage);
            BlobClient client = blobContainerClient.getBlobClient(path);
            client.delete();
            log.info("Blob is deleted sucessfully.");
        } catch (BlobStorageException e) {
            throw new AzureBlobStorageException(e.getServiceMessage());
        } catch (RuntimeException e) {
            throw new AzureBlobStorageException(e.getMessage());
        } catch (Exception e) {
            throw new AzureBlobStorageException(e.getMessage());
        }

    }

    @Override
    public void createContainer() throws AzureBlobStorageException {
        try {
            blobServiceClient.createBlobContainer(containerName);
            log.info("Container Created");
        } catch (BlobStorageException e) {
            throw new AzureBlobStorageException(e.getServiceMessage());
        } catch (RuntimeException e) {
            throw new AzureBlobStorageException(e.getMessage());
        } catch (Exception e) {
            throw new AzureBlobStorageException(e.getMessage());
        }
    }

    @Override
    public void deleteContainer() throws AzureBlobStorageException {
        try {
            blobServiceClient.deleteBlobContainer(containerName);
            log.info("Container Deleted");
        } catch (BlobStorageException e) {
            throw new AzureBlobStorageException(e.getServiceMessage());
        } catch (RuntimeException e) {
            throw new AzureBlobStorageException(e.getMessage());
        } catch (Exception e) {
            throw new AzureBlobStorageException(e.getMessage());
        }
    }

    private String getPath(Storage storage) {
        if (StringUtils.isNotBlank(storage.getPath())
                && StringUtils.isNotBlank(storage.getFileName())) {
            return storage.getPath() + "/" + storage.getFileName();
        }
        return null;
    }
}