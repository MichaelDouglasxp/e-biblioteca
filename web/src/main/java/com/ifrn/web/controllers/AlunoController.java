package com.ifrn.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ifrn.web.models.Aluno;
import com.ifrn.web.models.Livro;
import com.ifrn.web.repository.AlunoRepository;

@Controller
public class AlunoController {

	//Cria uma instância automaticamente
	@Autowired
	private AlunoRepository ar;
	
	//Retorna o formulário para cadastrar um aluno
	@RequestMapping(value="/cadastrarAluno", method=RequestMethod.GET)
	public String formAluno() {
		return "aluno/formCadAluno";
	}
	
	//Salva o aluno no banco de dados
	@RequestMapping(value="/cadastrarAluno", method=RequestMethod.POST)
	public String cadastrarAluno(Aluno aluno) {
		ar.save(aluno);
		return "redirect:/cadastrarAluno";
	}
	
	//Lista todos os alunos do banco de dados
	@RequestMapping("/alunos")
	public ModelAndView listaAlunos() {
		ModelAndView mv = new ModelAndView("aluno/listaAlunos");
		Iterable<Aluno> alunos = ar.findAll();
		mv.addObject("alunos", alunos);
		return mv;
	}
	
	//Retorna os detalhes de um aluno específico
	@RequestMapping("/aluno/{codigo}")
	public ModelAndView detalhesAluno(@PathVariable("codigo") long codigo) {
		Aluno aluno = ar.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("aluno/formAlteraAluno");
		mv.addObject("aluno", aluno);
		return mv;
	}
	
	//Atauliza os dados do aluno no Banco de dados 
	@RequestMapping(value="/aluno/atualizar/{codigo}", method=RequestMethod.POST)
	public String atualizarAluno(@PathVariable("codigo") long codigo, Aluno aluno) {
		ar.save(aluno);
	    return "redirect:/alunos";
	}
	
	@RequestMapping("aluno/deletar")
	public String deletarAluno(long codigo) {
		Aluno aluno = ar.findByCodigo(codigo);
		ar.delete(aluno);
		return "redirect:/alunos";
	}
}
