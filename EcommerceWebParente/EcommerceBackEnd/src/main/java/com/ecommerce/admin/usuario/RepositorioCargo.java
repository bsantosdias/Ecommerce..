package com.ecommerce.admin.usuario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.comum.entity.Cargo;

@Repository
public interface RepositorioCargo extends CrudRepository<Cargo, Integer> {

}
