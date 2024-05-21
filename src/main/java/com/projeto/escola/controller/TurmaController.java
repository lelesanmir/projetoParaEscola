package com.projeto.escola.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projeto.escola.Enum.Status;
import com.projeto.escola.model.Aluno;
import com.projeto.escola.model.Professor;
import com.projeto.escola.model.Turma;
import com.projeto.escola.repository.AlunoRepository;
import com.projeto.escola.repository.ProfessorRepository;
import com.projeto.escola.repository.TurmaRepository;
import com.projeto.escola.service.TurmaService;

@Controller
public class TurmaController {

	@Autowired
	ProfessorRepository professorRepository;
	@Autowired
	TurmaRepository turmaRepository;
	@Autowired
	TurmaService turmaService;
	@Autowired
	AlunoRepository alunoRepository;

	@GetMapping("/carregarTurma")
	public ModelAndView carregarTurma() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("professores", professorRepository.findAllOrderedById());
		mv.addObject("turma", new Turma());
		mv.setViewName("Turma/turmaInsert");
		return mv;
	}

	@PostMapping("/inserirTurma")
	public ModelAndView carregarTurma(Turma turma) {
		ModelAndView mv = new ModelAndView();
		String out = turmaService.cadastrarTurma(turma);

		if (out != null) {
			mv.addObject("professores", professorRepository.findAllOrderedById());
			mv.addObject("turma", new Turma());
			mv.addObject("msg", out);
			mv.setViewName("Turma/turmaInsert");
			return mv;
		} else {
			Professor professor = professorRepository.findById(turma.getProfessor().getId()).orElse(null);

			if (professor != null) {
				turma.setProfessor(professor);
				List<Aluno> alunos = alunoRepository.findByTurnoAndCursoAndStatus(turma.getTurno(), turma.getCurso(),
						Status.ATIVO);

				turma.setAlunos(alunos);
				mv.addObject("alunos", alunos);
				mv.addObject("turma", turma);
				mv.setViewName("Turma/inserirAlunosTurma");
			} else {
				mv.addObject("msg", "Professor não encontrado.");
				mv.setViewName("Turma/turmaInsert");
			}
		}
		return mv;
	}

	@PostMapping("/inserirAlunosTurma")
	public ModelAndView InserirAlunosTurma(Turma turma) {
		ModelAndView mv = new ModelAndView();
		turmaRepository.save(turma);
		mv.setViewName("redirect:/home");
		return mv;
	}

	@GetMapping("/listarTurmas")
	public ModelAndView listarTurmas() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("turmas", turmaRepository.findAllOrderedById());
		mv.setViewName("Turma/listarTurmas");
		return mv;
	}

}
