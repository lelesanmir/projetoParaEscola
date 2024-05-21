package com.projeto.escola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.escola.model.Aluno;
import com.projeto.escola.repository.AlunoRepository;
import com.projeto.escola.service.AlunoService;

@Controller
public class AlunoController {

	@Autowired
	AlunoRepository alunoRepository;

	@Autowired
	AlunoService alunoService;

	@GetMapping("/inserirAluno")
	public ModelAndView insertAluno() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("aluno", new Aluno());
		mv.setViewName("Aluno/inserirAluno");
		return mv;
	}

	@GetMapping("/listarAlunos")
	public ModelAndView listarAlunos() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("alunos", alunoRepository.findAllOrderedById());
		mv.setViewName("Aluno/listarAlunos");
		return mv;
	}

	@PostMapping("/inserirAluno")
	public ModelAndView Inserir(Aluno aluno) {
		ModelAndView mv = new ModelAndView();

		String out = alunoService.cadastrarAluno(aluno);

		if (out != null) {
			mv.addObject("aluno", new Aluno());
			mv.addObject("msg", out);
			mv.setViewName("Aluno/inserirALuno");
		} else {
			mv.setViewName("redirect:/listarAlunos");
		}
		return mv;
	}
	@GetMapping("/alterar/{id}")
	public ModelAndView alterarAluno(@PathVariable("id") Long id) {
		ModelAndView mv = new ModelAndView();
		Aluno aluno = alunoRepository.findById(id).get();
		mv.addObject("aluno", aluno);
		mv.setViewName("Aluno/alterar");
		return mv;
	}
	
	@PostMapping("/alterar/{id}")
	public ModelAndView alterarAluno(Aluno aluno) {
	ModelAndView mv = new ModelAndView();
	String out = alunoService.alterarAluno(aluno, aluno.getId());
	if (out != null) {
		mv.addObject("msg", out);
		mv.addObject("aluno", aluno);
		mv.setViewName("Aluno/alterar");
	} else {
		mv.setViewName("redirect:/listarAlunos");
	}
	return mv;		
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir (@PathVariable("id") Long id) {
		
		alunoRepository.deleteById(id);
		return "redirect:/listarAlunos";
	}
	
	@GetMapping("/listarAlterarAlunos")
	public ModelAndView listarAlterarAlunos() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("alunos", alunoRepository.findAllOrderedById());
		mv.setViewName("Aluno/listarAlterar");
		return mv;
	}
	
	@GetMapping("/listarExcluirAlunos")
	public ModelAndView listarExcluirAlunos() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("alunos", alunoRepository.findAllOrderedById());
		mv.setViewName("Aluno/listarExcluir");
		return mv;
	}
}
