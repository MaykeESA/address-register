package br.com.attornatus.model.dto;


import org.springframework.data.domain.Page;

import br.com.attornatus.model.Pessoa;

public class PessoaDto {

	private Long id;
	private String nome;
	private String dataNascimento;

	public PessoaDto(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.nome = pessoa.getNome();
		this.dataNascimento = pessoa.getDataNascimento();
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

	public Long getId() {
		return id;
	}
}
