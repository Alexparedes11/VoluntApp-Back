package iesdoctorbalmis.daw2.voluntapp.servicios;

import java.util.List;

import iesdoctorbalmis.daw2.voluntapp.excepciones.AzureBlobStorageException;
import iesdoctorbalmis.daw2.voluntapp.modelos.Storage;

public interface IAzureBlobStorage {

    public String write(Storage storage) throws AzureBlobStorageException;

    public String update(Storage storage) throws AzureBlobStorageException;

    public byte[] read(Storage storage) throws AzureBlobStorageException;

    public List<String> listFiles(Storage storage) throws AzureBlobStorageException;

    public void delete(Storage storage) throws AzureBlobStorageException;

    public void createContainer() throws AzureBlobStorageException;

    public void deleteContainer() throws AzureBlobStorageException;

}