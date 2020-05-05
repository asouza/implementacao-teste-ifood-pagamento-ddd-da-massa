package com.deveficiente.testepagamentoifood.pagamento;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CCSoEhValidoParaCartaoOnlineValidator implements Validator {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoPagamentoForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NovoPagamentoForm form = (NovoPagamentoForm) target;
		form.validaCc(manager,errors);		
	}

}
