package br.com.attornatus.service;

import java.net.URI;

import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.attornatus.model.Endereco;
import br.com.attornatus.model.Pessoa;
import br.com.attornatus.model.Residencia;
import br.com.attornatus.repository.EnderecoRepository;
import br.com.attornatus.repository.PessoaRepository;
import br.com.attornatus.repository.ResidenciasRepository;

@Service
public class PersistService {

	public URI uriResidenciaExiste(Pessoa pessoa, Endereco endereco, ResidenciasRepository residenciaRep, 
			UriComponentsBuilder uriBuilder) {
		
		Residencia residencia = new Residencia(pessoa, endereco);
		residenciaRep.save(residencia);
		return uriBuilder.path("endereco/{id}").buildAndExpand(endereco.getId()).toUri();
	}
	
	public URI uriResidenciaNaoExiste(Pessoa pessoa, Endereco enderecoForm, EnderecoRepository enderecoRep, 
			ResidenciasRepository residenciaRep, UriComponentsBuilder uriBuilder) {
		
		Residencia residencia = new Residencia(pessoa, enderecoForm);
		enderecoRep.save(enderecoForm);
		residenciaRep.save(residencia);
		
		return uriBuilder.path("endereco/{id}").buildAndExpand(enderecoForm.getId()).toUri();
	}
	
	public URI uriCadastrarPessoa(Pessoa pessoa, PessoaRepository pessoaRep, UriComponentsBuilder uriBuilder) {
		pessoaRep.save(pessoa);
		return uriBuilder.path("/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
	}

	public void persistIdPrincipal(Long idEndereco, Residencia pe, ResidenciasRepository residenciaRep) {
		pe.setIdEnderecoPrincipal(idEndereco);
		residenciaRep.save(pe);
	}
}
