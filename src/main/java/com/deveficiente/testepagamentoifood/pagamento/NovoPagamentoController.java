package com.deveficiente.testepagamentoifood.pagamento;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NovoPagamentoController {

	@PostMapping(value = "/pagamentos")
	public void execute(@Valid NovoPagamentoForm form) {
	}

}
