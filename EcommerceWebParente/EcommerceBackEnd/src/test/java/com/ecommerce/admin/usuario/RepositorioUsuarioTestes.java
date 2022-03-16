package com.ecommerce.admin.usuario;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import com.ecommerce.comum.entity.Cargo;
import com.ecommerce.comum.entity.Usuario;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)

public class RepositorioUsuarioTestes {

	@Autowired
	private RepositorioUsuario repo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testeCriarUsuario() {
		Cargo cargoEstoquista = entityManager.find(Cargo.class, 2);
		Usuario usuarioBianca = new Usuario("biancadias@email.com", "12345bsd", "Bianca", "2020-28-08", "115550123",
				"123456");
		usuarioBianca.adicionarCargo(cargoEstoquista);
		Usuario usuarioSalvo = repo.save(usuarioBianca);
		assertThat(usuarioSalvo.getId()).isGreaterThan(0);
	}

	@Test
	public void testeListarUsuarios() {
		Iterable<Usuario> listaUsuarios = repo.findAll();
		listaUsuarios.forEach(usuario -> System.out.println(usuario));
	}

	@Test
	public void testePegarUsuarioPorID() {
		Usuario usuarioBianca = repo.findById(6).get();
		System.out.println(usuarioBianca);
		assertThat(usuarioBianca).isNotNull();
	}

	@Test
	public void testeAtualizarDetalhesUsuario() {
		Usuario usuarioBianca = repo.findById(6).get();
		usuarioBianca.setAtivado(true);
		usuarioBianca.setNome("Angela");
		usuarioBianca.setNascimento("2001-02-31");
		usuarioBianca.setTelefone("00013456");
		usuarioBianca.setSenha("987654");
		repo.save(usuarioBianca);
	}

	@Test
	public void testeAtualizarCargoUsuario() {
		Usuario usuarioBianca = repo.findById(3).get();
		Cargo cargoEstoquista = new Cargo(2);
		Cargo cargoAdmin = new Cargo(1);
		usuarioBianca.getCargos().remove(cargoEstoquista);
		usuarioBianca.adicionarCargo(cargoAdmin);
		repo.save(usuarioBianca);
	}

	@Test
	public void testeDeletarUsuario() {
		Integer usuarioId = 6;
		repo.deleteById(usuarioId);
	}

	@Test
	public void testeGetUsuarioPeloEmail() {
		String email = "bianca@email.com";
		Usuario usuario = repo.getUsuarioPeloEmail(email);
		assertThat(usuario).isNotNull();
	}

	@Test
	public void testeCountById() {
		Integer id = 5;
		Long countById = repo.countById(id);
		assertThat(countById).isNotNull().isGreaterThan(0);
	}

	@Test
	public void testeUsuarioDesativado() {
		Integer id = 3;
		repo.atualizarStatusAtivado(id, false);
	}

	@Test
	public void testeUsuarioAtivo() {
		Integer id = 3;
		repo.atualizarStatusAtivado(id, true);
	}

	@Test
	public void testeListarPrimeiraPagina() {
		int numPagina = 1;
		int tamanhoPagina = 4;
		Pageable pageable = PageRequest.of(numPagina, tamanhoPagina);
		Page<Usuario> page = repo.findAll(pageable);
		List<Usuario> listaUsuarios = page.getContent();

		listaUsuarios.forEach(usuario -> System.out.println(usuario));		
		assertThat(listaUsuarios.size()).isEqualTo(tamanhoPagina);
	}
	
	@Test
	public void testeProcuraUsuarios() {
		String keyword = "BIANCA";
		
		int numPagina = 0;
		int tamanhoPagina = 4;
		Pageable pageable = PageRequest.of(numPagina, tamanhoPagina);
		Page<Usuario> page = repo.findAll(keyword, pageable);
		List<Usuario> listaUsuarios = page.getContent();

		listaUsuarios.forEach(usuario -> System.out.println(usuario));		
		assertThat(listaUsuarios.size()).isGreaterThan(0);
	}
}
