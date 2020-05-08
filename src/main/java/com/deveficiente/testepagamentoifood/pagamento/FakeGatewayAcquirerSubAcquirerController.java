package com.deveficiente.testepagamentoifood.pagamento;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakeGatewayAcquirerSubAcquirerController {

	@PostMapping(value = "/pagador-fake")
	public void execute(int tempoExecucaoEmMilisegundos,boolean lancaException) throws InterruptedException {
		Thread.sleep(tempoExecucaoEmMilisegundos);
		
		if(lancaException) {					
			throw new RuntimeException("resista a esse problema!!!");
		}
	}

}
