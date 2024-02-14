package iesdoctorbalmis.daw2.voluntapp.modelos;

import java.io.InputStream;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    private String path;
    private String fileName;
    private InputStream inputStream;
}
