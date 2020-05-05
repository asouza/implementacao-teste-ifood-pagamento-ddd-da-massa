package com.deveficiente.testepagamentoifood.pagamento;

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

public class NovoPagamentoForm {

	@NotNull
	private CountryCode countryCode;
	@NotNull
	@ExistsId(domainAttribute = "id",klass = FormaPagamento.class)
	private Long formaPagamentoId;		
	private String cc;
	@NotNull
	@ExistsId(domainAttribute = "id",klass = Restaurante.class)
	private Long restauranteId;
	@Positive
	@NotNull
	private BigDecimal valor;

	public void setCountryCode(CountryCode countryCode) {
		this.countryCode = countryCode;
	}

	public void setFormaPagamentoId(Long formaPagamentoId) {
		this.formaPagamentoId = formaPagamentoId;
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

	public Long getFormaPagamentoId() {
		return formaPagamentoId;
	}

	private boolean preencheuCC() {
		return StringUtils.hasLength(cc);
	}
	
	private boolean ccValido() {
		Assert.state(preencheuCC(),"este método só deveria ser chamado para cc valido");
		return cc.length() == 3;
	}

	public void validaCc(EntityManager manager, Errors errors) {
		FormaPagamento formaPagamento = manager.find(FormaPagamento.class,
				this.formaPagamentoId);
		
		if (!formaPagamento.online() && this.preencheuCC()) {
			errors.rejectValue("cc", null,
					"Não é online então não tem código");
			return;
		}
		
		if (formaPagamento.online() && !this.preencheuCC()) {
			errors.rejectValue("cc", null,
					"Todo cartão precisa do código");
			return;
		}
		
		if (formaPagamento.online() && this.preencheuCC() && !this.ccValido()) {
			errors.rejectValue("cc", null,
					"Todo cartão precisa do código com preenchido com 3 digitos");
		}		
	}

}
