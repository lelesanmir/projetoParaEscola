package com.projeto.escola.Enum;

public enum Titulacao {
	DOUTORADO("DOUTORADO"),
	MESTRADO("MESTRADO"),
	POS("POS"),
	POSDOCC("POSDOCC");
	
	private String titulo;
	
	private Titulacao(String titulo) {
		this.titulo=titulo;
	}
	

}
