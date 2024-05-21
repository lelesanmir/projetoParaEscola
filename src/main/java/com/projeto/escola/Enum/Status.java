package com.projeto.escola.Enum;

public enum Status {
	ATIVO("Ativo"),
	CANCELADO("Cancelado"),
	INATIVO("Inativo"),
	TRANCADO("Trancado");
	private String status;
	private Status(String status) {
		this.status=status;
	}

}
