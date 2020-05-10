package com.deveficiente.testepagamentoifood.pagamento;

import java.util.concurrent.CompletableFuture;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deveficiente.testepagamentoifood.listapagamentos.UsuarioRepository;
import com.deveficiente.testepagamentoifood.pagamento.processadores.PagamentoValidoParaUsuarioRestauranteValidator;
import com.deveficiente.testepagamentoifood.pagamento.processadores.TodosProcessadoresPagamento;
import com.deveficiente.testepagamentoifood.pagamento.validadores.CCSoEhValidoParaCartaoOnlineValidator;
import com.deveficiente.testepagamentoifood.pagamento.validadores.NovoPagamentoForm;

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
	public CompletableFuture<?> execute(@Valid NovoPagamentoForm form) {
		/*
		 * Round 1
		 * para aquela forma de pagamento eu acho quem vai processar(gateway,acquirer,sub...) a transacao
		 * pode ser uma lista de processadores ordenada pelo custo em cima da transacao
		 * pega o primeiro e processa a transacao
		 * registra o valor da tx que foi levado pelo processador		 
		 * 
		 */
		
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
		
		TentativaPagamento tentativaPagamento =  form.toModel(manager,usuarioRepository);
		CompletableFuture<Transacao> resultado = todosProcessadores.paga(tentativaPagamento);
		//grava transacao
		
		return resultado.thenApply(transacao -> {
			if(transacao.deuCerto()) {
				return ResponseEntity.ok().build();
			}			
			return ResponseEntity.status(403).build();
		});
		
	}

}
