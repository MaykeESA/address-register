package br.com.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "residencias")
public class Residencia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Pessoa idPessoa;
	@OneToOne
	@JoinColumn(referencedColumnName = "id")
	private Endereco idEndereco;
	private Long idEnderecoPrincipal;
	
	public Residencia() {
	}
	
	public Residencia(Pessoa pessoa, Endereco endereco) {
		this.idPessoa = pessoa;
		this.idEndereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Pessoa getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Pessoa idPessoa) {
		this.idPessoa = idPessoa;
	}

	public Endereco getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Endereco idEndereco) {
		this.idEndereco = idEndereco;
	}

	public Long getIdEnderecoPrincipal() {
		return idEnderecoPrincipal;
	}

	public void setIdEnderecoPrincipal(Long idEnderecoPrincipal) {
		this.idEnderecoPrincipal = idEnderecoPrincipal;
	}
}
