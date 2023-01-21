package br.com.attornatus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.attornatus.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

}
