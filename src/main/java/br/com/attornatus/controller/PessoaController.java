package br.com.attornatus.controller;

import java.net.URI;
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

import br.com.attornatus.model.Pessoa;
import br.com.attornatus.model.dto.PessoaDto;
import br.com.attornatus.model.form.PessoaForm;
import br.com.attornatus.repository.EnderecoRepository;
import br.com.attornatus.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

	@Autowired
	private PessoaRepository pessoaRep;
	
	@Autowired
	private EnderecoRepository enderecoRep;
	
	@GetMapping
	public Page<PessoaDto> listar(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 3) Pageable paginacao){
		Page<Pessoa> pessoas = this.pessoaRep.findAll(paginacao);
		return PessoaDto.converter(pessoas);
	}
	
	@PostMapping
	public ResponseEntity<PessoaDto> cadastrar(@RequestBody @Valid PessoaForm form, UriComponentsBuilder uriBuilder){
		Pessoa pessoa = form.converter();
		this.pessoaRep.save(pessoa);
		
		URI uri = uriBuilder.path("/pessoa/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).body(new PessoaDto(pessoa));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> especifico(@PathVariable Long id){
		Optional<Pessoa> pessoa = this.pessoaRep.findById(id);
		if(pessoa.isPresent()) {
			return ResponseEntity.ok(pessoa.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody @Valid PessoaForm form){
		Optional<Pessoa> pessoaOpt = this.pessoaRep.findById(id);
		
		if(pessoaOpt.isPresent()) {
			Pessoa pessoa = form.atualizar(id, this.pessoaRep);
			return ResponseEntity.ok(pessoa);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello World!";
	}
}
