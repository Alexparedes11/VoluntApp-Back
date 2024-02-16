package iesdoctorbalmis.daw2.voluntapp.modelos;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
public class Usuarios implements UserDetails{
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String nombre;

    @Column(name = "email", unique = true)
    private String username;

    @NonNull
    private String apellidos;

    @Column(unique = true)
    @NonNull
    private String dni;

    private String direccion;

    private String password;

    private String fotoPerfil;

    @NonNull
    private String telefono;

    @NonNull
    private String rol;

    @Builder.Default
    @EqualsAndHashCode.Exclude @ToString.Exclude
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
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
