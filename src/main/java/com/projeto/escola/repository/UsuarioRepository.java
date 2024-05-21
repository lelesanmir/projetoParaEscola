package com.projeto.escola.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.projeto.escola.model.Usuario;
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

	@Query("SELECT i FROM Usuario i WHERE i.email = :email")
	public Usuario findByEmail(String email);
	
	@Query("SELECT i FROM Usuario i WHERE i.email = :email and i.senha= :senha")
	public Usuario buscarLogin(String email, String senha);

}
