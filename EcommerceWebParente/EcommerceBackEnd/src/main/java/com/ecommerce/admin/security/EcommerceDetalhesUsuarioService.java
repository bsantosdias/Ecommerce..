package com.ecommerce.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ecommerce.admin.usuario.RepositorioUsuario;
import com.ecommerce.comum.entity.Usuario;

public class EcommerceDetalhesUsuarioService implements UserDetailsService {

	@Autowired
	private RepositorioUsuario usuarioRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Usuario usuario = usuarioRepo.getUsuarioPeloEmail(email);
		if (usuario != null) {
			return new EcommerceDetalhesUsuario(usuario);
		}

		throw new UsernameNotFoundException("Não foi encontrado usuario com email: " + email);

	}

}
