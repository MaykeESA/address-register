package br.com.attornatus.repository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.attornatus.model.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

	public Endereco findByCep(@NotBlank @NotNull @NotEmpty String cep);

}
