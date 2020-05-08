package com.deveficiente.testepagamentoifood.listapagamentos;

import com.deveficiente.testepagamentoifood.TipoPagamento;

public class DetalhePagamentoListaDTO {


	private TipoPagamento tipoPagamento;

	public DetalhePagamentoListaDTO(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	
	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}
	
	
}
