package com.ecommerce.admin.usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ecommerce.comum.entity.Usuario;

public interface RepositorioUsuario extends CrudRepository<Usuario, Integer> {

	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
	public Usuario getUsuarioPeloEmail(@Param("email") String email);
}
