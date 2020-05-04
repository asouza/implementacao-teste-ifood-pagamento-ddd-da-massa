package com.deveficiente.testepagamentoifood.listapagamentos;

import org.springframework.data.repository.Repository;

import com.deveficiente.testepagamentoifood.Usuario;

public interface UsuarioRepository extends Repository<Usuario, Long>{
	
	public Usuario findByNome(String nome);

}
