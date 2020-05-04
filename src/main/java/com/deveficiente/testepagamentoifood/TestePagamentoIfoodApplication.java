package com.deveficiente.testepagamentoifood;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestePagamentoIfoodApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(TestePagamentoIfoodApplication.class, args);
	}
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		FormaPagamento visa = new FormaPagamento("Visa",TipoPagamento.CARTAO);
		manager.persist(visa);
		FormaPagamento master = new FormaPagamento("Master",TipoPagamento.CARTAO);
		manager.persist(master);
		FormaPagamento dinheiro = new FormaPagamento("Dinheiro",TipoPagamento.DINHEIRO);
		manager.persist(dinheiro);
		FormaPagamento cheque = new FormaPagamento("Cheque",TipoPagamento.CHEQUE);
		manager.persist(cheque);
		FormaPagamento posMachine = new FormaPagamento("Sei lá o que é pos machine",TipoPagamento.POS_MACHINE);
		manager.persist(posMachine);
		
		Restaurante bulger = new Restaurante("bulger");
		bulger.adicionaPagamento(visa);
		bulger.adicionaPagamento(master);
		manager.persist(bulger);
		
		Restaurante mil1900 = new Restaurante("1900");
		mil1900.adicionaPagamento(visa);
		mil1900.adicionaPagamento(master);
		mil1900.adicionaPagamento(dinheiro);		
		manager.persist(mil1900);
		
		Restaurante ordinario = new Restaurante("ordinario");
		ordinario.adicionaPagamento(cheque);		
		manager.persist(ordinario);
		
		Restaurante lejazz = new Restaurante("lejazz");
		lejazz.adicionaPagamento(dinheiro);
		lejazz.adicionaPagamento(cheque);
		lejazz.adicionaPagamento(visa);
		manager.persist(lejazz);
		
		
		
		
		
		
		
		
	}

}
