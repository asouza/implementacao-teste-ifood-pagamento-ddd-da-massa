package com.deveficiente.testepagamentoifood.listapagamentos;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.deveficiente.testepagamentoifood.Restaurante;
import com.deveficiente.testepagamentoifood.Usuario;

@RestController
public class ListaPagamentosController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@PersistenceContext
	private EntityManager manager;

	@GetMapping(value = "/pagamentos-disponiveis/{restauranteId}")
	public Collection<DetalhePagamentoListaDTO> execute(String tokenUsuario,
			@PathVariable("restauranteId") Long restauranteId) {
		Usuario usuarioLogado = usuarioRepository.findByNome(tokenUsuario);

		Restaurante restaurante = manager.find(Restaurante.class,
				restauranteId);

		return usuarioLogado.pagamentosPossiveisParaRestaurante(restaurante)
				.stream().map(DetalhePagamentoListaDTO::new)
				.collect(Collectors.toList());
	}

}
