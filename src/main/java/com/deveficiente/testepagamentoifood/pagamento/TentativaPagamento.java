package com.deveficiente.testepagamentoifood.pagamento;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.deveficiente.testepagamentoifood.FormaPagamento;
import com.deveficiente.testepagamentoifood.Restaurante;
import com.deveficiente.testepagamentoifood.TipoPagamento;
import com.deveficiente.testepagamentoifood.Usuario;

public class TentativaPagamento {

	private @NotNull TipoPagamento tipoPagamento;
	private @NotNull Restaurante restaurante;
	private @Positive @NotNull BigDecimal valor;
	private @NotNull CountryCode countryCode;
	private @NotNull Usuario usuario;
	private String cc;

	public TentativaPagamento(@NotNull TipoPagamento tipoPagamento,
			@NotNull Restaurante restaurante, @Positive @NotNull BigDecimal valor,
			@NotNull CountryCode countryCode, @NotNull Usuario usuario) {
				this.tipoPagamento = tipoPagamento;
				this.restaurante = restaurante;
				this.valor = valor;
				this.countryCode = countryCode;
				this.usuario = usuario;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	@Override
	public String toString() {
		return "TentativaPagamento [tipoPagamento=" + tipoPagamento
				+ ", restaurante=" + restaurante + ", valor=" + valor
				+ ", countryCode=" + countryCode + ", usuario=" + usuario
				+ ", cc=" + cc + "]";
	}
	
	

}
