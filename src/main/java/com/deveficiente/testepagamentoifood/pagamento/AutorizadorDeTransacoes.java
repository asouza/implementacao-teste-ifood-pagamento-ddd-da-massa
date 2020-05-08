package com.deveficiente.testepagamentoifood.pagamento;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "http://localhost:8080", name = "pagador-fake")
public interface AutorizadorDeTransacoes {

	@PostMapping("/pagador-fake")
	void autoriza(@RequestParam int tempoExecucaoEmMilisegundos,@RequestParam boolean lancaException);

}
