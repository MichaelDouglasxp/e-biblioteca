package com.ifrn.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.ifrn.web.models.Livro;

public interface LivroRepository extends CrudRepository <Livro, String>{

	Livro findByCodigo(long codigo);
	
}
