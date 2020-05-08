package com.deveficiente.testepagamentoifood.pagamento;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessadoresPagamentoFactory {
	
	@Bean
	public ProcessadorPagamento criaCielo(AutorizadorDeTransacoes autorizadorDeTransacoes) {
		return new ProcessadorCielo(autorizadorDeTransacoes); 
	}
	
	@Bean
	public ProcessadorPagamento criaRede(AutorizadorDeTransacoes autorizadorDeTransacoes) {
		return new ProcessadorRede(autorizadorDeTransacoes); 
	}
}
