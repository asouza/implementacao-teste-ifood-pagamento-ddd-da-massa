package com.deveficiente.testepagamentoifood.pagamento;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessadoresPagamentoFactory {
	
	@Bean
	public ProcessadorPagamento criaCielo() {
		return new ProcessadorCielo(); 
	}
	
	@Bean
	public ProcessadorPagamento criaRede() {
		return new ProcessadorRede(); 
	}
}
