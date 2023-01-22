package br.com.attornatus.model.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.attornatus.model.Pessoa;
import br.com.attornatus.repository.PessoaRepository;

public class PessoaForm {

	@NotBlank @NotNull @NotEmpty
	private String nome;
	@NotBlank @NotNull @NotEmpty
	private String dataNascimento;
	
	public PessoaForm(String nome, String dataNascimento) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
	}

	public Pessoa atualizar(Long id, PessoaRepository pessoaRep) {
		Pessoa pessoa = pessoaRep.findById(id).get();
		pessoa.setNome(this.nome);
		pessoa.setDataNascimento(this.dataNascimento);
		pessoaRep.save(pessoa);
		return pessoa;
	}
	
	public Pessoa converter() {
		return new Pessoa(this.nome, this.dataNascimento);
	}
	
	public String getNome() {
		return nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

}
