package iesdoctorbalmis.daw2.voluntapp.servicios;

import org.springframework.stereotype.Service;

import iesdoctorbalmis.daw2.voluntapp.modelos.Eventos;
import iesdoctorbalmis.daw2.voluntapp.modelos.Ubicacion;
import iesdoctorbalmis.daw2.voluntapp.repositorios.UbicacionRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UbicacionService {

    private final UbicacionRepository ubicacionRepository;

    // guardar usuarios
    public Ubicacion guardar(Ubicacion ubicacion) {
        return  ubicacionRepository.save(ubicacion);
    }

    // editar usuarios
    public Ubicacion editar(Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }

    // eliminar usuarios
    public void eliminar(Ubicacion ubicacion) {
        ubicacionRepository.delete(ubicacion);
    }
}
