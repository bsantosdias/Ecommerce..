package com.ecommerce.admin.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.comum.entity.Cargo;
import com.ecommerce.comum.entity.Usuario;

@Service

public class UsuarioService {

	@Autowired
	private RepositorioUsuario usuarioRepo;

	@Autowired
	private RepositorioCargo cargoRepo;

	@Autowired
	private PasswordEncoder codificadorSenha;

	public List<Usuario> listarTodos() {
		return (List<Usuario>) usuarioRepo.findAll();
	}

	public List<Cargo> listarCargos() {
		return (List<Cargo>) cargoRepo.findAll();
	}

	public void salvar(Usuario usuario) {
		codificarSenha(usuario);
		usuarioRepo.save(usuario);
	}

	private void codificarSenha(Usuario usuario) {
		String senhaCodificada = codificadorSenha.encode(usuario.getSenha());
		usuario.setSenha(senhaCodificada);
	}

	public boolean emailUnico(String email) {
		Usuario usuarioPeloEmail = usuarioRepo.getUsuarioPeloEmail(email);
		return usuarioPeloEmail == null;
	}
}
