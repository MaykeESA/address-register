package br.com.attornatus.controller;

import java.net.URI;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.attornatus.model.Endereco;
import br.com.attornatus.model.Pessoa;
import br.com.attornatus.model.PessoaEndereco;
import br.com.attornatus.model.dto.EnderecoDto;
import br.com.attornatus.model.form.EnderecoForm;
import br.com.attornatus.repository.EnderecoRepository;
import br.com.attornatus.repository.PessoaEnderecoRepository;
import br.com.attornatus.repository.PessoaRepository;
import br.com.attornatus.service.PersistService;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoRepository enderecoRep;

	@Autowired
	private PessoaRepository pessoaRep;

	@Autowired
	private PessoaEnderecoRepository pessoaEndrecoRep;
	
	@Autowired
	private PersistService persistService;

	@GetMapping
	public Page<EnderecoDto> listar(@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 3) Pageable paginacao) {
		Page<Endereco> endereco = this.enderecoRep.findAll(paginacao);
		return EnderecoDto.converter(endereco);
	}

	@PostMapping
	public ResponseEntity<EnderecoDto> cadastrar(@RequestBody @Valid EnderecoForm form, UriComponentsBuilder uriBuilder) {
		Optional<Pessoa> pessoaOpt = this.pessoaRep.findById(form.getIdPessoa());
		
		if (pessoaOpt.isPresent()) {
			Pessoa pessoa = pessoaOpt.get();
			Endereco enderecoForm = form.converter();

			List<Endereco> enderecosBd = this.enderecoRep.findByCep(form.getCep());
			if(enderecosBd != null) {
				for (Endereco endereco : enderecosBd) {
					if (endereco.getCep().equals(enderecoForm.getCep())) {
						if (endereco.getNumero().equals(enderecoForm.getNumero())) {
							
							URI uri = this.persistService.uriPessoaEnderecoExiste(pessoa, endereco, this.pessoaEndrecoRep, uriBuilder);
							return ResponseEntity.created(uri).body(new EnderecoDto(endereco));
						}
					}
				}
			}
			
			URI uri = this.persistService.uriPessoaEnderecoNaoExiste(pessoa, enderecoForm, this.enderecoRep, this.pessoaEndrecoRep, uriBuilder);
			return ResponseEntity.created(uri).body(new EnderecoDto(enderecoForm));
		}
		return ResponseEntity.notFound().build();
	}

	@PostMapping("{idEndereco}/pessoa/{idPessoa}/principal")
	public ResponseEntity<EnderecoDto> enderecoPrincipal(@PathVariable Long idPessoa, @PathVariable Long idEndereco) {
		List<PessoaEndereco> pessoaEnderecoIdPessoa = this.pessoaEndrecoRep.findIdPessoa(idPessoa);

		if (pessoaEnderecoIdPessoa != null) {
			for (PessoaEndereco pe : pessoaEnderecoIdPessoa) {
				if (pe.getIdEndereco().getId() == idEndereco) {
					pe.setIdPrincipal(idEndereco);
					this.pessoaEndrecoRep.save(pe);

					return ResponseEntity.ok(new EnderecoDto(pe.getIdEndereco()));
				}
			}
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.notFound().build();
	}
}
