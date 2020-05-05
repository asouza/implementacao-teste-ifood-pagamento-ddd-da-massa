package com.deveficiente.testepagamentoifood.pagamento;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.deveficiente.testepagamentoifood.FormaPagamento;

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
		FormaPagamento formaPagamento = manager.find(FormaPagamento.class,
				form.getFormaPagamentoId());
		
		//se for online, tem que ter cc
		//se nao for online, não tem cc
		
		if (!formaPagamento.online() && form.preencheuCC()) {
			errors.rejectValue("cc", null,
					"Não é online então não tem código");
			return;
		}
		
		System.out.println(formaPagamento.online()+"==="+form.preencheuCC());
		if (formaPagamento.online() && !form.preencheuCC()) {
			errors.rejectValue("cc", null,
					"Todo cartão precisa do código");
			return;
		}
		
		if (formaPagamento.online() && form.preencheuCC() && !form.ccValido()) {
			errors.rejectValue("cc", null,
					"Todo cartão precisa do código com preenchido com 3 digitos");
		}
		
		
		
	}

}
