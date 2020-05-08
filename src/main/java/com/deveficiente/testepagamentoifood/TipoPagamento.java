package com.deveficiente.testepagamentoifood;

public enum TipoPagamento {
	visa(true),master(true),hipercard(true),elo(true),dinheiro(false), cheque(false), maquininha(false);

	public final boolean aceitaOnline;

	TipoPagamento(boolean acceptOnline) {
		this.aceitaOnline = acceptOnline;
	}
	
	

	
}
