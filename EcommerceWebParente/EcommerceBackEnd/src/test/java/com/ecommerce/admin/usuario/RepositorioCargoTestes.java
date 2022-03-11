package com.ecommerce.admin.usuario;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.ecommerce.comum.entity.Cargo;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)

public class RepositorioCargoTestes {

	@Autowired
	private RepositorioCargo repo;

	@Test
	public void testeCriarPrimeiroCargo() {
		Cargo cargoAdmin = new Cargo("Administrador", "Administrar usuarios cadastrados");
		Cargo cargoSalvo = repo.save(cargoAdmin);
		assertThat(cargoSalvo.getId()).isGreaterThan(0);
	}

	@Test
	public void testeCriarSegundoCargo() {
		Cargo cargoEstoquista = new Cargo("Estoquista", "Administrar produtos");
		Cargo cargoSalvo = repo.save(cargoEstoquista);
		assertThat(cargoSalvo.getId()).isGreaterThan(0);
	}
}
