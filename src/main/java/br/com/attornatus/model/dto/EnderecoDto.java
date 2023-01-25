package br.com.attornatus.model.dto;

import org.springframework.data.domain.Page;

import br.com.attornatus.model.Endereco;

public class EnderecoDto {

	private Long id;
	private String logradouro;
	private String cep;
	private String numero;
	private String cidade;

	public EnderecoDto(Endereco endereco) {
		this.id = endereco.getId();
		this.logradouro = endereco.getLogradouro();
		this.cep = endereco.getCep();
		this.numero = endereco.getNumero();
		this.cidade = endereco.getCidade();
	}
	
	public static Page<EnderecoDto> converter(Page<Endereco> endereco) {
		return endereco.map(EnderecoDto::new);
	}

	public String getLogradouro() {
		return logradouro;
	}

	public String getCep() {
		return cep;
	}

	public String getNumero() {
		return numero;
	}

	public String getCidade() {
		return cidade;
	}

	public Long getId() {
		return id;
	}
}
