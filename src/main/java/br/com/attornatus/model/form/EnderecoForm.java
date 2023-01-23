package br.com.attornatus.model.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.attornatus.model.Endereco;
import br.com.attornatus.model.Pessoa;
import br.com.attornatus.repository.PessoaRepository;

public class EnderecoForm {

	@NotBlank @NotNull @NotEmpty
	private String logradouro;
	@NotBlank @NotNull @NotEmpty
	private String cep;
	@NotBlank @NotNull @NotEmpty
	private String numero;
	@NotBlank @NotNull @NotEmpty
	private String cidade;
	@NotBlank @NotNull @NotEmpty
	private String idPessoa;
	
	public EnderecoForm() {
	}
	
	public EnderecoForm(String logradouro, String cep, String numero, String cidade, String idPessoa) {
		this.logradouro = logradouro;
		this.cep = cep;
		this.numero = numero;
		this.cidade = cidade;
		this.idPessoa = idPessoa;
	}

	public Endereco converter(PessoaRepository pessoaRep) {
		Pessoa pessoa = pessoaRep.findById(this.getIdPessoa()).get();
		return new Endereco(this.logradouro, this.cep, this.numero, this.cidade, pessoa);
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

	public long getIdPessoa() {
		return Long.parseLong(idPessoa);
	}
}
