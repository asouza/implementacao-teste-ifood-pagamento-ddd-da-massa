package com.deveficiente.testepagamentoifood.listapagamentos;

import com.deveficiente.testepagamentoifood.FormaPagamento;
import com.deveficiente.testepagamentoifood.Usuario;

public interface PossivelRestricaoPagamento {

	boolean aceita(Usuario usuario,FormaPagamento formaPagamento);
}
