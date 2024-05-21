package com.projeto.escola.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.escola.model.Turma;
import com.projeto.escola.repository.ProfessorRepository;
import com.projeto.escola.repository.TurmaRepository;

@Service
public class TurmaService {
	@Autowired
	ProfessorRepository professorRepository;
	
	@Autowired
	TurmaRepository turmaRepository;
	

	public String cadastrarTurma(Turma turma) {
		Turma objTurma = turmaRepository.findByCodTurma(turma.getCodTurma());
		if(objTurma != null) 
		{
			return "Já existe uma turma cadastrada com esse código!!";
		}
		else {
			return null;
		}
	}//fim cadastrar

}
