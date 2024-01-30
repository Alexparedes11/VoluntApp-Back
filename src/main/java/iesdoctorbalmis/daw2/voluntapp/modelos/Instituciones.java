package iesdoctorbalmis.daw2.voluntapp.modelos;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Instituciones {
    
    @Id @GeneratedValue
    private long id;

    @Column(unique = true)
    @NonNull
    private String cif;

    private String personaCargo;

    private String nombre;

    @Column(nullable = false)
    private String fotoInstitucion;

    @Builder.Default
    @ManyToMany
    @JoinTable(
        name = "institucion_eventos",
        joinColumns = @JoinColumn(name = "eventos_id"),
        inverseJoinColumns = @JoinColumn(name = "instituciones_id")
    )
    private Set<Eventos> eventos = new HashSet<>();

    /**
	 * Métodos helper
	 */
	public void addEventos(Eventos p) {
		this.eventos.add(p);
		p.getInstituciones().add(this);
	}
	
	public void deleteEventos(Eventos p) {
		this.eventos.remove(p);
		p.getInstituciones().remove(this);
	}
}
