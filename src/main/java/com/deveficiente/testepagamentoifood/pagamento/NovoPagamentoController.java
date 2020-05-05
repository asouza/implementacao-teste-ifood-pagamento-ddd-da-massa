package com.deveficiente.testepagamentoifood.pagamento;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NovoPagamentoController {

	@Autowired 
	private CCSoEhValidoParaCartaoOnlineValidator ccSoEhValidoParaCartaoOnlineValidator;
	@Autowired
	private PagamentoValidoParaUsuarioRestauranteValidator pagamentoValidoParaUsuarioRestauranteValidator;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(ccSoEhValidoParaCartaoOnlineValidator,pagamentoValidoParaUsuarioRestauranteValidator);
	}

	@PostMapping(value = "/pagamentos")
	public void execute(@Valid NovoPagamentoForm form) {
	}

}
