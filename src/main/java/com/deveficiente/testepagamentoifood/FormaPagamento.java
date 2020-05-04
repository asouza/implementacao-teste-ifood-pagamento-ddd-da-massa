package com.deveficiente.testepagamentoifood;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class FormaPagamento {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String descricao;
	private @NotNull TipoPagamento tipo;
	
	@Deprecated
	public FormaPagamento() {

	}
	
	public FormaPagamento(@NotBlank String descricao, @NotNull TipoPagamento tipo) {
		this.descricao = descricao;
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public Long getId() {
		return id;
	}
	
	public boolean online() {
		return tipo.aceitaOnline;
	}
}
