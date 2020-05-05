package com.deveficiente.testepagamentoifood.pagamento;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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

	public void setRestauranteId(Long restauranteId) {
		this.restauranteId = restauranteId;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
