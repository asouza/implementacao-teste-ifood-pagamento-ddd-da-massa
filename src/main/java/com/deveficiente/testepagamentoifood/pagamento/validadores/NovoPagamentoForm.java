package com.deveficiente.testepagamentoifood.pagamento.validadores;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;

import com.deveficiente.testepagamentoifood.FormaPagamento;
import com.deveficiente.testepagamentoifood.Restaurante;
import com.deveficiente.testepagamentoifood.TipoPagamento;
import com.deveficiente.testepagamentoifood.Usuario;
import com.deveficiente.testepagamentoifood.listapagamentos.UsuarioRepository;
import com.deveficiente.testepagamentoifood.pagamento.CountryCode;
import com.deveficiente.testepagamentoifood.pagamento.TentativaPagamento;

public class NovoPagamentoForm {

	@NotNull
	private CountryCode countryCode;
	@NotNull
	private TipoPagamento tipoPagamento;		
	private String cc;
	@NotNull
	@ExistsId(domainAttribute = "id",klass = Restaurante.class)
	private Long restauranteId;
	@Positive
	@NotNull
	private BigDecimal valor;
	@NotBlank
	//gambiarra pq eu nao configurei a autenticacao para receber injetado o usuario nos lugares
	private String tokenUsuario;
	@NotNull //para simular o id de uma compra em si
	private Long compraId;
	
	public void setTokenUsuario(String tokenUsuario) {
		this.tokenUsuario = tokenUsuario;
	}
	
	public String getTokenUsuario() {
		return tokenUsuario;
	}

	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}
	
	public void setCc(String cc) {
		this.cc = cc;
	}
	
	public String getCc() {
		return cc;
	}

	public void setRestauranteId(Long restauranteId) {
		this.restauranteId = restauranteId;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	private boolean preencheuCC() {
		return StringUtils.hasLength(cc);
	}
	
	public void setCompraId(Long compraId) {
		this.compraId = compraId;
	}
	
	private boolean ccValido() {
		Assert.state(preencheuCC(),"este método só deveria ser chamado para cc valido");
		return cc.length() == 3;
	}
	
	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void validaCc(Errors errors) {
		if (!tipoPagamento.aceitaOnline && this.preencheuCC()) {
			errors.rejectValue("cc", null,
					"Não é online então não tem código");
			return;
		}
		
		if (tipoPagamento.aceitaOnline && !this.preencheuCC()) {
			errors.rejectValue("cc", null,
					"Todo cartão precisa do código");
			return;
		}
		
		if (tipoPagamento.aceitaOnline && this.preencheuCC() && !this.ccValido()) {
			errors.rejectValue("cc", null,
					"Todo cartão precisa do código com preenchido com 3 digitos");
		}		
	}

	public Long getRestauranteId() {
		return restauranteId;
	}


	public TentativaPagamento toModel(EntityManager manager,UsuarioRepository usuarioRepository) {
		Restaurante restaurante = manager.find(Restaurante.class, restauranteId);
		Usuario usuario = usuarioRepository.findByNome(tokenUsuario);
		TentativaPagamento tentativaPagamento = new TentativaPagamento(tipoPagamento,restaurante,valor,countryCode,usuario);
		if(StringUtils.hasLength(cc)) {
			tentativaPagamento.setCc(cc);
		}
		
		return tentativaPagamento;
	}
	

}
