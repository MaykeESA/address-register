package br.com.attornatus.model.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.attornatus.model.Endereco;
import br.com.attornatus.model.Pessoa;

public class PessoaDto {

	private String nome;
	private String dataNascimento;
	private List<Endereco> endereco;

	public PessoaDto(Pessoa pessoa) {
		this.nome = pessoa.getNome();
		this.dataNascimento = pessoa.getDataNascimento();
		this.endereco = pessoa.getEndereco();
	}
	
	public static Page<PessoaDto> converter(Page<Pessoa> pessoa){
		return pessoa.map(PessoaDto::new);
	}

	public String getNome() {
		return nome;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public List<Endereco> getEndereco() {
		return endereco;
	}
}
