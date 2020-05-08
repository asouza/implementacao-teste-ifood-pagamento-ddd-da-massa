package com.deveficiente.testepagamentoifood.pagamento;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class GatewayRede implements Pagador{

	private @NotNull TentativaPagamento tentativaPagamento;

	public GatewayRede(@NotNull TentativaPagamento tentativaPagamento) {
		this.tentativaPagamento = tentativaPagamento;
	}

	@Override
	public BigDecimal custo() {
		return new BigDecimal(2);
	}

	@Override
	public Transacao paga() {
		System.out.println("[Rede] "+tentativaPagamento);
		return new Transacao();
	}

}
