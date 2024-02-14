package iesdoctorbalmis.daw2.voluntapp.servicios.impl;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.azure.core.http.rest.PagedIterable;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobItem;

import iesdoctorbalmis.daw2.voluntapp.modelos.Storage;
import iesdoctorbalmis.daw2.voluntapp.servicios.IAzureBlobStorageService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AzureBlobStorageImpl implements IAzureBlobStorageService {

    @Autowired
    BlobServiceClient blobServiceClient;

    @Autowired
    BlobContainerClient blobContainerClient;

    @Override
    public String write(Storage storage) {
        String path = getPath(storage);
        BlobClient blob = blobContainerClient.getBlobClient(path);
        blob.upload(storage.getInputStream(), false);
        return path;
    }

    @Override
    public String update(Storage storage) {
        String path = getPath(storage);
        BlobClient blob = blobContainerClient.getBlobClient(path);
        blob.upload(storage.getInputStream(), true);
        return path;
    }

    @Override
    public byte[] read(Storage storage) {
        String path = getPath(storage);
        BlobClient client = blobContainerClient.getBlobClient(path);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        client.download(outputStream);
        final byte[] bytes = outputStream.toByteArray();
        return bytes;
    }

    @Override
    public List<String> listFiles(Storage storage) {
        PagedIterable<BlobItem> blobList = blobContainerClient.listBlobsByHierarchy(storage.getPath() + "/");
        List<String> blobNamesList = new ArrayList<>();
        for (BlobItem blob : blobList) {
            blobNamesList.add(blob.getName());
        }
        return blobNamesList;
    }

    @Override
    public void delete(Storage storage) {
        String path = getPath(storage);
        BlobClient client = blobContainerClient.getBlobClient(path);
        client.delete();
        log.info("Blob eliminado correctamente");
    }

    private String getPath(Storage storage) {
        if (StringUtils.isNotBlank(storage.getPath()) && StringUtils.isNotBlank(storage.getFileName())) {
            return storage.getPath() + "/" + storage.getFileName();
        }
        return null;
    }

}
