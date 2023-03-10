package br.com.project.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.project.model.Endereco;
import br.com.project.model.Pessoa;
import br.com.project.model.Residencia;
import br.com.project.model.dto.PessoaDetalhadoDto;
import br.com.project.model.dto.PessoaDto;
import br.com.project.model.form.PessoaForm;
import br.com.project.repository.PessoaRepository;
import br.com.project.repository.ResidenciasRepository;
import br.com.project.service.PersistService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRep;

	@Autowired
	private ResidenciasRepository residenciaRep;

	@Autowired
	private PersistService persistService;

	@GetMapping
	@Cacheable(value = "listar")
	public Page<PessoaDto> listar(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 5) Pageable paginacao) {
		
		Page<Pessoa> pessoas = this.pessoaRep.findAll(paginacao);
		return PessoaDto.converter(pessoas);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PessoaDetalhadoDto> detalhar(@PathVariable Long id) {
		Optional<Pessoa> pessoa = this.pessoaRep.findById(id);

		if (pessoa.isPresent()) {
			List<Residencia> listResidencias = this.residenciaRep.findIdPessoa(id);
			List<Endereco> enderecos = new ArrayList<>();
			Long idEnderecoPrincipal = null;
			for (Residencia residencia : listResidencias) {
				enderecos.add(residencia.getIdEndereco());
				
				if(residencia.getIdEnderecoPrincipal() != null) {
					idEnderecoPrincipal = residencia.getIdEnderecoPrincipal();
				}
			}
			return ResponseEntity.ok(new PessoaDetalhadoDto(pessoa.get(), enderecos, idEnderecoPrincipal));
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@Transactional @CacheEvict(value = "listar", allEntries = true)
	public ResponseEntity<PessoaDto> cadastrar(@RequestBody @Valid PessoaForm form, UriComponentsBuilder uriBuilder) {
		Pessoa pessoa = form.converter();

		URI uri = this.persistService.uriCadastrarPessoa(pessoa, this.pessoaRep, uriBuilder);
		return ResponseEntity.created(uri).body(new PessoaDto(pessoa));
	}

	@PutMapping("/{id}")
	@Transactional @CacheEvict(value = "listar", allEntries = true)
	public ResponseEntity<PessoaDto> atualizar(@PathVariable Long id, @RequestBody @Valid PessoaForm form) {
		Optional<Pessoa> pessoaOpt = this.pessoaRep.findById(id);

		if (pessoaOpt.isPresent()) {
			Pessoa pessoa = form.atualizar(id, this.pessoaRep);
			return ResponseEntity.ok(new PessoaDto(pessoa));
		}

		return ResponseEntity.notFound().build();
	}
}
