package iesdoctorbalmis.daw2.voluntapp.modelos;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JsonManagedReference
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
	public void addEventos(Eventos e) {
		this.eventos.add(e);
		e.getUsuarios().add(this);
	}
	
	public void deleteEventos(Eventos e) {
		this.eventos.remove(e);
		e.getUsuarios().remove(this);
	}
}
