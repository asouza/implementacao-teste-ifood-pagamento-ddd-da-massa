package com.deveficiente.testepagamentoifood;

public enum TipoPagamento {
	CARTAO(true), DINHEIRO(false), CHEQUE(false), POS_MACHINE(false);

	private boolean acceptOnline;

	TipoPagamento(boolean acceptOnline) {
		this.acceptOnline = acceptOnline;
	}

	
}
