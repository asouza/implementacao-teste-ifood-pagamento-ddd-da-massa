package com.deveficiente.testepagamentoifood.pagamento;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class GatewayRede implements Pagador{

	private @NotNull TentativaPagamento tentativaPagamento;
	private AutorizadorDeTransacoes autorizadorDeTransacoes;

	public GatewayRede(AutorizadorDeTransacoes autorizadorDeTransacoes, @NotNull TentativaPagamento tentativaPagamento) {
		this.autorizadorDeTransacoes = autorizadorDeTransacoes;
		this.tentativaPagamento = tentativaPagamento;
	}

	@Override
	public BigDecimal custo() {
		return new BigDecimal(2);
	}

	@Override
	public Transacao paga() {
		autorizadorDeTransacoes.autoriza(80, false);
		System.out.println("[Rede] "+tentativaPagamento);
		return new Transacao();
	}

}
