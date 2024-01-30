package iesdoctorbalmis.daw2.voluntapp.modelos;

import java.util.HashSet;
import java.util.Set;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data 
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Usuarios {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nombre;

    @NonNull
    private String apellidos;

    @Column(unique = true)
    @NonNull
    private String dni;

    private String direccion;

    @Column(unique = true)
    @NonNull
    private String email;

    private String contraseña;

    private String fotoPerfil;

    @NonNull
    private String rol;

    @Builder.Default
    @ManyToMany
    @JoinTable(
        name = "usuario_eventos",
        joinColumns = @JoinColumn(name = "usuarios_id"),
        inverseJoinColumns = @JoinColumn(name="eventos_id")
    )
    private Set<Eventos> eventos = new HashSet<>();

    /**
	 * Métodos helper
	 */
	public void addEventos(Eventos p) {
		this.eventos.add(p);
		p.getUsuarios().add(this);
	}
	
	public void deleteEventos(Eventos p) {
		this.eventos.remove(p);
		p.getUsuarios().remove(this);
	}
}
