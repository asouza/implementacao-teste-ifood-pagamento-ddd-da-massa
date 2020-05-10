package com.deveficiente.testepagamentoifood.pagamento.processadores;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import javax.validation.constraints.NotNull;

import com.deveficiente.testepagamentoifood.pagamento.AutorizadorDeTransacoes;
import com.deveficiente.testepagamentoifood.pagamento.TentativaPagamento;
import com.deveficiente.testepagamentoifood.pagamento.Transacao;

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
	public CompletableFuture<Transacao> paga() {
		autorizadorDeTransacoes.autoriza(80, false);
		System.out.println("[Rede] "+tentativaPagamento);
		return CompletableFuture.completedFuture(new Transacao());
	}

}
