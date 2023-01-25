package br.com.attornatus.service;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.attornatus.model.Endereco;
import br.com.attornatus.model.Pessoa;
import br.com.attornatus.model.PessoaEndereco;
import br.com.attornatus.repository.EnderecoRepository;
import br.com.attornatus.repository.PessoaEnderecoRepository;

@Service
public class PersistService {

	public URI uriPessoaEnderecoExiste(Pessoa pessoa, Endereco endereco, PessoaEnderecoRepository peRep, 
			UriComponentsBuilder uriBuilder) {
		
		PessoaEndereco pessoaEndereco = new PessoaEndereco(pessoa, endereco);
		peRep.save(pessoaEndereco);
		return uriBuilder.path("endereco/{id}").buildAndExpand(endereco.getId()).toUri();
	}
	
	public URI uriPessoaEnderecoNaoExiste(Pessoa pessoa, Endereco enderecoForm, EnderecoRepository endRep, 
			PessoaEnderecoRepository pERep, UriComponentsBuilder uriBuilder) {
		
		PessoaEndereco pessoaEndereco = new PessoaEndereco(pessoa, enderecoForm);
		endRep.save(enderecoForm);
		pERep.save(pessoaEndereco);
		
		return uriBuilder.path("endereco/{id}").buildAndExpand(enderecoForm.getId()).toUri();
	}
}
