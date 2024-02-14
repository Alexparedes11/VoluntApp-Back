package iesdoctorbalmis.daw2.voluntapp.servicios;

import java.util.List;

import iesdoctorbalmis.daw2.voluntapp.modelos.Storage;

public interface IAzureBlobStorageService {
    public String write(Storage storage);
    public String update(Storage storage);
    public byte[] read(Storage storage);
    public List<String> listFiles(Storage storage);
    public void delete(Storage storage);
}
