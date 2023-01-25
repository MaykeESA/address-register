package br.com.attornatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.attornatus.model.Pessoa;
import br.com.attornatus.repository.PessoaRepository;

@SpringBootApplication
public class AvaliacaoAttornatusApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(AvaliacaoAttornatusApplication.class, args);
	}
	
	@Autowired
	private PessoaRepository pessoaRep;
	
	@Override
	public void run(String... args) throws Exception {
		//Pessoas
		Pessoa p1 = new Pessoa("Mayke", "16/03/2003");
		Pessoa p2 = new Pessoa("Bruno", "19/10/2002");
		Pessoa p3 = new Pessoa("Soares", "22/12/2002");
		this.pessoaRep.save(p1);
		this.pessoaRep.save(p2);
		this.pessoaRep.save(p3);
	}
}
