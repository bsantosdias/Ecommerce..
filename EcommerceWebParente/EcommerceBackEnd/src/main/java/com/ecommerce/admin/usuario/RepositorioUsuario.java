package com.ecommerce.admin.usuario;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.comum.entity.Usuario;

public interface RepositorioUsuario extends CrudRepository<Usuario, Integer> {

}
