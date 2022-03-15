package com.ecommerce.admin.usuario;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CodificadorSenhaTeste {

	@Test
	public void testeCodificarSenha() {
		BCryptPasswordEncoder codificadorSenha = new BCryptPasswordEncoder();
		String senhaCrua = "12345";
		String senhaCodificada = codificadorSenha.encode(senhaCrua);

		System.out.println(senhaCodificada);

		boolean matches = codificadorSenha.matches(senhaCrua, senhaCodificada);

		assertThat(matches).isTrue();
	}

}
