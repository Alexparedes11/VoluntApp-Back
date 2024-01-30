package iesdoctorbalmis.daw2.voluntapp.modelos;

import java.util.HashSet;
import java.util.Set;

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
public class Categorias {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Builder.Default
    @ManyToMany
    @JoinTable(
        name = "categorias_eventos",
        joinColumns = @JoinColumn(name = "eventos_id"),
        inverseJoinColumns = @JoinColumn(name = "categorias_id")
    )
    private Set<Eventos> eventos = new HashSet<>();

    /**
	 * Métodos helper
	 */
	public void addEventos(Eventos p) {
		this.eventos.add(p);
		p.getCategorias().add(this);
	}
	
	public void deleteEventos(Eventos p) {
		this.eventos.remove(p);
		p.getCategorias().remove(this);
	}
}
