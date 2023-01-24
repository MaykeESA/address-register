package br.com.attornatus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.attornatus.model.PessoaEndereco;

@Repository
public interface PessoaEnderecoRepository extends JpaRepository<PessoaEndereco, Long>{

	@Query(nativeQuery = true, value = "SELECT * FROM "
						 + "pessoa_endereco pe WHERE pe.id_endereco = :idEndereco")
	public List<PessoaEndereco> findIdEndereco(Long idEndereco);
	
	@Query(nativeQuery = true, value = "SELECT * FROM "
			 + "pessoa_endereco pe WHERE pe.id_pessoa = :idPessoa")
	public List<PessoaEndereco> findIdPessoa(Long idPessoa);
}
