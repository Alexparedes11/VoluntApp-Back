package iesdoctorbalmis.daw2.voluntapp.modelos;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Entity @Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Instituciones implements UserDetails{
    
    @Id @GeneratedValue
    private Long id;

    @Column(name = "email", unique = true)
    private String username;

    @Column(unique = true)
    @NonNull
    private String cif;

    @NonNull
    private String nombreLegal;

    @NonNull
    private String telefono;

    @Lob
    @Column(length = 1000000)
    private String fotoBanner;

    private String personaCargo;

    private String nombre;

    private String password;

    @Lob
    @Column(length = 1000000)
    private String fotoPerfil;

    @Builder.Default
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "institucion_eventos",
        joinColumns = @JoinColumn(name = "instituciones_id"),
        inverseJoinColumns = @JoinColumn(name = "eventos_id")
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'isAccountNonExpired'");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'isAccountNonLocked'");
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'isCredentialsNonExpired'");
        return true;
    }

    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
        return true;
    }
}
