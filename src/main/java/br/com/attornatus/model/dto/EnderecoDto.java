package br.com.attornatus.model.dto;

import org.springframework.data.domain.Page;

import br.com.attornatus.model.Endereco;

public class EnderecoDto {

	private String logradouro;
	private String cep;
	private String numero;
	private String cidade;
	private String residente;

	public EnderecoDto(Endereco endereco) {
		this.logradouro = endereco.getLogradouro();
		this.cep = endereco.getCep();
		this.numero = endereco.getNumero();
		this.cidade = endereco.getCidade();
		this.residente = endereco.getPessoaId().getNome();
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

	public String getResidente() {
		return residente;
	}

}
