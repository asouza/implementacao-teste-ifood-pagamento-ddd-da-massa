package com.deveficiente.testepagamentoifood.listapagamentos;

import org.springframework.stereotype.Service;

import com.deveficiente.testepagamentoifood.FormaPagamento;
import com.deveficiente.testepagamentoifood.Usuario;

/**
 * Analisa se um usuário é um possível fraudador
 * @author albertoluizsouza
 *
 */
@Service
public class PossivelFraudador implements PossivelRestricaoPagamento{

	@Override
	public boolean aceita(Usuario usuario, FormaPagamento formaPagamento) {
		if(usuario.getNome().equals("alberto")) {
			return !formaPagamento.online();			
		}
		return true;
	}

}
