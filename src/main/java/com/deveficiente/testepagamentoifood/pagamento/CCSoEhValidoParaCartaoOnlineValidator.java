package com.deveficiente.testepagamentoifood.pagamento;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CCSoEhValidoParaCartaoOnlineValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoPagamentoForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NovoPagamentoForm form = (NovoPagamentoForm) target;
		form.validaCc(errors);		
	}

}
