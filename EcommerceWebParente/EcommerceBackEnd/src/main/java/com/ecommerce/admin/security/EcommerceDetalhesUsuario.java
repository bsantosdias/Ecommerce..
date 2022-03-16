package com.ecommerce.admin.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ecommerce.comum.entity.Cargo;
import com.ecommerce.comum.entity.Usuario;

public class EcommerceDetalhesUsuario implements UserDetails {

	private Usuario usuario;

	public EcommerceDetalhesUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<Cargo> cargos = usuario.getCargos();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Cargo cargo : cargos) {
			authorities.add(new SimpleGrantedAuthority(cargo.getNome()));
		}

		return authorities;
	}

	@Override
	public String getPassword() {
		return usuario.getSenha();
	}

	@Override
	public String getUsername() {
		return usuario.getEmail();
	}
	
	public String getNome() {
		return this.usuario.getNome();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {		
		return usuario.isAtivado();
	}

}
