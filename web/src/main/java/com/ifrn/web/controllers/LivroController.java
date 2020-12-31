package com.ifrn.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ifrn.web.models.Livro;
import com.ifrn.web.repository.LivroRepository;

@Controller
public class LivroController {

	//Cria uma instância automaticamente
	@Autowired
	private LivroRepository lr;
	
	//Retorna o formulário para cadastro do livro
	@RequestMapping(value="/cadastrarLivro", method=RequestMethod.GET)
	public String formLivro() {
		return "livro/formLivro";
	}
	
	//Salva o livro no banco de dados
	@RequestMapping(value="/cadastrarLivro", method=RequestMethod.POST)
	public String cadastrarLivro(Livro livro) {
		lr.save(livro);
		return "redirect:/cadastrarLivro";
	}
	
	//Lista todos os livros do banco de dados
	@RequestMapping("/livros")
	public ModelAndView listaLivros() {
		ModelAndView mv = new ModelAndView("livro/listLivros");
		Iterable<Livro> livros = lr.findAll();
		mv.addObject("livros", livros);
		return mv;
	}
	
	//Retorna os detalhes de um livro específico
	@RequestMapping("/{codigo}")
	public ModelAndView detalhesLivro(@PathVariable("codigo") long codigo) {
		Livro livro = lr.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("livro/formAlteraLivro");
		mv.addObject("livro", livro);
		return mv;
	}
	
	//Atauliza os dados do Livro no Banco de dados 
	@RequestMapping(value="/atualizar/{codigo}", method=RequestMethod.POST)
	public String atualizarLivro(@PathVariable("codigo") long codigo, Livro livro) {
	    System.out.println(livro.getTitulo());
		lr.save(livro);
	    return "redirect:/livros";
	}
	
	@RequestMapping("/deletar")
	public String deletarLivro(long codigo) {
		Livro livro = lr.findByCodigo(codigo);
		lr.delete(livro);
		return "redirect:/livros";
	}
	
}
