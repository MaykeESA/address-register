package br.com.attornatus.model.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.attornatus.model.Endereco;
import br.com.attornatus.model.Pessoa;

public class PessoaDetalhadoDto {
	private String nome;
	private String dataNascimento;
	private Long idEnderecoPrincipal;
	private List<Endereco> enderecos;

	public PessoaDetalhadoDto(Pessoa pessoa, List<Endereco> enderecos, Long idResidencia) {
		this.nome = pessoa.getNome();
		this.dataNascimento = pessoa.getDataNascimento();
		this.idEnderecoPrincipal = idResidencia;
		this.enderecos = enderecos;
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

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public Long getIdEnderecoPrincipal() {
		return idEnderecoPrincipal;
	}
	
}
