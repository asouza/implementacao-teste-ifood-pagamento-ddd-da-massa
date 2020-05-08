package com.deveficiente.testepagamentoifood.pagamento;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deveficiente.testepagamentoifood.listapagamentos.UsuarioRepository;

@RestController
public class NovoPagamentoController {

	@Autowired 
	private CCSoEhValidoParaCartaoOnlineValidator ccSoEhValidoParaCartaoOnlineValidator;
	@Autowired
	private PagamentoValidoParaUsuarioRestauranteValidator pagamentoValidoParaUsuarioRestauranteValidator;
	
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private TodosProcessadoresPagamento todosProcessadores;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(ccSoEhValidoParaCartaoOnlineValidator,pagamentoValidoParaUsuarioRestauranteValidator);
	}

	@PostMapping(value = "/pagamentos")
	public void execute(@Valid NovoPagamentoForm form) {
		/*
		 * Round 1
		 * para aquela forma de pagamento eu acho quem vai processar(gateway,acquirer,sub...) a transacao
		 * pode ser uma lista de processadores ordenada pelo custo em cima da transacao
		 * pega o primeiro e processa a transacao
		 * registra o valor da tx que foi levado pelo processador		 
		 * 
		 */
		
		TentativaPagamento tentativaPagamento =  form.toModel(manager,usuarioRepository);
		Transacao resultado = todosProcessadores.paga(tentativaPagamento);
		//grava transacao
		
		if(resultado.deuCerto()) {
			//retorna status para usuario
		} 
		
		//retorna erro para o usuario
		
		
		
		
		
		/*
		 * Round 2
		 * O retorno pode ser refatorado para uma completable future
		 */
		
		/*
		 * Round 3
		 * todo processamento tem timeout. Passou do timeout, vai para o próximo. 
		 */
		
		/*
		 * Round 4, traz o spring reactive?
		 */
	}

}
