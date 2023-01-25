package br.com.attornatus.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.attornatus.model.Residencia;

@Repository
public interface ResidenciasRepository extends JpaRepository<Residencia, Long>{

	@Query(nativeQuery = true, value = "SELECT * FROM "
						 + "residencias re WHERE re.id_endereco = :idEndereco")
	public List<Residencia> findIdEndereco(Long idEndereco);
	
	@Query(nativeQuery = true, value = "SELECT * FROM "
			 + "residencias re WHERE re.id_pessoa = :idPessoa")
	public List<Residencia> findIdPessoa(Long idPessoa);
}
