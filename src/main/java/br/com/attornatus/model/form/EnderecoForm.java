package br.com.attornatus.model.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.attornatus.model.Endereco;

public class EnderecoForm {

	@NotBlank @NotNull @NotEmpty
	private String logradouro;
	@NotBlank @NotNull @NotEmpty
	private String cep;
	@NotBlank @NotNull @NotEmpty
	private String numero;
	@NotBlank @NotNull @NotEmpty
	private String cidade;
	
	public EnderecoForm(String logradouro, String cep, String numero, String cidade) {
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.cidade = cidade;
	}

	public Endereco converter() {
		return new Endereco(this.logradouro, this.cep, this.numero, this.cidade);
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
}
