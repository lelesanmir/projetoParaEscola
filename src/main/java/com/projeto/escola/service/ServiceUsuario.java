package com.projeto.escola.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.escola.exceptions.CriptoExistsException;
import com.projeto.escola.exceptions.EmailExistsException;
import com.projeto.escola.exceptions.ServiceExc;
import com.projeto.escola.model.Usuario;
import com.projeto.escola.repository.UsuarioRepository;
import com.projeto.escola.util.Util;

@Service
public class ServiceUsuario {

	@Autowired
	UsuarioRepository usuarioRepository; 
	
	public void salvarUsuario(Usuario user) throws Exception{
		try {
			if(usuarioRepository.findByEmail(user.getEmail()) != null)
			{
				
				throw new EmailExistsException("Existe um email cadastrado para :"+
			    user.getEmail());
			}
			user.setSenha(Util.md5(user.getSenha()));
			
		} catch (Exception e) {
			throw new CriptoExistsException("Erro na criptografia da senha!");
		}
		usuarioRepository.save(user);
	}

	public Usuario loginUser(String login, String senha) throws ServiceExc{
		Usuario userLogin= usuarioRepository.buscarLogin(login, senha);
		return userLogin;
}

	
	
	
	
	
}//fim classe
