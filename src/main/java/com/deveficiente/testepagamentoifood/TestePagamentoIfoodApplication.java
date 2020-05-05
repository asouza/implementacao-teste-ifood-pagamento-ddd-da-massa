package com.deveficiente.testepagamentoifood;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
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
		FormaPagamento maquininha = new FormaPagamento("Maquininha",TipoPagamento.POS_MACHINE);
		manager.persist(maquininha);
		
		Restaurante bulger = new Restaurante("bulger",visa,master,maquininha);
		manager.persist(bulger);
		
		Restaurante mil1900 = new Restaurante("1900",visa,master,dinheiro);
		manager.persist(mil1900);
		
		Restaurante ordinario = new Restaurante("ordinario",cheque);
		manager.persist(ordinario);
		
		Restaurante lejazz = new Restaurante("lejazz",dinheiro,cheque,visa);
		manager.persist(lejazz);
		
		Usuario albertoVisa = new Usuario("alberto",visa,dinheiro);
		manager.persist(albertoVisa);
		
		Usuario heisenbergMaster = new Usuario("heisenberg",master);
		manager.persist(heisenbergMaster);
		
		Usuario shakaOnline = new Usuario("shaka",master,visa);
		manager.persist(shakaOnline);
		
		Usuario seiyaDinheiro = new Usuario("seiya",dinheiro);
		manager.persist(seiyaDinheiro);
		
		Usuario starkMaquininha = new Usuario("stark",maquininha,dinheiro,cheque);
		manager.persist(starkMaquininha);
		
		Usuario natasha = new Usuario("natasha",visa,master,maquininha,dinheiro,cheque);
		manager.persist(natasha);
		
				
		
		
		
		
		
		
		
		
	}

}
