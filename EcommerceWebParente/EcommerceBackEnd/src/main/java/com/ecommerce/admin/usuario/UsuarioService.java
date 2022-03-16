package com.ecommerce.admin.usuario;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.comum.entity.Cargo;
import com.ecommerce.comum.entity.Usuario;

@Service
@Transactional
public class UsuarioService {
	public static final int USERS_PER_PAGE = 10;

	@Autowired
	private RepositorioUsuario usuarioRepo;

	@Autowired
	private RepositorioCargo cargoRepo;

	@Autowired
	private PasswordEncoder codificadorSenha;

	public List<Usuario> listarTodos() {
		return (List<Usuario>) usuarioRepo.findAll();
	}

	public Page<Usuario> listByPage(int numPagina, String keyword) {
		Pageable pageable = PageRequest.of(numPagina - 1, USERS_PER_PAGE);

		if (keyword != null) {
			return usuarioRepo.findAll(keyword, pageable);
		}
		return usuarioRepo.findAll(pageable);
	}

	public List<Cargo> listarCargos() {
		return (List<Cargo>) cargoRepo.findAll();
	}

	public void salvar(Usuario usuario) {
		boolean estaAtualizandoUsuario = (usuario.getId() != null);

		if (estaAtualizandoUsuario) {

			Usuario usuarioExistente = usuarioRepo.findById(usuario.getId()).get();

			if (usuario.getSenha().isEmpty()) {
				usuario.setSenha(usuarioExistente.getSenha());
			} else {
				codificarSenha(usuario);
			}

		} else {
			codificarSenha(usuario);
		}
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

	public Usuario get(Integer id) throws UserNotFoundException {
		try {

			return usuarioRepo.findById(id).get();
		} catch (NoSuchElementException ex) {
			throw new UserNotFoundException("Não foi localizado nenhum usuário com o ID " + id);
		}
	}

	public void delete(Integer id) throws UserNotFoundException {
		Long countById = usuarioRepo.countById(id);
		if (countById == null || countById == 0) {
			throw new UserNotFoundException("Não foi localizado nenhum usuário com o ID " + id);
		}
		usuarioRepo.deleteById(id);
	}

	public void atualizarStatusAtivadoUsuario(Integer id, boolean enabled) {
		usuarioRepo.atualizarStatusAtivado(id, enabled);
	}

}
