package com.deveficiente.testepagamentoifood.listapagamentos;

import com.deveficiente.testepagamentoifood.FormaPagamento;

public class DetalhePagamentoListaDTO {

	private String descricao;
	private Long id;

	public DetalhePagamentoListaDTO(FormaPagamento formaPagamento) {
		this.descricao = formaPagamento.getDescricao();
		this.id = formaPagamento.getId();
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public Long getId() {
		return id;
	}
	
}
