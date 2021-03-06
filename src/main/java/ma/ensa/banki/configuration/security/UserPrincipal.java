package ma.ensa.banki.configuration.security;

import ma.ensa.banki.entities.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {
	private static final long serialVersionUID = 1L;

	Utilisateur utilisateur;

	@Autowired
	public UserPrincipal(Utilisateur utilisateur) {
		super();
		this.utilisateur = utilisateur;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + this.utilisateur.getRole());
		authorities.add(authority);
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.utilisateur.getPassword();
	}

	@Override
	public String getUsername() {
		return this.utilisateur.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return !utilisateur.getLocked().toUpperCase().equals('N');
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
