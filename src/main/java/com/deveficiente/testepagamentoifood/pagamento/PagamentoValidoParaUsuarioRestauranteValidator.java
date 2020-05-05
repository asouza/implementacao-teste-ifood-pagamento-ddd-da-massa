package com.deveficiente.testepagamentoifood.pagamento;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.deveficiente.testepagamentoifood.FormaPagamento;
import com.deveficiente.testepagamentoifood.Restaurante;
import com.deveficiente.testepagamentoifood.Usuario;
import com.deveficiente.testepagamentoifood.listapagamentos.PossivelRestricaoPagamento;
import com.deveficiente.testepagamentoifood.listapagamentos.UsuarioRepository;

@Component
public class PagamentoValidoParaUsuarioRestauranteValidator implements Validator{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private EntityManager manager;
	@Autowired
	private List<PossivelRestricaoPagamento> possiveisRestricoes;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoPagamentoForm.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		NovoPagamentoForm form = (NovoPagamentoForm) target;
		
		Usuario logado = usuarioRepository.findByNome(form.getTokenUsuario());
		Restaurante restauranteEscolhido = manager.find(Restaurante.class, form.getRestauranteId());
		FormaPagamento formaPagamento = manager.find(FormaPagamento.class, form.getFormaPagamentoId());
		
		if(!logado.podePagarComForma(restauranteEscolhido,formaPagamento,possiveisRestricoes)) {
			errors.rejectValue(null, null, null, "essa forma de pagamento nao eh permitida");
		}
		
	}

}
