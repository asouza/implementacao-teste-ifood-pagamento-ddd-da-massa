package com.deveficiente.testepagamentoifood.listapagamentos;

import com.deveficiente.testepagamentoifood.TipoPagamento;
import com.deveficiente.testepagamentoifood.Usuario;

public interface PossivelRestricaoPagamento {

	boolean aceita(Usuario usuario,TipoPagamento tipoPagamento);
}
