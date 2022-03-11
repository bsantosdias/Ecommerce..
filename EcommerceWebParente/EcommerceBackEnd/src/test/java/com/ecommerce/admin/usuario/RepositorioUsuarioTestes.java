package com.ecommerce.admin.usuario;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.ecommerce.comum.entity.Cargo;
import com.ecommerce.comum.entity.Usuario;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)

public class RepositorioUsuarioTestes {

	@Autowired
	private RepositorioUsuario repo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void testeCriarUsuario() {
		Cargo cargoAdmin = entityManager.find(Cargo.class, 1);
		Usuario usuarioBianca = new Usuario("biancasd@email.com", "12345bsd", "Bianca", "2020-28-08", "115550123",
				"123");
		usuarioBianca.adicionarCargo(cargoAdmin);
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

}
