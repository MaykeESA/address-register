package br.com.attornatus.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.attornatus.model.Endereco;
import br.com.attornatus.model.Pessoa;
import br.com.attornatus.model.PessoaEndereco;
import br.com.attornatus.model.dto.PessoaDetalhadoDto;
import br.com.attornatus.model.dto.PessoaDto;
import br.com.attornatus.model.form.PessoaForm;
import br.com.attornatus.repository.PessoaEnderecoRepository;
import br.com.attornatus.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRep;
	
	@Autowired
	private PessoaEnderecoRepository pessoaEnderecoRep;
	
	@GetMapping
	public Page<PessoaDto> listar(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 5) Pageable paginacao){
		Page<Pessoa> pessoas = this.pessoaRep.findAll(paginacao);
		return PessoaDto.converter(pessoas);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PessoaDetalhadoDto> detalhar(@PathVariable Long id){
		Optional<Pessoa> pessoa = this.pessoaRep.findById(id);
		
		if(pessoa.isPresent()) {
			List<PessoaEndereco> pessoaEndereco = this.pessoaEnderecoRep.findIdPessoa(id);
			List<Endereco> end = new ArrayList<>();
			for(PessoaEndereco pessoaEnd : pessoaEndereco) {
				end.add(pessoaEnd.getIdEndereco());
			}
			return ResponseEntity.ok(new PessoaDetalhadoDto(pessoa.get(), end));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<PessoaDto> cadastrar(@RequestBody @Valid PessoaForm form, UriComponentsBuilder uriBuilder){
		Pessoa pessoa = form.converter();
		this.pessoaRep.save(pessoa);
		
		URI uri = uriBuilder.path("/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).body(new PessoaDto(pessoa));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<PessoaDto> atualizar(@PathVariable Long id, @RequestBody @Valid PessoaForm form){
		Optional<Pessoa> pessoaOpt = this.pessoaRep.findById(id);
		
		if(pessoaOpt.isPresent()) {
			Pessoa pessoa = form.atualizar(id, this.pessoaRep);
			return ResponseEntity.ok(new PessoaDto(pessoa));
		}
		
		return ResponseEntity.notFound().build();
	}
}
