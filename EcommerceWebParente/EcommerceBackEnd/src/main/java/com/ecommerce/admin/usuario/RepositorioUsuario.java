package com.ecommerce.admin.usuario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ecommerce.comum.entity.Usuario;

public interface RepositorioUsuario extends PagingAndSortingRepository<Usuario, Integer> {

	@Query("SELECT u FROM Usuario u WHERE u.email = :email")
	public Usuario getUsuarioPeloEmail(@Param("email") String email);

	public Long countById(Integer id);

	@Query("SELECT u FROM Usuario u WHERE u.nome LIKE %?1%")
	public Page<Usuario> findAll(String keyword, Pageable pageable);

	@Query("UPDATE Usuario u SET u.ativado = ?2 WHERE u.id = ?1")
	@Modifying
	public void atualizarStatusAtivado(Integer id, boolean enabled);
}
