package com.deveficiente.testepagamentoifood;

public enum TipoPagamento {
	CARTAO(true), DINHEIRO(false), CHEQUE(false), POS_MACHINE(false);

	public final boolean aceitaOnline;

	TipoPagamento(boolean acceptOnline) {
		this.aceitaOnline = acceptOnline;
	}
	
	

	
}
