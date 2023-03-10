# Address Register - π 

* Rest API para registrar endereΓ§os de pessoas.
<br>

<br>
<h2> π Estrutura de pastas: </h2>

```
βββ address-register
|   
|   br.com.project
|   |
β   βββ config.validacao/
|   |   βββ ErroFormularioDto.java
β   β   βββ ErroValidacaoHandler.java
|   |
β   βββ controller/
|   |   βββ EnderecoController.java
β   β   βββ PessoaController.java
|   |
β   βββ model/
β   β   βββ dto/
|   |   |   βββ EnderecoDto.java
|   |   |   βββ PessoaDetalhadoDto.java
|   |   |   βββ PessoaDto.java
|   |   |
β   β   βββ form/
|   |   |   βββ EnderecoForm.java
|   |   |   βββ PessoaForm.java
|   |   |
|   |   βββ Endereco.java
|   |   βββ Pessoa.java
|   |   βββ Residencia.java
|   |
β   βββ repository/
β   β   βββ EnderecoRepository.java
β   β   βββ PessoaRepository.java
β   β   βββ ResidenciasRepository.java
|   |
β   βββ service/
β   β   βββ PersistService.java
|   |
|   βββ AddressRegisterApplication.java
```

<br>
<h2> π Relacionamento: </h2>

<img height="210em" align="center" src="https://cdn.discordapp.com/attachments/817401092752932916/1080838645109501982/rounded-in-photoretrica.png"/>

<br>
<h2> π Endpoints: </h2>

* GET: ```localhost:8080/pessoa```

* GET: ```localhost:8080/pessoa/{id}```

* POST: ```localhost:8080/pessoa```

  ```
  {
    "nome":"Mayke",
    "dataNascimento":"16-03-2003"
  }
  ```
  
* PUT: ```localhost:8080/pessoa/{id}```
  ```
  {
    "nome":"Mayke Erick",
    "dataNascimento":"16-03-2003"
  }
  ```

* GET: ```localhost:8080/endereco```

* POST: ```localhost:8080/endereco```
  ```
  {
    "logradouro":"Rua Ayrton Senna",
    "cep":"010519941",
    "numero":"27",
    "cidade":"SΓ£o Paulo",
    "idPessoa":"1"
  }
  ```
  
* POST: ```localhost:8080/endereco/{idEndereco}/pessoa/{idPessoa}/principal```
