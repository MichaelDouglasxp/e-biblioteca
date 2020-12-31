package com.ifrn.web.repository;

import org.springframework.data.repository.CrudRepository;

import com.ifrn.web.models.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, String> {

	Aluno findByCodigo(long codigo);
}
