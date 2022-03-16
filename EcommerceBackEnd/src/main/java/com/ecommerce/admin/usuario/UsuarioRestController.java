package com.ecommerce.admin.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioRestController {

	@Autowired
	private UsuarioService service;

	@PostMapping("/usuarios/checar_email")
	public String checarEmailDuplicado(@Param("email") String email) {
		return service.emailUnico(email) ? "OK" : "Duplicado";

	}
}
