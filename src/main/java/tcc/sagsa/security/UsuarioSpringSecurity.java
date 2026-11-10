package tcc.sagsa.security;

import tcc.sagsa.models.*;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

@Getter
public class UsuarioSpringSecurity implements UserDetails {

    private final Long id;
    private final String email;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean ativo;

    public UsuarioSpringSecurity(Usuario usuario) {
        this.id = usuario.getId();
        this.email = usuario.getEmail();
        this.password = usuario.getSenha();
        this.ativo = Boolean.TRUE.equals(usuario.getAtivo());

        String roleName = usuario.getPerfil() != null ? "ROLE_" + usuario.getPerfil().getNome().toUpperCase() : "ROLE_INSTRUTOR";
        this.authorities = Collections.singletonList(new SimpleGrantedAuthority(roleName));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return ativo; }
}